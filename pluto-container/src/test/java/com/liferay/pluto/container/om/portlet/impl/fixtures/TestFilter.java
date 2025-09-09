/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.fixtures;

import jakarta.portlet.PortletException;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.PortletFilter;

/**
 * @author Scott Nicklous
 *
 */
public class TestFilter implements PortletFilter {

   /* (non-Javadoc)
    * @see jakarta.portlet.filter.PortletFilter#destroy()
    */
   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.filter.PortletFilter#init(jakarta.portlet.filter.FilterConfig)
    */
   @Override
   public void init(FilterConfig arg0) throws PortletException {
      // TODO Auto-generated method stub

   }

}
