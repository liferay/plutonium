/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Produces;
import jakarta.interceptor.Interceptor;
import jakarta.servlet.ServletContext;


/**
 * @author  Neil Griffin
 */
@Alternative
@ApplicationScoped
@Priority(Interceptor.Priority.APPLICATION + 10)
public class ServletContextProducer {

	private Set<ServletContext> servletContexts = new HashSet<>();

	public void applicationScopedInitialized(
		@Initialized(ApplicationScoped.class)
		@Observes ServletContext servletContext) {
		servletContexts.add(servletContext);
	}

	@Produces
	public ServletContext getServletContext() {

		Thread currentThread = Thread.currentThread();
		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		for (ServletContext servletContext : servletContexts) {

			if (contextClassLoader.equals(servletContext.getClassLoader())) {
				return servletContext;
			}
		}

		return null;
	}
}
