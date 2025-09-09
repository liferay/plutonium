/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

import jakarta.portlet.HeaderRequest;
import jakarta.portlet.PortletRequest;

import com.liferay.pluto.container.PortletHeaderResponseContext;
import com.liferay.pluto.container.PortletRequestContext;

/**
 * The header request.
 * 
 * @author Scott Nicklous
 */
public class HeaderRequestImpl extends RenderRequestImpl implements HeaderRequest {
   
   HeaderRequestImpl(PortletRequestContext requestContext, PortletHeaderResponseContext responseContext) {
      super(requestContext, responseContext, PortletRequest.HEADER_PHASE);
   }

}
