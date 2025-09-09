/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
