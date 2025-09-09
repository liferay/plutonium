/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class ParamConverterProviderManager {

	private static List<ParamConverterProvider> paramConverterProviders;

	@Inject
	private BeanManager _beanManager;

	public <T> ParamConverter getParamConverter(Class<?> rawType, Type baseType, Annotation[] annotations) {

		for (ParamConverterProvider paramConverterProvider : paramConverterProviders) {
			ParamConverter<T> paramConverter = paramConverterProvider.getConverter((Class<T>) rawType, baseType,
					annotations);

			if (paramConverter != null) {
				return paramConverter;
			}
		}

		return null;
	}

	@PostConstruct
	public void postConstruct() {

		paramConverterProviders = BeanUtil.getBeanInstances(_beanManager, ParamConverterProvider.class);

		Collections.sort(paramConverterProviders, new DescendingPriorityComparator());
	}
}
