/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

import jakarta.portlet.PortalContext;

import com.liferay.plutonium.container.CCPPProfileService;
import com.liferay.plutonium.container.EventCoordinationService;
import com.liferay.plutonium.container.FilterManagerService;
import com.liferay.plutonium.container.NamespaceMapper;
import com.liferay.plutonium.container.PortletEnvironmentService;
import com.liferay.plutonium.container.PortletInvokerService;
import com.liferay.plutonium.container.PortletPreferencesService;
import com.liferay.plutonium.container.PortletRequestContextService;
import com.liferay.plutonium.container.PortletURLListenerService;
import com.liferay.plutonium.container.RequestDispatcherService;
import com.liferay.plutonium.container.UserInfoService;


public class PlutoniumServices implements PortalDriverServices
{
    private PortalDriverServices driverServices;
    
    private static PlutoniumServices singleton;

    public static PlutoniumServices getServices()
    {
        return singleton;
    }
    
    public PlutoniumServices(PortalDriverServices driverServices)
    {
        singleton = this;
        this.driverServices = driverServices;
    }
    
    public CCPPProfileService getCCPPProfileService()
    {
        return driverServices.getCCPPProfileService();
    }

    public PortalContext getPortalContext()
    {
        return driverServices.getPortalContext();
    }

    public NamespaceMapper getNamespaceMapper()
    {
        return driverServices.getNamespaceMapper();
    }

    public PortalAdministrationService getPortalAdministrationService()
    {
        return driverServices.getPortalAdministrationService();
    }

    public PortletEnvironmentService getPortletEnvironmentService()
    {
        return driverServices.getPortletEnvironmentService();
    }

    public PortletInvokerService getPortletInvokerService()
    {
        return driverServices.getPortletInvokerService();
    }

    public PortletPreferencesService getPortletPreferencesService()
    {
        return driverServices.getPortletPreferencesService();
    }

    public PortletRegistryService getPortletRegistryService()
    {
        return driverServices.getPortletRegistryService();
    }

    public UserInfoService getUserInfoService()
    {
        return driverServices.getUserInfoService();
    }
    
    public PortletContextService getPortletContextService()
    {
        return driverServices.getPortletContextService();
    }

    public PortletRequestContextService getPortletRequestContextService()
    {
        return driverServices.getPortletRequestContextService();
    }

    public EventCoordinationService getEventCoordinationService()
    {
        return driverServices.getEventCoordinationService();
    }

    public FilterManagerService getFilterManagerService()
    {
        return driverServices.getFilterManagerService();
    }

    public PortletURLListenerService getPortletURLListenerService()
    {
        return driverServices.getPortletURLListenerService();
    }
    
    public RequestDispatcherService getRequestDispatcherService()
    {
        return driverServices.getRequestDispatcherService();
    }
}
