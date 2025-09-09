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
package com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.controller;

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
import jakarta.portlet.ResourceURL;
import jakarta.portlet.annotations.RenderMethod;
import jakarta.validation.executable.ExecutableType;
import jakarta.validation.executable.ValidateOnExecution;

import com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.dto.Applicant;
import com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.dto.Attachment;
import com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.service.ProvinceService;


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

			viewName = "applicant.html";

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

			// Thymeleaf
			models.put("autoFillResourceURL", ControllerUtil.createResourceURL(renderResponse, "autoFill"));
			models.put("deleteFileResourceURL", ControllerUtil.createResourceURL(renderResponse, "deleteFile"));
			models.put("mainFormActionURL", renderResponse.createActionURL());
			models.put("viewTermsResourceURL", ControllerUtil.createResourceURL(renderResponse, "viewTerms"));
			models.put("uploadFilesResourceURL", ControllerUtil.createResourceURL(renderResponse, "uploadFiles"));
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
