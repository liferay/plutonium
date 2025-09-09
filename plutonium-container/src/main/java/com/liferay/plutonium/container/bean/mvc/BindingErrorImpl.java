/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.mvc.binding.BindingError;


/**
 * @author  Neil Griffin
 */
public class BindingErrorImpl extends ParamErrorImpl implements BindingError {

	private String submittedValue;

	public BindingErrorImpl(String paramName, String message, String submittedValue) {
		super(paramName, message);
		this.submittedValue = submittedValue;
	}

	@Override
	public String getSubmittedValue() {
		return submittedValue;
	}
}
