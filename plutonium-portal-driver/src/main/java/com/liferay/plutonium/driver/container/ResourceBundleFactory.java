/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.container;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.plutonium.container.om.portlet.PortletDefinition;
import com.liferay.plutonium.container.om.portlet.PortletInfo;
import com.liferay.plutonium.container.util.StringManager;

/**
 * Factory object used to create Portlet Resource Bundles.
 * 
 * 
 */
class ResourceBundleFactory {

   private static final Logger         LOG        = LoggerFactory.getLogger(ResourceBundleFactory.class);

   private static final StringManager  EXCEPTIONS = StringManager.getManager(ResourceBundleFactory.class.getPackage().getName());

   /**
    * The default (no local) resources bundle for this bundle.
    */
   private InlinePortletResourceBundle defaultBundle;

   /**
    * All of the previously loaded bundles.
    */
   private Map<Locale, ResourceBundle> bundles    = new HashMap<Locale, ResourceBundle>();

   /**
    * The name of the bundle.
    */
   private String                      bundleName;

   public ResourceBundleFactory(PortletDefinition dd, PortletInfo windowInfo) {
      bundleName = dd.getResourceBundle();

      PortletInfo info = dd.getPortletInfo();

      String defaultTitle = "[ " + dd.getPortletName() + " ]";

      if (info != null) {
         String title = windowInfo == null ? info.getTitle() : windowInfo.getTitle();
         String shortTitle = windowInfo == null ? info.getShortTitle() : windowInfo.getShortTitle();
         String keywords = windowInfo == null ? info.getKeywords() : windowInfo.getKeywords();

         // Set default values

         if (title == null) {
            title = defaultTitle;
         }
         if (shortTitle == null) {
            shortTitle = defaultTitle;
         }

         defaultBundle = new InlinePortletResourceBundle(title, shortTitle, keywords);
      } else {
         defaultBundle = new InlinePortletResourceBundle(defaultTitle, defaultTitle, "");
      }

      if (LOG.isDebugEnabled()) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Bundle name: ").append(bundleName);
         txt.append(", default ").append(defaultBundle.toString());
         LOG.debug(txt.toString());
      }
   }

   public ResourceBundle getResourceBundle(Locale locale) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Resource Bundle: " + bundleName + " : " + locale + " requested. ");
      }

      // If allready loaded for this local, return immediately!
      if (bundles.containsKey(locale)) {
         return bundles.get(locale);
      }

      try {
         ResourceBundle bundle = null;
         if (bundleName != null && bundleName.length() > 0) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            bundle = ResourceBundle.getBundle(bundleName, locale, loader);
            bundles.put(locale, new CombinedPortletResourceBundle(defaultBundle, bundle));
         } else {
            bundles.put(locale, defaultBundle);
         }
      } catch (MissingResourceException mre) {
         if (bundleName != null && LOG.isWarnEnabled()) {
            LOG.warn(EXCEPTIONS.getString("warning.resourcebundle.notfound", bundleName, mre.getMessage()));
         }
         if (LOG.isDebugEnabled()) {
            LOG.debug("Using default bundle for locale (" + locale + ").");
         }
         bundles.put(locale, defaultBundle);
      }
      return bundles.get(locale);
   }
}
