/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.integration.test;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 *
 * @author Kyle Stiemann
 */
public class PortletConfigDemoIT extends DemoTestDriver {

   @Test
   public void testPortletConfigDemo() {
      navigateToPage("V3.0 PM and WS");

      // Portlet Modes for Portlet 1:
      assertTextVisible("Portlet Modes:", "MIME type: text/html, PortletMode: help, Allowed: true", 1);
      assertTextVisible("Portlet Modes:", "MIME type: text/html, PortletMode: view, Allowed: true", 1);
      assertTextVisible("Portlet Modes:", "MIME type: text/html, PortletMode: edit, Allowed: true", 1);
      assertTextVisible("Portlet Modes:", "MIME type: text/html, PortletMode: mymode_portalmanaged_3, Allowed: false",
            1);
      assertTextVisible("Portlet Modes:",
            "MIME type: text/html, PortletMode: mymode_nonportalmanaged_1, Allowed: false", 1);
      assertTextVisible("Portlet Modes:",
            "MIME type: text/html, PortletMode: mymode_nonportalmanaged_2, Allowed: false", 1);
      assertTextVisible("Portlet Modes:", "MIME type: text/vnd.wap.wml, PortletMode: view", 1);
      assertTextVisible("Portlet Modes:", "MIME type: text/vnd.wap.wml, PortletMode: mymode_nonportalmanaged_1", 1);

      // Window States for Portlet 1:
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: testwindowstate_2, Allowed: false", 1);
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: normal, Allowed: true", 1);
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: testwindowstate_1, Allowed: false", 1);
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: maximized, Allowed: true", 1);
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: minimized, Allowed: true", 1);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: testwindowstate_2", 1);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: normal", 1);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: testwindowstate_1", 1);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: maximized", 1);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: minimized", 1);

      // Public Render Parameters for Portlet 1:
      assertTextVisible("Public Render Parameters:",
            "Name: color, QName: {http://www.apache.org/portals/pluto/pub-render-params/ResourcePortlet}alt-color",
            1);
      assertTextVisible("Public Render Parameters:",
            "Name: photo, QName: {http://www.apache.org/portals/pluto/pub-render-params/ResourcePortlet}ph-imgName",
            1);

      // Portlet Context Info for Portlet 1:
      assertTextVisible("Portlet Context Info:", "Portlet application version: 3.0", 1);
      assertTextVisible("Portlet Context Info:", "ClassLoader:", 1);
      assertTextVisible("Portlet Context Info:", "Portlet context path: /v3-demo-portlet", 1);

      // Portlet Modes for Portlet 2:
      assertTextVisible("Portlet Modes:", "MIME type: text/html, PortletMode: help, Allowed: true", 2);
      assertTextVisible("Portlet Modes:", "MIME type: text/html, PortletMode: view, Allowed: true", 2);
      assertTextVisible("Portlet Modes:",
            "MIME type: text/html, PortletMode: mymode_nonportalmanaged_1, Allowed: false",
            2);
      assertTextVisible("Portlet Modes:",
            "MIME type: text/vnd.wap.wml, PortletMode: view", 2);

      // Window States for Portlet 2:
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: normal, Allowed: true", 2);
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: testwindowstate_1, Allowed: false", 2);
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: maximized, Allowed: true", 2);
      assertTextVisible("Window States:", "MIME type: text/html, WindowState: minimized, Allowed: true", 2);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: normal", 2);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: testwindowstate_1", 2);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: maximized", 2);
      assertTextVisible("Window States:", "MIME type: text/vnd.wap.wml, WindowState: minimized", 2);

      // Public Render Parameters for Portlet 2:
      assertTextVisible("Public Render Parameters:",
            "Name: color, QName: {http://www.apache.org/portals/pluto/pub-render-params/ResourcePortlet}alt-color",
            2);

      // Portlet Context Info for Portlet 2:
      assertTextVisible("Portlet Context Info:", "Portlet application version: 3.0", 2);
      assertTextVisible("Portlet Context Info:", "ClassLoader:", 2);
      assertTextVisible("Portlet Context Info:", "Portlet context path: /v3-demo-portlet", 2);
   }

   private void assertTextVisible(String title, String text, int portlet) {
      waitingAsserter.assertTrue(visibilityOfAllElementsLocatedBy(By.xpath(
            "(//h5[text()='" + title + "'])[" + portlet + "]/following-sibling::ul/" +
                  "li[contains(text(),'" + text + "')]")));
   }
}
