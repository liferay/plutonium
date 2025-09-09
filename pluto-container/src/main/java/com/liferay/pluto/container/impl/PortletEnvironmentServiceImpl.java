/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

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

import com.liferay.pluto.container.PortletActionResponseContext;
import com.liferay.pluto.container.PortletEnvironmentService;
import com.liferay.pluto.container.PortletEventResponseContext;
import com.liferay.pluto.container.PortletHeaderResponseContext;
import com.liferay.pluto.container.PortletRenderResponseContext;
import com.liferay.pluto.container.PortletRequestContext;
import com.liferay.pluto.container.PortletResourceRequestContext;
import com.liferay.pluto.container.PortletResourceResponseContext;
import com.liferay.pluto.container.PortletWindow;

public class PortletEnvironmentServiceImpl implements PortletEnvironmentService
{
    public ActionRequest createActionRequest(PortletRequestContext requestContext, PortletActionResponseContext responseContext)
    {
        return new ActionRequestImpl(requestContext, responseContext);
    }

    public ActionResponse createActionResponse(PortletActionResponseContext responseContext)
    {
        return new ActionResponseImpl(responseContext);
    }

    public EventRequest createEventRequest(PortletRequestContext requestContext, PortletEventResponseContext responseContext, Event event)
    {
        return new EventRequestImpl(requestContext, responseContext, event);
    }

    public EventResponse createEventResponse(PortletEventResponseContext responseContext)
    {
        return new EventResponseImpl(responseContext);
    }

    public PortletSession createPortletSession(PortletContext portletContext, PortletWindow portletWindow,
                                               HttpSession session)
    {
        return new PortletSessionImpl(portletContext, portletWindow, session);
    }

    public HeaderRequest createHeaderRequest(PortletRequestContext requestContext, PortletHeaderResponseContext responseContext)
    {
        return new HeaderRequestImpl(requestContext, responseContext);
    }

    public HeaderResponse createHeaderResponse(PortletHeaderResponseContext responseContext)
    {
        return new HeaderResponseImpl(responseContext);
    }

    public RenderRequest createRenderRequest(PortletRequestContext requestContext, PortletRenderResponseContext responseContext)
    {
        return new RenderRequestImpl(requestContext, responseContext);
    }

    public RenderResponse createRenderResponse(PortletRenderResponseContext responseContext)
    {
        return new RenderResponseImpl(responseContext);
    }

    public ResourceRequest createResourceRequest(PortletResourceRequestContext requestContext, PortletResourceResponseContext responseContext)
    {
        return new ResourceRequestImpl(requestContext, responseContext);
    }

    public ResourceResponse createResourceResponse(PortletResourceResponseContext responseContext,
                                                   String requestCacheLevel)
    {
        return new ResourceResponseImpl(responseContext, requestCacheLevel);
    }
}