/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.integration.test;

import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.liferay.pluto.demo.integration.test.Util.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 *
 * @author Kyle Stiemann
 */
public class AsyncDemoIT extends DemoTestDriver {
   private static final String REQUEST_RECIEVED_XPATH =
         "(//p[" +
            "contains(text(),'DispatcherType: ASYNC') or " +
            "contains(text(),'Dispatch type: ASYNC') or " +
            "contains(text(),'Dispatcher outputType: FORWARD') or " +
            "contains(text(),'Dispatch type: FORWARD')" +
         "][contains(.,'Request #:')])";
   private static final String MESSAGE_XPATH_TEMPLATE =
         "//div[contains(@class,'{0}')][contains(.,'Request number:')][contains(.,'DispatcherType: {1}')]";

   @Test
   public void testAsyncDemo() {
      navigateToPage("Async Tests");
      waitingAsserter.waitFor(visibilityOfElementLocated(By.xpath(REQUEST_RECIEVED_XPATH)));

      for (Boolean recursive : unmodifiableList(Boolean.FALSE, Boolean.TRUE)) {
         sendKeysToElement(driver, waitingAsserter, getXpath("input", "AsyncPortlet", "delay"), "1");

         int reps = 1;

         if (recursive) {
            clickElement(driver, waitingAsserter, "//input[@name='auto'][@type='checkbox']");
            sendKeysToElement(driver, waitingAsserter, getXpath("input", "AsyncPortlet", "reps"),
                  Integer.toString(reps));
         }

         List<WebElement> timeoutRadioButtons = driver.findElements(By.xpath("//input[@name='toType']"));

         for (int i = 0; i < timeoutRadioButtons.size(); i++) {

            timeoutRadioButtons = driver.findElements(By.xpath("//input[@name='toType']"));
            timeoutRadioButtons.get(i).click();

            List<WebElement> typeRadioButtons = driver.findElements(By.xpath("//input[@name='type']"));

            for (int j = 0; j < typeRadioButtons.size(); j++) {

               typeRadioButtons = driver.findElements(By.xpath("//input[@name='type']"));
               typeRadioButtons.get(j).click();

               String showFilterCheckboxByXpath = "//input[@name='filter'][@type='checkbox']";
               waitingAsserter.waitFor(visibilityOfElementLocated(By.xpath(showFilterCheckboxByXpath)));
               WebElement showFilterCheckbox =
                     driver.findElement(By.xpath(showFilterCheckboxByXpath));

               if (!isChecked(showFilterCheckboxByXpath)) {
                  showFilterCheckbox.click();
               }

               String showListenerCheckboxXpath = "//input[@name='listener'][@type='checkbox']";

               if (!isChecked(showListenerCheckboxXpath)) {
                  clickElement(driver, waitingAsserter, showListenerCheckboxXpath);
               }

               clickElement(driver, waitingAsserter, getXpath("input", "AsyncPortlet", "send") +
                     "[@value='execute']");

               for (int r = 1; r <= reps; r++) {

                  if (isChecked(showFilterCheckboxByXpath)) {
                     assertMessageVisisble("msgbox", "REQUEST", "[contains(text(),'Filter:')]");

                     if (!recursive) {
                        assertMessageVisisble("msgbox", "ASYNC", "[contains(text(),'Filter:')]");
                     }
                  }

                  if (isChecked(showListenerCheckboxXpath) && !recursive) {
                     assertMessageVisisble("orangebox", "ASYNC", "[contains(.,'Listener: restarting async.')]");
                  }
                  
                  waitingAsserter.waitFor(visibilityOfElementLocated(By.xpath(REQUEST_RECIEVED_XPATH +
                        "[" + r + "]")));
               }
            }
         }
      }
   }

   private void assertMessageVisisble(String styleClass, String request, String xpathSuffix) {
      waitingAsserter.assertTrue(visibilityOfElementLocated(By.xpath(MESSAGE_XPATH_TEMPLATE
            .replace("{0}", styleClass).replace("{1}", request) + xpathSuffix)));
   }

   private boolean isChecked(String xpath) {
      WebElement checkbox = driver.findElement(By.xpath(xpath));
      return checkbox.isSelected();
   }
}
