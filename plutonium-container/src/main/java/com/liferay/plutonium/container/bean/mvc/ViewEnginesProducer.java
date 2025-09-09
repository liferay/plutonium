/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.mvc.engine.ViewEngine;
import jakarta.portlet.PortletContext;
import jakarta.ws.rs.core.Configuration;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class ViewEnginesProducer {

	@ApplicationScoped
	@Produces
	@ViewEngines
	public List<ViewEngine> getViewEngines(BeanManager beanManager, PortletContext portletContext,
		Configuration configuration) {

		List<ViewEngine> viewEngines = BeanUtil.getBeanInstances(beanManager, ViewEngine.class);

		viewEngines.add(new ViewEngineJspImpl(configuration, portletContext));

		Collections.sort(viewEngines, new DescendingPriorityComparator(ViewEngine.PRIORITY_APPLICATION));

		return viewEngines;
	}

}
