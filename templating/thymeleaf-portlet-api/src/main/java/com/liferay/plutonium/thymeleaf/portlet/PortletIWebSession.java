/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.portlet;

import jakarta.portlet.PortletSession;
import org.thymeleaf.web.IWebSession;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Neil Griffin
 */
public class PortletIWebSession implements IWebSession {

	private final PortletSession portletSession;

	public PortletIWebSession(PortletSession portletSession) {
		this.portletSession = portletSession;
	}

	@Override
	public boolean exists() {
		return portletSession != null;
	}

	@Override
	public boolean containsAttribute(String name) {
		return portletSession.getAttribute(name) != null;
	}

	@Override
	public int getAttributeCount() {
		return getAllAttributeNames().size();
	}

	@Override
	public Set<String> getAllAttributeNames() {
		if (portletSession == null) {
			return Collections.emptySet();
		}
		Set<String> names = new HashSet<>();
		Enumeration<String> attributeNames = portletSession.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			names.add(attributeNames.nextElement());
		}
		return names;
	}

	@Override
	public Map<String, Object> getAttributeMap() {
		if (portletSession == null) {
			return Collections.emptyMap();
		}
		Map<String, Object> attributes = new HashMap<>();
		Enumeration<String> attributeNames = portletSession.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String name = attributeNames.nextElement();
			attributes.put(name, portletSession.getAttribute(name));
		}
		return attributes;
	}

	@Override
	public Object getAttributeValue(String name) {
		return portletSession != null ? portletSession.getAttribute(name) : null;
	}

	@Override
	public void setAttributeValue(String name, Object value) {
		if (portletSession != null) {
			portletSession.setAttribute(name, value);
		}
	}

	@Override
	public void removeAttribute(String name) {
		if (portletSession != null) {
			portletSession.removeAttribute(name);
		}
	}
}
