/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

import jakarta.servlet.http.HttpServlet;

/**
 * Dummy servlet to allow "legacy" portlets with web.xml files specifying PortletServlet to deploy properly.
 * The real portlet servlet is PortletServlet3, which is deployed through the servlet container
 * initializer.
 * 
 * @see PortletServlet3
 * @see PortletContainerInitializer
 * @author Scott Nicklous
 */
public class PortletServlet extends HttpServlet {
   private static final long serialVersionUID = -2550104981099637033L;

}
