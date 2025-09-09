/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

/**
 * The portlet window ID.
 * @version 1.0
 */
public interface PortletWindowID {

	/**
	 * Returns the unique string ID of the portlet window.
	 * <p>
	 * Depending on the implementation of <code>toString()</code> is dangerous,
	 * because the original implementation in <code>Object</code> is not
	 * qualified.
	 * </p>
	 * @return the unique string ID of the portlet window.
	 */
	String getStringId();

}
