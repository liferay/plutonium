/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.portal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class RenderConfig {
   private static final Logger     LOG                = LoggerFactory.getLogger(RenderConfig.class);

   private Map<String, PageConfig> pages;
   private String                  defaultPageId;

   // internally used.
   private int                     orderNumberCounter = 0;
   private Comparator<PageConfig>  pageComparator;

   public RenderConfig() {
      this.pages = new java.util.HashMap<String, PageConfig>();
      this.pageComparator = new Comparator<PageConfig>() {
         public int compare(PageConfig pa, PageConfig pb) {
            if (pa.getOrderNumber() > pb.getOrderNumber()) {
               return 1;
            } else if (pa.getOrderNumber() == pb.getOrderNumber()) {
               return 0;
            } else {
               return -1;
            }
         }

         public boolean equals(Object a) {
            return false;
         }
      };
   }

   public String getDefaultPageId() {
      return defaultPageId;
   }

   public void setDefaultPageId(String defaultPageId) {
      this.defaultPageId = defaultPageId;
   }

   public List<PageConfig> getPages() {
      List<PageConfig> col = new ArrayList<PageConfig>(pages.values());
      Collections.sort(col, pageComparator);
      return col;
   }

   public PageConfig getPageConfig(String pageId) {
      if (pageId == null || "".equals(pageId)) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Requested page is null.  Returning default: " + defaultPageId);
         }
         pageId = defaultPageId;
      }
      // return (PageConfig) pages.get(pageId);

      // Make sure this is needed.
      // This is the PLUTO-251 fix submitted by Charles Severence. Thank you!!!
      // Sometimes pages come with a prefix of a slash - if the page is not
      // found, and the first character of the pageId is a slash we attempt
      // to look up the page without the slash.

      PageConfig retval = (PageConfig) pages.get(pageId);

      if (retval == null && pageId.startsWith("/") && pageId.length() > 2) {
         retval = (PageConfig) pages.get(pageId.substring(1));
      }

      if (retval == null) {
         LOG.warn("Couldn't find a PageConfig for page ID: [" + pageId + "]");
         if ((retval = (PageConfig) pages.get(defaultPageId)) != null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Returning default page ID: [" + defaultPageId + "]");
            }
         } else {
            LOG.error("Could not find default page Id for render config!");
         }
      }
      return retval;
   }

   public void addPage(PageConfig config) {
      config.setOrderNumber(orderNumberCounter++);
      pages.put(config.getName(), config);
   }

   public void removePage(PageConfig config) {
      pages.remove(config.getName());
   }

}
