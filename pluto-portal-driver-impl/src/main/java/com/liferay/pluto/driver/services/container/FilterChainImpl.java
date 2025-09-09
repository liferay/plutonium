/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventPortlet;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.HeaderPortlet;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;
import jakarta.portlet.filter.ActionFilter;
import jakarta.portlet.filter.EventFilter;
import jakarta.portlet.filter.FilterChain;
import jakarta.portlet.filter.HeaderFilter;
import jakarta.portlet.filter.HeaderFilterChain;
import jakarta.portlet.filter.RenderFilter;
import jakarta.portlet.filter.ResourceFilter;

import com.liferay.pluto.container.om.portlet.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A <code>FilterChain</code> is an object provided by the portlet container to
 * the developer giving a view into the invocation chain of a filtered request
 * for a portlet. Filters use the <code>FilterChain</code> to invoke the next
 * filter in the chain, or if the calling filter is the last filter in the
 * chain, to invoke the portlet at the end of the chain.
 * 
 * @since 29/05/2007
 * @version 2.0
 */
public class FilterChainImpl implements FilterChain, HeaderFilterChain {
   private static final Logger LOG = LoggerFactory.getLogger(FilterChainImpl.class);
   private static final boolean isDebug = LOG.isDebugEnabled();
   

   private List<Filter>   filterList      = new ArrayList<Filter>();
   @SuppressWarnings("unused")
   private String         lifeCycle;
   Portlet                portlet;
   EventPortlet           eventPortlet;
   ResourceServingPortlet resourceServingPortlet;
   HeaderPortlet          headerPortlet;
   ClassLoader            loader;
   PortletContext         portletContext;
   int                    filterListIndex = 0;

   private BeanManager    beanmgr;

   public FilterChainImpl(String lifeCycle) {
      this.lifeCycle = lifeCycle;
   }

   /** 
    * enables contextual support
    */
   public void setBeanManager(BeanManager bm) {
      beanmgr = bm;
   }

   private Object loadFilter(Filter filter) throws InstantiationException, IllegalAccessException,
         ClassNotFoundException {
      Object obj = null;
      Class<?> fcls = loader.loadClass(filter.getFilterClass());
      if (fcls != null) {
         if (beanmgr == null) {
            // CDI is not active
            obj = fcls.newInstance();
         } else {
            // CDI active ... instantiate as bean to enable contextual features
            Set<Bean<?>> beans = beanmgr.getBeans(fcls);
            Bean<?> bean = beanmgr.resolve(beans);
            if (bean != null) {
               obj = beanmgr.getReference(bean, bean.getBeanClass(), beanmgr.createCreationalContext(bean));
            } else {
               LOG.warn("Could not get bean reference: " + filter.getFilterClass());
               obj = fcls.newInstance();
            }
         }
         if (isDebug) {
            StringBuilder txt = new StringBuilder();
            txt.append("Loaded filter for: ").append(fcls.getCanonicalName());
            txt.append(", bean manager active: ").append(beanmgr != null);
            LOG.debug(txt.toString());
         }
      } else {
         LOG.error("Could not load class: " + filter.getFilterClass());
      }
      return obj;
   }

   /**
    * For async processing, the filter chain needs to be executed multiple
    * times.
    */
   public void reset() {
      filterListIndex = 0;
   }

   public void processFilter(EventRequest req, EventResponse res, EventPortlet eventPortlet,
         PortletContext portletContext) throws IOException, PortletException {
      this.eventPortlet = eventPortlet;
      this.loader = Thread.currentThread().getContextClassLoader();
      this.portletContext = portletContext;
      doFilter(req, res);
   }

   public void processFilter(ResourceRequest req, ResourceResponse res, ResourceServingPortlet resourceServingPortlet,
         PortletContext portletContext) throws IOException, PortletException {
      this.resourceServingPortlet = resourceServingPortlet;
      this.loader = Thread.currentThread().getContextClassLoader();
      this.portletContext = portletContext;
      doFilter(req, res);
   }

   public void processFilter(ActionRequest req, ActionResponse res, Portlet portlet, PortletContext portletContext)
         throws IOException, PortletException {
      this.portlet = portlet;
      this.loader = Thread.currentThread().getContextClassLoader();
      this.portletContext = portletContext;
      doFilter(req, res);
   }

   public void processFilter(RenderRequest req, RenderResponse res, Portlet portlet, PortletContext portletContext)
         throws IOException, PortletException {
      this.portlet = portlet;
      this.loader = Thread.currentThread().getContextClassLoader();
      this.portletContext = portletContext;
      doFilter(req, res);
   }

   public void processFilter(HeaderRequest req, HeaderResponse res, HeaderPortlet portlet, PortletContext portletContext)
         throws IOException, PortletException {
      this.headerPortlet = portlet;
      this.loader = Thread.currentThread().getContextClassLoader();
      this.portletContext = portletContext;
      doFilter(req, res);
   }

   public void addFilter(Filter filter) {
      filterList.add(filter);
   }

   @Override
   public void doFilter(ActionRequest request, ActionResponse response) throws IOException, PortletException {
      if (filterListIndex < filterList.size()) {
         Filter filter = filterList.get(filterListIndex);
         filterListIndex++;
         try {
            ActionFilter actionFilter = (ActionFilter) loadFilter(filter);
            FilterConfigImpl filterConfig = new FilterConfigImpl(filter.getFilterName(), filter.getInitParams(),
                  portletContext);
            actionFilter.init(filterConfig);
            actionFilter.doFilter(request, response, this);
            actionFilter.destroy();
         } catch (InstantiationException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
      } else {
         portlet.processAction(request, response);
      }
   }

   @Override
   public void doFilter(EventRequest request, EventResponse response) throws IOException, PortletException {
      if (filterListIndex < filterList.size()) {
         Filter filter = filterList.get(filterListIndex);
         filterListIndex++;
         try {
            EventFilter eventFilter = (EventFilter) loadFilter(filter);;
            FilterConfigImpl filterConfig = new FilterConfigImpl(filter.getFilterName(), filter.getInitParams(),
                  portletContext);
            eventFilter.init(filterConfig);
            eventFilter.doFilter(request, response, this);
            eventFilter.destroy();
         } catch (InstantiationException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
      } else {
         eventPortlet.processEvent(request, response);
      }
   }

   @Override
   public void doFilter(RenderRequest request, RenderResponse response) throws IOException, PortletException {
      if (filterListIndex < filterList.size()) {
         Filter filter = filterList.get(filterListIndex);
         filterListIndex++;
         try {
            RenderFilter renderFilter = (RenderFilter) loadFilter(filter);;
            FilterConfigImpl filterConfig = new FilterConfigImpl(filter.getFilterName(), filter.getInitParams(),
                  portletContext);
            renderFilter.init(filterConfig);
            renderFilter.doFilter(request, response, this);
            renderFilter.destroy();
         } catch (InstantiationException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
      } else {
         portlet.render(request, response);
      }
   }

   @Override
   public void doFilter(HeaderRequest request, HeaderResponse response) throws IOException, PortletException {
      if (filterListIndex < filterList.size()) {
         Filter filter = filterList.get(filterListIndex);
         filterListIndex++;
         try {
            HeaderFilter headerFilter = (HeaderFilter) loadFilter(filter);;
            FilterConfigImpl filterConfig = new FilterConfigImpl(filter.getFilterName(), filter.getInitParams(),
                  portletContext);
            headerFilter.init(filterConfig);
            headerFilter.doFilter(request, response, this);
            headerFilter.destroy();
         } catch (InstantiationException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
      } else {
         headerPortlet.renderHeaders(request, response);
      }
   }

   @Override
   public void doFilter(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
      if (filterListIndex < filterList.size()) {
         Filter filter = filterList.get(filterListIndex);
         filterListIndex++;
         try {
            ResourceFilter resourceFilter = (ResourceFilter) loadFilter(filter);;
            FilterConfigImpl filterConfig = new FilterConfigImpl(filter.getFilterName(), filter.getInitParams(),
                  portletContext);
            resourceFilter.init(filterConfig);
            resourceFilter.doFilter(request, response, this);
            resourceFilter.destroy();
         } catch (InstantiationException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
      } else {
         resourceServingPortlet.serveResource(request, response);
      }
   }
}
