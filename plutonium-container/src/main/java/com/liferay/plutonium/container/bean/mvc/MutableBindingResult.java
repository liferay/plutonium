/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.mvc.binding.BindingError;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ValidationError;


/**
 * @author  Neil Griffin
 */
public interface MutableBindingResult extends BindingResult {

	public void addBindingError(BindingError bindingError);

	public void addValidationError(ValidationError validationError);

	public boolean isConsulted();

}
