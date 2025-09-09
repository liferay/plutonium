/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.driver;


public interface PortletInvocationListener {

    void onBegin(PortletInvocationEvent event);
    
    void onEnd(PortletInvocationEvent event);

    void onError(PortletInvocationEvent event, Throwable error);

}