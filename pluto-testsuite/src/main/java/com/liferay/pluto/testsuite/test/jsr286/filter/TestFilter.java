/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test.jsr286.filter;

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

public class TestFilter extends BaseFilter implements
    RenderFilter, ActionFilter, ResourceFilter, EventFilter {

    public static String TEST_FILTER_ATTR = "TEST_FILTER_ATTR";
    
    private void processRequest(PortletRequest request) {
        String phase = (String)
            request.getAttribute(PortletRequest.LIFECYCLE_PHASE); 
        request.setAttribute(
                WildcardMappedFilter.ATTR_TO_BE_OVERWRITTEN, 
                phase);
        request.setAttribute(TEST_FILTER_ATTR, Boolean.TRUE);
    }
    
    public void doFilter(RenderRequest request, RenderResponse response,
            FilterChain chain) throws IOException, PortletException {
        processRequest(request);
        chain.doFilter(request, response);
    }

    public void doFilter(ActionRequest request, ActionResponse response,
            FilterChain chain) throws IOException, PortletException {
        processRequest(request);
        chain.doFilter(request, response);
    }

    public void doFilter(ResourceRequest request, ResourceResponse response,
            FilterChain chain) throws IOException, PortletException {
        processRequest(request);
        chain.doFilter(request, response);
    }

    public void doFilter(EventRequest request, EventResponse response,
            FilterChain chain) throws IOException, PortletException {
        processRequest(request);
        chain.doFilter(request, response);
    }

}
