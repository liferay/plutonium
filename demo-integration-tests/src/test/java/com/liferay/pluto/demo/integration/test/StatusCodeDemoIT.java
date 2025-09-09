/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.integration.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.liferay.pluto.demo.integration.test.Util.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 *
 * @author Kyle Stiemann
 */
public class StatusCodeDemoIT extends DemoTestDriver {

   @Test
   public void testStatusCodeDemo() {
      for (String statusCode : unmodifiableList("404", "200", "500")) {
         navigateToPage("V3.0 Misc Tests - HTTP SC, fragment id");
         sendKeysToElement(driver, waitingAsserter, "//input[@name='statusCode'][@type='text']", statusCode);
         clickElement(driver, waitingAsserter,
               "//input[contains(@id,'v3_demo_portlet')][contains(@id,'AuthSCPortlet')][@value='send'][@type='submit']");
         clickElement(driver, waitingAsserter, "//a[text()='Resource URL, status code = " + statusCode + "']");
         waitingAsserter.assertTrue(visibilityOfElementLocated(By.xpath(
               "//p[contains(text(),'Status code: " + statusCode + "')]")));
      }
   }
}
