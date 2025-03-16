/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.apache.pluto.testsuite.test.jsr286.filter;

import java.io.IOException;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.filter.ActionFilter;
import jakarta.portlet.filter.EventFilter;
import jakarta.portlet.filter.FilterChain;
import jakarta.portlet.filter.RenderFilter;
import jakarta.portlet.filter.ResourceFilter;

public class WildcardMappedFilter extends BaseFilter  implements
    RenderFilter, ActionFilter, EventFilter, ResourceFilter {

    public static String ATTR_SET_IN_FILTER = 
                                        WildcardMappedFilter.class.getName();
    
    public static String ATTR_TO_BE_OVERWRITTEN = "OVERWRITE_ME";
    
    private void processRequest(PortletRequest request) {
        request.setAttribute(ATTR_SET_IN_FILTER, Boolean.TRUE);
        request.setAttribute(ATTR_TO_BE_OVERWRITTEN, ATTR_TO_BE_OVERWRITTEN);
    }
    
    public void doFilter(RenderRequest request, RenderResponse response,
            FilterChain filterChain) throws IOException, PortletException {
        processRequest(request);
        filterChain.doFilter(request, response);
    }

    public void doFilter(ActionRequest request, ActionResponse response,
            FilterChain filterChain) throws IOException, PortletException {
        processRequest(request);
        filterChain.doFilter(request, response);
    }

    public void doFilter(EventRequest request, EventResponse response, 
            FilterChain filterChain) throws IOException, PortletException {
        processRequest(request);
        filterChain.doFilter(request, response);
    }

    public void doFilter(ResourceRequest request, ResourceResponse response,
            FilterChain filterChain) throws IOException, PortletException {
        processRequest(request);
        filterChain.doFilter(request, response);        
    }

}
