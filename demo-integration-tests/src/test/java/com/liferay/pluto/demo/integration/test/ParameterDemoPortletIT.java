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
public class ParameterDemoPortletIT extends ParameterPortletsDemoBase {

   @Test
   public void testParameterDemoPortlet() {
      navigateToPage("V3.0 Parameter Tests");
      testCustomParameters(1);
   }
}
