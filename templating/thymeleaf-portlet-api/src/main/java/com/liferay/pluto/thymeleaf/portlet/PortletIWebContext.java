/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.portlet;

import jakarta.portlet.PortletContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.web.IWebExchange;

import java.util.Locale;
import java.util.Set;

/**
 * @author Neil Griffin
 */
public class PortletIWebContext implements IWebContext {

	private PortletContext portletContext;
	public PortletIWebContext(PortletContext portletContext) {
		this.portletContext = portletContext;
	}

	@Override
	public IWebExchange getExchange() {
		return null;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public boolean containsVariable(String name) {
		return false;
	}

	@Override
	public Set<String> getVariableNames() {
		return Set.of();
	}

	@Override
	public Object getVariable(String name) {
		return null;
	}
}
