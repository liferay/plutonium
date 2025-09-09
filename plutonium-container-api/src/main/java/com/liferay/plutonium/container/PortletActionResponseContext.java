/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container;

/**
 * @author <a href="mailto:ate@douma.nu">Ate Douma</a>
 */
public interface PortletActionResponseContext extends PortletStateAwareResponseContext
{
    void setRedirect(String location);
    void setRedirect(String location, String renderURLParamName);
    boolean isRedirect();
    String getResponseURL();
}
