/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.v3;

/**
 * Utility class for {@link ActEvtProxyServlet} and {@link TagLibPortlet}.
 */
public class ParamUtil {
	private static final String JSPOBJ     = "/WEB-INF/jsp/tagLibObjects.jsp";
	private static final String JSPBEANS   = "/WEB-INF/jsp/tagLibBeans.jsp";
	public static final String  TEST_BEAN  = "bean";

	/**
	 * for selecting the JSP based on parameter value
	 */
	public static String getJsp(String ttype) {
		String jsp = JSPOBJ;
		if (ttype != null && ttype.equals(TEST_BEAN)) {
			jsp = JSPBEANS;
		}
		return jsp;
	}
}
