/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import com.liferay.plutonium.container.FilterManager;
import com.liferay.plutonium.container.FilterManagerService;
import com.liferay.plutonium.container.PortletWindow;

/**
 * @version $Id$
 *
 */
public class FilterManagerServiceImpl implements FilterManagerService {

    /**
     * @see com.liferay.plutonium.container.FilterManagerService#getFilterManager(com.liferay.plutonium.container.PortletWindow, java.lang.String)
     */
    public FilterManager getFilterManager(PortletWindow portletWindow, String lifeCycle) {
        return new FilterManagerImpl(portletWindow, lifeCycle);
    }
}
