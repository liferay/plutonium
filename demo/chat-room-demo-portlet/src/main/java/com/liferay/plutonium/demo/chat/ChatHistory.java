/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.chat;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Manages the chat history by maintaining a list of messages entered. This is an
 * application scoped bean, so all portlet instances see the same view.
 * 
 * @author Scott Nicklous
 */
@ApplicationScoped
public class ChatHistory {

   private List<String>        messages = Collections.synchronizedList(new ArrayList<String>());
   
   // Injects the name bean in order to prepend the name to the message

   @Inject
   private NameBean            nameBean;

   /**
    * Adds a new chat message
    */
   public void addMessage(String message) {
      String name = nameBean.getName();
      if (name == null || name.length() == 0) {
         name = "World";
      }
      messages.add(name + ": " + message);
   }

   /**
    * returns the number of messages. Used in order to poll for changes.
    */
   public int getNumberOfMessages() {
      return messages.size();
   }

   /**
    * returns the markup for the stored messages
    */
   public String getMarkup() {
      StringBuilder txt = new StringBuilder(128);
      synchronized (messages) {
         for (String msg : messages) {
            txt.append("<p>").append(StringEscapeUtils.escapeHtml4(msg)).append("</p>\n");
         }
      }
      return txt.toString();
   }

   /**
    * clears all stored messages
    */
   public void clear() {
      messages.clear();
   }
}
