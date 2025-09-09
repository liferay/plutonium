/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.jsp.el;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;


/**
 * @author  Neil Griffin
 */
@Named
@ApplicationScoped
public class Fields {

	@Inject
	private BindingResult bindingResult;

	public String error(String field) {

		StringBuilder sb = new StringBuilder();
		Collection<String> errors = errors(field);

		boolean first = true;

		for (String error : errors) {

			if (first) {
				first = false;
			}
			else {
				sb.append(" ");
			}

			sb.append(error);
		}

		return sb.toString();
	}

	public Collection<String> errors(String field) {

		if (field == null) {
			return null;
		}

		Set<String> errors = new LinkedHashSet<>();

		Set<ParamError> bindingResultErrors = bindingResult.getErrors(field);

		for (ParamError bindingResultError : bindingResultErrors) {
			errors.add(bindingResultError.getMessage());
		}

		return errors;
	}

	public boolean hasErrors(String field) {

		if (field == null) {
			return false;
		}

		Set<ParamError> bindingResultErrors = bindingResult.getErrors(field);

		return !bindingResultErrors.isEmpty();
	}
}
