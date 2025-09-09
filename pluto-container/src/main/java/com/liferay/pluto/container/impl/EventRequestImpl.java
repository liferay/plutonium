/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;


import jakarta.portlet.Event;
import jakarta.portlet.EventRequest;
import jakarta.portlet.PortletRequest;

import com.liferay.pluto.container.PortletEventResponseContext;
import com.liferay.pluto.container.PortletRequestContext;

/**
 * <code>jakarta.portlet.EventRequest</code> implementation.
 *
 */
public class EventRequestImpl extends PortletRequestImpl implements EventRequest
{
	private final Event event; 
	
    public EventRequestImpl(PortletRequestContext requestContext, PortletEventResponseContext responseContext, Event event)
    {
        super(requestContext, responseContext, PortletRequest.EVENT_PHASE);
        this.event = event;
    }
    
    public Event getEvent()
    {
        return event;
    }

    public String getMethod()
    {
        return getServletRequest().getMethod();
    }
}
