/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.mvc.portlet.cdi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.mvc.MvcContext;
import jakarta.mvc.engine.ViewEngine;
import jakarta.portlet.PortletConfig;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.core.Configuration;

import com.liferay.pluto.thymeleaf.portlet.DefaultTemplateEngineSupplier;
import com.liferay.pluto.thymeleaf.portlet.PortletMessageResolver;
import com.liferay.pluto.thymeleaf.portlet.PortletTemplateResolver;
import com.liferay.pluto.thymeleaf.portlet.TemplateEngineSupplier;
import com.liferay.pluto.thymeleaf.portlet.TemplateLocationSupplier;

import org.thymeleaf.TemplateEngine;


/**
 * This class is a CDI producer that provides the ability to generate an instance of {@link TemplateEngineSupplier}.
 *
 * @author  Neil Griffin
 */
public class TemplateEngineSupplierProducer {

	@ApplicationScoped
	@Produces
	public TemplateEngineSupplier getTemplateEngineSupplier(PortletConfig portletConfig, ServletContext servletContext,
															MvcContext mvcContext) {

		TemplateEngine templateEngine = new TemplateEngine();

		templateEngine.setMessageResolver(new PortletMessageResolver(portletConfig));

		Configuration configuration = mvcContext.getConfig();

		String templateLocation = (String) configuration.getProperty(ViewEngine.VIEW_FOLDER);

		if (templateLocation == null) {
			templateLocation = ViewEngine.DEFAULT_VIEW_FOLDER;
		}

		templateEngine.setTemplateResolver(new PortletTemplateResolver(
				new CDITemplateLocationSupplier(templateLocation)));

		return new DefaultTemplateEngineSupplier(templateEngine);
	}

	private static class CDITemplateLocationSupplier implements TemplateLocationSupplier {

		private String templateLocation;

		public CDITemplateLocationSupplier(String templateLocation) {
			this.templateLocation = templateLocation;
		}

		@Override
		public String get() {
			return templateLocation;
		}
	}
}
