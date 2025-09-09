/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.mvc.RedirectScoped;
import jakarta.mvc.binding.BindingError;
import jakarta.mvc.binding.ParamError;
import jakarta.mvc.binding.ValidationError;


/**
 * @author  Neil Griffin
 */
@RedirectScoped
public class BindingResultImpl implements MutableBindingResult, Serializable {

	private static final long serialVersionUID = 2217732469752342741L;

	private Set<BindingError> bindingErrors = new LinkedHashSet<>();
	private boolean consulted = false;
	private Set<ValidationError> validationErrors = new LinkedHashSet<>();

	@Override
	public void addBindingError(BindingError bindingError) {
		bindingErrors.add(bindingError);
	}

	@Override
	public void addValidationError(ValidationError validationError) {
		validationErrors.add(validationError);
	}

	public void clear() {
		bindingErrors.clear();
		validationErrors.clear();
		consulted = false;
	}

	@Override
	public Set<ParamError> getAllErrors() {

		consulted = true;

		Set<ParamError> allErrors = new LinkedHashSet<>();
		allErrors.addAll(bindingErrors);
		allErrors.addAll(validationErrors);

		return allErrors;
	}

	@Override
	public List<String> getAllMessages() {

		consulted = true;

		List<String> allMessages = new ArrayList<>();

		for (BindingError bindingError : bindingErrors) {
			allMessages.add(bindingError.getMessage());
		}

		for (ValidationError validationError : validationErrors) {
			allMessages.add(validationError.getMessage());
		}

		return allMessages;
	}

	@Override
	public Set<ParamError> getErrors(String paramName) {
		consulted = true;

		Set<ParamError> errors = new LinkedHashSet<>();

		for (BindingError bindingError : bindingErrors) {

			if (Objects.equals(bindingError.getParamName(), paramName)) {
				errors.add(bindingError);
			}
		}

		for (ValidationError validationError : validationErrors) {

			if (Objects.equals(validationError.getParamName(), paramName)) {
				errors.add(validationError);
			}
		}

		return errors;
	}

	public boolean isConsulted() {
		return consulted;
	}

	@Override
	public boolean isFailed() {
		consulted = true;

		return !bindingErrors.isEmpty() || !validationErrors.isEmpty();
	}
}
