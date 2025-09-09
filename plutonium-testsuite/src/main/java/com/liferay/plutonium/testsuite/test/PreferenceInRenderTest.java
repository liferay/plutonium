/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite.test;

import java.io.IOException;

import jakarta.portlet.PortletPreferences;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.ValidatorException;

import com.liferay.plutonium.testsuite.TestResult;
import com.liferay.plutonium.testsuite.TestUtils;

public class PreferenceInRenderTest extends PreferenceCommonTest {
	
	// Test Methods ------------------------------------------------------------
	
	protected TestResult checkStorePreferences(PortletRequest request) {
		TestResult result = new TestResult();
		result.setDescription("Ensure that if the store() method is invoked "
				+ "within render() method, an IllegalStateException will be "
				+ "thrown out.");
		result.setSpecPLT("14.1");
		
		PortletPreferences preferences = request.getPreferences();
        boolean exceptionThrown = false;
        
        // Store preferences and wait for IllegalStateException.
        try {
            preferences.store();
        } catch (ValidatorException ex) {
        	TestUtils.failOnException("Unable to store preferences.", ex, result);
        	return result;
        } catch (IOException ex) {
        	TestUtils.failOnException("Unable to store preferences.", ex, result);
        	return result;
        } catch (IllegalStateException ex) {
        	exceptionThrown = true;
        }
		
        if (exceptionThrown) {
        	result.setReturnCode(TestResult.PASSED);
        } else {
        	result.setReturnCode(TestResult.FAILED);
        	result.setResultMessage("IllegalStateException is not thrown out "
        			+ "when store() method is invoked within render() method.");
        }
		return result;
	}
	
}
