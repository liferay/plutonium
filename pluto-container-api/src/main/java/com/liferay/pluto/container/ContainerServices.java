/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import jakarta.portlet.PortalContext;

/**
 * This interface defines the services required for integration between the
 * Pluto Portlet Container and a Portal.
 */
public interface ContainerServices {

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
    FilterManagerService getFilterManagerService();

    /**
     * Returns the PortletURLListenerService
     */
    PortletURLListenerService getPortletURLListenerService();

    /**
     * Returns the portlet preferences service implementation
     * used by the container.
     *
     * @return portlet preferences service implementation.
     */
    PortletPreferencesService getPortletPreferencesService();

    /**
     * Returns the environment services implementation
     * used by the container.
     *
     * @return portlet environment services implementation.
     */
    PortletEnvironmentService getPortletEnvironmentService();

    /**
     * Returns an invoker for the specified PortletWindow.
     *
     * @return an invoker which can be used to service the indicated portlet.
     */
    PortletInvokerService getPortletInvokerService();

    /**
     * Returns the user info service implementation used
     * by the container.
     *
     * @return user info service
     */
    UserInfoService getUserInfoService();

    /**
     * Returns the NamespaceMapper used to retrieve the Portal
     * specific PortletWindow namespace and encoding/decoding
     * of PortletWindow parameters in a PortalURL
     */
    NamespaceMapper getNamespaceMapper();

    /**
     * Returns the CC/PP profile service implementation
     * @return a CCPPProfileServiceImplementation
     */
    CCPPProfileService getCCPPProfileService();
    
    /**
     * Returns the RequestDispatcher service implementation
     * @return a RequestDispatcherService implementation
     */
    RequestDispatcherService getRequestDispatcherService();
}
