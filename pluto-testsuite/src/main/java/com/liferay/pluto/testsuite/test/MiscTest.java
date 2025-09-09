/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletMode;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.WindowState;

import com.liferay.pluto.testsuite.TestResult;
import com.liferay.pluto.testsuite.TestUtils;

/**
 */
public class MiscTest extends AbstractReflectivePortletTest {

	// Test Methods ------------------------------------------------------------

    protected TestResult checkContextMajorVersion(PortletContext context) {
        TestResult result = new TestResult();
        result.setDescription("Ensure the expected major version number is returned.");

        String majorVersion = String.valueOf(context.getMajorVersion());
        ExpectedResults expectedResults = ExpectedResults.getInstance();
        String expected = expectedResults.getMajorVersion();
        if (majorVersion != null && majorVersion.equals(expected)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("major version", majorVersion, expected, result);
        }
        return result;
    }

    protected TestResult checkContextMinorVersion(PortletContext context) {
        TestResult result = new TestResult();
        result.setDescription("Ensure the expected minor version number is returned.");

        String minorVersion = String.valueOf(context.getMinorVersion());
        ExpectedResults expectedResults = ExpectedResults.getInstance();
        String expected = expectedResults.getMinorVersion();
        if (minorVersion != null && minorVersion.equals(expected)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("minor version", minorVersion, expected, result);
        }
        return result;
    }

    protected TestResult checkContextServerInfo(PortletContext context) {
        TestResult result = new TestResult();
        result.setDescription("Ensure the expected server info is returned.");

        String serverInfo = context.getServerInfo();
        ExpectedResults expectedResults = ExpectedResults.getInstance();
        String expected = expectedResults.getServerInfo();
        if (serverInfo != null && serverInfo.equals(expected)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("server info", serverInfo, expected, result);
        }
        return result;
    }

    protected TestResult checkPortalInfo(PortletRequest request) {
        TestResult result = new TestResult();
        result.setDescription("Ensure the expected portal info is returned.");

        String portalInfo = request.getPortalContext().getPortalInfo();
        ExpectedResults expectedResults = ExpectedResults.getInstance();
        String expected = expectedResults.getPortalInfo();
        if (portalInfo != null && portalInfo.equals(expected)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("portal info", portalInfo, expected, result);
        }
        return result;
    }

    /**
     * Test to ensure that the basic modes are supported.
     * @todo Enhance to check for custom modes.
     * @param req
     * @return
     */
    protected TestResult checkSupportedModes(PortletRequest request)  {
        TestResult result = new TestResult();
        result.setDescription("Ensure the expected portlet modes are returned.");

        List<PortletMode> requiredPortletModes = new ArrayList<PortletMode>();
        requiredPortletModes.add(PortletMode.VIEW);
        requiredPortletModes.add(PortletMode.EDIT);
        requiredPortletModes.add(PortletMode.HELP);

        for (Enumeration<PortletMode> en = 
        		request.getPortalContext().getSupportedPortletModes();
        		en.hasMoreElements(); ) {
            PortletMode portletMode = en.nextElement();
            requiredPortletModes.remove(portletMode);
        }

        if (requiredPortletModes.isEmpty()) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	result.setReturnCode(TestResult.FAILED);
        	StringBuffer buffer = new StringBuffer();
        	for (Iterator<PortletMode> it = requiredPortletModes.iterator();
        			it.hasNext(); ) {
        		buffer.append(it.next()).append(", ");
        	}
        	result.setResultMessage("Required portlet modes ["
        			+ buffer.toString() + "] are not supported.");
        }
        return result;
    }

    protected TestResult checkSupportedWindowSates(PortletRequest request) {
        TestResult result = new TestResult();
        result.setDescription("Ensure the expected window states are returned.");

        List<WindowState> requiredWindowStates = new ArrayList<WindowState>();
        requiredWindowStates.add(WindowState.MINIMIZED);
        requiredWindowStates.add(WindowState.MAXIMIZED);
        requiredWindowStates.add(WindowState.NORMAL);

        for (Enumeration<WindowState> en = 
        		request.getPortalContext().getSupportedWindowStates();
        		en.hasMoreElements(); ) {
            WindowState windowState = (WindowState) en.nextElement();
            requiredWindowStates.remove(windowState);
        }

        if (requiredWindowStates.isEmpty()) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	result.setReturnCode(TestResult.FAILED);
            StringBuffer buffer = new StringBuffer();
            for (Iterator<WindowState> it = requiredWindowStates.iterator();
            		it.hasNext(); ) {
            	buffer.append(it.next()).append(", ");
            }
            result.setResultMessage("Required window states ["
            		+ buffer.toString() + "] are not supported.");
        }
        return result;
    }

}
