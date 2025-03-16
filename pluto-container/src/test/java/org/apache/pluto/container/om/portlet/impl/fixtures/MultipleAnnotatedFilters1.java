/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package org.apache.pluto.container.om.portlet.impl.fixtures;

import java.io.IOException;

import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletConfigurations;
import jakarta.portlet.annotations.PortletLifecycleFilter;
import jakarta.portlet.filter.FilterChain;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.HeaderFilter;
import jakarta.portlet.filter.HeaderFilterChain;
import jakarta.portlet.filter.RenderFilter;
import jakarta.portlet.filter.ResourceFilter;

/**
 * Test filter annotation with portletNames = '*', meaning that it applies
 * to all portlets in the portlet app. 
 *
 */
@PortletConfigurations({
   @PortletConfiguration(portletName = "portlet1"),
   @PortletConfiguration(portletName = "portlet2"),
   @PortletConfiguration(portletName = "portlet3")
})
@PortletLifecycleFilter(portletNames = {"*"}, 
                      ordinal = 100,
                      filterName = "aFilter")
public class MultipleAnnotatedFilters1 implements RenderFilter,
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
