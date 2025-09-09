/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;

/**
 * Encapsulation of event information.
 *
 * @since 1.1.0
 */
public class PortletRegistryEvent {

    private String applicationName;
    private PortletApplicationDefinition portletApplication;


    public String getApplicationName() {
        return applicationName != null ? applicationName : portletApplication.getName();
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public PortletApplicationDefinition getPortletApplication() {
        return portletApplication;
    }

    public void setPortletApplication(PortletApplicationDefinition portletApplication) {
        this.portletApplication = portletApplication;
    }
}