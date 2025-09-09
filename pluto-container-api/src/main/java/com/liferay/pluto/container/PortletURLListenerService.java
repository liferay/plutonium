/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import java.util.List;

import jakarta.portlet.PortletURLGenerationListener;

import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;

public interface PortletURLListenerService
{
    List<PortletURLGenerationListener> getPortletURLGenerationListeners(PortletApplicationDefinition app);
}
