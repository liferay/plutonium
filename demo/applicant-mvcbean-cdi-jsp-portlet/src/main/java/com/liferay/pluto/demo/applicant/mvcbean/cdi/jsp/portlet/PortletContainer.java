/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.portlet;

import jakarta.portlet.PortletRequest;
import jakarta.portlet.filter.PortletRequestWrapper;


/**
 * @author  Neil Griffin
 */
public enum PortletContainer {

	LIFERAY("com.liferay"), PLUTO("com.liferay.pluto"), WEBSPHERE("com.ibm");

	private final String fqcnPrefix;

	PortletContainer(String fqcnPrefix) {
		this.fqcnPrefix = fqcnPrefix;
	}

	public boolean isDetected(PortletRequest portletRequest) {

		portletRequest = unwrapPortletRequest(portletRequest);

		Class<? extends PortletRequest> portletRequestClass = portletRequest.getClass();

		String portletRequestClassName = portletRequestClass.getName();

		return portletRequestClassName.startsWith(fqcnPrefix);
	}

	private PortletRequest unwrapPortletRequest(PortletRequest portletRequest) {

		if (portletRequest instanceof PortletRequestWrapper) {
			PortletRequestWrapper portletRequestWrapper = (PortletRequestWrapper) portletRequest;

			return unwrapPortletRequest(portletRequestWrapper.getRequest());
		}

		return portletRequest;
	}
}
