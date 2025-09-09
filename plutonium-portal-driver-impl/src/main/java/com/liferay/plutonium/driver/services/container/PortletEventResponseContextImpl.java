/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import jakarta.portlet.PortletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.plutonium.container.PortletContainer;
import com.liferay.plutonium.container.PortletEventResponseContext;
import com.liferay.plutonium.container.PortletRequestContext;
import com.liferay.plutonium.container.PortletWindow;

/**
 * @version $Id$
 *
 */
public class PortletEventResponseContextImpl extends PortletStateAwareResponseContextImpl implements
                PortletEventResponseContext
{

    public PortletEventResponseContextImpl(PortletContainer container, HttpServletRequest containerRequest,
          HttpServletResponse containerResponse, PortletWindow window, PortletRequestContext requestContext)
    {
        super(container, containerRequest, containerResponse, window, requestContext);
        setLifecycle(PortletRequest.EVENT_PHASE);
    }
}
