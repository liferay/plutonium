/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;

import com.liferay.pluto.container.om.portlet.PortletDefinition;


/**
 * Thin representation of the portlet window for which the container
 * request should be processed.  The PortletWindow is used internally
 * to map the request to the configured Portlet Application and Portlet.
 *
 * @see com.liferay.pluto.container.om.portlet.PortletDefinition
 *
 * @version 1.0
 * @since Sep 22, 2004
 */
public interface PortletWindow {

    /**
     * Retrieve this windows unique id which will be
     *  used to communicate back to the referencing portal.
     * @return unique id.
     */
    PortletWindowID getId();

    /**
     * Retrieve the current window state for this window.
     * @return the current window state.
     */
    WindowState getWindowState();

    /**
     * Retrieve the current portlet mode for this window.
     * @return the current portlet mode.
     */
    PortletMode getPortletMode();

    /**
     * Returns the portlet description. The return value cannot be NULL.
     * @return the portlet description.
     */
    PortletDefinition getPortletDefinition();
}
