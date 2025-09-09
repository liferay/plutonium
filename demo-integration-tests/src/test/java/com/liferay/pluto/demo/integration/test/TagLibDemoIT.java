/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.integration.test;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.liferay.pluto.demo.integration.test.Util.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 *
 * @author Kyle Stiemann
 */
public class TagLibDemoIT extends DemoTestDriver {

   @Test
   public void testTagLibDemo() {
      navigateToPage("Tag Library");

      clickElement(driver, waitingAsserter, "//input[@value='render'][@type='radio']");
      clickElement(driver, waitingAsserter, "//input[@value='obj'][@type='radio']");

      String executeButtonXpath = "//button[text()='Execute'][@type='submit']";
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "true");
      assertTextVisible("resourceRequest", "false");
      assertTextVisible("actionRequest", "false");
      assertTextVisible("eventRequest", "false");
      assertTextVisible("renderResponse", "true");
      assertTextVisible("resourceResponse", "false");
      assertTextVisible("actionResponse", "false");
      assertTextVisible("eventResponse", "false");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "true");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "true");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "false");
      assertTextVisible("headerResponse", "false");
      assertTextVisible("clientDataRequest", "false");
      assertTextVisible("mimeResponse", "true");
      assertTextVisible("stateAwareResponse", "false");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "false");
      assertTextVisible("resourceParams", "false");
      assertTextVisible("actionParams", "false");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "true");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='bean'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "true");
      assertTextVisible("resourceRequest", "undefined");
      assertTextVisible("actionRequest", "undefined");
      assertTextVisible("eventRequest", "undefined");
      assertTextVisible("renderResponse", "true");
      assertTextVisible("resourceResponse", "undefined");
      assertTextVisible("actionResponse", "undefined");
      assertTextVisible("eventResponse", "undefined");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "false");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "false");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "undefined");
      assertTextVisible("headerResponse", "undefined");
      assertTextVisible("clientDataRequest", "undefined");
      assertTextVisible("mimeResponse", "true");
      assertTextVisible("stateAwareResponse", "undefined");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "undefined");
      assertTextVisible("resourceParams", "undefined");
      assertTextVisible("actionParams", "undefined");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "false");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='header'][@type='radio']");
      clickElement(driver, waitingAsserter, "//input[@value='obj'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "false");
      assertTextVisible("resourceRequest", "false");
      assertTextVisible("actionRequest", "false");
      assertTextVisible("eventRequest", "false");
      assertTextVisible("renderResponse", "false");
      assertTextVisible("resourceResponse", "false");
      assertTextVisible("actionResponse", "false");
      assertTextVisible("eventResponse", "false");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "true");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "true");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "true");
      assertTextVisible("headerResponse", "true");
      assertTextVisible("clientDataRequest", "false");
      assertTextVisible("mimeResponse", "true");
      assertTextVisible("stateAwareResponse", "false");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "false");
      assertTextVisible("resourceParams", "false");
      assertTextVisible("actionParams", "false");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "true");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='bean'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "undefined");
      assertTextVisible("resourceRequest", "undefined");
      assertTextVisible("actionRequest", "undefined");
      assertTextVisible("eventRequest", "undefined");
      assertTextVisible("renderResponse", "undefined");
      assertTextVisible("resourceResponse", "undefined");
      assertTextVisible("actionResponse", "undefined");
      assertTextVisible("eventResponse", "undefined");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "false");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "false");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "true");
      assertTextVisible("headerResponse", "true");
      assertTextVisible("clientDataRequest", "undefined");
      assertTextVisible("mimeResponse", "true");
      assertTextVisible("stateAwareResponse", "undefined");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "undefined");
      assertTextVisible("resourceParams", "undefined");
      assertTextVisible("actionParams", "undefined");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "false");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='resource'][@type='radio']");
      clickElement(driver, waitingAsserter, "//input[@value='obj'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "false");
      assertTextVisible("resourceRequest", "true");
      assertTextVisible("actionRequest", "false");
      assertTextVisible("eventRequest", "false");
      assertTextVisible("renderResponse", "false");
      assertTextVisible("resourceResponse", "true");
      assertTextVisible("actionResponse", "false");
      assertTextVisible("eventResponse", "false");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "true");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "true");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "false");
      assertTextVisible("headerResponse", "false");
      assertTextVisible("clientDataRequest", "true");
      assertTextVisible("mimeResponse", "true");
      assertTextVisible("stateAwareResponse", "false");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "false");
      assertTextVisible("resourceParams", "false");
      assertTextVisible("actionParams", "false");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "true");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='bean'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "undefined");
      assertTextVisible("resourceRequest", "true");
      assertTextVisible("actionRequest", "undefined");
      assertTextVisible("eventRequest", "undefined");
      assertTextVisible("renderResponse", "undefined");
      assertTextVisible("resourceResponse", "true");
      assertTextVisible("actionResponse", "undefined");
      assertTextVisible("eventResponse", "undefined");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "false");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "false");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "undefined");
      assertTextVisible("headerResponse", "undefined");
      assertTextVisible("clientDataRequest", "true");
      assertTextVisible("mimeResponse", "true");
      assertTextVisible("stateAwareResponse", "undefined");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "undefined");
      assertTextVisible("resourceParams", "true");
      assertTextVisible("actionParams", "undefined");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "false");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='action'][@type='radio']");
      clickElement(driver, waitingAsserter, "//input[@value='obj'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "false");
      assertTextVisible("resourceRequest", "false");
      assertTextVisible("actionRequest", "true");
      assertTextVisible("eventRequest", "false");
      assertTextVisible("renderResponse", "false");
      assertTextVisible("resourceResponse", "false");
      assertTextVisible("actionResponse", "true");
      assertTextVisible("eventResponse", "false");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "true");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "true");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "false");
      assertTextVisible("headerResponse", "false");
      assertTextVisible("clientDataRequest", "true");
      assertTextVisible("mimeResponse", "false");
      assertTextVisible("stateAwareResponse", "true");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "true");
      assertTextVisible("resourceParams", "false");
      assertTextVisible("actionParams", "false");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "true");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='bean'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "undefined");
      assertTextVisible("resourceRequest", "undefined");
      assertTextVisible("actionRequest", "true");
      assertTextVisible("eventRequest", "undefined");
      assertTextVisible("renderResponse", "undefined");
      assertTextVisible("resourceResponse", "undefined");
      assertTextVisible("actionResponse", "true");
      assertTextVisible("eventResponse", "undefined");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "false");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "false");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "undefined");
      assertTextVisible("headerResponse", "undefined");
      assertTextVisible("clientDataRequest", "true");
      assertTextVisible("mimeResponse", "undefined");
      assertTextVisible("stateAwareResponse", "true");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "true");
      assertTextVisible("resourceParams", "undefined");
      assertTextVisible("actionParams", "true");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "false");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='event'][@type='radio']");
      clickElement(driver, waitingAsserter, "//input[@value='obj'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "false");
      assertTextVisible("resourceRequest", "false");
      assertTextVisible("actionRequest", "false");
      assertTextVisible("eventRequest", "true");
      assertTextVisible("renderResponse", "false");
      assertTextVisible("resourceResponse", "false");
      assertTextVisible("actionResponse", "false");
      assertTextVisible("eventResponse", "true");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "true");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "true");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "false");
      assertTextVisible("headerResponse", "false");
      assertTextVisible("clientDataRequest", "false");
      assertTextVisible("mimeResponse", "false");
      assertTextVisible("stateAwareResponse", "true");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "true");
      assertTextVisible("resourceParams", "false");
      assertTextVisible("actionParams", "false");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "true");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");

      clickElement(driver, waitingAsserter, "//input[@value='bean'][@type='radio']");
      clickElement(driver, waitingAsserter, executeButtonXpath);

      assertTextVisible("renderRequest", "undefined");
      assertTextVisible("resourceRequest", "undefined");
      assertTextVisible("actionRequest", "undefined");
      assertTextVisible("eventRequest", "true");
      assertTextVisible("renderResponse", "undefined");
      assertTextVisible("resourceResponse", "undefined");
      assertTextVisible("actionResponse", "undefined");
      assertTextVisible("eventResponse", "true");
      assertTextVisible("portletConfig", "true");
      assertTextVisible("portletSession", "true");
      assertTextVisible("portletSessionScope", "false");
      assertTextVisible("portletPreferences", "true");
      assertTextVisible("portletPreferencesValues", "false");
      assertTextVisible("portletRequest", "true");
      assertTextVisible("portletResponse", "true");
      assertTextVisible("headerRequest", "undefined");
      assertTextVisible("headerResponse", "undefined");
      assertTextVisible("clientDataRequest", "undefined");
      assertTextVisible("mimeResponse", "undefined");
      assertTextVisible("stateAwareResponse", "true");
      assertTextVisible("renderParams", "true");
      assertTextVisible("mutableRenderParams", "true");
      assertTextVisible("resourceParams", "undefined");
      assertTextVisible("actionParams", "undefined");
      assertTextVisible("portletContext", "true");
      assertTextVisible("portletMode", "true");
      assertTextVisible("windowState", "true");
      assertTextVisible("cookies", "true");
      assertTextVisible("locale", "false");
      assertTextVisible("locales", "true");
      assertTextVisible("namespace", "true");
      assertTextVisible("contextPath", "true");
      assertTextVisible("windowId", "true");
      assertTextVisible("portletName", "true");
   }

   private void assertTextVisible(String pageObject, String isPresent) {
      waitingAsserter.assertTrue(visibilityOfAllElementsLocatedBy(By.xpath(
            "//td[contains(text(),'" + pageObject + "')]" +
                  "/following-sibling::td[contains(text(),'" + isPresent + "')]")));
   }
}
