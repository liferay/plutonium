/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.portlet;

import java.util.Locale;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.thymeleaf.context.IWebContext;


/**
 * This is a convenient abstract base class that partially implements the {@link IWebContext} interface from Thymeleaf.
 * Sub-classes must implement the {@link #containsVariable(String)}, {@link #getVariableNames()}, and
 * {@link #getVariable(String)} methods.
 *
 * @author  Neil Griffin
 */
public abstract class WebContextBase implements IWebContext {

	private final HttpServletRequest httpServletRequest;
	private final HttpServletResponse httpServletResponse;
	private final Locale locale;
	private final ServletContext servletContext;

	public WebContextBase(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		ServletContext servletContext, Locale locale) {

		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;
		this.locale = locale;
		this.servletContext = servletContext;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

}
