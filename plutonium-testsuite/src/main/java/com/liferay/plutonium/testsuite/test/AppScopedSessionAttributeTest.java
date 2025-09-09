/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite.test;

import jakarta.portlet.PortletSession;

import com.liferay.plutonium.testsuite.TestResult;
import com.liferay.plutonium.testsuite.TestUtils;

/**
 */
public class AppScopedSessionAttributeTest
extends AbstractReflectivePortletTest  {

    private static final String BOGUS_KEY = "com.liferay.plutonium.testsuite.BOGUS_KEY";
    private static final String KEY = "com.liferay.plutonium.testsuite.KEY";
    private static final String VALUE = "VALUE";


    // Test Methods ------------------------------------------------------------

    protected TestResult checkGetEmptyAppScopedAttribute(PortletSession session) {
        TestResult result = new TestResult();
        result.setDescription("Retrieve an attribute that has not been set "
        		+ "in the session's application scope "
        		+ "and ensure it's value is null.");
        result.setSpecPLT("15.3");

        Object value = session.getAttribute(BOGUS_KEY, PortletSession.APPLICATION_SCOPE);
        if (value == null) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("session attribute", value, null, result);
        }
        return result;
    }

    protected TestResult checkSetAppScopedAttribute(PortletSession session) {
        TestResult result = new TestResult();
        result.setDescription("Set an application scoped session attribute "
        		+ "and ensure it's retrievable.");
        result.setSpecPLT("15.3");

        session.setAttribute(KEY, VALUE, PortletSession.APPLICATION_SCOPE);
        Object value = session.getAttribute(KEY, PortletSession.APPLICATION_SCOPE);
        if (VALUE.equals(value)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("session attribute", value, VALUE, result);
        }
        return result;
    }

    protected TestResult checkRemoveAppScopedAttribute(PortletSession session) {
        TestResult result = new TestResult();
        result.setDescription("Remove an application scoped session attribute "
        		+ "and ensure it's null.");
        result.setSpecPLT("15.3");

        session.setAttribute(KEY, VALUE, PortletSession.APPLICATION_SCOPE);
        session.removeAttribute(KEY, PortletSession.APPLICATION_SCOPE);
        Object value = session.getAttribute(KEY, PortletSession.APPLICATION_SCOPE);
        if (value == null) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("session attribute", value, null, result);
        }
        return result;
    }

}
