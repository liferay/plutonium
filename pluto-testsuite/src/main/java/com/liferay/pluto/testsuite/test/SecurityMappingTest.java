/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test;

import jakarta.portlet.PortletRequest;

import com.liferay.pluto.testsuite.TestResult;

/**
 */
public class SecurityMappingTest extends AbstractReflectivePortletTest {

    // Test Methods ------------------------------------------------------------

    protected TestResult checkIsUserInMappedRole(PortletRequest request) {
        TestResult result = isUserLoggedIn(request);
        result.setDescription("Test if user is in mapped role.");
        if (result.getReturnCode() == TestResult.WARNING) {
            return result;
        }

        ExpectedResults expectedResults = ExpectedResults.getInstance();
        String role = expectedResults.getMappedSecurityRole();
        if (request.isUserInRole(role)) {
            result.setReturnCode(TestResult.PASSED);
        } else {
        	result.setReturnCode(TestResult.WARNING);
        	result.setResultMessage("User is not in the expected role: " + role
        			+ ". This may be due to misconfiuration.");
        }
        return result;
    }

    protected TestResult checkIsUserInUnmappedRole(PortletRequest request) {
        TestResult result = isUserLoggedIn(request);
        result.setDescription("Test if user is in unmapped role");
        if (result.getReturnCode() == TestResult.WARNING) {
            return result;
        }

        ExpectedResults expectedResults = ExpectedResults.getInstance();
        String role = expectedResults.getUnmappedSecurityRole();
        if (request.isUserInRole(role)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	result.setReturnCode(TestResult.WARNING);
        	result.setResultMessage("User is not in the expected role: " + role
        			+ ". This may be due to misconfiuration.");
        }
        return result;
    }

    protected TestResult checkIsUserIndUndeclaredRole(PortletRequest request) {
        TestResult result = isUserLoggedIn(request);
        result.setDescription("Test if user is in undeclared role");
        if (result.getReturnCode() == TestResult.WARNING) {
            return result;
        }

        String fakeRole = "fakeTestRoleFooBar";
        if (!request.isUserInRole(fakeRole)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	result.setReturnCode(TestResult.FAILED);
        	result.setResultMessage("User is in the fake role named " + fakeRole);
        }
        return result;
    }


    // Private Methods ---------------------------------------------------------

    private TestResult isUserLoggedIn(PortletRequest request) {
    	TestResult result = new TestResult();
        if (request.getRemoteUser() == null) {
            result.setReturnCode(TestResult.WARNING);
            result.setResultMessage("User is not logged in.");
        }
        return result;
    }
}
