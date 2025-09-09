/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.mvc.portlet.spring;

import java.io.IOException;

import jakarta.mvc.engine.ViewEngine;
import jakarta.mvc.engine.ViewEngineException;
import jakarta.portlet.MimeResponse;

import com.liferay.pluto.thymeleaf.mvc.portlet.ThymeleafViewEngine;
import com.liferay.pluto.thymeleaf.portlet.TemplateEngineSupplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import org.thymeleaf.context.IWebContext;


/**
 * This class is a Spring producer that provides the ability to generate instances of {@link ViewEngine}.
 *
 * @author  Neil Griffin
 */
@Configuration
public class ViewEngineProducer {

	@Bean
	@Scope(proxyMode = ScopedProxyMode.INTERFACES, value = "portletRequest")
	public ViewEngine getViewEngine(TemplateEngineSupplier templateEngineSupplier, IWebContext webContext) {
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
