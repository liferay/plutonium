/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.portlet.PortletPreferences;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class DateParamConverterProvider implements ParamConverterProvider {

	@Inject
	protected PortletPreferences portletPreferences;

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type baseType, Annotation[] annotations) {

		if (rawType == null) {
			return null;
		}

		if (rawType.equals(Date.class)) {
			return (ParamConverter<T>) new DateParamConverter(portletPreferences);
		}

		return null;
	}
}
