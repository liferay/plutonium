/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.driver;

import java.util.List;


/**
 * Provides callback for executing portal administration
 * tasks within the portlet environment.  This is key for
 * when the portal needs to accessing items such as the
 * session.
 *
 * Example Use Cases for the PortalAdministrationService:
 * <ul>
 *   <li>PortletException
 *   <p>The portal wants to ensure that portlets never
 *      become stuck in an unusable state.  To make sure
 *      this happens, they want the ability to clear the
 *      session if they repetedly catch exception from
 *      container invocations
 *   </p>
 *   <p>By implementing this services and registering
 *      and administrative request handler, the portal
 *      can invoke the doAdmin method of the container
 *      and then receive the request through the handler
 *      executing within the portlet environment (as
 *      opposed to the portal environment)
 *   </p>
 *   </li>
 * </ul>
 */
public interface PortalAdministrationService 
{
    List<AdministrativeRequestListener> getAdministrativeRequestListeners();
    List<PortletInvocationListener> getPortletInvocationListeners();
}