/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.portlet.ActionParameters;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletSession;
import jakarta.portlet.RenderParameters;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * @author <a href="mailto:ate@douma.nu">Ate Douma</a>
 * @version $Id$
 */
public interface PortletRequestContext
{
    void init(PortletConfig portletConfig, ServletContext servletContext, HttpServletRequest servletRequest,
          HttpServletResponse servletResponse, PortletResponseContext responseContext);
    PortletContainer getContainer();
    PortletConfig getPortletConfig();
    ServletContext getServletContext();
    HttpServletRequest getContainerRequest();
    HttpServletResponse getContainerResponse();
    HttpServletRequest getServletRequest();
    HttpServletResponse getServletResponse();
    PortletWindow getPortletWindow();
    
    Enumeration<String> getAttributeNames();
    Object getAttribute(String name);
    void setAttribute(String name, Object value);

    Locale getPreferredLocale();
    Cookie[] getCookies();
    Map<String, String[]> getProperties();
    PortletSession getPortletSession(boolean create);
    
    // V2 compatibility parameter methods
    
    Map<String, String[]> getParameterMap();
    Map<String, String[]> getPrivateParameterMap();
    Map<String, String[]> getPublicParameterMap();
    
    /**
     * Returns the render parameters for the portlet. V3 method.
     * @return
     */
    RenderParameters getRenderParameters();

    /**
     * Returns the action parameters for the portlet. V3 method.
     * @return
     */
    ActionParameters getActionParameters();
    
    // for render headers support
    void setRenderHeaders(String renderHeaders);
    String getRenderHeaders();
    
    // for async support
    DispatcherType getDispatcherType();
    boolean isExecutingRequestBody();
    void setExecutingRequestBody(boolean executingRequestBody);
    HttpServletRequest getAsyncServletRequest();
    void setAsyncServletRequest(HttpServletRequest asyncServletRequest);
    
    // To provide special handling during portlet request dispatcher and async dispatches
    void endDispatch();
    void startDispatch(HttpServletRequest wrappedServletRequest, Map<String, List<String>> queryParams, String phase);
    Map<String, List<String>> getQueryParams();
    String getPhase();
}
