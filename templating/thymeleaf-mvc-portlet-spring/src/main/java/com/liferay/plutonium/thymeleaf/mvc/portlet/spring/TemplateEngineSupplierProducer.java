/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.mvc.portlet.spring;

import jakarta.mvc.MvcContext;
import jakarta.mvc.engine.ViewEngine;
import jakarta.portlet.PortletConfig;
import jakarta.servlet.ServletContext;

import com.liferay.plutonium.thymeleaf.portlet.DefaultTemplateEngineSupplier;
import com.liferay.plutonium.thymeleaf.portlet.PortletMessageResolver;
import com.liferay.plutonium.thymeleaf.portlet.PortletTemplateResolver;
import com.liferay.plutonium.thymeleaf.portlet.TemplateEngineSupplier;
import com.liferay.plutonium.thymeleaf.portlet.TemplateLocationSupplier;

import org.springframework.beans.factory.BeanFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import org.thymeleaf.TemplateEngine;


/**
 * This class is a Spring producer that provides the ability to generate an instance of {@link TemplateEngineSupplier}.
 *
 * @author  Neil Griffin
 */
@Configuration
public class TemplateEngineSupplierProducer {

	@Bean
	@Scope(proxyMode = ScopedProxyMode.INTERFACES)
	public TemplateEngineSupplier getTemplateEngineSupplier(PortletConfig portletConfig, ServletContext servletContext,
		BeanFactory beanFactory) {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setMessageResolver(new PortletMessageResolver(portletConfig));
		 templateEngine.setTemplateResolver(new PortletTemplateResolver(
				new SpringConfigurationSupplier(beanFactory)));

		return new DefaultTemplateEngineSupplier(templateEngine);
	}

	private static class SpringConfigurationSupplier implements TemplateLocationSupplier {

		private BeanFactory beanFactory;

		public SpringConfigurationSupplier(BeanFactory beanFactory) {
			this.beanFactory = beanFactory;
		}

		@Override
		public String get() {
			MvcContext mvcContext = beanFactory.getBean(MvcContext.class);

			jakarta.ws.rs.core.Configuration configuration = mvcContext.getConfig();

			String templateLocation = (String) configuration.getProperty(ViewEngine.VIEW_FOLDER);

			if (templateLocation == null) {
				templateLocation = ViewEngine.DEFAULT_VIEW_FOLDER;
			}

			return templateLocation;
		}
	}
}
