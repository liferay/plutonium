/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

import com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.dto.Attachment;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class AttachmentManager {

	@Inject
	private ServletContext servletContext;

	public File getAttachmentDir(String sessionId) {

		File tempDir = (File) servletContext.getAttribute(ServletContext.TEMPDIR);

		return new File(tempDir, sessionId);
	}

	public List<Attachment> getAttachments(String sessionId) {
		return getAttachments(getAttachmentDir(sessionId));
	}

	public List<Attachment> getAttachments(File attachmentDir) {

		List<Attachment> attachments = new ArrayList<>();

		if (attachmentDir.exists()) {

			File[] files = attachmentDir.listFiles();

			for (File file : files) {
				attachments.add(new Attachment(file));
			}
		}

		return attachments;
	}
}
