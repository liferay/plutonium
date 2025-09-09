/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletContext;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Feature;


/**
 * @author  Neil Griffin
 */
@Dependent
public class ConfigurationImpl implements Configuration {

	public static final String DEFAULT_VIEW_EXTENSION = "com.liferay.pluto.defaultViewExtension";

	private Map<String, Object> properties;

	@Inject
	private PortletConfig portletConfig;

	@Inject
	private PortletContext portletContext;

	@Override
	public Set<Class<?>> getClasses() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<Class<?>, Integer> getContracts(Class<?> componentClass) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Object> getInstances() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> getProperties() {

		if (properties == null) {

			properties = new HashMap<>();

			Enumeration<String> initParameterNames = portletConfig.getInitParameterNames();

			while (initParameterNames.hasMoreElements()) {
				String initParameterName = initParameterNames.nextElement();
				properties.put(initParameterName, portletConfig.getInitParameter(initParameterName));
			}

			initParameterNames = portletContext.getInitParameterNames();

			while (initParameterNames.hasMoreElements()) {
				String initParameterName = initParameterNames.nextElement();
				properties.put(initParameterName, portletContext.getInitParameter(initParameterName));
			}

			if (!properties.containsKey(DEFAULT_VIEW_EXTENSION)) {
				properties.put(DEFAULT_VIEW_EXTENSION, "jsp");
			}
		}

		return properties;
	}

	@Override
	public Object getProperty(String name) {

		Map<String, Object> properties = getProperties();

		return properties.get(name);
	}

	@Override
	public Collection<String> getPropertyNames() {

		Map<String, Object> properties = getProperties();

		return properties.keySet();
	}

	@Override
	public RuntimeType getRuntimeType() {
		return RuntimeType.SERVER;
	}

	@Override
	public boolean isEnabled(Feature feature) {
		return false;
	}

	@Override
	public boolean isEnabled(Class<? extends Feature> featureClass) {
		return false;
	}

	@Override
	public boolean isRegistered(Object component) {
		return false;
	}

	@Override
	public boolean isRegistered(Class<?> componentClass) {
		return false;
	}
}
