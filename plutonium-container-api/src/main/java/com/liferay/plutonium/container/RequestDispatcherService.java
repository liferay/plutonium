/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container;

import jakarta.portlet.PortletRequestDispatcher;
import jakarta.servlet.ServletContext;

import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;

/**
 * Service to retrieve a RequestDispatcher and corresponding HttpServletRequest and HttpServletResponse wrappers
 * for usage by the PortletContext and to support custom Servlet Request dispatching.
 * 
 * @version $Id$
 */
public interface RequestDispatcherService
{
    PortletRequestDispatcher getRequestDispatcher(ServletContext servletContext, PortletApplicationDefinition app,
                                                  String path);

    PortletRequestDispatcher getNamedDispatcher(ServletContext servletContext, PortletApplicationDefinition app,
                                                String name);
}
