/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.driver;

/**
 * Defines the additional services needed for the Pluto Portal Driver to integrate with the Pluto Container
 * and accessed from the <code>com.liferay.pluto.container.driver.PortletServlet3</code>
 * and the <code>com.liferay.pluto.container.driver.impl.DefaultPortletInvokerService</code>.
 * These extra services are not required nor used by the container itself directly.
 *
 * @since 2.0
 * @version $Id$
 */
public interface PortalDriverContainerServices
{
    /**
     * Returns the portlet context services implementation
     * used by the container.
     *
     * @return registry service implementation
     */
    PortletContextService getPortletContextService();

    /**
     * Returns the portlet registry services implementation
     * used by the container.
     *
     * @return registry service implementation
     */
    PortletRegistryService getPortletRegistryService();

    /**
     * Returns the admin service implementation used by
     * the container.
     *
     * @return portal admin service
     */
    PortalAdministrationService getPortalAdministrationService();
}
