/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.pluto.container.driver;

import jakarta.portlet.PortalContext;

import com.liferay.pluto.container.CCPPProfileService;
import com.liferay.pluto.container.EventCoordinationService;
import com.liferay.pluto.container.FilterManagerService;
import com.liferay.pluto.container.NamespaceMapper;
import com.liferay.pluto.container.PortletEnvironmentService;
import com.liferay.pluto.container.PortletInvokerService;
import com.liferay.pluto.container.PortletPreferencesService;
import com.liferay.pluto.container.PortletRequestContextService;
import com.liferay.pluto.container.PortletURLListenerService;
import com.liferay.pluto.container.RequestDispatcherService;
import com.liferay.pluto.container.UserInfoService;


public class PlutoServices implements PortalDriverServices
{
    private PortalDriverServices driverServices;
    
    private static PlutoServices singleton;

    public static PlutoServices getServices()
    {
        return singleton;
    }
    
    public PlutoServices(PortalDriverServices driverServices)
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
