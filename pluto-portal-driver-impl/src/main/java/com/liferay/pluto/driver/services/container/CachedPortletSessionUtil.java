/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class contains a ClassLoader singleton {@link Map} named {@link #INVALIDATED_SESSIONS} that is used to work-
 * around a Tomcat issue such that invalidated {@link jakarta.servlet.http.HttpSession} objects are recycled in a cross-
 * context environment.
 */
public class CachedPortletSessionUtil {

	public static final Map<String, Boolean> INVALIDATED_SESSIONS = new ConcurrentHashMap<>();
}
