/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.el;

/**
 * @author  Neil Griffin
 */
class Expression {

	private final String base;
	private final String property;

	public Expression(String expression) {

		int pos = expression.indexOf(".");

		if (pos < 0) {
			base = null;
			property = expression;
		}
		else {
			base = expression.substring(0, pos);
			property = expression.substring(pos + 1);
		}
	}

	public String getBase() {
		return base;
	}

	public String getProperty() {
		return property;
	}
}
