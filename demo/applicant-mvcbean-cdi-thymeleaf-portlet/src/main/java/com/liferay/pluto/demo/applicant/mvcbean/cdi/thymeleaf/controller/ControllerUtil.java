/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.controller;

import jakarta.portlet.MimeResponse;
import jakarta.portlet.ResourceURL;


/**
 * @author  Neil Griffin
 */
public class ControllerUtil {

	public static String createResourceURL(MimeResponse mimeResponse, String resourceID) {
		ResourceURL resourceURL = mimeResponse.createResourceURL();
		resourceURL.setResourceID(resourceID);

		return resourceURL.toString();
	}
}
