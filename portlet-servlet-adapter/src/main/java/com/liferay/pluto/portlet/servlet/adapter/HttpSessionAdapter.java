/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.portlet.servlet.adapter;

import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletSession;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;


/**
 * Provides a way to decorate a {@link PortletSession} as an {@link HttpSession}. Methods that have no correspondence to
 * {@link HttpSession} throw {@link UnsupportedOperationException}
 *
 * @author  Neil Griffin
 */
public class HttpSessionAdapter implements PortletSession, HttpSession {

	// Private Data Members
	private PortletSession wrappedPortletSession;

	public HttpSessionAdapter(PortletSession portletSession) {
		this.wrappedPortletSession = portletSession;
	}

	@Override
	public Object getAttribute(String name) {
		Object value = wrappedPortletSession.getAttribute(name, PortletSession.PORTLET_SCOPE);

		if (value == null) {
			value = wrappedPortletSession.getAttribute(name, PortletSession.APPLICATION_SCOPE);
		}

		return value;
	}

	@Override
	public Object getAttribute(String name, int scope) {
		return wrappedPortletSession.getAttribute(name, scope);
	}

	@Override
	public Map<String, Object> getAttributeMap() {
		return wrappedPortletSession.getAttributeMap();
	}

	@Override
	public Map<String, Object> getAttributeMap(int scope) {
		return wrappedPortletSession.getAttributeMap(scope);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return wrappedPortletSession.getAttributeNames();
	}

	@Override
	public Enumeration<String> getAttributeNames(int scope) {
		return wrappedPortletSession.getAttributeNames(scope);
	}

	@Override
	public long getCreationTime() {
		return wrappedPortletSession.getCreationTime();
	}

	@Override
	public String getId() {
		return wrappedPortletSession.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return wrappedPortletSession.getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {
		return wrappedPortletSession.getMaxInactiveInterval();
	}

	@Override
	public PortletContext getPortletContext() {
		return wrappedPortletSession.getPortletContext();
	}

	@Override
	public ServletContext getServletContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void invalidate() {
		wrappedPortletSession.invalidate();
	}

	@Override
	public boolean isNew() {
		return wrappedPortletSession.isNew();
	}

	@Override
	public void removeAttribute(String name) {
		wrappedPortletSession.removeAttribute(name);
	}

	@Override
	public void removeAttribute(String name, int scope) {
		wrappedPortletSession.removeAttribute(name, scope);
	}

	@Override
	public void setAttribute(String name, Object value) {
		wrappedPortletSession.setAttribute(name, value);
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		wrappedPortletSession.setAttribute(name, value, scope);
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		wrappedPortletSession.setMaxInactiveInterval(interval);
	}
}
