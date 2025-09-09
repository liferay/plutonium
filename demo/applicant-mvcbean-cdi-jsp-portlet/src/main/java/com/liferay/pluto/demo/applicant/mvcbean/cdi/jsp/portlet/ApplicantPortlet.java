/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.portlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.portlet.HeaderPortlet;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletException;
import jakarta.portlet.annotations.HeaderMethod;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.Preference;
import jakarta.portlet.annotations.Supports;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@PortletConfiguration(
	portletName = "portlet1", prefs = { @Preference(name = "datePattern", values = { "MM/dd/yyyy" }) },
	supports = { @Supports(portletModes = { "view", "edit", "help" }) }, resourceBundle = "content.portlet1"
)
public class ApplicantPortlet implements HeaderPortlet {

	@Override
	@HeaderMethod(portletNames = { "portlet1" })
	public void renderHeaders(HeaderRequest headerRequest, HeaderResponse headerResponse) throws PortletException,
		IOException {

		boolean liferayDetected = PortletContainer.LIFERAY.isDetected(headerRequest);
		boolean plutoDetected = PortletContainer.PLUTO.isDetected(headerRequest);

		if (plutoDetected) {
			headerResponse.addDependency("jQuery", "com.jquery", "1.12.1",
				"<script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>");
			headerResponse.addDependency("Bootstrap", "com.getbootstrap", "4.0.0",
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");

			PrintWriter writer = headerResponse.getWriter();
			writer.write(
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\"></meta>");
		}

		if (liferayDetected || plutoDetected) {
			headerResponse.addDependency("jQueryUI", "com.jquery", "1.12.1",
				"<script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>");
			headerResponse.addDependency("jQueryUICSS", "com.jquery", "1.12.1",
				"<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">");
		}
	}
}
