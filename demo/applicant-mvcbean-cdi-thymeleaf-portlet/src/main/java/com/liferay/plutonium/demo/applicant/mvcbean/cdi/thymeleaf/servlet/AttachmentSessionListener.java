/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.servlet;

import java.io.File;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@WebListener
public class AttachmentSessionListener implements HttpSessionListener {

	private static final Logger logger = LoggerFactory.getLogger(AttachmentSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

		HttpSession httpSession = httpSessionEvent.getSession();
		ServletContext servletContext = httpSession.getServletContext();
		File tempDir = (File) servletContext.getAttribute(ServletContext.TEMPDIR);

		File attachmentDir = new File(tempDir, httpSession.getId());

		if (attachmentDir.exists()) {

			File[] files = attachmentDir.listFiles();

			for (File file : files) {

				file.delete();

				logger.debug("Deleted file: " + file.getAbsolutePath());
			}
		}
	}
}
