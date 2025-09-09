/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.integration.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.liferay.plutonium.demo.integration.test.Util.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 *
 * @author Kyle Stiemann
 */
public class RedirectDemoIT extends DemoTestDriver {

   @Test
   public void testRedirectDemo() {
      navigateToPage("V3.0 Misc Tests - redirect");
      int red = 0xFF;
      int green = 0x00;
      int blue = 0x00;

      String color = getColorCode(red, green, blue);
      sendKeysToElement(driver, waitingAsserter, "//input[@name='color'][@type='text']", color);
      clickElement(driver, waitingAsserter, "//input[@value='redirect'][@type='submit']");
      waitingAsserter.assertTrue(backgroundColor(By.xpath("//h3[text()='V3 Redirect Portlet']/.."), red, green, blue));
      sendKeysToElement(driver, waitingAsserter, "//input[@name='url'][@type='text']",
            ChatRoomDemoIT.CHAT_ROOM_DEMO_URL);
      clickElement(driver, waitingAsserter, "//input[@value='redirect'][@type='submit']");
      waitingAsserter.assertTrue(visibilityOfElementLocated(By.xpath(
            "//h3[contains(text(),'Hello')][contains(text(),'World')][contains(text(),'!!')]")));
   }
}
