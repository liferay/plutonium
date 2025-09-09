/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.jsp.controller;

import java.io.File;
import java.util.List;
import java.util.Set;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.mvc.security.CsrfProtected;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.PortletSession;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.PortletRequestScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;

import com.liferay.plutonium.demo.applicant.mvcbean.cdi.jsp.dto.Applicant;
import com.liferay.plutonium.demo.applicant.mvcbean.cdi.jsp.dto.Attachment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@Controller
@PortletRequestScoped // Injection of the @Dependent pseudo-scoped @BeanPram Applicant requires this controller to be
					  // @PortletRequestScoped instead of @ApplicationScoped.
public class ApplicantActionController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicantActionController.class);

	@Inject
	@BeanParam
	@Valid
	private Applicant applicant;

	@Inject
	private AttachmentManager attachmentManager;

	@Inject
	private BindingResult bindingResult;

	@Inject
	private Models models;

	@ActionMethod(portletName = "portlet1", actionName = "submitApplicant")
	@CsrfProtected
	public String submitApplicant(ActionRequest actionRequest, ActionResponse actionResponse) {

		models.put("applicant", applicant);

		Set<ParamError> bindingErrors = bindingResult.getAllErrors();

		if (bindingErrors.isEmpty()) {

			PortletSession portletSession = actionRequest.getPortletSession();
			List<Attachment> attachments = attachmentManager.getAttachments(portletSession.getId());

			if (logger.isDebugEnabled()) {
				logger.debug("firstName=" + applicant.getFirstName());
				logger.debug("lastName=" + applicant.getLastName());
				logger.debug("emailAddress=" + applicant.getEmailAddress());
				logger.debug("phoneNumber=" + applicant.getPhoneNumber());
				logger.debug("dateOfBirth=" + applicant.getDateOfBirth());
				logger.debug("city=" + applicant.getCity());
				logger.debug("provinceId=" + applicant.getProvinceId());
				logger.debug("postalCode=" + applicant.getPostalCode());
				logger.debug("comments=" + applicant.getComments());
			}

			for (Attachment attachment : attachments) {

				logger.debug("attachment=[{}]", attachment.getName());

				File file = attachment.getFile();
				file.delete();
			}

			return "redirect:confirmation.jspx";
		}

		return null;
	}
}
