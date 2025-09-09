/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite;

import java.util.HashMap;
import java.util.Map;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

/**
 * @version 1.0
 * @since Mar 9, 2005
 */
public class NoOpTest implements PortletTest {

	/** The test configuration. */
    private TestConfig config;


    // Constructor -------------------------------------------------------------

    /**
     * Default no-arg constructor.
     */
    public NoOpTest() {
    	// Do nothing.
    }


    // PortletTest Impl --------------------------------------------------------

    public String getTestSuiteName() {
        return "NoOpTest";
    }

    public Map<String, String[]> getRenderParameters(PortletRequest request) {
        return new HashMap<String, String[]>();
    }

    public TestResults doTest(PortletConfig config,
                              PortletContext context,
                              PortletRequest request,
                              PortletResponse response) {
        return new TestResults("None");
    }

    public void init(TestConfig config) {
        this.config = config;
    }

    public TestConfig getConfig() {
        return config;
    }

    public void doHeaders(PortletConfig config, PortletContext context,
            RenderRequest request, RenderResponse response) {
        
    }

}

