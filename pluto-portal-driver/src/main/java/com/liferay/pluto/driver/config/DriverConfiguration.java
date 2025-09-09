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
package com.liferay.pluto.driver.config;

import java.util.Collection;
import java.util.Set;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;

import com.liferay.pluto.container.PortletContainerException;
import com.liferay.pluto.container.PortletPreferencesService;
import com.liferay.pluto.container.driver.PortletRegistryService;
import com.liferay.pluto.driver.services.portal.PageConfig;
import com.liferay.pluto.driver.services.portal.PublicRenderParameterService;
import com.liferay.pluto.driver.services.portal.RenderConfigService;
import com.liferay.pluto.driver.url.PortalURLParser;

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
     * pluto should create and embed.
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
