/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.mvc.portlet;

import jakarta.mvc.engine.ViewEngine;
import jakarta.mvc.engine.ViewEngineContext;
import jakarta.mvc.engine.ViewEngineException;

import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.IWebContext;


/**
 * This class implements the MVC {@link ViewEngine} interface in order to help support Thymeleaf views in a portlet
 * application.
 *
 * @author  Neil Griffin
 */
public class ThymeleafViewEngine implements ViewEngine {

	private TemplateEngine templateEngine;
	private IWebContext webContext;
	private WriterSupplier writerSupplier;

	public ThymeleafViewEngine(TemplateEngine templateEngine, IWebContext webContext, WriterSupplier writerSupplier) {
		this.templateEngine = templateEngine;
		this.webContext = webContext;
		this.writerSupplier = writerSupplier;
	}

	@Override
	public void processView(ViewEngineContext viewEngineContext) throws ViewEngineException {
		templateEngine.process(viewEngineContext.getView(), webContext, writerSupplier.get(viewEngineContext));
	}

	@Override
	public boolean supports(String view) {

		if (view == null) {
			return false;
		}

		return view.endsWith(".html");
	}
}
