/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

import jakarta.portlet.MimeResponse.Copy;
import jakarta.portlet.RenderURL;

import com.liferay.pluto.container.PortletResponseContext;
import com.liferay.pluto.container.PortletURLProvider;
import com.liferay.pluto.container.util.ArgumentUtility;

/**
 * @author Scott Nicklous
 *
 */
public class RenderURLImpl extends PortletURLImpl implements RenderURL {

   /**
    * @param responseContext
    * @param copy 
    */
   public RenderURLImpl(PortletResponseContext responseContext, Copy copy) {
      super(responseContext,
            responseContext.getPortletURLProvider(PortletURLProvider.TYPE.RENDER), copy);
   }

   @Override
   public void setFragmentIdentifier(String fragment) {
      urlProvider.setFragmentIdentifier(fragment);
   }

   @Override
   public String getFragmentIdentifier() {
      return urlProvider.getFragmentIdentifier();
   }

}
