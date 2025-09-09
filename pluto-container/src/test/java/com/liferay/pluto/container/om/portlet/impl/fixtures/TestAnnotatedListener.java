/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.fixtures;

import jakarta.portlet.ActionURL;
import jakarta.portlet.PortletURLGenerationListener;
import jakarta.portlet.RenderURL;
import jakarta.portlet.ResourceURL;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletListener;

/**
 * Annotated listener for unit testing.
 * @author Scott Nicklous
 */

@PortletListener(ordinal = 100,
listenerName = "aListener",
description = {
   @LocaleString("Quite the listener"),
   @LocaleString(locale="DE", value = "Ein ordentlicher Listener")},
displayName = {
   @LocaleString("A Listener"),
   @LocaleString(locale="DE", value = "Ein Listener")})
public class  TestAnnotatedListener implements
      PortletURLGenerationListener<RenderURL, ActionURL> {

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletURLGenerationListener#filterActionURL(jakarta.portlet.PortletURL)
    */
   @Override
   public void filterActionURL(ActionURL arg0) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletURLGenerationListener#filterRenderURL(jakarta.portlet.PortletURL)
    */
   @Override
   public void filterRenderURL(RenderURL arg0) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletURLGenerationListener#filterResourceURL(jakarta.portlet.ResourceURL)
    */
   @Override
   public void filterResourceURL(ResourceURL arg0) {
   }

}
