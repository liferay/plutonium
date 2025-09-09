/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;


/**
 * Service to retrieve a FilterManager for a specific Portlet in a lifecycle
 */
public interface FilterManagerService {

    /**
     * Returns the FilterManager, this is used to process the filter.
     * @param window The portlet window.
     * @return FilterManager
     */
    FilterManager getFilterManager(PortletWindow window, String lifeCycle);
}
