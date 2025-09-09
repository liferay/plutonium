/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.tags;

/**
 * Constant values as defined by the specification
 * typically to a request.
 * 
 * @version 1.0
 */
public class Constants {

    /**
     * The key used to bind the <code>PortletRequest</code> to the underlying
     * <code>HttpServletRequest</code>.
     */
    public final static String PORTLET_REQUEST = "jakarta.portlet.request";

    /**
     * The key used to bind the <code>PortletResponse</code> to the underlying
     * <code>HttpServletRequest</code>.
     */
    public final static String PORTLET_RESPONSE = "jakarta.portlet.response";

    /**
     * The key used to bind the <code>PortletConfig</code> to the underlying
     * PortletConfig.
     */
    public final static String PORTLET_CONFIG = "jakarta.portlet.config";
    
	public final static String ESCAPE_XML_RUNTIME_OPTION = "jakarta.portlet.escapeXml";

}
