/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import java.util.Map;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.portlet.ResourceParameters;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @version $Id$
 *
 */
public interface PortletResourceRequestContext extends PortletRequestContext
{
    String getResourceID();
    String getCacheability();
    Map<String, String[]> getPrivateRenderParameterMap();
    
    /**
     * Returns the resource parameters for the request. V3 method.
     * @return
     */
    ResourceParameters getResourceParameters();
    
    /**
     * Returns the resource response needed for async support. V3 method.
     * @return
     */
    ResourceResponse getResponse();
    
    /**
     * Sets the resource response needed for async support. V3 method.
     * @return
     */
    void setResponse(ResourceResponse response);
    
    /**
     * For async support
     */
    
    AsyncContext startAsync(ResourceRequest request) throws IllegalStateException;
    AsyncContext startAsync(ResourceRequest request, ResourceResponse response, boolean origReqResp) throws IllegalStateException;
    boolean isAsyncStarted();
    boolean isAsyncSupported();
    AsyncContext getAsyncContext() throws IllegalStateException;
    DispatcherType getDispatcherType();
    HttpSession getSession();
    AsyncContext startAsync();
    AsyncContext startAsync(ServletRequest request, ServletResponse response);
    PortletAsyncManager getPortletAsyncContext();
    BeanManager getBeanManager();
    void setBeanManager(BeanManager beanmgr);
}
