/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.config;

import jakarta.servlet.ServletContext;

import com.liferay.pluto.driver.services.portal.admin.PortletRegistryAdminService;
import com.liferay.pluto.driver.services.portal.admin.RenderConfigAdminService;

/**
 * Interface defining a means for obtaining administrative
 * services for portal configuration.  An implementation
 * of this interface will be bound to the portal's
 * ServletContext IF administrative functions are
 * supported by the current implementation.
 *
 * @version 1.0
 * @since Nov 30, 2005
 */
public interface AdminConfiguration {

    /**
     * Lifecyle method used to initialize the configuration
     * @param context
     */
    void init(ServletContext context) throws DriverConfigurationException;

    /**
     * Lifecylce method used to remove the configuration
     * from service.
     */
    void destroy() throws DriverConfigurationException;

    /**
     * Retrieve the administrative service for managing the
     * portlet registry.
     *
     * @return the service if one has been provided
     */
    PortletRegistryAdminService getPortletRegistryAdminService();

    /**
     * Retrieve the administrative service for managing the
     * render configuration.
     * @return the service if one has been provided
     */
    RenderConfigAdminService getRenderConfigAdminService();


}
