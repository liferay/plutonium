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
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletConfigurations;
import jakarta.portlet.annotations.PortletListener;

/**
 * Test Listener annotation  
 *
 */
@PortletConfigurations({
   @PortletConfiguration(portletName = "portlet1"),
   @PortletConfiguration(portletName = "portlet2"),
   @PortletConfiguration(portletName = "portlet3")
})
@PortletListener(ordinal = 100, listenerName = "aListener")
public class MultipleAnnotatedListeners1 implements PortletURLGenerationListener<RenderURL, ActionURL> {

   @Override
   public void filterActionURL(ActionURL arg0) {
   }

   @Override
   public void filterRenderURL(RenderURL arg0) {
   }

   @Override
   public void filterResourceURL(ResourceURL arg0) {
   }

}
