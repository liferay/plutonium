/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.config.impl;

import java.util.Collection;
import java.util.Set;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;

import com.liferay.plutonium.container.PortletContainerException;
import com.liferay.plutonium.container.PortletPreferencesService;
import com.liferay.plutonium.container.driver.PortletRegistryService;
import com.liferay.plutonium.driver.config.DriverConfiguration;
import com.liferay.plutonium.driver.services.portal.PageConfig;
import com.liferay.plutonium.driver.services.portal.PropertyConfigService;
import com.liferay.plutonium.driver.services.portal.PublicRenderParameterService;
import com.liferay.plutonium.driver.services.portal.RenderConfigService;
import com.liferay.plutonium.driver.services.portal.SupportedModesService;
import com.liferay.plutonium.driver.services.portal.SupportedWindowStateService;
import com.liferay.plutonium.driver.url.PortalURLParser;

/**
 * Encapsulation of the Plutonium Driver ResourceConfig.
 *
 * @version 1.0
 * @since Sep 23, 2004
 */
public class DriverConfigurationImpl
    implements DriverConfiguration {

    private final PortalURLParser portalUrlParser;
    private final PropertyConfigService propertyService;
    private final RenderConfigService renderService;
    private final SupportedModesService supportedModesService;
    private final SupportedWindowStateService supportedWindowStateService;
    private final PublicRenderParameterService publicRenderParameterService;
    private final PortletRegistryService portletRegistryService;

    // Container Services
    private PortletPreferencesService portletPreferencesService;
    
    public DriverConfigurationImpl(PortalURLParser portalUrlParser,
                                   PropertyConfigService propertyService,
                                   RenderConfigService renderService,
                                   SupportedModesService supportedModesService,
                                   SupportedWindowStateService supportedWindowStateService,
                                   PublicRenderParameterService publicRenderParameterService,
                                   PortletRegistryService portletRegistryService) {

        this.portalUrlParser = portalUrlParser;
        this.propertyService = propertyService;
        this.renderService = renderService;
        this.supportedModesService = supportedModesService;
        this.supportedWindowStateService = supportedWindowStateService;
        this.publicRenderParameterService = publicRenderParameterService;
        this.portletRegistryService = portletRegistryService;
    }

    /**
     * Standard Getter.
     * @return the name of the portal.
     */
    public String getPortalName() {
        return propertyService.getPortalName();
    }

    /**
     * Standard Getter.
     * @return the portal version.
     */
    public String getPortalVersion() {
        return propertyService.getPortalVersion();
    }

    /**
     * Standard Getter.
     * @return the name of the container.
     */
    public String getContainerName() {
        return propertyService.getContainerName();
    }

    /**
     * Standard Getter.
     * @return the names of the supported portlet modes.
     */
    public Collection<String> getSupportedPortletModes() {
        return propertyService.getSupportedPortletModes();
    }

    /**
     * Standard Getter.
     * @return the names of the supported window states.
     */
    public Collection<String> getSupportedWindowStates() {
        return propertyService.getSupportedWindowStates();
    }

    /**
     * Standard Getter.
     * @return the render configuration.
     */
    public Collection<PageConfig> getPages() {
        return renderService.getPages();
    }

    public PageConfig getPageConfig(String pageId) {
        return renderService.getPage(pageId);
    }
    
    public boolean isPortletModeSupportedByPortal(String mode) {
        return supportedModesService.isPortletModeSupportedByPortal(mode);
    }
    
    public boolean isPortletModeSupportedByPortlet(String portletId, String mode) {
        return supportedModesService.isPortletModeSupportedByPortlet(portletId, mode);
    }
    
    public boolean isPortletModeSupported(String portletId, String mode) {
        return supportedModesService.isPortletModeSupported(portletId, mode);
    }

//
// Portal Driver Services
//

    public PortalURLParser getPortalUrlParser() {
        return portalUrlParser;
    }

//
// Container Services
//
    public PortletPreferencesService getPortletPreferencesService() {
        return portletPreferencesService;
    }

    public void setPortletPreferencesService(PortletPreferencesService portletPreferencesService) {
        this.portletPreferencesService = portletPreferencesService;
    }

    public boolean isWindowStateSupported(String portletId, String windowState)
    {
        return supportedWindowStateService.isWindowStateSupported(portletId, windowState);
    }

    public boolean isWindowStateSupportedByPortal(String windowState)
    {
        return supportedWindowStateService.isWindowStateSupportedByPortal(windowState);
    }

    public boolean isWindowStateSupportedByPortlet(String portletId, String windowState)
    {
        return supportedWindowStateService.isWindowStateSupportedByPortlet(portletId, windowState);
    }

    public Set<WindowState> getSupportedWindowStates(String portletId, String contentType) throws PortletContainerException {
      return supportedWindowStateService.getSupportedWindowStates(portletId, contentType);
    }
    
    public RenderConfigService getRenderConfigService(){
    	return renderService;
    }
    
    public PublicRenderParameterService getPublicRenderParameterService() {
       return publicRenderParameterService;
    }
    
    public PortletRegistryService getPortletRegistryService() {
       return portletRegistryService;
    }

    public Set<PortletMode> getSupportedPortletModes(String portletId) throws PortletContainerException {
    	return supportedModesService.getSupportedPortletModes(portletId);
    }

	public boolean isPortletManagedMode(String portletId, String mode) {
		return supportedModesService.isPortletManagedMode(portletId, mode);
	}
	
    public PortletConfig getPortletConfig(String portletId)  throws PortletContainerException {
    	return supportedModesService.getPortletConfig(portletId);
    }
}

