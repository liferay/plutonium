/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.container;

import jakarta.servlet.ServletContext;

/**
 <pre>
      <context-param>
      <param-name>com.liferay.plutonium.CONTEXT_PATH</param-name>
      <param-value>/path</param-value>
    </context-param>
 </pre>
 */
public class InitParameterApplicationIdResolver implements ApplicationIdResolver {

    public static final String CONTEXT_PATH_PARAM = "com.liferay.plutonium.CONTEXT_PATH";

    public String resolveApplicationId(ServletContext context) {
        return context.getInitParameter(CONTEXT_PATH_PARAM);
    }

    public int getAuthority() {
        return MANUAL;
    }
}