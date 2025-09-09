/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.lang.reflect.Method;

import jakarta.enterprise.inject.Vetoed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@Vetoed
public class SpringCsrfToken {

	private static final Logger LOG = LoggerFactory.getLogger(SpringCsrfToken.class);

	private String name;
	private String value;

	public SpringCsrfToken(Object springCsrfToken) {

		if (springCsrfToken != null) {

			Class<?> springCsrfTokenClass = springCsrfToken.getClass();

			try {
				Method getParameterNameMethod = springCsrfTokenClass.getMethod("getParameterName", new Class[] {});
				getParameterNameMethod.setAccessible(true);
				name = (String) getParameterNameMethod.invoke(springCsrfToken, new Object[] {});

				Method getTokenMethod = springCsrfTokenClass.getMethod("getToken", null);
				getTokenMethod.setAccessible(true);
				value = (String) getTokenMethod.invoke(springCsrfToken, null);
			}
			catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
