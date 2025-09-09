/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.portlet;

import org.thymeleaf.TemplateEngine;


/**
 * This is a simple implementation of the {@link TemplateEngineSupplier} interface that can be used as a default.
 *
 * @author  Neil Griffin
 */
public class DefaultTemplateEngineSupplier implements TemplateEngineSupplier {

	private TemplateEngine templateEngine;

	public DefaultTemplateEngineSupplier(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Override
	public TemplateEngine get() {
		return templateEngine;
	}
}
