/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import java.util.Collection;

import jakarta.portlet.PortletMode;

/**
 * @author Scott Nicklous
 */
public interface PortletHeaderResponseContext extends PortletMimeResponseContext
{
    void setTitle(String title);
    void setNextPossiblePortletModes(Collection<PortletMode> portletModes);
    void addDependency(String name, String scope, String version);
    void addDependency(String name, String scope, String version, String markup);
}
