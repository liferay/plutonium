/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import java.util.ResourceBundle;

/**
 * Utility class used to retrieve environment information from the
 * <code>environment.properties</code> file packaged with pluto.
 */
final class Environment {

    /**
     * Properties Resource Bundle containing pluto environment information.
     */
    public static final ResourceBundle PROPS;

    static {
        PROPS = ResourceBundle.getBundle("com.liferay.pluto.environment");
    }


    /**
     * Retrieve the name of the container.
     * @return the container name.
     */
    public static final String getPortletContainerName() {
        return PROPS.getString("pluto.container.name");
    }

    /**
     * Retrieve the major version number.
     * @return the major version number.
     * @deprecated
     */
    public static final String getPortletContainerMajorVersion() {
        String version = getPortletContainerVersion();
        return version.substring(0, version.indexOf("."));
    }

    /**
     * Retrieve the minor version number.
     * @return the minor version number.
     * @deprecated
     */
    public static final String getPortletContainerMinorVersion() {
        String version = getPortletContainerVersion();
        return version.substring(version.indexOf("."));
    }

    /**
     * Retrieve the portlet container version.
     *
     * @return container version
     */
    public static final String getPortletContainerVersion() {
        return PROPS.getString("pluto.container.version");
    }

    /**
     * Retrieve the major version number of the specification which this version
     * of pluto supports.
     * @return te major specification version.
     */
    public static final int getMajorSpecificationVersion() {
        return Integer.parseInt(PROPS.getString("jakarta.portlet.version.major"));
    }

    /**
     * Retrieve the minor version number of the specification which this version
     * of pluto supports.
     * @return the minor specification version.
     */
    public static final int getMinorSpecificationVersion() {
        return Integer.parseInt(PROPS.getString("jakarta.portlet.version.minor"));
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
