/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.portlet.PortletMode;
import jakarta.portlet.PortletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.plutonium.container.PortletContainer;
import com.liferay.plutonium.container.PortletHeaderResponseContext;
import com.liferay.plutonium.container.PortletRequestContext;
import com.liferay.plutonium.container.PortletWindow;
import com.liferay.plutonium.driver.AttributeKeys;

/**
 * @version $Id$
 * 
 */
public class PortletHeaderResponseContextImpl extends PortletMimeResponseContextImpl implements PortletHeaderResponseContext {
   public PortletHeaderResponseContextImpl(PortletContainer container, HttpServletRequest containerRequest, HttpServletResponse containerResponse,
         PortletWindow window, PortletRequestContext requestContext) {
      super(container, containerRequest, containerResponse, window, requestContext);
      setLifecycle(PortletRequest.HEADER_PHASE);
   }

   @Override
   public void setNextPossiblePortletModes(Collection<PortletMode> portletModes) {
      // not supported
   }

   @SuppressWarnings("unchecked")
   @Override
   public void setTitle(String title) {
      if (!isClosed()) {
         Map<String, String> titles = (Map<String, String>) getServletRequest().getAttribute(AttributeKeys.PORTLET_TITLE);
         if (titles == null) {
            titles = new HashMap<String, String>();
            getServletRequest().setAttribute(AttributeKeys.PORTLET_TITLE, titles);
         }
         String key = getPortletWindow().getId().getStringId();
         titles.put(key, title);
      }
   }

   @Override
   public void addDependency(String name, String scope, String version) {
      headerData.addDependency(name, scope, version);
   }

   @Override
   public void addDependency(String name, String scope, String version, String markup) {
      headerData.addDependency(name, scope, version, markup);
   }
}
