/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.el;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mvc.Models;
import jakarta.portlet.PortletPreferences;

import com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.convert.DateParamConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@Named
public class Converter {

	private static final Logger logger = LoggerFactory.getLogger(Converter.class);

	@Inject
	private PortletPreferences portletPreferences;

	@Inject
	private Models models;

	public Object convert(String exp) {

		if (exp == null) {
			return null;
		}

		Expression expression = new Expression(exp);

		Object base = models.get(expression.getBase());

		if (base == null) {
			return null;
		}

		Class<?> baseClass = base.getClass();

		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(expression.getProperty(), baseClass);
			Method readMethod = propertyDescriptor.getReadMethod();

			Object invoke = readMethod.invoke(base);

			if (invoke == null) {
				return null;
			}

			DateParamConverter dateParamConverter = new DateParamConverter(portletPreferences);

			return dateParamConverter.toString((Date) invoke);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}
}
