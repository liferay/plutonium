/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContainerAuthBridgeFilter extends GenericFilterBean {

	private static final String[] containerRoles = { "pluto", "tckuser" };

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		if (SecurityContextHolder.getContext().getAuthentication() == null && request.getUserPrincipal() != null) {

			String username = request.getUserPrincipal().getName();
			List<GrantedAuthority> authorities = new ArrayList<>();

			for (String role : containerRoles) {
				if (request.isUserInRole(role)) {
					authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
				}
			}

			PreAuthenticatedAuthenticationToken auth =
				new PreAuthenticatedAuthenticationToken(username, "N/A", authorities);
			auth.setAuthenticated(true);

			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		chain.doFilter(req, res);
	}
}