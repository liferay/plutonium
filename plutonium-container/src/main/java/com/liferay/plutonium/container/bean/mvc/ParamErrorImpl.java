/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.enterprise.context.Dependent;
import jakarta.mvc.binding.ParamError;


/**
 * @author  Neil Griffin
 */
@Dependent
public class ParamErrorImpl implements ParamError {

	private final String message;
	private final String paramName;

	public ParamErrorImpl(String paramName, String message) {
		this.paramName = paramName;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getParamName() {
		return paramName;
	}
}
