/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import java.util.ArrayList;
import java.util.List;

import com.liferay.pluto.container.driver.AdministrativeRequestListener;
import com.liferay.pluto.container.driver.PortalAdministrationService;
import com.liferay.pluto.container.driver.PortletInvocationListener;

public class DefaultPortalAdministrationService implements PortalAdministrationService 
{
    private List<AdministrativeRequestListener> administrativeRequestListeners =new ArrayList<AdministrativeRequestListener>();
    private List<PortletInvocationListener> portletInvocationListeners = new ArrayList<PortletInvocationListener>();

    public List<AdministrativeRequestListener> getAdministrativeRequestListeners() 
    {
        return administrativeRequestListeners;
    }

    public void setAdministrativeRequestListeners(List<AdministrativeRequestListener> administrativeRequestListeners) 
    {
        this.administrativeRequestListeners = administrativeRequestListeners;
    }

    public void addAdministrativeRequestListener(AdministrativeRequestListener listener) 
    {
        administrativeRequestListeners.add(listener);
    }

    public List<PortletInvocationListener> getPortletInvocationListeners() 
    {
        return portletInvocationListeners;
    }

    public void setPortletInvocationListeners(List<PortletInvocationListener> portletInvocationListeners) 
    {
        this.portletInvocationListeners = portletInvocationListeners;
    }

    public void addPortletInvocationListener(PortletInvocationListener listener) 
    {
        portletInvocationListeners.add(listener);
    }
}