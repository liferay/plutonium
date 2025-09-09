/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.util.Collections;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.mvc.locale.LocaleResolver;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class LocaleResolverChain {

	private static List<LocaleResolver> localeResolvers;

	@Inject
	private BeanManager _beanManager;

	public List<LocaleResolver> getLocaleResolvers() {
		return localeResolvers;
	}

	@PostConstruct
	private void postConstruct() {

		localeResolvers = BeanUtil.getBeanInstances(_beanManager, LocaleResolver.class);

		Collections.sort(localeResolvers, new DescendingPriorityComparator(1000));
	}
}
