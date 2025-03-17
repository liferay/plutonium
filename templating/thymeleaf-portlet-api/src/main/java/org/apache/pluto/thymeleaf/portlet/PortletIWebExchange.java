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

import jakarta.portlet.ClientDataRequest;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletSession;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.IWebSession;

import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Neil Griffin
 */
public class PortletIWebExchange implements IWebExchange {

	private final PortletRequest portletRequest;

	public PortletIWebExchange(PortletRequest portletRequest) {
		this.portletRequest = portletRequest;
	}

	@Override
	public IWebRequest getRequest() {
		return new PortletIWebRequest(portletRequest);
	}

	@Override
	public IWebSession getSession() {
		PortletSession session = portletRequest.getPortletSession(true);
		return new PortletIWebSession(session);
	}

	@Override
	public IWebApplication getApplication() {
		return new PortletIWebApplication(portletRequest.getPortletSession().getPortletContext());
	}

	@Override
	public Principal getPrincipal() {
		return portletRequest.getUserPrincipal();
	}

	@Override
	public Locale getLocale() {
		return portletRequest.getLocale();
	}

	@Override
	public String getContentType() {
		return portletRequest.getResponseContentType();
	}

	@Override
	public String getCharacterEncoding() {

		if (portletRequest instanceof ClientDataRequest) {
			ClientDataRequest clientDatRequest = (ClientDataRequest) portletRequest;
			return clientDatRequest.getCharacterEncoding();
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAttribute(String name) {
		return portletRequest.getAttribute(name) != null;
	}

	@Override
	public int getAttributeCount() {
		Enumeration<String> attributeNames = portletRequest.getAttributeNames();
		int count = 0;
		while (attributeNames.hasMoreElements()) {
			count++;
			attributeNames.nextElement();
		}
		return count;
	}

	@Override
	public Set<String> getAllAttributeNames() {
		Enumeration<String> attributeNames = portletRequest.getAttributeNames();
		Set<String> attributeSet = new HashSet<>();
		while (attributeNames.hasMoreElements()) {
			attributeSet.add(attributeNames.nextElement());
		}
		return attributeSet;
	}

	@Override
	public Map<String, Object> getAttributeMap() {
		Enumeration<String> attributeNames = portletRequest.getAttributeNames();
		Map<String, Object> attributeMap = new HashMap<>();
		while (attributeNames.hasMoreElements()) {
			String name = attributeNames.nextElement();
			attributeMap.put(name, portletRequest.getAttribute(name));
		}
		return Collections.unmodifiableMap(attributeMap);
	}

	@Override
	public Object getAttributeValue(String name) {
		return portletRequest.getAttribute(name);
	}

	@Override
	public void setAttributeValue(String name, Object value) {
		portletRequest.setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		portletRequest.removeAttribute(name);
	}

	@Override
	public String transformURL(String url) {
		return portletRequest.getContextPath() + url;
	}
}
