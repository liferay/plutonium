/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

import jakarta.portlet.PortalContext;

import com.liferay.plutonium.container.EventCoordinationService;
import com.liferay.plutonium.container.FilterManagerService;
import com.liferay.plutonium.container.PortletRequestContextService;
import com.liferay.plutonium.container.PortletURLListenerService;


/**
 * This interface defines the services required for integration between the
 * Plutonium Portlet Container and a Portal.
 *
 */
public interface RequiredContainerServices {

    /**
     * Returns the PortalContext instance associated with this group of
     * portlet container services.
     * @return a PortalContext implementation.
     */
    PortalContext getPortalContext();

    EventCoordinationService getEventCoordinationService();

    /**
     * Returns the portlet request context service implementation
     * used by the container
     */
    PortletRequestContextService getPortletRequestContextService();

    /**
     * Returns the FilterManagerService
     */
    public FilterManagerService getFilterManagerService(); 
    
    /**
     * Returns the PortletURLListenerService
     */
    public PortletURLListenerService getPortletURLListenerService();
}
