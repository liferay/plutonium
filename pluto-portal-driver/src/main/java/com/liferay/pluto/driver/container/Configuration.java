/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Central location for Configuration info.
 *
 * @since Jul 2, 2005
 */
public class Configuration {

    private static final Logger LOG =
        LoggerFactory.getLogger(Configuration.class);

    public static final ResourceBundle BUNDLE =
        ResourceBundle.getBundle("com.liferay.pluto.driver.container.pluto-configuration");

    private static final String DESCRIPTOR_SERVICE =
        "com.liferay.pluto.descriptors.services.PortletAppDescriptorService";

    private static final String CONTAINER_RUNTIME_OPTIONS =
        "com.liferay.pluto.container.supportedContainerRuntimeOptions";

    /**
     * com.liferay.pluto.PREVENT_UNECESSARY_CROSS_CONTEXT
     */
    private static final String PREVENT_UNECESSARY_CROSS_CONTEXT =
        "com.liferay.pluto.PREVENT_UNECESSARY_CROSS_CONTEXT";

    /**
     * com.liferay.pluto.ALLOW_BUFFER
     */
    private static final String BUFFER_SUPPORT =
        "com.liferay.pluto.ALLOW_BUFFER";

    private static Boolean prevent;
    private static Boolean buffering;

    public static String getPortletAppDescriptorServiceImpl() {
        String impl = BUNDLE.getString(DESCRIPTOR_SERVICE);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Using Descriptor Service Impl: " + impl);
        }
        return impl;
    }

    public static boolean isBufferingSupported() {
        if (buffering == null) {
            try {
                String buffer = BUNDLE.getString(BUFFER_SUPPORT);
                buffering = new Boolean(buffer);
            } catch (MissingResourceException mre) {
                buffering = Boolean.FALSE;
            }
        }
        return buffering.booleanValue();
    }

    public static List<String> getSupportedContainerRuntimeOptions() {
        String options =  BUNDLE.getString(CONTAINER_RUNTIME_OPTIONS);
        List<String> result = new ArrayList<String>();
        String[] s = options.split(",");
        for (String string : s) {
            result.add(string);
        }
        return result;
    }

    public static boolean preventUnecessaryCrossContext() {
        if (prevent == null) {
            try {
                String test = BUNDLE.getString(PREVENT_UNECESSARY_CROSS_CONTEXT);
                prevent = new Boolean(test);
            } catch (MissingResourceException mre) {
                LOG.warn(mre.getMessage());
                prevent = Boolean.FALSE;
            }
        }
        return prevent.booleanValue();
    }

    /**
     * Retrieve the name of the container.
     * @return the container name.
     */
    public static final String getPortletContainerName() {
        return BUNDLE.getString("pluto.container.name");
    }

    /**
     * Retrieve the portlet container version.
     *
     * @return container version
     */
    public static final String getPortletContainerVersion() {
        return BUNDLE.getString("pluto.container.version");
    }

    /**
     * Retrieve the major version number of the specification which this version
     * of pluto supports.
     * @return te major specification version.
     */
    public static final int getMajorSpecificationVersion() {
        return Integer.parseInt(BUNDLE.getString("jakarta.portlet.version.major"));
    }

    /**
     * Retrieve the minor version number of the specification which this version
     * of pluto supports.
     * @return the minor specification version.
     */
    public static final int getMinorSpecificationVersion() {
        return Integer.parseInt(BUNDLE.getString("jakarta.portlet.version.minor"));
    }

    /**
     * Retrieve the formatted server info String required to be returned by the
     * PortletContext.
     * @return the server info.
     */
    public static final String getServerInfo() {
        StringBuffer sb = new StringBuffer(getPortletContainerName())
            .append("/")
            .append(getPortletContainerVersion());
        return sb.toString();
    }
}
