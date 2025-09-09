/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import static jakarta.portlet.PortletRequest.ACTION_PHASE;
import static jakarta.portlet.PortletRequest.EVENT_PHASE;
import static jakarta.portlet.PortletRequest.HEADER_PHASE;
import static jakarta.portlet.PortletRequest.RENDER_PHASE;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.pluto.container.PortletActionResponseContext;
import com.liferay.pluto.container.PortletContainer;
import com.liferay.pluto.container.PortletEventResponseContext;
import com.liferay.pluto.container.PortletHeaderResponseContext;
import com.liferay.pluto.container.PortletRenderResponseContext;
import com.liferay.pluto.container.PortletRequestContext;
import com.liferay.pluto.container.PortletRequestContextService;
import com.liferay.pluto.container.PortletResourceRequestContext;
import com.liferay.pluto.container.PortletResourceResponseContext;
import com.liferay.pluto.container.PortletWindow;

/**
 * @author <a href="mailto:ate@douma.nu">Ate Douma</a>
 * @version $Id$
 */
public class PortletRequestContextServiceImpl implements PortletRequestContextService
{
    public PortletRequestContext getPortletActionRequestContext(PortletContainer container, HttpServletRequest containerRequest,
                                                                HttpServletResponse containerResponse, PortletWindow window)
    {
        return new PortletRequestContextImpl(container, containerRequest, containerResponse, window, true, ACTION_PHASE);
    }

    public PortletActionResponseContext getPortletActionResponseContext(PortletContainer container,
                                                                        HttpServletRequest containerRequest,
                                                                        HttpServletResponse containerResponse,
                                                                        PortletWindow window, PortletRequestContext requestContext)
    {
        return new PortletActionResponseContextImpl(container, containerRequest, containerResponse, window, requestContext);
    }

    public PortletRequestContext getPortletEventRequestContext(PortletContainer container, HttpServletRequest containerRequest,
                                                               HttpServletResponse containerResponse, PortletWindow window)
    {
        return new PortletRequestContextImpl(container, containerRequest, containerResponse, window, false, EVENT_PHASE);
    }

    public PortletEventResponseContext getPortletEventResponseContext(PortletContainer container,
                                                                      HttpServletRequest containerRequest,
                                                                      HttpServletResponse containerResponse, PortletWindow window, PortletRequestContext requestContext)
    {
        return new PortletEventResponseContextImpl(container, containerRequest, containerResponse, window, requestContext);
    }

    public PortletRequestContext getPortletRenderRequestContext(PortletContainer container, HttpServletRequest containerRequest,
                                                                HttpServletResponse containerResponse, PortletWindow window)
    {
        return new PortletRequestContextImpl(container, containerRequest, containerResponse, window, false, RENDER_PHASE);
    }

    public PortletRenderResponseContext getPortletRenderResponseContext(PortletContainer container,
                                                                        HttpServletRequest containerRequest,
                                                                        HttpServletResponse containerResponse,
                                                                        PortletWindow window, PortletRequestContext requestContext)
    {
        return new PortletRenderResponseContextImpl(container, containerRequest, containerResponse, window, requestContext);
    }

    public PortletRequestContext getPortletHeaderRequestContext(PortletContainer container, HttpServletRequest containerRequest,
                                                                HttpServletResponse containerResponse, PortletWindow window)
    {
        return new PortletRequestContextImpl(container, containerRequest, containerResponse, window, false, HEADER_PHASE);
    }

    public PortletHeaderResponseContext getPortletHeaderResponseContext(PortletContainer container,
                                                                        HttpServletRequest containerRequest,
                                                                        HttpServletResponse containerResponse,
                                                                        PortletWindow window, PortletRequestContext requestContext)
    {
        return new PortletHeaderResponseContextImpl(container, containerRequest, containerResponse, window, requestContext);
    }

    public PortletResourceRequestContext getPortletResourceRequestContext(PortletContainer container,
                                                                          HttpServletRequest containerRequest,
                                                                          HttpServletResponse containerResponse,
                                                                          PortletWindow window)
    {
        return new PortletResourceRequestContextImpl(container, containerRequest, containerResponse, window);
    }

    public PortletResourceResponseContext getPortletResourceResponseContext(PortletContainer container,
                                                                            HttpServletRequest containerRequest,
                                                                            HttpServletResponse containerResponse,
                                                                            PortletWindow window, PortletRequestContext requestContext)
    {
        return new PortletResourceResponseContextImpl(container, containerRequest, containerResponse, window, requestContext);
    }
}
