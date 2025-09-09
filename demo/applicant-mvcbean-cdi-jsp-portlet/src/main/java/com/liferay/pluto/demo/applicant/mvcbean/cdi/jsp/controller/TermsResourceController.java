/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.mvc.security.CsrfProtected;
import jakarta.portlet.ResourceParameters;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.ServeResourceMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@Controller
public class TermsResourceController {

	private static final Logger logger = LoggerFactory.getLogger(TermsResourceController.class);

	@Inject
	private Models models;

	@CsrfProtected
	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html", resourceID = "acceptTerms")
	public String acceptTerms(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		return "redirect:acceptance.jspx";
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html")
	public void redirectedView(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		ResourceParameters resourceParameters = resourceRequest.getResourceParameters();

		logger.debug("redirectedView=[{}]", resourceParameters.getValue("redirectedView"));
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html", resourceID = "viewTerms")
	@View("terms.jspx")
	public void viewTerms(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy");

		Calendar todayCalendar = Calendar.getInstance();

		models.put("thisYear", dateFormat.format(todayCalendar.getTime()));
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html", resourceID = "viewTermsAgain")
	@View("redirect:terms.jspx")
	public void viewTermsAgain(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		logger.debug("Redirecting to Terms of Service");
	}
}
