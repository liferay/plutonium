/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.integration.test;

import com.liferay.plutonium.test.utilities.SimpleTestDriver;
import org.junit.BeforeClass;

/**
 *
 * @author Kyle Stiemann
 */
public class DemoTestDriver extends SimpleTestDriver {

   protected static WaitingAsserter waitingAsserter;

   @BeforeClass
   public static void setUpBeforeDemoSimpleTestDriverClass() {

      if (waitingAsserter == null) {
         waitingAsserter = new WaitingAsserter(driver, timeout);
      }
   }

   protected void navigateToPage(String pageName) {
      driver.get(baseUrl + Util.encodeURL(pageName));
   }
}
