/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.integration.test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author Kyle Stiemann
 */
public class ElementEnabled implements ExpectedCondition<WebElement> {

   private final By locator;

   public ElementEnabled(By locator) {
      this.locator = locator;
   }

   @Override
   public WebElement apply(WebDriver webDriver) {

      WebElement webElement = null;

      try {

         List<WebElement> webElements = webDriver.findElements(locator);

         if (!webElements.isEmpty() && webElements.get(0).isEnabled()) {
            webElement = webElements.get(0);
         }
      } catch (StaleElementReferenceException e) {
         // Do nothing.
      }

      return webElement;
   }
}
