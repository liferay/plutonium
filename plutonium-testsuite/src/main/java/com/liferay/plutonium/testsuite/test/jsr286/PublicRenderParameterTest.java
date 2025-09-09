/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite.test.jsr286;

import java.util.Map;

import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletResponse;

import com.liferay.plutonium.testsuite.TestResult;
import com.liferay.plutonium.testsuite.test.AbstractReflectivePortletTest;

/**
 * Testsuite test to test public render parmeters. 
 *
 *
 */
public class PublicRenderParameterTest extends AbstractReflectivePortletTest {
	public static final String TEST_PARAM = "public-render-param1";
	public static final String TEST_PARAM2 = "public-render-param2";
	public static final String TEST_PARAM_VALUE = "Passed";

	public TestResult checkPublicRenderParameter(PortletResponse response) {
		TestResult result = new TestResult();
        result.setDescription("Check the 286 Companion Portlet for evidence " +
        		"that this test passed.");
        result.setSpecPLT("11.1.2");
       	result.setReturnCode(TestResult.WARNING);
        return result;
    }

	@Override
	public Map<String, String[]> getRenderParameters(PortletRequest request) {
		Map<String, String[]> params = super.getRenderParameters(request);
		params.put(TEST_PARAM, new String[]{TEST_PARAM_VALUE});
		params.put(TEST_PARAM2, new String[]{TEST_PARAM_VALUE});
		return params;
	}
	
}
