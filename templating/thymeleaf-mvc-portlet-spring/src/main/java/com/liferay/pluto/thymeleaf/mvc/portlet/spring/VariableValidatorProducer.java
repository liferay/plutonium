/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.mvc.portlet.spring;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletContext;

import com.liferay.pluto.thymeleaf.portlet.VariableValidator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This class is a Spring producer that provides the ability to generate an instance of {@link VariableValidator}.
 *
 * @author  Neil Griffin
 */
@Configuration
public class VariableValidatorProducer {

	@Bean
	public VariableValidator getVariableValidator(PortletConfig portletConfig, PortletContext portletContext) {

		SpringVariableValidator springVariableValidator = new SpringVariableValidator(portletConfig, portletContext);

		return springVariableValidator;
	}
}
