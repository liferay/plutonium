/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import jakarta.servlet.ServletContext;

public interface ApplicationIdResolver {

    public static final int DECISIVE = 1;

    public static final int MANUAL = 2;

    public static final int DEFAULT = 3;

    /**
     * Resolve the applicationId for the given
     * context.
     *
     * @param context
     * @return
     */
    String resolveApplicationId(ServletContext context);

    /**
     * Retrive the degree of authority with which
     * the resolver speaks.
     * 
     * @return
     */
    int getAuthority();

}