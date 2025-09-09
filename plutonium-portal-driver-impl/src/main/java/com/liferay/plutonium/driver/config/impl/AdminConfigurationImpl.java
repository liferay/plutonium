/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.config.impl;

import jakarta.servlet.ServletContext;

import com.liferay.plutonium.driver.config.AdminConfiguration;
import com.liferay.plutonium.driver.services.portal.admin.PortletRegistryAdminService;
import com.liferay.plutonium.driver.services.portal.admin.RenderConfigAdminService;

/**
 *
 * @version 1.0
 * @since Nov 30, 2005
 */
public class AdminConfigurationImpl implements AdminConfiguration {

    private PortletRegistryAdminService portletRegistryAdminService;
    private RenderConfigAdminService renderConfigAdminService;

    public void init(ServletContext context) {
        
    }

    public void destroy() {

    }

    public PortletRegistryAdminService getPortletRegistryAdminService() {
        return portletRegistryAdminService;
    }

    public void setPortletRegistryAdminService(PortletRegistryAdminService portletRegistryAdminService) {
        this.portletRegistryAdminService = portletRegistryAdminService;
    }

    public RenderConfigAdminService getRenderConfigAdminService() {
        return renderConfigAdminService;
    }

    public void setRenderConfigAdminService(RenderConfigAdminService renderConfigAdminService) {
        this.renderConfigAdminService = renderConfigAdminService;
    }


}
