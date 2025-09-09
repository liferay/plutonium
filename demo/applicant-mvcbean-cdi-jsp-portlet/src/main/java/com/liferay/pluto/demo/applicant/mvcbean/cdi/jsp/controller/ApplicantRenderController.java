/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.engine.ViewEngineContext;
import jakarta.portlet.PortletPreferences;
import jakarta.portlet.PortletSession;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.annotations.RenderMethod;
import jakarta.validation.executable.ExecutableType;
import jakarta.validation.executable.ValidateOnExecution;

import com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.dto.Applicant;
import com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.dto.Attachment;
import com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.service.ProvinceService;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@Controller
public class ApplicantRenderController {

	@Inject
	private AttachmentManager attachmentManager;

	@Inject
	private Models models;

	@Inject
	private PortletPreferences portletPreferences;

	@Inject
	private ProvinceService provinceService;

	@Inject
	private ViewEngineContext viewEngineContext;

	@RenderMethod(portletNames = { "portlet1" })
	@ValidateOnExecution(type = ExecutableType.NONE)
	public String prepareView(RenderRequest renderRequest, RenderResponse renderResponse) {

		String viewName = viewEngineContext.getView();

		if (viewName == null) {

			viewName = "applicant.jspx";

			Applicant applicant = (Applicant) models.get("applicant");

			if (applicant == null) {
				applicant = new Applicant();
				models.put("applicant", applicant);
			}

			PortletSession portletSession = renderRequest.getPortletSession();
			List<Attachment> attachments = attachmentManager.getAttachments(portletSession.getId());
			applicant.setAttachments(attachments);

			String datePattern = portletPreferences.getValue("datePattern", null);

			models.put("jQueryDatePattern", _getJQueryDatePattern(datePattern));

			models.put("provinces", provinceService.getAllProvinces());

			CDI<Object> currentCDI = CDI.current();
			BeanManager beanManager = currentCDI.getBeanManager();
			Class<? extends BeanManager> beanManagerClass = beanManager.getClass();
			Package beanManagerPackage = beanManagerClass.getPackage();
			models.put("weldVersion", beanManagerPackage.getImplementationVersion());
		}

		return viewName;
	}

	private String _getJQueryDatePattern(String datePattern) {

		String jQueryDatePattern = datePattern;

		if (datePattern.contains("yyyy")) {
			jQueryDatePattern = datePattern.replaceAll("yyyy", "yy");
		}
		else if (datePattern.contains("yy")) {
			jQueryDatePattern = datePattern.replaceAll("yy", "y");
		}

		jQueryDatePattern = jQueryDatePattern.replaceAll("M", "m");

		return jQueryDatePattern;
	}
}
