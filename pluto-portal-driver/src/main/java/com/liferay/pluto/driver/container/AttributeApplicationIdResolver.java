/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import jakarta.servlet.ServletContext;

public class AttributeApplicationIdResolver implements ApplicationIdResolver {

    public static final String CONTEXT_PATH_PARAM = "com.liferay.pluto.CONTEXT_PATH";

    public String resolveApplicationId(ServletContext context) {
        return (String)context.getAttribute(CONTEXT_PATH_PARAM);
    }


    public int getAuthority() {
        return MANUAL;
    }
}