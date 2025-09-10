/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

import java.util.Iterator;

import jakarta.servlet.ServletConfig;

import com.liferay.plutonium.container.PortletContainerException;
import com.liferay.plutonium.container.PortletWindow;

/**
 * Interface defining the services used by both the Plutonium Portal Driver
 * and the Plutonium PortletServlet for registration and access to 
 * the PortletContexts and ClassLoader of registered Portlet Applications.
 *
 * @since 2.0
 * @version $Id$
 *
 */
public interface PortletContextService
{
    /**
     * Retrieve all registered applications.  This list
     * will only contain those applications which have
     * been registered with the container.  Others may
     * or may not be available within the servers.
     *
     * @return iterator of all application descriptors.
     */
    Iterator<DriverPortletContext> getPortletContexts();

    /**
     * Retrieve the InternalPortletContext for the specified portlet application name
     *
     * @param applicationName portlet application name
     * @return portlet context or null if not registered
     */
    DriverPortletContext getPortletContext(String applicationName)
        throws PortletContainerException;

    /**
     * Retrieve the InternalPortletContext for the specified portlet window
     *
     * @param portletWindow portlet window
     * @return portlet context or null if not registered
     */
    DriverPortletContext getPortletContext(PortletWindow portletWindow)
        throws PortletContainerException;

    /**
     * Retrieve the portlet configuration for the specified portlet
     * @param applicationName portlet application name
     * @param portletName portlet name
     * @return portletconfig
     * @throws PortletContainerException if portlet or application unknown
     */
    DriverPortletConfig getPortletConfig(String applicationName, String portletName)
        throws PortletContainerException;

    /**
     * Retrieve the ClassLoader of the specified portlet application
     * @param applicationName portlet application name
     * @return classLoader
     */
    ClassLoader getClassLoader(String applicationName)
        throws PortletContainerException;
    
    /**
     * Register a portlet application
     * 
     * @param config
     * @return
     * @throws PortletContainerException
     */
    String register(ServletConfig config) throws PortletContainerException;
    
    /**
     * Unregister a portlet application
     * 
     * @param context
     */
    void unregister(DriverPortletContext context);
}
