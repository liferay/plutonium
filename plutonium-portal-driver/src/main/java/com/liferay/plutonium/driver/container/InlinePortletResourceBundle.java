/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.container;

import java.util.ArrayList;
import java.util.ListResourceBundle;

/**
 * InlinePortletResourceBundle implementation which provides the inline title, short-title, and keywords as properties
 * from the bundle.
 * 
 * @version 1.0
 * @since Jan 9, 2006
 */
class InlinePortletResourceBundle extends ListResourceBundle {

   private final static String TITLE_KEY       = "jakarta.portlet.title";
   private final static String SHORT_TITLE_KEY = "jakarta.portlet.short-title";
   private final static String KEYWORDS_KEY    = "jakarta.portlet.keywords";

   private Object[][]          contents;

   public InlinePortletResourceBundle(Object[][] contents) {
      this.contents = contents;
   }

   public InlinePortletResourceBundle(String title, String shortTitle, String keywords) {
      ArrayList<Object[]> temp = new ArrayList<Object[]>();
      if (title != null)
         temp.add(new Object[] { TITLE_KEY, title });

      if (shortTitle != null)
         temp.add(new Object[] { SHORT_TITLE_KEY, shortTitle });

      if (keywords != null)
         temp.add(new Object[] { KEYWORDS_KEY, keywords });

      contents = temp.toArray(new Object[temp.size()][]);
   }

   @Override
   protected Object[][] getContents() {
      return contents;
   }
   
   @Override
   public String toString() {
      StringBuilder txt = new StringBuilder(128);
      txt.append("Bundle contents: ");
      String sep = "";
      for (Object[] entry : contents) {
         if (entry.length == 2) {
            txt.append(sep).append(entry[0]).append("=").append(entry[1]);
            sep = ", ";
         }
      }
      return txt.toString();
   }
}
