/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import com.liferay.pluto.container.NamespaceMapper;
import com.liferay.pluto.container.PortletWindowID;

/**
 * Default implementation of <code>NamespaceMapper</code> interface.
 */
public class DefaultNamespaceMapper implements NamespaceMapper {

    public DefaultNamespaceMapper() {
    	// Do nothing.
    }

    // NamespaceMapper Impl ----------------------------------------------------

    public String encode(PortletWindowID portletWindowId, String name) {
        StringBuffer buffer = new StringBuffer(50);
        buffer.append("Pluto_");
        buffer.append(portletWindowId.getStringId());
        buffer.append('_');
        buffer.append(name);
        return buffer.toString();
    }

    public String decode(PortletWindowID portletWindowId, String name) {
        if (!name.startsWith("Pluto_")) {
            return null;
        }
        StringBuffer buffer = new StringBuffer(50);
        buffer.append("Pluto_");
        buffer.append(portletWindowId.getStringId());
        buffer.append('_');
        if (!name.startsWith(buffer.toString())) {
            return null;
        }
        return name.substring(buffer.length());
    }
}
