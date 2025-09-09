/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.integration.test;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Kyle Stiemann
 */
public final class WaitingAsserter {

   private final WebDriverWait webDriverWait;

   public WaitingAsserter(WebDriver driver, long timeOutInSeconds) {
      this.webDriverWait = new WebDriverWait(driver, timeOutInSeconds);
   }

   public void assertTrue(ExpectedCondition expectedCondition) {

      try {
         waitFor(expectedCondition);
      } catch (TimeoutException e) {
         throw new AssertionError(e);
      }
   }

   public void waitFor(ExpectedCondition expectedCondition) {
      webDriverWait.until(expectedCondition);
   }
}
