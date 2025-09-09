/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.net.URI;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mvc.MvcContext;
import jakarta.mvc.locale.LocaleResolver;
import jakarta.mvc.security.Csrf;
import jakarta.mvc.security.Encoders;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.annotations.PortletRequestScoped;
import jakarta.servlet.http.Cookie;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.UriBuilder;


/**
 * @author  Neil Griffin
 */
@Named("mvc")
@PortletRequestScoped
public class MvcContextImpl implements MvcContext {

	@Inject
	private Configuration configuration;

	private Csrf csrf;

	@Inject
	private Encoders encoders;

	private Locale locale;

	@Inject
	private LocaleResolverChain localeResolverChain;

	@Inject
	private PortletContext portletContext;

	@Inject
	private PortletRequest portletRequest;

	@Override
	public String getBasePath() {
		return portletContext.getContextPath();
	}

	@Override
	public Configuration getConfig() {
		return configuration;
	}

	@Override
	public Csrf getCsrf() {
		return csrf;
	}

	@Override
	public Encoders getEncoders() {
		return encoders;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@PostConstruct
	public void postConstruct() {

		SpringCsrfToken springCsrfToken = new SpringCsrfToken(portletRequest.getAttribute(
					"org.springframework.security.web.csrf.CsrfToken"));

		csrf = new CsrfImpl(springCsrfToken.getName(), springCsrfToken.getValue());

		List<LocaleResolver> localeResolvers = localeResolverChain.getLocaleResolvers();

		Map<String, jakarta.ws.rs.core.Cookie> cookieMap = new HashMap<>();

		Cookie[] cookies = portletRequest.getCookies();

		for (Cookie cookie : cookies) {
			cookieMap.put(cookie.getName(), new jakarta.ws.rs.core.Cookie(cookie.getName(), cookie.getValue()));
		}

		Map<String, String> headerMap = new HashMap<>();

		Enumeration<String> propertyNames = portletRequest.getPropertyNames();

		while (propertyNames.hasMoreElements()) {
			String header = propertyNames.nextElement();
			headerMap.put(header, portletRequest.getProperty(header));
		}

		LocaleResolverContextImpl localeResolverContext = new LocaleResolverContextImpl(Collections.list(
					portletRequest.getLocales()), configuration, cookieMap, headerMap, new UriInfoImpl());

		for (LocaleResolver localeResolver : localeResolvers) {
			locale = localeResolver.resolveLocale(localeResolverContext);

			if (locale != null) {
				break;
			}
		}
	}

	@Override
	public URI uri(String identifier) {
		throw new UnsupportedOperationException();
	}

	@Override
	public URI uri(String identifier, Map<String, Object> params) {
		throw new UnsupportedOperationException();
	}

	@Override
	public UriBuilder uriBuilder(String s) {
		throw new UnsupportedOperationException();
	}
}
