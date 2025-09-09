/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import jakarta.portlet.ActionParameters;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.PortletRequest;

import com.liferay.plutonium.container.PortletActionResponseContext;
import com.liferay.plutonium.container.PortletRequestContext;

/**
 * Implementation of the <code>jakarta.portlet.ActionRequest</code> interface.
 */
public class ActionRequestImpl extends ClientDataRequestImpl implements ActionRequest
{
    public ActionRequestImpl(PortletRequestContext requestContext, PortletActionResponseContext responseContext)
    {
        super(requestContext, responseContext, PortletRequest.ACTION_PHASE);
    }

   public ActionParameters getActionParameters() {
      return requestContext.getActionParameters();
   }
}
