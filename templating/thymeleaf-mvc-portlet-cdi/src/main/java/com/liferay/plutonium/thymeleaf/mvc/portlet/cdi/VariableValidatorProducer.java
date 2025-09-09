/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.mvc.portlet.cdi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletContext;

import com.liferay.plutonium.thymeleaf.portlet.VariableValidator;


/**
 * This class is a CDI producer that provides the ability to generate an instance of {@link VariableValidator}.
 *
 * @author  Neil Griffin
 */
@ApplicationScoped
public class VariableValidatorProducer {

	@Produces
	@ApplicationScoped
	public VariableValidator getVariableValidator(PortletConfig portletConfig, PortletContext portletContext) {

		boolean includeStandardBeans = false;

		String initParameter = portletConfig.getInitParameter(VariableValidator.INCLUDE_STANDARD_BEANS);

		if (initParameter != null) {
			includeStandardBeans = Boolean.valueOf(initParameter.toString());
		}

		initParameter = portletContext.getInitParameter(VariableValidator.INCLUDE_STANDARD_BEANS);

		if (initParameter != null) {
			includeStandardBeans = Boolean.valueOf(initParameter.toString());
		}

		return new CDIVariableValidator(includeStandardBeans);
	}
}
