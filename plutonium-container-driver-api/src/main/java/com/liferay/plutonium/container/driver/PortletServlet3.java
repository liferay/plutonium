/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.StateAwareResponse;
import jakarta.portlet.UnavailableException;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestWrapper;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletResponseWrapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.plutonium.container.FilterManager;
import com.liferay.plutonium.container.PortletAsyncManager;
import com.liferay.plutonium.container.PortletContainerException;
import com.liferay.plutonium.container.PortletInvokerService;
import com.liferay.plutonium.container.PortletRequestContext;
import com.liferay.plutonium.container.PortletResourceRequestContext;
import com.liferay.plutonium.container.PortletResponseContext;
import com.liferay.plutonium.container.PortletWindow;
import com.liferay.plutonium.container.bean.processor.AnnotatedConfigBean;
import com.liferay.plutonium.container.bean.processor.PortletArtifactProducer;
import com.liferay.plutonium.container.bean.processor.PortletInvoker;
import com.liferay.plutonium.container.bean.processor.PortletRequestScopedBeanHolder;
import com.liferay.plutonium.container.bean.processor.PortletSessionBeanHolder;
import com.liferay.plutonium.container.bean.processor.PortletStateScopedBeanHolder;
import com.liferay.plutonium.container.bean.processor.RedirectScopedBeanHolder;
import com.liferay.plutonium.container.impl.HttpServletPortletRequestWrapper;
import com.liferay.plutonium.container.om.portlet.impl.ConfigurationHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Portlet Invocation Servlet. This servlet receives cross context requests from the the container and services the
 * portlet request for the specified method.
 * 
 * @version 1.1
 * @since 09/22/2004
 */
public class PortletServlet3 extends HttpServlet {
   private static final long  serialVersionUID = -5096339022539360365L;
   
   /** Logger. */
   private static final Logger LOG = LoggerFactory.getLogger(PortletServlet3.class);

   /**
    * Portlet name constant, needed by portlet container initializer
    */
   public static final String PORTLET_NAME     = "portlet-name";

   // Private Member Variables ------------------------------------------------
   
   /**
    * For CDI support
    */
   private AnnotatedConfigBean acb = null;
   
   @Inject private BeanManager injectedBeanmgr;

   private BeanManager beanmgr = null;
   
   /**
    * The webapp configuration
    */
   ConfigurationHolder holder;

   /**
    * The portlet name as defined in the portlet app descriptor.
    */
   private String                 portletName;

   /**
    * The portlet instance wrapped by this servlet.
    */
   private PortletInvoker invoker = null;

   /**
    * The internal portlet context instance.
    */
   private DriverPortletContext   portletContext;

   /**
    * The internal portlet config instance.
    */
   private DriverPortletConfig    portletConfig;
   private boolean isOutOfService = false;
   private PortletContextService  contextService;

   private boolean                started = false;
   Timer                          startTimer;

   // HttpServlet Impl --------------------------------------------------------

   public String getServletInfo() {
      return "Plutonium PortletServlet3 [" + portletName + "]";
   }

   /**
    * Initialize the portlet invocation servlet.
    * 
    * @throws ServletException
    *            if an error occurs while loading portlet.
    */
   public void init(ServletConfig config) throws ServletException {

      // Call the super initialization method.
      super.init(config);
      
      // Retrieve portlet name as defined as an initialization parameter.
      portletName = getInitParameter(PORTLET_NAME);
      
      // look up the bean manager to get a properly initialized one
      // (Fix for running on Liberty)

      BeanManager ibm = null;
      try {
         InitialContext context = new InitialContext();
         
         try {
            // "regular" naming
            ibm = (BeanManager) context.lookup("java:comp/BeanManager");
         } catch (NameNotFoundException e) {
            // try again with Tomcat naming
            try {
               ibm = (BeanManager) context.lookup("java:comp/env/BeanManager");
            } catch (Throwable t) {}
         }
         
      } catch (Throwable e) {}

      if (ibm != null) {
         LOG.debug("BeanManager by JNDI lookup, portlet name: " + portletName);
         beanmgr = ibm;
      } else {
         LOG.debug("BeanManager by injection, portlet name: " + portletName);
         beanmgr = injectedBeanmgr;
      }
      
      if (beanmgr != null) {
         try {
            Set<Bean<?>> beans = beanmgr.getBeans(AnnotatedConfigBean.class);
            Bean<?> bean = beanmgr.resolve(beans);
            acb = (AnnotatedConfigBean) beanmgr.getReference(bean, bean.getBeanClass(), beanmgr.createCreationalContext(bean));
            LOG.debug("ACB instance: " + acb + ", RS config: " + ((acb==null) ? "null" : acb.getSessionScopedConfig()));
            acb.getRedirectScopedConfig().activate(beanmgr);
            acb.getSessionScopedConfig().activate(beanmgr);
            acb.getStateScopedConfig().activate(beanmgr);
         } catch (Throwable t) {
            LOG.debug("Could not retrieve annotated config bean.");
         }
      }
      
      // Get the config bean, instantiate the portlets, and create the invoker
      holder = (ConfigurationHolder) config.getServletContext().getAttribute(ConfigurationHolder.ATTRIB_NAME);
      try {
         if (holder == null || holder.getMethodStore() == null) {
            LOG.error("Could not obtain configuration bean for portlet " + portletName + ". Exiting.");
            return;
         } else {
            holder.instantiatePortlets(beanmgr);
            invoker = new PortletInvoker(holder.getMethodStore(), portletName);
            LOG.debug("Created the portlet invoker for portlet: " + portletName);
         }
      } catch(Exception e) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Exception obtaining configuration bean for portlet ");
         txt.append(portletName).append(". Exiting. Exception: ");

         StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw);
         e.printStackTrace(pw);
         pw.flush();
         txt.append(sw.toString());
         
         LOG.error(txt.toString());

         // take out of service
         
         invoker = null;
         portletConfig = null;
         isOutOfService = true;
         return;
      }

      started = false;

      startTimer = new Timer(true);
      final ServletContext servletContext = getServletContext();
      final ClassLoader paClassLoader = Thread.currentThread().getContextClassLoader();
      startTimer.schedule(new TimerTask() {
         public void run() {
            synchronized (servletContext) {
               if (startTimer != null) {
                  if (attemptRegistration(servletContext, paClassLoader)) {
                     startTimer.cancel();
                     startTimer = null;
                  }
               }
            }
         }
      }, 1, 10000);
   }

   protected boolean attemptRegistration(ServletContext context, ClassLoader paClassLoader) {
      if (PlutoniumServices.getServices() != null) {
         contextService = PlutoniumServices.getServices().getPortletContextService();
         try {
            ServletConfig sConfig = getServletConfig();
            if (sConfig == null) {
               String msg = "Problem obtaining servlet configuration(getServletConfig() returns null).";
               context.log(msg);

               // take out of service
               
               invoker = null;
               portletConfig = null;
               isOutOfService = true;
               return true;
            }

            String applicationName = contextService.register(sConfig);
            started = true;
            portletContext = contextService.getPortletContext(applicationName);
            portletConfig = contextService.getPortletConfig(applicationName, portletName);

         } catch (PortletContainerException ex) {
            context.log(ex.getMessage(), ex);

            // take out of service
            
            invoker = null;
            portletConfig = null;
            isOutOfService = true;
            return true;
         }

         // initialize the portlet wrapped in the servlet.
         try {
            invoker.init(portletConfig);
            return true;
         } catch (Throwable ex) {
 
            StringBuilder txt = new StringBuilder(128);
            txt.append("Portlet threw exception during initialization and will be taken out of service. Portlet name: ");
            txt.append(portletName).append(". Exiting. Exception: ");

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            pw.flush();
            txt.append(sw.toString());
            
            LOG.error(txt.toString());

            // take out of service
            
            invoker = null;
            portletConfig = null;
            isOutOfService = true;
            return true;
         }
      }
      return false;
   }

   public void destroy() {
      synchronized (getServletContext()) {
         if (startTimer != null) {
            startTimer.cancel();
            startTimer = null;
         } else if (started && portletContext != null) {
            started = false;
            contextService.unregister(portletContext);
            if (invoker != null) {
               try {
                  invoker.destroy();
               } catch (Exception e) {
                  // ignore
               }
               invoker = null;
            }
         }
         super.destroy();
      }
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      dispatch(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      dispatch(request, response);
   }

   @Override
   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      dispatch(request, response);
   }

   // Private Methods ---------------------------------------------------------

   /**
    * Dispatch the request to the appropriate portlet methods. This method assumes that the following attributes are set
    * in the servlet request scope:
    * <ul>
    * <li>METHOD_ID: indicating which method to dispatch.</li>
    * <li>PORTLET_REQUEST: the internal portlet request.</li>
    * <li>PORTLET_RESPONSE: the internal portlet response.</li>
    * </ul>
    * 
    * @param request
    *           the servlet request.
    * @param response
    *           the servlet response.
    * @throws ServletException
    * @throws IOException
    */
   private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (LOG.isDebugEnabled()) {
         StringBuilder txt = new StringBuilder();
         txt.append("Processing request.");
         txt.append(" Dispatcher type: ").append(request.getDispatcherType());
         txt.append(", request URI: ").append(request.getRequestURI());
         LOG.debug(txt.toString());
      }

      // Retrieve attributes from the servlet request.
      Integer methodId = (Integer) request.getAttribute(PortletInvokerService.METHOD_ID);
      
      // check for out of service. If it's a render, display string.
      if (isOutOfService) {
         LOG.warn("Portlet is out of service. Portlet name: " + portletName);
         if (methodId == PortletInvokerService.METHOD_RENDER) {
            PrintWriter writer = response.getWriter();
            writer.write("<p>Out of service.</p>");
         }
         return;
      }

      final PortletRequest portletRequest = (PortletRequest) request
            .getAttribute(PortletInvokerService.PORTLET_REQUEST);

      final PortletResponse portletResponse = (PortletResponse) request
            .getAttribute(PortletInvokerService.PORTLET_RESPONSE);

      final PortletRequestContext requestContext = (PortletRequestContext) portletRequest
            .getAttribute(PortletInvokerService.REQUEST_CONTEXT);
      final PortletResponseContext responseContext = (PortletResponseContext) portletRequest
            .getAttribute(PortletInvokerService.RESPONSE_CONTEXT);

      final FilterManager filterManager = 
            (FilterManager) request.getAttribute(PortletInvokerService.FILTER_MANAGER);
      filterManager.setBeanManager(beanmgr);

      if (LOG.isTraceEnabled()) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("\nRequest wrapper stack: ");
         ServletRequest wreq = request;
         ServletRequest tstreq = requestContext.getServletRequest();
         int n = 1;
         while (wreq instanceof ServletRequestWrapper) {
            txt.append("\nLevel ").append(n++).append(": ").append(wreq.getClass().getCanonicalName());
            txt.append(", dispatch type: ").append(wreq.getDispatcherType());
            txt.append(", equal to req context req: ").append(wreq == tstreq);
            wreq = ((ServletRequestWrapper) wreq).getRequest();
         }
         txt.append("\nLevel ").append(n++).append(": ").append(wreq.getClass().getCanonicalName());
         txt.append(", dispatch type: ").append(wreq.getDispatcherType());
         txt.append(", equal to req context req: ").append(wreq == tstreq);

         txt.append("\n\nResponse wrapper stack: ");
         ServletResponse wresp = response;
         ServletResponse tstresp = requestContext.getServletResponse();
         n = 1;
         while (wresp instanceof ServletResponseWrapper) {
            txt.append("\nLevel ").append(n++).append(": ").append(wresp.getClass().getCanonicalName());
            txt.append(", equal to req context resp: ").append(wresp == tstresp);
            wresp = ((ServletResponseWrapper) wresp).getResponse();
         }
         txt.append("\nLevel ").append(n++).append(": ").append(wresp.getClass().getCanonicalName());
         txt.append(", equal to req context resp: ").append(wresp == tstresp);
         LOG.debug(txt.toString());
      }
      
      if (request.getDispatcherType() == DispatcherType.ASYNC) {
         
         // have to reinitialize the request context with the request under our wrapper.
         
         ServletRequest wreq = request;
         while ((wreq instanceof ServletRequestWrapper) &&
               !(wreq instanceof HttpServletPortletRequestWrapper) ) {
            wreq = ((ServletRequestWrapper) wreq).getRequest();
         }
         
         if (wreq instanceof HttpServletPortletRequestWrapper) {
            
            HttpServletRequest hreq = (HttpServletRequest) ((HttpServletPortletRequestWrapper) wreq).getRequest();
            HttpServletResponse hresp = requestContext.getServletResponse();
            
            LOG.debug("Extracted wrapped request. Dispatch type: " + hreq.getDispatcherType());

            requestContext.init(portletConfig, getServletContext(), hreq, hresp, responseContext);
            requestContext.setAsyncServletRequest(request);       // store original request
            responseContext.init(portletConfig, hreq, hresp);
            
         } else {
            LOG.debug("Couldn't find the portlet async wrapper.");
         }

         // enable contextual support for async
         ((PortletResourceRequestContext)requestContext).getPortletAsyncContext().registerContext(false);
         
      } else {
         
         // Not an async dispatch
         
         requestContext.init(portletConfig, getServletContext(), request, response, responseContext);
         requestContext.setExecutingRequestBody(true);
         responseContext.init(portletConfig, request, response);

         // enable contextual support
         beforeInvoke(portletRequest, portletResponse, portletConfig);
      
      }

      PortletWindow window = requestContext.getPortletWindow();

      PortletInvocationEvent event = new PortletInvocationEvent(portletRequest, window, methodId.intValue());
      notify(event, true, null);
      
      try {
         
         // The requested method is RENDER: call Portlet.render(..)
         if (methodId == PortletInvokerService.METHOD_RENDER) {
            RenderRequest renderRequest = (RenderRequest) portletRequest;
            String rh = requestContext.getRenderHeaders();
            if (rh != null) {
               renderRequest.setAttribute(PortletRequest.RENDER_PART, rh);
            }
            RenderResponse renderResponse = (RenderResponse) portletResponse;
            filterManager.processFilter(renderRequest, renderResponse, invoker, portletContext);
         }

         // The requested method is HEADER: call
         // HeaderPortlet.renderHeaders(..)
         else if (methodId == PortletInvokerService.METHOD_HEADER) {
            HeaderRequest headerRequest = (HeaderRequest) portletRequest;
            HeaderResponse headerResponse = (HeaderResponse) portletResponse;
            filterManager.processFilter(headerRequest, headerResponse, invoker, portletContext);
         }

         // The requested method is RESOURCE: call
         // ResourceServingPortlet.serveResource(..)
         else if (methodId == PortletInvokerService.METHOD_RESOURCE) {
            ResourceRequest resourceRequest = (ResourceRequest) portletRequest;

            // if pageState != null, we're dealing with a Partial Action request, so
            // store the page state string as a request attribute
            PortletResourceRequestContext rc = (PortletResourceRequestContext) requestContext;

            rc.setBeanManager(beanmgr);

            ResourceResponse resourceResponse = (ResourceResponse) portletResponse;
            filterManager.processFilter(resourceRequest, resourceResponse, invoker, portletContext);
         }

         // The requested method is ACTION: call Portlet.processAction(..)
         else if (methodId == PortletInvokerService.METHOD_ACTION) {
            ActionRequest actionRequest = (ActionRequest) portletRequest;
            ActionResponse actionResponse = (ActionResponse) portletResponse;
            filterManager.processFilter(actionRequest, actionResponse, invoker, portletContext);
         }

         // The request methode is Event: call Portlet.processEvent(..)
         else if (methodId == PortletInvokerService.METHOD_EVENT) {
            EventRequest eventRequest = (EventRequest) portletRequest;
            EventResponse eventResponse = (EventResponse) portletResponse;
            filterManager.processFilter(eventRequest, eventResponse, invoker, portletContext);
         }
         // The requested method is ADMIN: call handlers.
         else if (methodId == PortletInvokerService.METHOD_ADMIN) {
            PortalAdministrationService pas = PlutoniumServices.getServices().getPortalAdministrationService();

            for (AdministrativeRequestListener l : pas.getAdministrativeRequestListeners()) {
               l.administer(portletRequest, portletResponse);
            }
         }

         // The requested method is LOAD: do nothing.
         else if (methodId == PortletInvokerService.METHOD_LOAD) {
            // Do nothing.
         }

         notify(event, false, null);

      } catch (UnavailableException ex) {
         //
         // if (e.isPermanent()) { throw new
         // UnavailableException(e.getMessage()); } else { throw new
         // UnavailableException(e.getMessage(), e.getUnavailableSeconds());
         // }
         //

         // Portlet.destroy() isn't called by Tomcat, so we have to fix it.
         try {
            invoker.destroy();
         } catch (Throwable th) {
            // Don't care for Exception
            this.getServletContext().log("Error during portlet destroy.", th);
         }
         
         // take portlet out of service
         isOutOfService = true;

         throw new jakarta.servlet.UnavailableException(ex.getMessage());

      } catch (PortletException ex) {
         notify(event, false, ex);
         throw new ServletException(ex);

      } finally {
         
         requestContext.setExecutingRequestBody(false);
         
         // If an async request is running or has been dispatched, resources
         // will be released by the PortletAsyncListener. Otherwise release here.
         
         if (!request.isAsyncStarted() && (request.getDispatcherType() != DispatcherType.ASYNC)) {

            LOG.debug("Async not being processed, releasing resources. executing req body: " + requestContext.isExecutingRequestBody());

            request.removeAttribute(PortletInvokerService.METHOD_ID);
            request.removeAttribute(PortletInvokerService.PORTLET_REQUEST);
            request.removeAttribute(PortletInvokerService.PORTLET_RESPONSE);
            request.removeAttribute(PortletInvokerService.FILTER_MANAGER);

            afterInvoke(portletRequest, portletResponse);

         } else {
            LOG.debug("Async started, not releasing resources. executing req body: " + requestContext.isExecutingRequestBody());

            if (requestContext instanceof PortletResourceRequestContext) {
               PortletResourceRequestContext resctx = (PortletResourceRequestContext)requestContext;
               PortletAsyncManager pac = resctx.getPortletAsyncContext();
               if (pac != null) {
                  pac.deregisterContext(false);
                  pac.launchRunner();
               } else {
                  LOG.warn("Couldn't get portlet async context.");
               }
            } else {
               LOG.warn("Wrong kind of request context: " + requestContext.getClass().getCanonicalName());
            }

         }
      }
   }

   protected void notify(PortletInvocationEvent event, boolean pre, Throwable e) {
      PortalAdministrationService pas = PlutoniumServices.getServices().getPortalAdministrationService();

      for (PortletInvocationListener listener : pas.getPortletInvocationListeners()) {
         if (pre) {
            listener.onBegin(event);
         } else if (e == null) {
            listener.onEnd(event);
         } else {
            listener.onError(event, e);
         }
      }
   }


   /**
    * To be called before bean method invocation begins
    */
   private void beforeInvoke(PortletRequest req, PortletResponse resp, PortletConfig config) {

      if (acb != null) {

         // Set the portlet session bean holder for the thread & session
         PortletRequestScopedBeanHolder.setBeanHolder();

         // Set the portlet session bean holder for the thread & session
         PortletSessionBeanHolder.setBeanHolder(req, acb.getSessionScopedConfig());

         // Set the redirect scoped bean holder
         RedirectScopedBeanHolder.setBeanHolder(req);

         // Set the render state scoped bean holder
         PortletStateScopedBeanHolder.setBeanHolder(req, acb.getStateScopedConfig());

         // Set up the artifact producer with request, response, and portlet config
         PortletArtifactProducer.setPrecursors(req, resp, config);
         
         if (LOG.isTraceEnabled()) {
            LOG.trace("CDI context is now set up.");
         }

      } else {
         if (LOG.isTraceEnabled()) {
            LOG.trace("CDI contextual support not available");
         }
      }      
      
   }

   /**
    * must be called after all method invocations have taken place, even if an
    * exception occurs.
    */
   private void afterInvoke(PortletRequest portletRequest, PortletResponse portletResponse) {

      if (acb != null) {

         // Remove the portlet session bean holder for the thread
         PortletRequestScopedBeanHolder.removeBeanHolder();

         // Remove the portlet session bean holder for the thread
         PortletSessionBeanHolder.removeBeanHolder();

         // Remove the redirect bean holder for the thread
         RedirectScopedBeanHolder.removeBeanHolder(
             (portletRequest instanceof RenderRequest) && !(portletRequest instanceof HeaderRequest));

         // Remove the render state bean holder. pass response if we're
         // dealing with a StateAwareResponse. The response is used for state
         // storage.

         StateAwareResponse sar = null;
         if (portletResponse instanceof StateAwareResponse) {
            sar = (StateAwareResponse) portletResponse;
         }
         PortletStateScopedBeanHolder.removeBeanHolder(sar);

         // remove the portlet artifact producer
         PortletArtifactProducer.remove();
         
         if (LOG.isTraceEnabled()) {
            LOG.trace("CDI context is now deactivated.");
         }
      
      }
   }
}
