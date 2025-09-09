/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.fixtures;

import java.io.IOException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.InitParameter;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletLifecycleFilter;
import jakarta.portlet.filter.FilterChain;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.HeaderFilter;
import jakarta.portlet.filter.HeaderFilterChain;
import jakarta.portlet.filter.RenderFilter;
import jakarta.portlet.filter.ResourceFilter;

/**
 * Some tests for filter annotations
 *
 */
@ApplicationScoped
@PortletLifecycleFilter(portletNames = {"portlet362"}, 
                      ordinal = 100,
                      filterName = "aFilter",
                      initParams = {
                         @InitParameter(name = "execute", value = "true"),
                         @InitParameter(name = "id", value = "ego")},
                      description = {
                         @LocaleString("Quite the filter"),
                         @LocaleString(locale="DE", value = "Ein ordentlicher Filter")},
                      displayName = {
                         @LocaleString("A Filter"),
                         @LocaleString(locale="DE", value = "Ein Filter")})
public class TestAnnotatedFilter implements RenderFilter,
      ResourceFilter, HeaderFilter {

   @Override
   public void init(FilterConfig filterConfig) throws PortletException {
   }

   @Override
   public void destroy() {
   }

   @Override
   public void doFilter(ResourceRequest request, ResourceResponse response,
         FilterChain chain) throws IOException, PortletException {

   }

   @Override
   public void doFilter(RenderRequest request, RenderResponse response,
         FilterChain chain) throws IOException, PortletException {

   }

   @Override
   public void doFilter(HeaderRequest request, HeaderResponse response, HeaderFilterChain chain) throws IOException,
         PortletException {
      
   }

}
