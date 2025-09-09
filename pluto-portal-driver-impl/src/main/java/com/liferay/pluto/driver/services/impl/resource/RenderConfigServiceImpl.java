/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.impl.resource;

import java.util.List;
import java.util.Set;

import com.liferay.pluto.container.PageResourceId;
import com.liferay.pluto.driver.services.portal.PageConfig;
import com.liferay.pluto.driver.services.portal.PageResources;
import com.liferay.pluto.driver.services.portal.RenderConfigService;
import com.liferay.pluto.driver.services.portal.admin.RenderConfigAdminService;

/**
 * Default implementation of all of the portal Services.
 * Utilizes resource configuration from
 * <code>pluto-portal-driver-config.xml</code>
 *
 * @since Aug 10, 2005
 */
public class RenderConfigServiceImpl
    implements RenderConfigService, RenderConfigAdminService {


    private final ResourceConfig config;

    public RenderConfigServiceImpl(ResourceConfig config) {
        this.config = config;
    }

    public String getPortalName() {
        return config.getPortalName();
    }

    public String getPortalVersion() {
        return config.getPortalVersion();
    }

    public String getContainerName() {
        return config.getContainerName();
    }

    public Set<String> getSupportedPortletModes() {
        return config.getSupportedPortletModes();
    }

    public Set<String> getSupportedWindowStates() {
        return config.getSupportedWindowStates();
    }

    @Override
    public List<PageConfig> getPages() {
        return config.getRenderConfig().getPages();
    }

    @Override
    public PageConfig getDefaultPage() {
        return config.getRenderConfig().getPageConfig(null);
    }

    @Override
    public PageConfig getPage(String id) {
        return config.getRenderConfig().getPageConfig(id);
    }

    @Override
    public void addPage(PageConfig pageConfig) {
        config.getRenderConfig().addPage(pageConfig);
    }
    
    @Override
    public void removePage(PageConfig pageConfig){
        config.getRenderConfig().removePage(pageConfig);
    }
    
    @Override
    public PageResources getPageResources() {
       return config.getResources();
    }
    
    @Override
    public List<PageResourceId> getDefaultPageDependencies() {
       return config.getDefaultPageDependencies();
    }
}
