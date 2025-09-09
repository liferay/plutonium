/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite;

import java.util.Map;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

/**
 * Interface for plutonium portlet test classes.
 *
 */
public interface PortletTest {

	/**
	 * Returns the test suite name.
	 * @return the test suite name.
	 */
    public String getTestSuiteName();

    /**
     * Initializes the portlet test using test configuration.
     * @param config  the test configuration.
     */
    public void init(TestConfig config);

    /**
     * Returns the render parameters. This method will be invoked in
     * <code>Portlet.processAction()</code> method. All parameters returned
     * by this method will be set as render parameters.
     *
     * @param request  the portlet request.
     * @return a map of render parameters, key is the string name of the
     *         parameter, value is a string array.
     */
    public Map<String, String[]> getRenderParameters(PortletRequest request);

    /**
     * Runs the test.
     * @param config  the portlet config.
     * @param context  the portlet context.
     * @param request  the portlet request.
     * @param response  the portlet response.
     * @return the results of the test.
     */
    public TestResults doTest(PortletConfig config,
                              PortletContext context,
                              PortletRequest request,
                              PortletResponse response);

    /**
     * Returns the test configuration.
     * @return the test configuration.
     */
    public TestConfig getConfig();

    /**
     * Called by TestPortlet.doHeaders()
     * @param config
     * @param context
     * @param request
     * @param response
     */
    public void doHeaders(PortletConfig config,
                          PortletContext context,
                          RenderRequest request,
                          RenderResponse response);
    
}

