/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.core;

import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.pluto.container.PortletContainer;
import com.liferay.pluto.container.PortletContainerException;
import com.liferay.pluto.container.PortletWindow;
import com.liferay.pluto.container.PortletWindowID;
import com.liferay.pluto.container.driver.PlutoServices;
import com.liferay.pluto.container.om.portlet.PortletDefinition;
import com.liferay.pluto.driver.services.portal.PortletWindowConfig;
import com.liferay.pluto.driver.url.PortalURL;

/**
 * Implementation of <code>PortletWindow</code> interface.
 */
public class PortletWindowImpl implements PortletWindow {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(PortletWindowImpl.class);
    
    // Private Member Variables ------------------------------------------------

    private PortletWindowConfig config;
    private PortalURL portalURL;
    private PortletWindowIDImpl objectIdImpl;
    private PortletDefinition portlet;


    // Constructor -------------------------------------------------------------

    /**
     * Constructs an instance.
     * @param config  the portlet window configuration.
     * @param portalURL  the portal URL.
     */
    public PortletWindowImpl(PortletContainer container, PortletWindowConfig config, PortalURL portalURL) {
        this.config = config;
        this.portalURL = portalURL;
        try
        {
            String applicationName = config.getContextPath();
            this.portlet = PlutoServices.getServices().getPortletRegistryService().getPortlet(applicationName, config.getPortletName());
        }
        catch (PortletContainerException ex)
        {
            String message = "Unable to load Portlet App Deployment Descriptor:"+ ex.getMessage();
            LOG.warn(message);
            throw new RuntimeException(message);
        }
    }


    // PortletWindow Impl ------------------------------------------------------

    public String getContextPath() {
        return config.getContextPath();
    }

    public String getPortletName() {
        return config.getPortletName();
    }

    public WindowState getWindowState() {
        return portalURL.getWindowState(getId().getStringId());
    }

    public PortletMode getPortletMode() {
        return portalURL.getPortletMode(getId().getStringId());
    }

    public PortletWindowID getId() {
        if (objectIdImpl == null) {
            objectIdImpl = PortletWindowIDImpl.createFromString(config.getId());
        }
        return objectIdImpl;
    }

    public PortletDefinition getPortletDefinition() {
        return portlet;
    }
}
