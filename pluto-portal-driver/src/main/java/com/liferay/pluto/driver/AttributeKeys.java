/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver;

/**
 * Constants used as attribute keys to bind values to servlet context or servlet
 * request.
 *
 * @version 1.0
 * @since Sep 25, 2004
 */
public class AttributeKeys {

    /**
     * Attribute Key used to bind the application's driver config to the
     * ServletContext.
     */
    public static final String DRIVER_CONFIG = "driverConfig";

    /**
     * Attribute Key used to bind the application's driver admin config
     * to the ServletContext.
     */
    public static final String DRIVER_ADMIN_CONFIG = "driverAdminConfig";

    /**
     * Attribute Key used to bind the application's portlet container to the
     * ServletContext.
     */
    public static final String PORTLET_CONTAINER = "portletContainer";

    /** Attribute key used to bind the current page to servlet request. */
    public static final String CURRENT_PAGE = "currentPage";

    /** 
     * Attribute key used to bind the portlet title to servlet request. 
     * The attribute object maps the portlet window ID to the title. 
     */
    public static final String PORTLET_TITLE =
    		"com.liferay.pluto.driver.DynamicPortletTitle";

    public static final String PORTAL_URL_PARSER = "PORTAL_URL_PARSER";
    
    /**
     * For passing the head section markup collected from the portlets 
     * to the aggregation JSP.
     */
    public static final String HEAD_SECTION_MARKUP = "headMarkup";
    
    /**
     * For including resources that were generated dynamically
     */
    public static final String DYNAMIC_PAGE_RESOURCES = "dynamicResources";

    // Constructor -------------------------------------------------------------

    /**
     * Private constructor that prevents external instantiation.
     */
    private AttributeKeys() {

    }
}

