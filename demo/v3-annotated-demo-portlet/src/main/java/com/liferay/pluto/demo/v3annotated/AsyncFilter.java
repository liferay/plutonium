/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.v3annotated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.portlet.PortletException;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.PortletLifecycleFilter;
import jakarta.portlet.filter.FilterChain;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.ResourceFilter;
import jakarta.servlet.DispatcherType;

/**
 * Filter for the async portlet. logs the dispatcher type and sometimes
 * generates output.
 * 
 * @author Scott Nicklous
 *
 */
@PortletLifecycleFilter(portletNames="AsyncPortlet")
public class AsyncFilter implements ResourceFilter {
   private static final Logger logger = LoggerFactory.getLogger(AsyncFilter.class);
   
   @Inject private PortletRequestRandomNumberBean reqnum;
   @Inject private AsyncDialogBean asyncDialogBean;

   @Override
   public void init(FilterConfig filterConfig) throws PortletException {
   }

   @Override
   public void destroy() {
   }

   @Override
   public void doFilter(ResourceRequest resourceRequest, ResourceResponse resourceResponse, FilterChain chain) throws IOException,
         PortletException {
      
      DispatcherType dispatcherType = resourceRequest.getDispatcherType();
      
      StringBuilder txt = new StringBuilder(128);
      txt.append("Entering request. DispatcherType: ").append(dispatcherType);
      txt.append(", request #: ").append(reqnum.getRandomNumber());
      logger.debug(txt.toString());
      
      if (asyncDialogBean.isShowFilter()) {
         txt.setLength(0);
         txt.append("<div class='msgbox'>");
         txt.append("Filter: Request number: ").append(reqnum.getRandomNumber());
         txt.append(", DispatcherType: ").append(resourceRequest.getDispatcherType());
         txt.append("</div>");
         resourceResponse.getWriter().write(txt.toString());
      }
      
      chain.doFilter(resourceRequest, resourceResponse);
      
      logger.debug("Exiting request. DispatcherType: " + resourceRequest.getDispatcherType());
      
   }

}
