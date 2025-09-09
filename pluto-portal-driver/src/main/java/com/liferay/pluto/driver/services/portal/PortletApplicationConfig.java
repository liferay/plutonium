/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.portal;

import java.util.Collection;
import java.util.Map;

/**
 * @author <a href="mailto:ddewolf@apache.org">David H. DeWolf</a>
 * @author <a href="mailto:zheng@apache.org">ZHENG Zhong</a>
 */
public class PortletApplicationConfig {

    private String contextPath;
    private Map<String, PortletWindowConfig> portlets;

    public PortletApplicationConfig() {
        portlets = new java.util.HashMap<String, PortletWindowConfig>();
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Collection<PortletWindowConfig> getPortlets() {
        return portlets.values();
    }

    public PortletWindowConfig getPortlet(String portletName) {
        return portlets.get(portletName);
    }

    public void addPortlet(PortletWindowConfig portlet) {
        portlet.setContextPath(getContextPath());
        portlets.put(portlet.getPortletName(), portlet);
    }

}
