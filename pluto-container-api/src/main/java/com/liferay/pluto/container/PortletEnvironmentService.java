/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Event;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletSession;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Factory Service for creating Portlet request, responses and session.
 *
 * @since 1.1.0
 */
public interface PortletEnvironmentService
{    
    ActionRequest createActionRequest(PortletRequestContext requestContext, PortletActionResponseContext responseContext);
    ActionResponse createActionResponse(PortletActionResponseContext responseContext);

    EventRequest createEventRequest(PortletRequestContext requestContext, PortletEventResponseContext responseContext, Event event);
    EventResponse createEventResponse(PortletEventResponseContext responseContext);
    
    HeaderRequest createHeaderRequest(PortletRequestContext requestContext, PortletHeaderResponseContext responseContext);
    HeaderResponse createHeaderResponse(PortletHeaderResponseContext responseContext);
    
    RenderRequest createRenderRequest(PortletRequestContext requestContext, PortletRenderResponseContext responseContext);
    RenderResponse createRenderResponse(PortletRenderResponseContext responseContext);
    
    ResourceRequest createResourceRequest(PortletResourceRequestContext requestContext, PortletResourceResponseContext responseContext);
    ResourceResponse createResourceResponse(PortletResourceResponseContext responseContext, String requestCacheLevel);

    PortletSession createPortletSession(PortletContext portletContext, PortletWindow portletWindow, HttpSession session);
}
