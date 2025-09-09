/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.driver;

import jakarta.portlet.PortletRequest;

import com.liferay.pluto.container.PortletInvokerService;
import com.liferay.pluto.container.PortletWindow;


public class PortletInvocationEvent {

    public static int LOAD = PortletInvokerService.METHOD_LOAD.intValue();

    public static int ACTION = PortletInvokerService.METHOD_ACTION.intValue();

    public static int RENDER = PortletInvokerService.METHOD_RENDER.intValue();

    public static int ADMIN = PortletInvokerService.METHOD_ADMIN.intValue();

    private PortletRequest portletRequest;

    private PortletWindow portletWindow;

    private int invocationMethod;

    public PortletInvocationEvent(PortletRequest portletRequest, PortletWindow portletWindow, int invocationMethod) {
        this.portletRequest = portletRequest;
        this.portletWindow = portletWindow;
        this.invocationMethod = invocationMethod;
    }

    public PortletRequest getPortletRequest() {
        return portletRequest;
    }

    public int getInvocationMethod() {
        return invocationMethod;
    }

    public PortletWindow getPortletWindow() {
        return portletWindow;
    }

}