/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.portal;

import java.util.Set;

import jakarta.portlet.WindowState;

import com.liferay.plutonium.container.PortletContainerException;

/**
 * Allows clients to see if a particular WindowState is supported by
 * the portal, a particular portlet, or both.
 *
 * @since Feb 27, 2007
 * @version $Id$
 * @see jakarta.portlet.WindowState
 */
public interface SupportedWindowStateService {

    /**
     * Returns true if the portlet and the portal support the supplied
     * window state.
     * @param portletId the id uniquely identifying the portlet
     * @param state the portlet window state
     * @return true if the portlet and portal both support the supplied window state
     */
    boolean isWindowStateSupported( String portletId, String state );

    /**
     * Returns true if the portal supports the supplied window state.
     * @param state the portlet window state
     * @return true if the portal supports the supplied window state
     */
    boolean isWindowStateSupportedByPortal( String state );

    /**
     * Returns true if the portlet supports the supplied window state.
     * @param portletId the id uniquely identifying the portlet
     * @param state the window state
     * @return true if the portlet support the supplied state
     */
    boolean isWindowStateSupportedByPortlet( String portletId, String state );

    /**
     * Gets all window states supported by a portlet according to the deployment descriptor. 
     * in portlet.xml.
     * 
     * @param portletId Id of the portlet of interest
     * @param The content type, in general will be "text/html"
     * @return all window states supported by a portlet.
     * @throws PortletContainerException
      */
     Set<WindowState> getSupportedWindowStates(String portletId, String contentType) throws PortletContainerException;

}
