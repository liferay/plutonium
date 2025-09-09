/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container;

/**
 * Information about the container.
 */
public interface ContainerInfo {

    String getServerInfo();
    String getPortletContainerName();
    String getPortletContainerVersion();
    int getMajorSpecificationVersion();
    int getMinorSpecificationVersion();
}
