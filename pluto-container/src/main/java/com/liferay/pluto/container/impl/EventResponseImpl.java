/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;

import com.liferay.pluto.container.PortletEventResponseContext;
import com.liferay.pluto.container.util.ArgumentUtility;

/**
 * Implementation of JSR-286 <code>EventResponse</code>.
 *
 * @since 2.0
 */

public class EventResponseImpl extends StateAwareResponseImpl implements EventResponse
{
    public EventResponseImpl(PortletEventResponseContext responseContext)
    {
        super(responseContext);
    }
    
    protected void checkSetStateChanged()
    {
        // nothing to check or do for EventResponse
    }

	@SuppressWarnings("deprecation")
   public void setRenderParameters(EventRequest request)
	{
        ArgumentUtility.validateNotNull("request", request);
        setRenderParameters(request.getParameterMap());
	}
}
