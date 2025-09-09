/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.integration.test;

import java.util.ArrayList;
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
public class PathInfoDemoIT extends DemoTestDriver{

   private static final List<String> EXPECTED_TITLES = unmodifiableList(
         "Included by resource method.",
         "Forwarded to Servlet by render method.",
         "Forwarded to JSP by render method.",
         "Included by render method.",
         "Included nested in Portlet -> Servlet -> JSP.",
         "Included named Servlet.");

   @Test
   public void testPathInfoDemo() {
      navigateToPage("Async Tests");
      By linksByXpath = By.xpath("//h3[text()='Path Info Portlet']/parent::div[@class='body']//a");
      waitingAsserter.waitFor(visibilityOfAllElementsLocatedBy(linksByXpath));
      List<WebElement> links = driver.findElements(linksByXpath);
      List<String> urls = new ArrayList<>();

      for (WebElement link : links) {
         urls.add(link.getAttribute("href"));
      }

      for (int i = 0; i < urls.size(); i++) {
         driver.get(urls.get(i));
         By titleByXpath = By.xpath("//p[contains(@id,'path_info_title')]");
         waitingAsserter.assertTrue(and(visibilityOfElementLocated(titleByXpath),
               textToBe(titleByXpath, EXPECTED_TITLES.get(i))));
      }
   }
}
