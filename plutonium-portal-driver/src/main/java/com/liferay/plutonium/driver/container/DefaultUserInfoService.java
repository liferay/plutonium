/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.container;

import java.util.Collections;
import java.util.Map;

import jakarta.portlet.PortletRequest;

import com.liferay.plutonium.container.PortletContainerException;
import com.liferay.plutonium.container.PortletWindow;
import com.liferay.plutonium.container.UserInfoService;

/**
 * UserInfo
 * TODO: no real implementation yet
 *
 */
public class DefaultUserInfoService implements UserInfoService {

    public Map<String, String> getUserInfo(PortletRequest request, PortletWindow window)
        throws PortletContainerException {
        if ( request.getRemoteUser() != null ) {
            return Collections.emptyMap();
        }
        return null;
    }
}