/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.portlet;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.portlet.PortletConfig;

import org.thymeleaf.messageresolver.StandardMessageResolver;

import org.thymeleaf.templateresource.ITemplateResource;


/**
 * This is a convenient utility class that provides Thymeleaf with a way to resolve messages in a portlet application.
 *
 * @author  Neil Griffin
 */
public class PortletMessageResolver extends StandardMessageResolver {

	private PortletConfig portletConfig;

	public PortletMessageResolver(PortletConfig portletConfig) {
		this.portletConfig = portletConfig;
	}

	@Override
	protected Map<String, String> resolveMessagesForTemplate(String template, ITemplateResource templateResource,
		Locale locale) {

		Map<String, String> messages = super.resolveMessagesForTemplate(template, templateResource, locale);

		if (messages.isEmpty()) {

			ResourceBundle resourceBundle = portletConfig.getResourceBundle(locale);

			if (resourceBundle != null) {
				messages = new HashMap<>();

				Enumeration<String> resourceBundleKeys = resourceBundle.getKeys();

				while (resourceBundleKeys.hasMoreElements()) {
					String key = resourceBundleKeys.nextElement();
					Object value = resourceBundle.getObject(key);

					if ((key != null) && (value != null)) {
						messages.put(key, value.toString());
					}
				}
			}
		}

		return messages;
	}
}
