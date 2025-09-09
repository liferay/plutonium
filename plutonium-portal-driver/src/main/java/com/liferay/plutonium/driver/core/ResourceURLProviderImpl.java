/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.core;

import java.net.MalformedURLException;
import java.net.URL;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.plutonium.container.PortletWindow;
import com.liferay.plutonium.container.ResourceURLProvider;

public class ResourceURLProviderImpl implements ResourceURLProvider {

    private static final Logger LOG =
        LoggerFactory.getLogger(ResourceURLProviderImpl.class);

    private String stringUrl = "";
    private String base = "";

    public ResourceURLProviderImpl(HttpServletRequest req,
                                   PortletWindow portletWindow) {
        PortalRequestContext ctx = PortalRequestContext.getContext(req);

        this.base = ctx.createPortalURL().getServerURI();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Resource URL Created with base: " + base);
        }
    }

    public void setAbsoluteURL(String path) {
        stringUrl = path;
    }

    public void setFullPath(String path) {
        stringUrl = base + path;
    }

    public String toString() {
        URL url = null;

        if (!"".equals(stringUrl)) {
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                throw new java.lang.IllegalArgumentException(
                    "A malformed URL has occured");
            }
        }

        return ((url == null) ? "" : url.toString());

    }
}
