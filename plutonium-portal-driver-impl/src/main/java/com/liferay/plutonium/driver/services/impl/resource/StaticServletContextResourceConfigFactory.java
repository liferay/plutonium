/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.impl.resource;

import com.liferay.plutonium.driver.config.DriverConfigurationException;
import com.liferay.plutonium.driver.PortalStartupListener;

import jakarta.servlet.ServletContext;
import java.io.InputStream;

/**
 * gross DI component to work around Spring limitations
 * @version $Rev$ $Date$
 */
public class StaticServletContextResourceConfigFactory
{

    private static ResourceConfig resourceConfig;

    private static void init(ServletContext servletContext)
    {
        try
        {
            InputStream in = servletContext.getResourceAsStream(ResourceConfigReader.CONFIG_FILE);
            resourceConfig = ResourceConfigReader.getFactory().parse(in);
            resourceConfig.initialized();    // Lets the resource config set defaults
        }
        catch (Exception e)
        {
            throw new DriverConfigurationException(e);
        }
    }

    public static synchronized ResourceConfig getResourceConfig()
    {
        if (resourceConfig == null)
        {
            init(PortalStartupListener.getServletContext());
        }
        return resourceConfig;
    }
}
