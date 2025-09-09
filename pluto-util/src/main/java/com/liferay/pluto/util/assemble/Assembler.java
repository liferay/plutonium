/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.assemble;

import com.liferay.pluto.util.UtilityException;

/**
 * The pluto assembler is used to assemble a portlet application which is
 * deployable to pluto.
 *
 * @version 1.0
 * @since Oct 15, 2004
 */
public interface Assembler {

    /**
     * Name of the Portlet Application descriptor file relative
     * to the web application root, normally <code>WEB-INF/portlet.xml</code>.
     */
    String PORTLET_XML = "WEB-INF/portlet.xml";

    /**
     * Name of the Web Application descriptor file relative
     * to the web application root, normally <code>WEB-INF/web.xml</code>.
     */
    String SERVLET_XML = "WEB-INF/web.xml";

    /**
     * The fully qualified name of the class responsible for
     * dispatching requests from the Pluto container to portlets. 
     */
    String DISPATCH_SERVLET_CLASS =
        	"com.liferay.pluto.container.driver.PortletServlet";

    /**
     * Assemble a web application into a portlet web application which is
     * deployable into the pluto-1.1 portlet container. The specified web
     * application will be overwritten with the new application.
     */
    void assemble(AssemblerConfig config) throws UtilityException;

}

