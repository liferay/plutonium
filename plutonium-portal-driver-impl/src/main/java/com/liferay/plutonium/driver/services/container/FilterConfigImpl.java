/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import jakarta.portlet.PortletContext;
import jakarta.portlet.filter.FilterConfig;

import com.liferay.plutonium.container.om.portlet.InitParam;

/**
 *
 *@since 29/05/2007
 *@version 2.0
 */
public class FilterConfigImpl implements FilterConfig {

	private final String filterName;
	private final List<? extends InitParam> initParameters;
	private final PortletContext portletContext;

	public FilterConfigImpl(String filterName, List<? extends InitParam> initParameters, PortletContext portletContext){
		this.filterName = filterName;
		this.initParameters = initParameters;
		this.portletContext = portletContext;
	}

	/**
	 * @see jakarta.portlet.filter.FilterConfig#getFilterName()
	 */
	public String getFilterName() {
		return filterName;
	}

	/**
	 * @see jakarta.portlet.filter.FilterConfig#getInitParameter(java.lang.String)
	 */
	public String getInitParameter(String name) {
		if (initParameters != null) {
			for (InitParam initParameter2 : initParameters) {
				if (initParameter2.getParamName().equals(name)){
					return initParameter2.getParamValue();
				}
			}
		}
		return null;
	}

	/**
	 * @see jakarta.portlet.filter.FilterConfig#getInitParameterNames()
	 */
	public Enumeration<String> getInitParameterNames() {
        Vector<String> enum1 = new Vector<String>();
        if ( initParameters != null ) {
            for(InitParam ip : this.initParameters ) {
                enum1.add(ip.getParamName());
            }
        }
        return enum1.elements();
	}

	/**
	 * @see jakarta.portlet.filter.FilterConfig#getPortletContext()
	 */
	public PortletContext getPortletContext() {
		return portletContext;
	}
}
