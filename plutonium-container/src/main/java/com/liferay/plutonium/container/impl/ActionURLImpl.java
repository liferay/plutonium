package com.liferay.plutonium.container.impl;

import jakarta.portlet.ActionURL;
import jakarta.portlet.MimeResponse.Copy;
import jakarta.portlet.MutableActionParameters;

import com.liferay.plutonium.container.PortletMimeResponseContext;
import com.liferay.plutonium.container.PortletURLProvider;

/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

/**
 * @author nick
 *
 */
public class ActionURLImpl extends PortletURLImpl implements ActionURL {

   /**
    * @param responseContext
    * @param copy
    */
   public ActionURLImpl(PortletMimeResponseContext responseContext, Copy copy) {
      super(responseContext, 
            responseContext.getPortletURLProvider(PortletURLProvider.TYPE.ACTION), copy);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ActionURL#getActionParameters()
    */
   public MutableActionParameters getActionParameters() {
      return new MutableActionParametersImpl(urlProvider, windowId);
   }

}
