/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.container;

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
import com.liferay.plutonium.container.driver.OptionalContainerServices;
import com.liferay.plutonium.container.driver.PortalAdministrationService;
import com.liferay.plutonium.container.driver.PortalDriverServices;
import com.liferay.plutonium.container.driver.PortletContextService;
import com.liferay.plutonium.container.driver.PortletRegistryService;
import com.liferay.plutonium.container.driver.RequiredContainerServices;
import com.liferay.plutonium.container.impl.PortletEnvironmentServiceImpl;
import com.liferay.plutonium.container.impl.RequestDispatcherServiceImpl;
import com.liferay.plutonium.container.impl.PortletAppDescriptorServiceImpl;


public class PortalDriverServicesImpl implements RequiredContainerServices, OptionalContainerServices, PortalDriverServices
{
    /*
     * required services
     */
    private PortalContext context;
    private EventCoordinationService eventCoordinationService;
    private PortletRequestContextService portletRequestContextService;
    private CCPPProfileService ccppProfileService;
    private FilterManagerService filterManagerService;
    private PortletURLListenerService portletURLListenerService;
    
    /*
     * optional services
     */
    private PortletPreferencesService portletPreferencesService;
    private PortletInvokerService portletInvokerService;
    private PortletEnvironmentService portletEnvironmentService;
    private UserInfoService userInfoService;
    private NamespaceMapper namespaceMapper;
    private RequestDispatcherService rdService;

    /*
     * portal driver services
     */
    private PortletContextService portletContextService;
    private PortletRegistryService portletRegistryService;
    private PortalAdministrationService portalAdministrationService;

    /**
     * Constructor for just passing in the required services.
     * @param context
     * @param portletRequestContextService
     * @param eventCoordinationService
     * @param filterManagerService
     * @param portletURLListenerService
     */
    public PortalDriverServicesImpl(PortalContext context,
            PortletRequestContextService portletRequestContextService,
            EventCoordinationService eventCoordinationService,
            FilterManagerService filterManagerService,
            PortletURLListenerService portletURLListenerService)  
    {
        this(context, portletRequestContextService, eventCoordinationService, 
            filterManagerService, portletURLListenerService, null);
    }

    /**
     * Constructor for passing in the required services and optional container services.
     * @param context
     * @param portletRequestContextService
     * @param eventCoordinationService
     * @param filterManagerService
     * @param portletURLListenerService
     * @param optionalServices Optional services (if this is null, default services are used)
     */
    public PortalDriverServicesImpl(PortalContext context,
            PortletRequestContextService portletRequestContextService,
            EventCoordinationService eventCoordinationService,
            FilterManagerService filterManagerService,
            PortletURLListenerService portletURLListenerService,
            OptionalContainerServices optionalServices)  
    {
        this(context, portletRequestContextService, eventCoordinationService,
             filterManagerService, portletURLListenerService, optionalServices, null, null, null);
    }
    
    /**
     * Constructor for passing in the required services and optional container services.
     * @param context
     * @param portletRequestContextService
     * @param eventCoordinationService
     * @param filterManagerService
     * @param portletURLListenerService
     * @param optionalServices Optional services (if this is null, default services are used)
     */
    public PortalDriverServicesImpl(PortalContext context,
            PortletRequestContextService portletRequestContextService,
            EventCoordinationService eventCoordinationService,
            FilterManagerService filterManagerService,
            PortletURLListenerService portletURLListenerService,
            OptionalContainerServices optionalServices,
            PortletContextService portletContextService,
            PortletRegistryService portletRegistryService,
            PortalAdministrationService portalAdministrationService)  
    {    
        // set required first
        this.context = context;
        this.eventCoordinationService = eventCoordinationService;
        this.portletRequestContextService = portletRequestContextService;
        this.filterManagerService = filterManagerService;
        this.portletURLListenerService = portletURLListenerService;

        // now optional
        if ( optionalServices != null ) {
            ccppProfileService = optionalServices.getCCPPProfileService();
            portletPreferencesService = optionalServices.getPortletPreferencesService();
            portletInvokerService = optionalServices.getPortletInvokerService();
            portletEnvironmentService = optionalServices.getPortletEnvironmentService();
            userInfoService = optionalServices.getUserInfoService();
            namespaceMapper = optionalServices.getNamespaceMapper();
            rdService = optionalServices.getRequestDispatcherService();
        }

        // and finally driver
        this.portletContextService = portletContextService;
        this.portletRegistryService = portletRegistryService;
        this.portalAdministrationService = portalAdministrationService;

        createDefaultServicesIfNeeded();
    }
    
    /**
     * Constructor
     * @param required
     * @param optional Optional services (if this is null, default services are used)
     */
    public PortalDriverServicesImpl(RequiredContainerServices required, OptionalContainerServices optional) {
        this(required.getPortalContext(), required.getPortletRequestContextService(),
             required.getEventCoordinationService(), required.getFilterManagerService(), 
             required.getPortletURLListenerService(), optional);
    }

    protected void createDefaultServicesIfNeeded()
    {
        rdService = rdService == null ? new RequestDispatcherServiceImpl() : rdService;
        portletRegistryService = portletRegistryService == null ? new PortletContextManager(rdService, new PortletAppDescriptorServiceImpl()) : portletRegistryService;
        portletContextService = portletContextService == null ? (PortletContextManager)portletRegistryService : portletContextService;
        portalAdministrationService = portalAdministrationService == null ? new DefaultPortalAdministrationService() : portalAdministrationService;
        ccppProfileService = ccppProfileService == null ? new DummyCCPPProfileServiceImpl() : ccppProfileService;
        portletPreferencesService = portletPreferencesService == null ? new DefaultPortletPreferencesService() : portletPreferencesService;
        portletInvokerService = portletInvokerService == null ? new DefaultPortletInvokerService(portletContextService) : portletInvokerService;
        portletEnvironmentService = portletEnvironmentService == null ? new PortletEnvironmentServiceImpl() : portletEnvironmentService;
        userInfoService = userInfoService == null ? new DefaultUserInfoService() : userInfoService;
        namespaceMapper = namespaceMapper == null ? new DefaultNamespaceMapper() : namespaceMapper;
    }
    
    /**
     * @see com.liferay.plutonium.container.ContainerServices#getPortalContext()
     */
    public PortalContext getPortalContext() 
    {
        return context;
    }

    /**
     * The PortletPreferencesService provides access to the portal's
     * PortletPreference persistence mechanism.
     * @return a PortletPreferencesService instance.
     */
    public PortletPreferencesService getPortletPreferencesService() 
    {
        return this.portletPreferencesService;
    }

    /**
     * Returns null to use plutonium's default
     */
    public PortletRegistryService getPortletRegistryService() 
    {
        return this.portletRegistryService;
    }

    public PortletContextService getPortletContextService() 
    {
        return this.portletContextService;
    }

    public PortletRequestContextService getPortletRequestContextService() 
    {
        return this.portletRequestContextService;
    }

    public PortletEnvironmentService getPortletEnvironmentService() 
    {
        return this.portletEnvironmentService;
    }

    public PortletInvokerService getPortletInvokerService() 
    {
        return this.portletInvokerService;
    }

    public CCPPProfileService getCCPPProfileService() 
    {
        return ccppProfileService;
    }

    public PortalAdministrationService getPortalAdministrationService() 
    {
        return this.portalAdministrationService;
    }

    public UserInfoService getUserInfoService() 
    {
        return this.userInfoService;
    }
    
    public NamespaceMapper getNamespaceMapper() 
    {
        return this.namespaceMapper;
    }

    public CCPPProfileService getCcppProfileService()
    {
        return ccppProfileService;
    }

    public EventCoordinationService getEventCoordinationService()
    {
        return eventCoordinationService;
    }

    public FilterManagerService getFilterManagerService()
    {
        return filterManagerService;
    }

    public PortletURLListenerService getPortletURLListenerService()
    {
        return portletURLListenerService;
    }    
    
    public RequestDispatcherService getRequestDispatcherService()
    {
        return rdService;
    }
}
