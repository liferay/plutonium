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
package com.liferay.pluto.thymeleaf.portlet;

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