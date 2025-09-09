/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.integration.test;

import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.liferay.pluto.demo.integration.test.Util.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 *
 * @author Kyle Stiemann
 */
public class ChatRoomDemoIT extends DemoTestDriver {

   // Public Constants
   public static final String CHAT_ROOM_DEMO_URL = baseUrl + Util.encodeURL("Chat Room Demo");

   @Test
   public void testChatRoomDemos() throws UnsupportedEncodingException {
      driver.get(CHAT_ROOM_DEMO_URL);

      // 1. Clear any previous chat history.
      clickElement(driver, waitingAsserter, "input", "chat_room_demo_portlet", "BeanPortletDemo", "clear");

      String chatHistoryXpath = "(" + getXpath("div", "chat_room_demo_portlet", "BeanPortletDemo", "chatHistory") + ")[1]";
      waitingAsserter.assertTrue(textEmpty(By.xpath(chatHistoryXpath)));

      // 2. Open a new window/tab to the same page.
      String originalWindowHandle = driver.getWindowHandle();
      executeScript(driver, "window.open('" + CHAT_ROOM_DEMO_URL + "');");

      // 3. In the current window/tab, add a user named "me".
      // 4. Verify that an image appears for the user.
      // 5. Send the message "Hello you!".
      // 6. Verify that the message appears.
      // 7. Switch to the next window/tab.
      // 8. Verify that the message from "me" saying "Hello you!" appears.
      // 9. Add a user named "you".
      // 10. Verify that an image appears for the user.
      // 11. Send the message "Hello me!".
      // 12. Verify that the message appears.
      By chatHistoryMessageByXpath = By.xpath(chatHistoryXpath + "/p[text()][position() = last()]");
      String name = null;
      String message = null;

      for (String windowHandle : driver.getWindowHandles()) {

         // See #7
         driver.switchTo().window(windowHandle);

         String originalName = "me";
         name = originalName;
         String originalMessage = "Hello you!";
         message = originalMessage;

         // See #8.
         if (!originalWindowHandle.equals(windowHandle)) {

            name = "you";
            message = "Hello me!";
            assertMessageVisible(chatHistoryMessageByXpath, originalName, originalMessage);
         }

         // See #3 and #9.
         String formXpath = getXpath("form", "chat_room_demo_portlet", "BeanPortletDemo", "setParams");
         sendKeysToElement(driver, waitingAsserter, "(" + formXpath + "//input[@name='name'])[1]", name);
         clickElement(driver, waitingAsserter, "(" + formXpath + "//input[@value='send'][@type='submit'])[1]");

         // See #4 and #10.
         waitingAsserter.assertTrue(elementToBeClickable(By.xpath(getXpath("div", "chat_room_demo_portlet", "BeanPortletDemo", "image") + "/img")));

         // See #5 and #11.
         String messageInputXpath = "(" + getXpath("input", "chat_room_demo_portlet", "BeanPortletDemo", "msg") + ")[1]";
         sendKeysToElement(driver, waitingAsserter, messageInputXpath, message);
         clickElement(driver, waitingAsserter, "input", "chat_room_demo_portlet", "BeanPortletDemo", "send");

         // See #6 and #12.
         assertMessageVisible(chatHistoryMessageByXpath, name, message);
      }

      // 13. Switch back to the original window/tab and verify that the message from "Hello me!" message appears
      // there.
      driver.switchTo().window(originalWindowHandle);
      assertMessageVisible(chatHistoryMessageByXpath, name, message);
   }

   private void assertMessageVisible(By locator, String name, String message) {
      waitingAsserter.assertTrue(and(visibilityOfElementLocated(locator),
            textToBePresentInElementLocated(locator, name + ": " + message)));
   }
}
