/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.integration.test;

import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.liferay.pluto.demo.integration.test.Util.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 *
 * @author Kyle Stiemann
 */
public class HeaderPhaseDemoIT extends DemoTestDriver {

   @Test
   public void testHeaderPhaseDemo() {
      navigateToPage("V2 and V3 Header Tests");
      By characterEncodingByXpath = By.xpath("//p[contains(text(),'Character encoding:')]");
      waitingAsserter.assertTrue(and(visibilityOfElementLocated(characterEncodingByXpath),
            textToBePresentInElementLocated(characterEncodingByXpath, StandardCharsets.UTF_8.toString())));

      By contentTypeByXpath = By.xpath("//p[contains(text(),'Content type:')]");
      waitingAsserter.assertTrue(and(visibilityOfElementLocated(contentTypeByXpath),
            textToBePresentInElementLocated(contentTypeByXpath, "text/html")));

      By cookieDivByXpath = By.xpath(getXpath("div", "cookieDiv"));
      waitingAsserter.assertTrue(and(visibilityOfElementLocated(cookieDivByXpath),
            textToBePresentInElementLocated(cookieDivByXpath, "V3HeaderPortlet=something-special"),
            textToBePresentInElementLocated(cookieDivByXpath, "Author=Scott")));

      String propertyNameXpath = "//li[contains(text(),'Property')][contains(text(),'name:')]";
      By portletPropertyByXpath = By.xpath(propertyNameXpath + "[contains(text(),'Portlet')]");
      waitingAsserter.assertTrue(and(visibilityOfElementLocated(portletPropertyByXpath),
            textToBePresentInElementLocated(portletPropertyByXpath, "V3HeaderPortlet"),
            textToBePresentInElementLocated(portletPropertyByXpath, "[V3HeaderPortlet]")));

      By portalPropertyByXpath = By.xpath(propertyNameXpath + "[contains(text(),'Portal')]");
      String portalName = System.getProperty("test.portal.name");
      waitingAsserter.assertTrue(and(visibilityOfElementLocated(portalPropertyByXpath),
            textToBePresentInElementLocated(portalPropertyByXpath, portalName),
            textToBePresentInElementLocated(portalPropertyByXpath, "[" + portalName)));
   }
}
