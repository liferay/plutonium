/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.driver;

import jakarta.portlet.PortletConfig;

import com.liferay.pluto.container.om.portlet.PortletDefinition;

public interface DriverPortletConfig extends PortletConfig {

    PortletDefinition getPortletDefinition();
}


