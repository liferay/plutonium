/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.enterprise.context.Dependent;
import jakarta.mvc.locale.LocaleResolverContext;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.UriInfo;


/**
 * @author  Neil Griffin
 */
@Dependent
public class LocaleResolverContextImpl implements LocaleResolverContext {

	private List<Locale> acceptableLanguages;
	private Configuration configuration;
	private Map<String, Cookie> cookies;
	private Map<String, String> headers;
	private UriInfo uriInfo;

	public LocaleResolverContextImpl(List<Locale> acceptableLanguages, Configuration configuration,
		Map<String, Cookie> cookies, Map<String, String> headers, UriInfo uriInfo) {
		this.acceptableLanguages = acceptableLanguages;
		this.configuration = configuration;
		this.cookies = cookies;
		this.headers = headers;
		this.uriInfo = uriInfo;
	}

	@Override
	public List<Locale> getAcceptableLanguages() {
		return acceptableLanguages;
	}

	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public Cookie getCookie(String name) {
		return cookies.get(name);
	}

	@Override
	public String getHeaderString(String name) {
		return headers.get(name);
	}

	@Override
	public Request getRequest() {
		return new RequestImpl();
	}

	@Override
	public UriInfo getUriInfo() {
		return uriInfo;
	}
}
