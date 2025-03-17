/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pluto.thymeleaf.portlet;

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
