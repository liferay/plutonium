/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.mvc.portlet.cdi;

import java.io.IOException;

import jakarta.enterprise.inject.Produces;
import jakarta.mvc.engine.ViewEngine;
import jakarta.mvc.engine.ViewEngineException;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.annotations.PortletRequestScoped;

import com.liferay.plutonium.thymeleaf.mvc.portlet.ThymeleafViewEngine;
import com.liferay.plutonium.thymeleaf.portlet.TemplateEngineSupplier;

import org.thymeleaf.context.IWebContext;


/**
 * This class is a CDI producer that provides the ability to generate instances of {@link ViewEngine}.
 *
 * @author  Neil Griffin
 */
public class ViewEngineProducer {

	@PortletRequestScoped
	@Produces
	public ViewEngine getTimeleafViewEngine(TemplateEngineSupplier templateEngineSupplier, IWebContext webContext) {
		return new ThymeleafViewEngine(templateEngineSupplier.get(), webContext,
				viewEngineContext -> {
					MimeResponse mimeResponse = viewEngineContext.getResponse(MimeResponse.class);

					try {
						return mimeResponse.getWriter();
					}
					catch (IOException e) {
						throw new ViewEngineException(e);
					}
				});
	}
}
