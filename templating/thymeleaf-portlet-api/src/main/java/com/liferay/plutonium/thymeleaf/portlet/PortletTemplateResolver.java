/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.portlet;

import java.util.Map;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * This is a convenient utility class that provides Thymeleaf with a way to resolve templates in a portlet application.
 *
 * @author  Neil Griffin
 */
public class PortletTemplateResolver extends ClassLoaderTemplateResolver {

	private TemplateLocationSupplier templateLocationSupplier;

	public PortletTemplateResolver(TemplateLocationSupplier templateLocationSupplier) {
		this.templateLocationSupplier = templateLocationSupplier;
		setPrefix("templates/");
		setSuffix(".html");
		setTemplateMode("HTML");
	}

	@Override
	protected String computeResourceName(IEngineConfiguration engineConfiguration, String ownerTemplate,
		String template, String prefix, String suffix, boolean forceSuffix, Map<String, String> templateAliases,
		Map<String, Object> templateResolutionAttributes) {

		String resourceName = super.computeResourceName(engineConfiguration, ownerTemplate, template, prefix, suffix,
				forceSuffix, templateAliases, templateResolutionAttributes);

		if (resourceName.startsWith("/")) {
			return resourceName;
		}

		String templateLocation = templateLocationSupplier.get();

		if (!templateLocation.endsWith("/")) {
			templateLocation = templateLocation.concat("/");
		}

		return templateLocation.concat(resourceName);
	}
}
