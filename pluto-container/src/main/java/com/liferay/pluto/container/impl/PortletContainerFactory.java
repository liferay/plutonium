/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.pluto.container.ContainerServices;
import com.liferay.pluto.container.PortletContainer;
import com.liferay.pluto.container.PortletContainerException;
import com.liferay.pluto.container.util.ArgumentUtility;

/**
 * Factory used to create new PortletContainer instances.  The factor constructs
 * the underlying pluto container implementation by using the the given
 * container services.
 *
 * @version 1.0
 * @since Sep 18, 2004
 * @deprecated just use blueprint or spring to construct the PortletContainer
 */
public class PortletContainerFactory {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(PortletContainerFactory.class);


    /** Singleton instance of the <code>PortletContainerFactory</code>. */
    private static final PortletContainerFactory FACTORY =
    		new PortletContainerFactory();

    /**
     * Accessor method for the singleton instance of the
     * <code>PortletContainerFactory</code>.
     * @return singleton instance of the PortletContainerFactory
     */
    public static PortletContainerFactory getInstance() {
    	return FACTORY;
    }

    /**
     * Private constructor that prevents external instantiation.
     */
    private PortletContainerFactory() {
    	// Do nothing.
    }

    /**
     * Create a container with the given containerName, initialized from the given
     * servlet config, and using the given container services.
     * @param containerName  the name of the portlet container.
     * @param requiredServices  the required portlet container services.
     * @return newly created PortletContainer instance.
     * @throws PortletContainerException
     */
    public PortletContainer createContainer(
    		String containerName,
    		ContainerServices requiredServices)
    throws PortletContainerException {
        ArgumentUtility.validateNotNull("requiredServices", requiredServices);
        ArgumentUtility.validateNotEmpty("containerName", containerName);

        PortletContainer container = new PortletContainerImpl(
        		containerName, requiredServices);

        if (LOG.isInfoEnabled()) {
            LOG.info("Portlet Container [" + containerName + "] created.");
        }
        return container;
    }
}

