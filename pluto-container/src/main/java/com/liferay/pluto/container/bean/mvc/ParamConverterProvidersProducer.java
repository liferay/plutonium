/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.util.Collections;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.ext.ParamConverterProvider;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class ParamConverterProvidersProducer {

	@Inject
	private BeanManager beanManager;

	private List<ParamConverterProvider> paramConverterProviders;

	@ApplicationScoped
	@ParamConverterProviders
	@Produces
	public List<ParamConverterProvider> getParamConverterProviders() {
		return paramConverterProviders;
	}

	@PostConstruct
	public void postConstruct() {
		paramConverterProviders = BeanUtil.getBeanInstances(beanManager, ParamConverterProvider.class);

		paramConverterProviders.add(new ParamConverterProviderImpl());

		Collections.sort(paramConverterProviders, new DescendingPriorityComparator());
	}

}
