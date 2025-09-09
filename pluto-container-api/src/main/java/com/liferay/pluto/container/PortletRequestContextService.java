/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:ate@douma.nu">Ate Douma</a>
 * @version $Id$
 */
public interface PortletRequestContextService {
   PortletRequestContext getPortletActionRequestContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, PortletWindow window);

   PortletRequestContext getPortletEventRequestContext(PortletContainer container, HttpServletRequest containerRequest,
         HttpServletResponse containerResponse, PortletWindow window);

   PortletResourceRequestContext getPortletResourceRequestContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, PortletWindow window);

   PortletRequestContext getPortletHeaderRequestContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, PortletWindow window);

   PortletRequestContext getPortletRenderRequestContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, PortletWindow window);

   PortletActionResponseContext getPortletActionResponseContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, 
         PortletWindow window, PortletRequestContext requestContext);

   PortletEventResponseContext getPortletEventResponseContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, 
         PortletWindow window, PortletRequestContext requestContext);

   PortletResourceResponseContext getPortletResourceResponseContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, 
         PortletWindow window, PortletRequestContext requestContext);

   PortletRenderResponseContext getPortletRenderResponseContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, 
         PortletWindow window, PortletRequestContext requestContext);

   PortletHeaderResponseContext getPortletHeaderResponseContext(PortletContainer container,
         HttpServletRequest containerRequest, HttpServletResponse containerResponse, 
         PortletWindow window, PortletRequestContext requestContext);
}
