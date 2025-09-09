/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.mvc.portlet.spring;

import java.util.HashSet;
import java.util.Set;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletContext;

import com.liferay.pluto.thymeleaf.portlet.VariableValidator;
import com.liferay.pluto.thymeleaf.portlet.VariableValidatorBase;


/**
 * This class provides a Spring implementation of the {@link com.liferay.pluto.thymeleaf.portlet.VariableValidator}
 * interface.
 *
 * @author  Neil Griffin
 */
public class SpringVariableValidator extends VariableValidatorBase {

	private static final Set<String> literalBeanNames;

	static {
		literalBeanNames = new HashSet<>();
		literalBeanNames.add("beanMethodDecorator");
		literalBeanNames.add("bindingResultImpl");
		literalBeanNames.add("configurationImpl");
		literalBeanNames.add("encodersImpl");
		literalBeanNames.add("getBooleanParam");
		literalBeanNames.add("getDateParam");
		literalBeanNames.add("getDoubleParam");
		literalBeanNames.add("getFloatParam");
		literalBeanNames.add("getIntegerParam");
		literalBeanNames.add("getLongParam");
		literalBeanNames.add("getMessageInterpolator");
		literalBeanNames.add("getParamConverterProviders");
		literalBeanNames.add("getStringParam");
		literalBeanNames.add("getTemplateEngineSupplier");
		literalBeanNames.add("getViewEngine");
		literalBeanNames.add("getWebContext");
		literalBeanNames.add("getValidator");
		literalBeanNames.add("localeResolverImpl");
		literalBeanNames.add("messageSource");
		literalBeanNames.add("paramConverterProviderImpl");
		literalBeanNames.add("springBeanPortletFilter");
		literalBeanNames.add("springPostProcessor");
		literalBeanNames.add("variableValidator");
		literalBeanNames.add("viewEngineContextInjectableImpl");
		literalBeanNames.add("viewEngineJspImpl");
		literalBeanNames.add("viewRenderer");
	}

	// Instance field must be declared volatile in order for the double-check idiom to work (requires JRE 1.5+)
	private volatile Boolean includeStandardBeans;

	private PortletConfig portletConfig;
	private PortletContext portletContext;

	public SpringVariableValidator(PortletConfig portletConfig, PortletContext portletContext) {
		this.portletConfig = portletConfig;
		this.portletContext = portletContext;
	}

	public PortletConfig getPortletConfig() {
		return portletConfig;
	}

	public PortletContext getPortletContext() {
		return portletContext;
	}

	@Override
	public boolean isIncludeStandardBeans() {

		Boolean include = this.includeStandardBeans;

		// First check without locking (not yet thread-safe)
		if (include == null) {

			synchronized (this) {
				include = this.includeStandardBeans;

				// Second check with locking (thread-safe)
				if (include == null) {

					PortletConfig portletConfig = getPortletConfig();
					String initParameterValue = portletConfig.getInitParameter(
							VariableValidator.INCLUDE_STANDARD_BEANS);

					boolean initParameterFlag = false;

					if (initParameterValue != null) {
						initParameterFlag = Boolean.valueOf(initParameterValue.toString());
					}

					PortletContext portletContext = getPortletContext();
					initParameterValue = portletContext.getInitParameter(VariableValidator.INCLUDE_STANDARD_BEANS);

					if (initParameterValue != null) {
						initParameterFlag = Boolean.valueOf(initParameterValue.toString());
					}

					include = this.includeStandardBeans = initParameterFlag;
				}
			}
		}

		return include;
	}

	@Override
	public boolean isValidName(String name, boolean headerPhase, boolean renderPhase, boolean resourcePhase) {

		boolean valid = super.isValidName(name, headerPhase, renderPhase, resourcePhase);

		if (!valid) {
			return false;
		}

		if (literalBeanNames.contains(name)) {
			return false;
		}

		if (name.endsWith("ConverterProvider") || name.startsWith("org.springframework") || name.endsWith("Producer") ||
				name.startsWith("scopedTarget.") || name.endsWith("VariableValidator")) {
			return false;
		}

		return true;
	}
}
