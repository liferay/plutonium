/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.security;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.enterprise.inject.Vetoed;
import jakarta.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is automatically picked up by Spring due to the presence of the annotation-config and component-scan
 * elements in the Spring bean descriptor. Its purpose is to enable only the CSRF protection provided by Spring
 * Security.
 *
 * @author Neil Griffin
 */
@Configuration
@EnableWebSecurity
@Vetoed
public class PortalSecurityConfigurer {

	private static final RequestMatcher ACTION_REQUEST_MATCHER = new ActionRequestMatcher();

	@Autowired
	private ObjectFactory<HttpServletRequest> requestFactory;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();

		httpSecurity
			.csrf(csrf -> csrf
				.requireCsrfProtectionMatcher(ACTION_REQUEST_MATCHER)
				.csrfTokenRepository(new HttpSessionCsrfTokenRepository())
				.csrfTokenRequestHandler(requestAttributeHandler))
			.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(portletAccessDeniedHandler()))
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
			.addFilterBefore(new ContainerAuthBridgeFilter(), SecurityContextPersistenceFilter.class);

		return httpSecurity.build();
	}

	@Bean
	public AccessDeniedHandler portletAccessDeniedHandler() {
		return new AccessDeniedHandlerImpl();
	}

	private static class ActionRequestMatcher implements RequestMatcher {

		private static final Pattern ACTION_URL_PATTERN = Pattern.compile(".*[/]__ac[0-9]+.*");
		private static final Pattern AJAX_ACTION_URL_PATTERN = Pattern.compile(".*[/]__aa[0-9]+.*");
		private static final Pattern PARTIAL_ACTION_URL_PATTERN = Pattern.compile(".*[/]__pa[0-9]+.*");

		@Override
		public boolean matches(HttpServletRequest httpServletRequest) {

			String requestURI = httpServletRequest.getRequestURI();

			Matcher actionURLMatcher = ACTION_URL_PATTERN.matcher(requestURI);

			if (actionURLMatcher.matches()) {
				return true;
			}

			Matcher ajaxActionURLMatcher = AJAX_ACTION_URL_PATTERN.matcher(requestURI);

			if (ajaxActionURLMatcher.matches()) {
				return true;
			}

			Matcher partialActionURLMatcher = PARTIAL_ACTION_URL_PATTERN.matcher(requestURI);
			return partialActionURLMatcher.matches();
		}
	}
}
