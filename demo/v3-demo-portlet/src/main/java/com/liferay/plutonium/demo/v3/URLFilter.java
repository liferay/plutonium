/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.portlet.ActionURL;
import jakarta.portlet.PortletURLGenerationListener;
import jakarta.portlet.RenderURL;
import jakarta.portlet.ResourceURL;

/**
 * @author nick
 *
 */
public class URLFilter implements PortletURLGenerationListener<RenderURL, ActionURL> {
   private static final Logger logger = LoggerFactory.getLogger(URLFilter.class);

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletURLGenerationListener#filterActionURL(jakarta.portlet.PortletURL)
    */
   @Override
   public void filterActionURL(ActionURL actionURL) {
      if (logger.isDebugEnabled()) {
         logger.debug(this.getClass().getName(), "filterActionURL",
               "Filtered action URL. argument class: " + actionURL.getClass().getCanonicalName());
      }
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletURLGenerationListener#filterRenderURL(jakarta.portlet.PortletURL)
    */
   @Override
   public void filterRenderURL(RenderURL renderURL) {
      // do nothing
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletURLGenerationListener#filterResourceURL(jakarta.portlet.ResourceURL)
    */
   @Override
   public void filterResourceURL(ResourceURL resourceURL) {
      // do nothing
   }

}
