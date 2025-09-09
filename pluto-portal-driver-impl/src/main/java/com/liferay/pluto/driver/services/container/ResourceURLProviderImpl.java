/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import jakarta.servlet.http.HttpServletRequest;

import com.liferay.pluto.container.PortletWindow;
import com.liferay.pluto.container.ResourceURLProvider;

public class ResourceURLProviderImpl implements ResourceURLProvider {

    private String stringUrl = "";
    private String base = "";

    public ResourceURLProviderImpl(HttpServletRequest req,
                                   PortletWindow portletWindow) {
    }

    public void setAbsoluteURL(String path) {
        stringUrl = path;
    }

    public void setFullPath(String path) {
        stringUrl = base + path;
    }

    public String toString() {
        return (stringUrl == null) ? "" : stringUrl;

    }
}
