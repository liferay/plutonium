/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.controller;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.portlet.PortletPreferences;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.annotations.RenderMethod;
import jakarta.validation.executable.ExecutableType;
import jakarta.validation.executable.ValidateOnExecution;

import com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.dto.Preferences;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@Controller
public class PreferencesRenderController {

	@Inject
	private Models models;

	@Inject
	private PortletPreferences portletPreferences;

	@RenderMethod(portletNames = { "portlet1" }, portletMode = "edit")
	@ValidateOnExecution(type = ExecutableType.NONE)
	@View("preferences.html")
	public void prepareView(RenderRequest renderRequest, RenderResponse renderResponse) {

		Map<String, Object> modelsMap = models.asMap();

		if (!modelsMap.containsKey("preferences")) {
			models.put("preferences", new Preferences(portletPreferences.getValue("datePattern", null)));
		}

		// Thymeleaf
		models.put("mainFormActionURL", renderResponse.createActionURL());
	}
}
