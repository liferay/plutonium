/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test;

import java.io.IOException;

import jakarta.portlet.PortletResponse;
import jakarta.portlet.RenderResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.pluto.testsuite.TestResult;
import com.liferay.pluto.testsuite.TestUtils;

/**
 * FIXME: separate the two check methods to two classes.
 *
 */
public class RenderResponseNoContentTypeTest
extends AbstractReflectivePortletTest {

	private static final Logger LOG = LoggerFactory.getLogger(RenderResponseNoContentTypeTest.class);

	/**
	 * Overwrite super implementation to return null. This test requires that
	 * content type of the render response is not set.
	 * @return <code>null</code>.
	 */
	public String getRenderResponseContentType() {
		return null;
	}

	// Test Methods ------------------------------------------------------------

	protected TestResult checkGetPortletOutputStream(PortletResponse response) {
		TestResult result = new TestResult();
		result.setDescription("Ensure If the getPortletOutputStream() method "
				+ "is called before the setContentType() method, it will throw "
				+ "an IllegalStateException.");
		result.setSpecPLT("12.3.1");

		RenderResponse renderResponse = (RenderResponse) response;
		ensureContentTypeNotSet(renderResponse, result);
		if (result.getReturnCode() == TestResult.WARNING) {
			return result;
		}

		try {
			renderResponse.getPortletOutputStream();
			result.setReturnCode(TestResult.FAILED);
			result.setResultMessage("Method getPortletOutputStream() didn't "
					+ "throw an IllegalStateException when content type is "
					+ "not set before.");
		} catch (IllegalStateException ex) {
			// We are expecting this exception!
			result.setReturnCode(TestResult.PASSED);
		} catch (IOException ex) {
			TestUtils.failOnException("Method getPortletOutputStream() throws "
					+ "an unexpected IOException", ex, result);
		}
		return result;
	}

	protected TestResult checkGetWriter(PortletResponse response) {
		TestResult result = new TestResult();
		result.setDescription("Ensure If the getWriter() method "
				+ "is called before the setContentType() method, it will throw "
				+ "an IllegalStateException.");
		result.setSpecPLT("12.3.1");

		RenderResponse renderResponse = (RenderResponse) response;
		ensureContentTypeNotSet(renderResponse, result);
		if (result.getReturnCode() == TestResult.WARNING) {
			return result;
		}

		try {
			renderResponse.getWriter();
			result.setReturnCode(TestResult.FAILED);
			result.setResultMessage("Method getWriter() didn't "
					+ "throw an IllegalStateException when content type is "
					+ "not set before.");
		} catch (IllegalStateException ex) {
			// We are expecting this exception!
			result.setReturnCode(TestResult.PASSED);
		} catch (IOException ex) {
			TestUtils.failOnException("Method getWriter() throws "
					+ "an unexpected IOException", ex, result);
		}
		return result;
	}


	// Private Methods ---------------------------------------------------------

	/**
	 * Ensures that the content type of the current render response is not set.
	 * If the content type is already set, this method sets the test result to
	 * WARNING with a warning message. Otherwise, this method does nothing.
	 * @param response  the render response to check.
	 * @param result  the test result.
	 */
	private void ensureContentTypeNotSet(RenderResponse response,
	                                     TestResult result) {
		String contentType = response.getContentType();
		if (contentType != null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Unable to run test: content type is already set ("
						+ contentType + ").");
			}
        	result.setReturnCode(TestResult.WARNING);
        	result.setResultMessage("The content type of the render response "
        			+ "is not as expected (" + contentType + " != null).");
		}
	}

}
