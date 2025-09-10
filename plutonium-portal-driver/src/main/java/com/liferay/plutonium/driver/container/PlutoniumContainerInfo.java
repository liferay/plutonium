/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.container;

import com.liferay.plutonium.container.ContainerInfo;

/**
 * @version $Id$
 *
 */
public final class PlutoniumContainerInfo implements ContainerInfo
{
    private static final ContainerInfo instance = new PlutoniumContainerInfo();
    
    private PlutoniumContainerInfo()
    {
    }
    
    public static ContainerInfo getInfo()
    {
        return instance;
    }
    /**
     * Retrieve the name of the container.
     * @return the container name.
     */
    public String getPortletContainerName() {
        return Configuration.getPortletContainerName();
    }

    /**
     * Retrieve the portlet container version.
     *
     * @return container version
     */
    public String getPortletContainerVersion() {
        return Configuration.getPortletContainerVersion();
    }

    /**
     * Retrieve the major version number of the specification which this version
     * of plutonium supports.
     * @return te major specification version.
     */
    public int getMajorSpecificationVersion() {
        return Configuration.getMajorSpecificationVersion();
    }

    /**
     * Retrieve the minor version number of the specification which this version
     * of plutonium supports.
     * @return the minor specification version.
     */
    public int getMinorSpecificationVersion() {
        return Configuration.getMinorSpecificationVersion();
    }

    /**
     * Retrieve the formatted server info String required to be returned by the
     * PortletContext.
     * @return the server info.
     */
    public String getServerInfo() {
        return Configuration.getServerInfo();
    }
}
