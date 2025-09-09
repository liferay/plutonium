/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import com.liferay.pluto.container.FilterManager;
import com.liferay.pluto.container.FilterManagerService;
import com.liferay.pluto.container.PortletWindow;

/**
 * @version $Id$
 *
 */
public class FilterManagerServiceImpl implements FilterManagerService {

    /**
     * @see com.liferay.pluto.container.FilterManagerService#getFilterManager(com.liferay.pluto.container.PortletWindow, java.lang.String)
     */
    public FilterManager getFilterManager(PortletWindow portletWindow, String lifeCycle) {
        return new FilterManagerImpl(portletWindow, lifeCycle);
    }
}
