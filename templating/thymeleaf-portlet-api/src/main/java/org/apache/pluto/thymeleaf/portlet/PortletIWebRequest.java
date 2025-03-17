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
import jakarta.portlet.RenderRequest;
import jakarta.servlet.http.Cookie;
import org.thymeleaf.web.IWebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Neil Griffin
 */
public class PortletIWebRequest implements IWebRequest {

	private final PortletRequest portletRequest;

	public PortletIWebRequest(PortletRequest portletRequest) {
		this.portletRequest = portletRequest;
	}

	@Override
	public String getMethod() {
		if (portletRequest instanceof RenderRequest) {
			return "GET";
		} else if (portletRequest instanceof ClientDataRequest) {
			ClientDataRequest clientDataRequest = (ClientDataRequest) portletRequest;
			return clientDataRequest.getMethod();
		}
		throw new UnsupportedOperationException("Unsupported PortletRequest type for method retrieval");
	}

	@Override
	public String getScheme() {
		return portletRequest.getScheme();
	}

	@Override
	public String getServerName() {
		return portletRequest.getServerName();
	}

	@Override
	public Integer getServerPort() {
		return portletRequest.getServerPort();
	}

	@Override
	public String getApplicationPath() {
		return portletRequest.getContextPath();
	}

	@Override
	public String getPathWithinApplication() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getQueryString() {
		Map<String, String[]> paramMap = portletRequest.getParameterMap();
		if (paramMap == null || paramMap.isEmpty()) {
			return null;
		}
		StringBuilder queryString = new StringBuilder();
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			if (queryString.length() > 0) {
				queryString.append("&");
			}
			String key = entry.getKey();
			String[] values = entry.getValue();
			if (key != null && values != null) {
				queryString.append(key).append("=").append(String.join(",", values));
			}
		}
		return queryString.toString();
	}

	@Override
	public boolean containsHeader(String name) {
		return name != null && portletRequest.getProperty(name) != null;
	}

	@Override
	public int getHeaderCount() {
		Enumeration<String> headerNames = portletRequest.getPropertyNames();
		return headerNames != null ? Collections.list(headerNames).size() : 0;
	}

	@Override
	public Set<String> getAllHeaderNames() {
		Enumeration<String> headerNames = portletRequest.getPropertyNames();
		return headerNames != null ? new HashSet<>(Collections.list(headerNames)) : new HashSet<>();
	}

	@Override
	public Map<String, String[]> getHeaderMap() {
		Map<String, String[]> headers = new HashMap<>();
		Enumeration<String> headerNames = portletRequest.getPropertyNames();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();
				Enumeration<String> values = portletRequest.getProperties(name);
				if (name != null && values != null) {
					headers.put(name, Collections.list(values).toArray(new String[0]));
				}
			}
		}
		return headers;
	}

	@Override
	public String[] getHeaderValues(String name) {
		if (name == null) {
			return new String[0];
		}
		Enumeration<String> values = portletRequest.getProperties(name);
		return values != null ? Collections.list(values).toArray(new String[0]) : new String[0];
	}

	@Override
	public boolean containsParameter(String name) {
		return name != null && portletRequest.getParameter(name) != null;
	}

	@Override
	public int getParameterCount() {
		Map<String, String[]> paramMap = portletRequest.getParameterMap();
		return paramMap != null ? paramMap.size() : 0;
	}

	@Override
	public Set<String> getAllParameterNames() {
		Map<String, String[]> paramMap = portletRequest.getParameterMap();
		return paramMap != null ? paramMap.keySet() : new HashSet<>();
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> paramMap = portletRequest.getParameterMap();
		return paramMap != null ? paramMap : new HashMap<>();
	}

	@Override
	public String[] getParameterValues(String name) {
		return name != null ? portletRequest.getParameterValues(name) : new String[0];
	}

	@Override
	public boolean containsCookie(String name) {
		if (name == null) {
			return false;
		}
		Cookie[] cookies = portletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getCookieCount() {
		Cookie[] cookies = portletRequest.getCookies();
		return cookies != null ? cookies.length : 0;
	}

	@Override
	public Set<String> getAllCookieNames() {
		Set<String> cookieNames = new HashSet<>();
		Cookie[] cookies = portletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName() != null) {
					cookieNames.add(cookie.getName());
				}
			}
		}
		return cookieNames;
	}

	@Override
	public Map<String, String[]> getCookieMap() {
		Map<String, String[]> cookieMap = new HashMap<>();
		Cookie[] cookies = portletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName() != null && cookie.getValue() != null) {
					cookieMap.put(cookie.getName(), new String[]{cookie.getValue()});
				}
			}
		}
		return cookieMap;
	}

	@Override
	public String[] getCookieValues(String name) {
		if (name == null) {
			return new String[0];
		}
		List<String> values = new ArrayList<>();
		Cookie[] cookies = portletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName()) && cookie.getValue() != null) {
					values.add(cookie.getValue());
				}
			}
		}
		return values.toArray(new String[0]);
	}
}
