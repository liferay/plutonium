/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.inject.Vetoed;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;


/**
 * @author  Neil Griffin
 */
@Vetoed
public class BeanUtil {

	public static <T> List<T> getBeanInstances(BeanManager beanManager, Class<T> type, Annotation... qualifiers) {

		List<T> beanInstances = new ArrayList<>();

		if (qualifiers == null) {
			qualifiers = new Annotation[] {};
		}

		Set<Bean<?>> beans = beanManager.getBeans(type, qualifiers);

		for (Bean<?> bean : beans) {
			beanInstances.add((T) beanManager.getReference(bean, type, beanManager.createCreationalContext(bean)));
		}

		return beanInstances;
	}
}
