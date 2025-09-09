/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.portlet;

/**
 * Defines a contract for validating {@link org.thymeleaf.context.WebContext} variable names during a portlet request.
 *
 * @author  Neil Griffin
 */
public interface VariableValidator {

	/**
	 * Context initialization parameter that defines whether or not standard portlet beans should be added as variables
	 * to the current {@link org.thymeleaf.context.WebContext}.
	 */
	public static final String INCLUDE_STANDARD_BEANS = "com.liferay.pluto.thymeleaf.portlet.INCLUDE_STANDARD_BEANS";

	/**
	 * Determines whether or not the specified variable name is valid for the current portlet request considering the
	 * specified flags. If valid, then the variable name and value should be added to the associated {@link
	 * org.thymeleaf.context.WebContext}.
	 */
	public boolean isValidName(String name, boolean headerPhase, boolean renderPhase, boolean resourcePhase);
}
