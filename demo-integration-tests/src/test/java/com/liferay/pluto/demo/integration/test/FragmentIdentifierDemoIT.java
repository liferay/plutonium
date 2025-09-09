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
public class FragmentIdentifierDemoIT extends DemoTestDriver {

   @Test
   public void testFragmentIdentifierDemo() {
      navigateToPage("V3.0 Misc Tests - HTTP SC, fragment id");

      String fragmentId = "fragmentId";
      sendKeysToElement(driver, waitingAsserter, "//input[@name='frag'][@type='text']", fragmentId);
      sendKeysToElement(driver, waitingAsserter, "//input[@name='line'][@type='text']", "3");
      clickElement(driver, waitingAsserter,
         "//input[contains(@id,'v3_demo_portlet')][contains(@id,'LongPortlet')][@value='send'][@type='submit']");
      clickElement(driver, waitingAsserter, "//a[text()='Jump to line 3']");
      waitingAsserter.assertTrue(new ExpectedCondition<Boolean>() {
         @Override
         public Boolean apply(WebDriver webDriver) {
            Long pageYOffset = (Long) executeScript(webDriver,
                  "return (document.documentElement.scrollTop || window.pageYOffset);");
            return (pageYOffset > 0);
         }
      });
      waitingAsserter.assertTrue(visibilityOfElementLocated(By.id(fragmentId)));
   }
}
