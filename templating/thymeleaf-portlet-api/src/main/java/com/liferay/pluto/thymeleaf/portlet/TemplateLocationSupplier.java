/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.portlet;

import java.util.function.Supplier;

/**
 * This interface defines a contract for getting the location of a Thymeleaf template.
 *
 * @author  Neil Griffin
 */
@FunctionalInterface
public interface TemplateLocationSupplier extends Supplier<String>  {

	/**
	 * Returns the location of a Thymeleaf template.
	 */
	public String get();
}
