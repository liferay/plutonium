/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.portal;

import java.util.Set;

/**
 * Service interface which defines the methods required
 * for a provider wishing to provide property management.
 *
 * @since Aug 10, 2005
 */
public interface PropertyConfigService {

    /**
     * Name of the portal driver.
     * @return the name of this portal implementation
     */
    String getPortalName();

    /**
     * Portal driver version.
     * @return version information
     */
    String getPortalVersion();

    /**
     * Unique name of the Portlet Container
     * used to service this portal instance.
     * @return container name
     */
    String getContainerName();

    /**
     * Set of unique Portlet Modes.
     * The set must include
     * {@link jakarta.portlet.PortletMode#VIEW},
     * {@link jakarta.portlet.PortletMode#EDIT}, and
     * {@link jakarta.portlet.PortletMode#HELP}
     * @return set of unique portlet modes.
     */
    Set<String> getSupportedPortletModes();

    /**
     * Set of unique Window States.
     * The set must include:
     * {@link jakarta.portlet.WindowState#NORMAL},
     * {@link jakarta.portlet.WindowState#MAXIMIZED}, and
     * {@link jakarta.portlet.WindowState#MINIMIZED}
     * @return set of unique window states.
     */
    Set<String> getSupportedWindowStates();
}
