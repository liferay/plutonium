/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import jakarta.enterprise.context.Dependent;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.metadata.ConstraintDescriptor;


/**
 * @author  Neil Griffin
 */
@Dependent
public class MessageInterpolatorContextImpl implements MessageInterpolator.Context {

	private ConstraintViolation<?> constraintViolation;

	public MessageInterpolatorContextImpl(ConstraintViolation<?> constraintViolation) {
		this.constraintViolation = constraintViolation;
	}

	@Override
	public ConstraintDescriptor<?> getConstraintDescriptor() {
		return constraintViolation.getConstraintDescriptor();
	}

	@Override
	public Object getValidatedValue() {
		return constraintViolation.getInvalidValue();
	}

	@Override
	public <T> T unwrap(Class<T> aClass) {
		throw new UnsupportedOperationException();
	}
}
