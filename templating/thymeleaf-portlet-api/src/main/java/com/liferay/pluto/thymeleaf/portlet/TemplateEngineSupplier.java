/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.portlet;

import org.thymeleaf.TemplateEngine;

import java.util.function.Supplier;


/**
 * This interface defines a contract for getting a Thymeleaf template engine.
 *
 * @author  Neil Griffin
 */
@FunctionalInterface
public interface TemplateEngineSupplier extends Supplier<TemplateEngine> {

	/**
	 * Returns a Thymeleaf template engine.
	 */
	public TemplateEngine get();
}
