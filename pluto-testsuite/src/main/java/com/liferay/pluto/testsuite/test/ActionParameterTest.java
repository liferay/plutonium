/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test;

import java.util.Enumeration;
import java.util.Map;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.PortletRequest;

import com.liferay.pluto.testsuite.TestResult;
import com.liferay.pluto.testsuite.TestUtils;
import com.liferay.pluto.testsuite.annotations.DefaultTestPhase;

/**
 */
@DefaultTestPhase(PortletRequest.ACTION_PHASE)
public class ActionParameterTest extends AbstractReflectivePortletTest {

	/** Parameter key encoded in the action URL. */
    public static final String KEY = "com.liferay.pluto.testsuite.PARAM_ACTION_KEY";

    /** Parameter value encoded in the action URL. */
    public static final String VALUE = "com.liferay.pluto.testsuite.ACTION_VALUE";


    // Test Methods ------------------------------------------------------------

    protected TestResult checkGetActionParameter(ActionRequest request) {
        TestResult result = new TestResult();
        result.setDescription("Ensure parameters encoded in action URL are "
        		+ "available in the action request.");

        String value = request.getParameter(KEY);
        if (value != null && value.equals(VALUE)) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("parameter", value, VALUE, result);
        }
        return result;
    }

    protected TestResult checkGetActionParamerMap(ActionRequest request) {
        TestResult result = new TestResult();
        result.setDescription("Ensure parameters encoded in action URL are "
        		+ "available in the action request parameter map.");

        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] values = parameterMap.get(KEY);
        if (values != null && values.length == 1 && VALUE.equals(values[0])) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	TestUtils.failOnAssertion("parameter values",
        			values, new String[] { VALUE }, result);
        }
        return result;
    }

    protected TestResult checkParameterNames(ActionRequest request) {
        TestResult result = new TestResult();
        result.setDescription("Ensure parameters encoded in action URL "
        		+ "exists in the parameter name enumeration.");

        boolean hasParameterName = false;
        for (Enumeration<String> en = request.getParameterNames();
        		!hasParameterName && en.hasMoreElements(); ) {
        	String name = en.nextElement();
        	if (KEY.equals(name)) {
        		hasParameterName = true;
        	}
        }

        if (hasParameterName) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	result.setReturnCode(TestResult.FAILED);
        	result.setResultMessage("Parameter name " + KEY
        			+ " not found in parameter name enumeration.");
        }
        return result;
    }

}
