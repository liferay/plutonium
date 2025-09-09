/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.integration.test;

import org.junit.Test;

/**
 *
 * @author Kyle Stiemann
 */
public class URLRenderParameterDemoPortletIT extends URLMethodsPortletsDemoBase {

   @Test
   public void testURLRenderParameterDemoPortlet() {

      Parameter publicRenderParameter = new Parameter("Bild", "Bild1");
      testURLMethods(1, "a", publicRenderParameter);

      // Verify that the public render parameter is available to all portlets on the page.
      assertParametersSet(2, publicRenderParameter);
   }
}
