/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.driver;

import java.util.Iterator;

import com.liferay.pluto.container.PortletContainerException;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;
import com.liferay.pluto.container.om.portlet.PortletDefinition;

/**
 * Interface defining the services used by the container
 * to access portlet applications.  The registry
 * acts as both registry and publically
 * as a mechanism for notifying the container of new applications.
 *
 * @since 1.1.0
 *
 */
public interface PortletRegistryService 
{
    /**
     * Retrieve the names of all registered applications.
     * This list will only contain those applications
     * which have been registered with the container.
     * Others may or may not be available within
     * the servers.
     *
     * @return iterator of all PortletApp names (strings).
     */
    Iterator<String> getRegisteredPortletApplicationNames();

    /**
     * Retrieve the PortletApp for the specified
     * portlet application Name.
     *
     * @param applicationName the name of the portlet application.
     * @return the named PortletApp.
     * @throws PortletContainerException if the portlet application 
     *         isn't registered.
     */
    PortletApplicationDefinition getPortletApplication(String applicationName)
        throws PortletContainerException;

    /**
     * Retreive the Portlet for the specified portlet.
     *
     * @param applicationName portlet application name
     * @param portletName portlet name
     * @return portlet
     * @throws PortletContainerException if portlet or application unknown
     */
    PortletDefinition getPortlet(String applicationName, String portletName)
        throws PortletContainerException;

    /**
     * Add a listener which will recieve notifications of newly
     * registered applications.
     *
     * @param listener the listener to add
     */
    void addPortletRegistryListener(PortletRegistryListener listener);

    /**
     * Remove a previously registered listener.
     *
     * @param listener the listener to remove
     */
    void removePortletRegistryListener(PortletRegistryListener listener);
    
}