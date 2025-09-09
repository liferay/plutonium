/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test;

import java.io.IOException;

import jakarta.portlet.PortletSession;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.liferay.pluto.testsuite.TestResult;

/**
 */
public class ExternalAppScopedAttributeTest 
extends AbstractReflectivePortletTest {

    public static final String INT_KEY = "com.liferay.pluto.testsuite.INTERNALLY_SET_APP_SCOPED_SESSION_TEST_KEY";
    public static final String EXT_KEY = "com.liferay.pluto.testsuite.EXTERNALLY_SET_APP_SCOPED_SESSION_TEST_KEY";
    public static final String VALUE = "Should be visible to all Portlets and Web Resources.";


    // Test Methods ------------------------------------------------------------

    protected TestResult checkSetAppScopedAttributeHereSeenElsewhere(
    		PortletSession session) {
        TestResult result = new TestResult();
        result.setDescription("Ensure application scoped attributes set here "
        		+ "in portlet session can be seen elsewhere.");

        session.setAttribute(INT_KEY, VALUE, PortletSession.APPLICATION_SCOPE);
        result.setReturnCode(TestResult.WARNING);
        result.setResultMessage("Click the provided link to validate test.");
        return result;
    }

    protected TestResult checkSetAppScopedAttributeElsewhereSeenHere(
    		PortletSession session) {
        TestResult result = new TestResult();
        result.setDescription("Ensure application scoped attributes set "
        		+ "elsewhere in portlet session can be seen here.");

        Object value = session.getAttribute(EXT_KEY,
                                            PortletSession.APPLICATION_SCOPE);
        if (VALUE.equals(value)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	result.setReturnCode(TestResult.WARNING);
        	result.setResultMessage("This test will not pass until you have "
        			+ "opened the external resource using the link provided below.");
        }
        return result;
    }


    // Nested Servlet Class ----------------------------------------------------

    /**
     * The companion servlet that cooperates with this portlet test.
     */
    public static class CompanionServlet extends HttpServlet {

		private static final long serialVersionUID = 8921101365853307609L;

		public void doGet(HttpServletRequest request,
                          HttpServletResponse response)
        throws ServletException, IOException {
            HttpSession session = request.getSession();
            String value = (String) session.getAttribute(INT_KEY);
            if (ExternalAppScopedAttributeTest.VALUE.equals(value)) {
            	request.setAttribute("passed", new Boolean(true));
                session.setAttribute(EXT_KEY, VALUE);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(
            		"/jsp/ExternalAppScopedAttributeTest_companion.jsp");
            dispatcher.forward(request, response);
        }

    }

}
