/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.integration.test;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.liferay.plutonium.demo.integration.test.Util.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 *
 * @author Kyle Stiemann
 */
public class BeanPortletDemoIT extends DemoTestDriver {

   @Test
   public void testBeanPortletDemo() {
      navigateToPage("Bean Portlet Demo");
      By scopeInfoNumbersByXpath = By.xpath(getXpath("table", "BeanPortlet", "scope_info") + "//tr/td[2]");
      waitingAsserter.waitFor(visibilityOfAllElementsLocatedBy(scopeInfoNumbersByXpath));
      List<WebElement> scopeInfoNumbers = driver.findElements(scopeInfoNumbersByXpath);

      for (WebElement scopeInfoNumber : scopeInfoNumbers) {
         Assert.assertTrue(Integer.parseInt(scopeInfoNumber.getText().trim()) > 0);
      }

      sendKeysToElement(driver, waitingAsserter, "(//input[@name='name'][@type='text'])[1]", "user");
      clickElement(driver, waitingAsserter, "(//input[@value='send'][@type='submit'])[1]");
      waitingAsserter.assertTrue(and(
            visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Hello')][contains(text(),'user!!')]")),
            visibilityOfElementLocated(By.xpath(getXpath("div", "BeanPortlet", "putResourceHere") + "/img"))));
   }
}
