/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.config;

import java.util.Collection;
import java.util.Set;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;

import com.liferay.plutonium.container.PortletContainerException;
import com.liferay.plutonium.container.PortletPreferencesService;
import com.liferay.plutonium.container.driver.PortletRegistryService;
import com.liferay.plutonium.driver.services.portal.PageConfig;
import com.liferay.plutonium.driver.services.portal.PublicRenderParameterService;
import com.liferay.plutonium.driver.services.portal.RenderConfigService;
import com.liferay.plutonium.driver.url.PortalURLParser;

/**
 * Interface defining a means for retrieving driver services
 * based upon configuration information. Within the portal,
 * an implementation of this interface should be bound to
 * the portal's ServletContext.
 *
 *
 * @author <a href="mailto:ddewolf@apache.org">David H. DeWolf</a>
 * @since Sep 2, 2005
 *
 */
public interface DriverConfiguration {

//
// Service / Configuration Methods
//

    /**
     * Retrieve the name of the portal
     * as should be returned in
     * {@link jakarta.portlet.PortalContext#getPortalInfo()}
     * @return the name of the portal.
     */
    String getPortalName();

    /**
     * Retrieve the version of the portal
     * as should be returned in
     * {@link jakarta.portlet.PortalContext#getPortalInfo()}
     * @return the portal version.
     */
    String getPortalVersion();

    /**
     * Retrieves the name of the container which
     * plutonium should create and embed.
     * @return the container name.
     */
    String getContainerName();

    Collection<String> getSupportedPortletModes();

    Collection<String> getSupportedWindowStates();
    
//    Collection getPortletApplications();

    Collection<PageConfig> getPages();

    PageConfig getPageConfig(String pageId);
    
    boolean isPortletModeSupportedByPortal(String mode);
    
    boolean isPortletModeSupportedByPortlet(String portletId, String mode);
    
    boolean isPortletModeSupported(String portletId, String mode);

    boolean isWindowStateSupportedByPortal(String windowState);

    boolean isWindowStateSupportedByPortlet(String portletId, String windowState);

    boolean isWindowStateSupported(String portletId, String windowState);

    public Set<WindowState> getSupportedWindowStates(String portletId, String contentType) throws PortletContainerException; 
    
    public PublicRenderParameterService getPublicRenderParameterService();
    
    public PortletRegistryService getPortletRegistryService();

//
// Utility methods for the container
//
    PortletPreferencesService getPortletPreferencesService();

    PortalURLParser getPortalUrlParser();
    
    public RenderConfigService getRenderConfigService();

    public Set<PortletMode> getSupportedPortletModes(String portletId) throws PortletContainerException; 
    
    public PortletConfig getPortletConfig(String portletId)  throws PortletContainerException;

	 public boolean isPortletManagedMode(String portletId, String mode);
    
}
