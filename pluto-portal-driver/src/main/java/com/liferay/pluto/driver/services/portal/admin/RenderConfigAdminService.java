/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.portal.admin;

import com.liferay.pluto.driver.config.DriverConfigurationException;
import com.liferay.pluto.driver.services.portal.PageConfig;

/**
 *
 *
 * @author <a href="mailto:ddewolf@apache.org">David H. DeWolf</a>:
 * @version 1.0
 * @since Nov 30, 2005
 */
public interface RenderConfigAdminService {

    void addPage(PageConfig config) throws DriverConfigurationException;

    void removePage(PageConfig pageConfig) throws DriverConfigurationException;
    
}
