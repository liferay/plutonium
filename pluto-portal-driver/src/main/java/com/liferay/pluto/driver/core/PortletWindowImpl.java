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
