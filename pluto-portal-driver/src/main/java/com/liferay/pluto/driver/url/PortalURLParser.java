/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.url;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: ddewolf
 * Date: Sep 4, 2006
 * Time: 5:49:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PortalURLParser {
    PortalURL parse(HttpServletRequest request);
    String toString(PortalURL portalURL);
}
