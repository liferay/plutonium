/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.enterprise.inject.Vetoed;
import jakarta.mvc.security.Csrf;


/**
 * @author  Neil Griffin
 */
@Vetoed
public class CsrfImpl implements Csrf {

	private String name;
	private String token;

	public CsrfImpl(String name, String token) {
		this.name = name;
		this.token = token;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getToken() {
		return token;
	}
}
