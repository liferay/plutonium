/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.portals.pluto.demo.applicant.mvcbean.cdi.thymeleaf.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.mvc.security.CsrfProtected;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceParameters;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceURL;
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

		models.put("viewTermsAgainResourceURL", ControllerUtil.createResourceURL(resourceResponse, "viewTermsAgain"));

		return "redirect:acceptance.html";
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html")
	public void redirectedView(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		ResourceParameters resourceParameters = resourceRequest.getResourceParameters();

		logger.debug("redirectedView=[{}]", resourceParameters.getValue("redirectedView"));
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html", resourceID = "viewTerms")
	@View("terms.html")
	public void viewTerms(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		_populateViewTermsModel(resourceResponse);

		models.put("acceptTermsResourceURL", ControllerUtil.createResourceURL(resourceResponse, "acceptTerms"));
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html", resourceID = "viewTermsAgain")
	@View("redirect:terms.html")
	public void viewTermsAgain(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		logger.debug("Redirecting to Terms of Service");

		_populateViewTermsModel(resourceResponse);
	}

	private void _populateViewTermsModel(ResourceResponse resourceResponse) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy");

		Calendar todayCalendar = Calendar.getInstance();

		models.put("thisYear", dateFormat.format(todayCalendar.getTime()));

		// Thymeleaf
		models.put("acceptTermsResourceURL", ControllerUtil.createResourceURL(resourceResponse, "acceptTerms"));
	}
}
