/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import jakarta.servlet.ServletContext;

import com.liferay.pluto.container.RequestDispatcherService;
import com.liferay.pluto.container.driver.DriverPortletContext;
import com.liferay.pluto.container.impl.PortletContextImpl;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;

/**
 * Pluto's Portlet Context Implementation. This class implements the
 * <code>InternalPortletContext</code> which provides container specific
 * information needed for processing.
 * 
 * @version 1.1
 */
public class DriverPortletContextImpl extends PortletContextImpl implements DriverPortletContext {
	
    // Private Member Variables ------------------------------------------------
    
    protected ClassLoader contextClassLoader;

    // Constructor -------------------------------------------------------------
    
    /**
     * Constructs an instance.
     * @param servletContext  the servlet context in which we are contained.
     * @param portletApp  the portlet application descriptor.
     */
    public DriverPortletContextImpl(ServletContext servletContext,
                              PortletApplicationDefinition portletApp,
                              RequestDispatcherService rdService)
    {
        super(servletContext, portletApp, PlutoContainerInfo.getInfo(), Configuration.getSupportedContainerRuntimeOptions(), rdService);
        init();
    }
    
    private void init() {
        setContextClassLoader(Thread.currentThread().getContextClassLoader());
    }
    
    public String getApplicationName() {
        return portletApp.getName();
    }
    
    /**
     * ClassLoader associated with this context.
     * @return
     */
    public ClassLoader getContextClassLoader() {
        return contextClassLoader;
    }

    /**
     * ClassLoader associated with this context.
     * @param contextClassLoader
     */
    public void setContextClassLoader(ClassLoader contextClassLoader) {
        this.contextClassLoader = contextClassLoader;
    }
}

