/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

import com.liferay.plutonium.container.CCPPProfileService;
import com.liferay.plutonium.container.NamespaceMapper;
import com.liferay.plutonium.container.PortletEnvironmentService;
import com.liferay.plutonium.container.PortletInvokerService;
import com.liferay.plutonium.container.PortletPreferencesService;
import com.liferay.plutonium.container.RequestDispatcherService;
import com.liferay.plutonium.container.UserInfoService;

/**
 * Defines the services necessary for integration between the Pluto Container
 * and a Portal.
 *
 * <p>NOTE: Backwards compatibility is not garaunteed against
 * this interface as additional services may be needed.
 * Please extend the DefaultOptionalContainerServices
 * class to ensure your implementation can be used without
 * modicications in the future.</p>
 *
 * @since 1.1.0
 */
public interface OptionalContainerServices {

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
     * Returns the RequestDispatcherService
     */
    RequestDispatcherService getRequestDispatcherService();
}
