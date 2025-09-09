/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import java.util.Set;

import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.portlet.PortletAsyncContext;
import jakarta.portlet.PortletException;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestWrapper;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.pluto.container.PortletAsyncManager;
import com.liferay.pluto.container.PortletResourceRequestContext;
import com.liferay.pluto.container.bean.processor.PortletArtifactProducer;
import com.liferay.pluto.container.bean.processor.PortletRequestScopedBeanHolder;
import com.liferay.pluto.container.bean.processor.PortletSessionBeanHolder;
import com.liferay.pluto.container.bean.processor.PortletStateScopedBeanHolder;
import com.liferay.pluto.container.bean.processor.RedirectScopedBeanHolder;
import com.liferay.pluto.container.impl.HttpServletPortletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper class for the AsyncContext obtained from the servlet container. Provides a couple of work-arounds for Tomcat
 * async bugs as well as portlet-specific listener support.
 * 
 * @author Scott Nicklous
 * 
 */
public class PortletAsyncContextImpl implements PortletAsyncManager, AsyncContext, PortletAsyncContext {
   private static final Logger                 LOG                = LoggerFactory.getLogger(PortletAsyncContextImpl.class);
   private static final boolean                isTrace            = LOG.isTraceEnabled();

   private AsyncContext                        actx;

   private final HttpServletRequest            hreq;
   private final PortletAsyncContextListener   pal;
   private final PortletResourceRequestContext prctx;

   private ResourceRequest                     resreq;
   private ResourceResponse                    resresp;
   private boolean                             hasOriginalReqResp = true;

   private PortletSessionBeanHolder            sessbh;
   private PortletStateScopedBeanHolder        statebh;
   private PortletRequestScopedBeanHolder      reqbh;
   private RedirectScopedBeanHolder            redbh;
   private BeanManager                         beanmgr;
   private Runnable                            pendingRunner;
   private boolean                             doDeregister       = true;
   private boolean                             complete           = false;
   private boolean                             isContextActive    = true;
   private boolean                             isDispatchedOrComplete       = false;

   public PortletAsyncContextImpl(AsyncContext actx, PortletResourceRequestContext prctx, ResourceRequest resreq, ResourceResponse resresp, boolean origReqResp) {
      this.actx = actx;
      this.prctx = prctx;
      this.resreq = resreq;
      this.resresp = resresp;
      this.hasOriginalReqResp = origReqResp;

      this.hreq = (HttpServletRequest) actx.getRequest();
      this.beanmgr = prctx.getBeanManager();

      // get the original container req & resp to pass to listener for resource releasing

      HttpServletRequest creq = prctx.getContainerRequest();
      HttpServletResponse cresp = prctx.getContainerResponse();

      pal = new PortletAsyncContextListener(this);
      actx.addListener(pal, creq, cresp);
   }

   /**
    * @return the complete
    */
   @Override
   public boolean isComplete() {
      return complete;
   }

   /**
    * @param complete
    *           the complete to set
    */
   @Override
   public void setComplete(boolean complete) {
      this.complete = complete;
   }

   /**
    * Called when a new thread begins running in order to set up contextual support
    */
   @Override
   public void registerContext(boolean isListener) {

      // if the context is already active, then ignore register / deregister calls.
      if (complete || (isListener && PortletRequestScopedBeanHolder.getBeanHolder() != null)) {
         doDeregister = false;
      } else {
         doDeregister = true;
         PortletSessionBeanHolder.register(sessbh);
         PortletStateScopedBeanHolder.register(statebh);
         PortletRequestScopedBeanHolder.register(reqbh);
         RedirectScopedBeanHolder.register(redbh);
         PortletArtifactProducer.setPrecursors(resreq, prctx.getResponse(), prctx.getPortletConfig());
      }

      if (isTrace) {
         StringBuilder txt = new StringBuilder();
         txt.append("Registered context.");
         txt.append(" complete: ").append(complete);
         txt.append(", isListener: ").append(isListener);
         txt.append(", doRegister: ").append(doDeregister);
         LOG.trace(txt.toString());
      }
   }

   /**
    * Called when exiting portlet handling for this thread. The bean holders are deregistered from the thread but are
    * saved rather than destroyed.
    */
   @Override
   public void deregisterContext(boolean isListener) {
      if (!complete && (!isListener || doDeregister)) {
         this.sessbh = PortletSessionBeanHolder.deregister();
         this.redbh = RedirectScopedBeanHolder.deregister();
         this.statebh = PortletStateScopedBeanHolder.deregister();
         this.reqbh = PortletRequestScopedBeanHolder.deregister();
         PortletArtifactProducer.remove();
      }

      if (isTrace) {
         StringBuilder txt = new StringBuilder();
         txt.append("Deregistered context.");
         txt.append(" complete: ").append(complete);
         txt.append(", isListener: ").append(isListener);
         txt.append(", doRegister: ").append(doDeregister);
         LOG.trace(txt.toString());
      }
   }

   /**
    * Launches any runner that was registered for execution. To be called when leaving the portlet servlet.
    */
   @Override
   public void launchRunner() {

      // now if a runner is pending, initialize the contextual runnable and start it

      if (pendingRunner != null) {
         PortletAsyncContextualRunner runner = new PortletAsyncContextualRunner();

         if (isTrace) {
            StringBuilder txt = new StringBuilder();
            txt.append("Executing Portlet Runnable: " + pendingRunner.getClass().getCanonicalName());
            LOG.trace(txt.toString());
         }

         runner.init(this, pendingRunner);
         pendingRunner = null;
         actx.start(runner);
      }

   }

   /**
    * Finds and returns the portlet servlet request wrapper that provides async functionality
    * 
    * @return
    */
   @Override
   public HttpServletRequestWrapper getAsyncRequestWrapper() {
      HttpServletPortletRequestWrapper wrapper = null;

      // find our wrapper in case it was wrapped again

      ServletRequest wreq = prctx.getAsyncServletRequest();
      while ((wreq instanceof ServletRequestWrapper) && !(wreq instanceof HttpServletPortletRequestWrapper)) {
         wreq = ((ServletRequestWrapper) wreq).getRequest();
      }

      if (wreq instanceof HttpServletPortletRequestWrapper) {
         wrapper = (HttpServletPortletRequestWrapper) wreq;
      }

      return wrapper;
   }

   /**
    * Called when exiting portlet handling for this thread. The bean holders are deregistered from the thread and any
    * beans contained are destroyed.
    */
   @Override
   public void removeContext() {
      PortletSessionBeanHolder.removeBeanHolder();
      PortletStateScopedBeanHolder.removeBeanHolder(null);
      PortletRequestScopedBeanHolder.removeBeanHolder();
      PortletArtifactProducer.remove();
   }

   /*
    * Called when asynchronous processing is restarted
    * 
    * @see com.liferay.pluto.driver.services.container.PortletAsyncContext#setWrapped(jakarta.servlet.AsyncContext)
    */
   @Override
   public void setWrapped(AsyncContext actx) {
      this.actx = actx;
      isContextActive = true;
      isDispatchedOrComplete = false;
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#addListener(jakarta.servlet.AsyncListener)
    */
   @Override
   public void addListener(AsyncListener l) {
      if (!isContextActive) {
         throw new IllegalStateException("Listener can only be added when the asynchronous context is active.");
      }
      pal.addListener(l);
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#addListener(jakarta.servlet.AsyncListener, jakarta.servlet.ServletRequest,
    * jakarta.servlet.ServletResponse)
    */
   @Override
   public void addListener(AsyncListener l, ServletRequest req, ServletResponse resp) {
      if (!isContextActive) {
         throw new IllegalStateException("Listener can only be added when the asynchronous context is active.");
      }
      pal.addListener(l, req, resp);
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#complete()
    */
   @Override
   public void complete() {
      isDispatchedOrComplete = true;
      actx.complete();
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#createListener(java.lang.Class)
    */
   @SuppressWarnings("unchecked")
   @Override
   public <T extends AsyncListener> T createListener(Class<T> cls) throws ServletException {
      T obj = null;
      try {
         obj = (T) createInstance(cls);
      } catch (Throwable t) {
         throw new ServletException(t);
      }
      return obj;
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#dispatch()
    */
   @Override
   public void dispatch() {
      isDispatchedOrComplete = true;
      // workaround for Tomcat bug 59213
      actx.dispatch(hreq.getServletContext(), hreq.getServletPath());
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#dispatch(java.lang.String)
    */
   @Override
   public void dispatch(String path) {
      isDispatchedOrComplete = true;

      // enable proper query string parameter handling during async dispatch
      HttpServletPortletRequestWrapper wrapper = (HttpServletPortletRequestWrapper) getAsyncRequestWrapper();
      wrapper.startAsyncDispatch(path);

      // workaround for Tomcat bug 59213
      actx.dispatch(hreq.getServletContext(), path);
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#dispatch(jakarta.servlet.ServletContext, java.lang.String)
    */
   @Override
   public void dispatch(ServletContext sctx, String path) {
      isDispatchedOrComplete = true;
      actx.dispatch(sctx, path);
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#getRequest()
    */
   @Override
   public ServletRequest getRequest() {
      return actx.getRequest();
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#getResponse()
    */
   @Override
   public ServletResponse getResponse() {
      return actx.getResponse();
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#getTimeout()
    */
   @Override
   public long getTimeout() {
      return actx.getTimeout();
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#hasOriginalRequestAndResponse()
    */
   @Override
   public boolean hasOriginalRequestAndResponse() {
      return hasOriginalReqResp;
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#setTimeout(long)
    */
   @Override
   public void setTimeout(long time) {
      actx.setTimeout(time);
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.AsyncContext#start(java.lang.Runnable)
    */
   @Override
   public void start(Runnable run) {
      if (!isContextActive) {
         throw new IllegalStateException("Asynchronous thread can only be started when the asynchronous context is active.");
      }
      if (isDispatchedOrComplete) {
         throw new IllegalStateException("Asynchronous thread cannot be started after an asynchronous dispatch has been performed or asynchronous processing has been completed.");
      }
      pendingRunner = run;
   }

   @Override
   public void addListener(jakarta.portlet.PortletAsyncListener listener) throws IllegalStateException {
      if (!isContextActive) {
         throw new IllegalStateException("Listener can only be added when the asynchronous context is active.");
      }
      pal.addListener(listener);
   }

   @Override
   public void addListener(jakarta.portlet.PortletAsyncListener listener, ResourceRequest request, ResourceResponse response) throws IllegalStateException {
      if (!isContextActive) {
         throw new IllegalStateException("Listener can only be added when the asynchronous context is active.");
      }
      pal.addListener(listener, request, response);
   }

   @SuppressWarnings("unchecked")
   @Override
   public <T extends jakarta.portlet.PortletAsyncListener> T createPortletAsyncListener(Class<T> cls) throws PortletException {
      T obj;
      try {
         obj = (T) createInstance(cls);
      } catch (Throwable t) {
         throw new PortletException(t);
      }
      return obj;
   }

   @Override
   public ResourceRequest getResourceRequest() throws IllegalStateException {
      return resreq;
   }

   @Override
   public ResourceResponse getResourceResponse() throws IllegalStateException {
      return resresp;
   }

   /**
    * Called when the dispatched request body is not longer being executed. After 
    * a startAsync() call, this means that listeners cannot be added, etc.
    */
   @Override
   public void setContextInactive() {
      isContextActive = false;
   }

   private Object createInstance(Class<?> cls) throws IllegalAccessException, InstantiationException {
      if (isTrace) {
         StringBuilder txt = new StringBuilder();
         txt.append("Creating listener.");
         txt.append(" Bean manager: ").append(beanmgr);
         txt.append(", listener class: ").append(cls.getCanonicalName());
         LOG.trace(txt.toString());
      }

      Object lis = null;
      if (beanmgr != null) {
         Set<Bean<?>> beans = beanmgr.getBeans(cls);
         Bean<?> bean = beanmgr.resolve(beans);
         if (bean != null) {
            lis = beanmgr.getReference(bean, bean.getBeanClass(), beanmgr.createCreationalContext(bean));
         } else {
            LOG.warn("Could not get bean reference for: " + cls.getCanonicalName());
         }
      }

      if (lis == null) {
         LOG.trace("Instantiating class directly: " + cls.getCanonicalName());
         lis = cls.newInstance();
      }

      return lis;

   }

}
