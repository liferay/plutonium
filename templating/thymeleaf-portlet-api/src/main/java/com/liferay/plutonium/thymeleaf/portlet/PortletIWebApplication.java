/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.portlet;

import jakarta.portlet.PortletContext;
import org.thymeleaf.web.IWebApplication;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Neil Griffin
 */
public class PortletIWebApplication implements IWebApplication {

	private final PortletContext portletContext;

	public PortletIWebApplication(PortletContext portletContext) {
		this.portletContext = portletContext;
	}

	@Override
	public boolean containsAttribute(String name) {
		return portletContext.getAttribute(name) != null;
	}

	@Override
	public int getAttributeCount() {
		Enumeration<String> attributeNames = portletContext.getAttributeNames();
		int count = 0;
		while (attributeNames.hasMoreElements()) {
			attributeNames.nextElement();
			count++;
		}
		return count;
	}

	@Override
	public Set<String> getAllAttributeNames() {
		Set<String> attributeNamesSet = new HashSet<>();
		Enumeration<String> attributeNames = portletContext.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			attributeNamesSet.add(attributeNames.nextElement());
		}
		return attributeNamesSet;
	}

	@Override
	public Map<String, Object> getAttributeMap() {
		Map<String, Object> attributeMap = new HashMap<>();
		Enumeration<String> attributeNames = portletContext.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String name = attributeNames.nextElement();
			attributeMap.put(name, portletContext.getAttribute(name));
		}
		return attributeMap;
	}

	@Override
	public Object getAttributeValue(String name) {
		return portletContext.getAttribute(name);
	}

	@Override
	public void setAttributeValue(String name, Object value) {
		portletContext.setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		portletContext.removeAttribute(name);
	}

	@Override
	public boolean resourceExists(String path) {
		return portletContext.getResourceAsStream(path) != null;
	}

	@Override
	public InputStream getResourceAsStream(String path) {
		return portletContext.getResourceAsStream(path);
	}
}