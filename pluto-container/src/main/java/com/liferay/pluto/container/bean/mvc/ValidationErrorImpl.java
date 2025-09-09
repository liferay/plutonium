/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import jakarta.enterprise.context.Dependent;
import jakarta.mvc.binding.ValidationError;
import jakarta.validation.ConstraintViolation;


/**
 * @author  Neil Griffin
 */
@Dependent
public class ValidationErrorImpl extends ParamErrorImpl implements ValidationError {

	private ConstraintViolation constraintViolation;

	public ValidationErrorImpl(String paramName, String message, ConstraintViolation constraintViolation) {
		super(paramName, message);
		this.constraintViolation = constraintViolation;
	}

	@Override
	public ConstraintViolation<?> getViolation() {
		return constraintViolation;
	}
}
