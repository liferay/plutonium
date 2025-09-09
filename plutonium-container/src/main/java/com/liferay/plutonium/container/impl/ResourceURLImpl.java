/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import jakarta.portlet.MutableResourceParameters;
import jakarta.portlet.ResourceURL;

import com.liferay.plutonium.container.PortletMimeResponseContext;
import com.liferay.plutonium.container.PortletURLProvider;
import com.liferay.plutonium.container.util.ArgumentUtility;

/**
 * @author nick
 * 
 */
public class ResourceURLImpl extends BaseURLImpl implements ResourceURL {

   private String cacheLevel = ResourceURL.PAGE;

   /**
    * @param responseContext
    */
   public ResourceURLImpl(PortletMimeResponseContext responseContext) {
      super(responseContext, 
            responseContext.getPortletURLProvider(PortletURLProvider.TYPE.RESOURCE));
   }

   /**
    * @param responseContext
    * @param cacheLevel
    */
   public ResourceURLImpl(PortletMimeResponseContext responseContext,
         String cacheLevel) {
      this(responseContext);
      if (cacheLevel != null) {
         this.cacheLevel = cacheLevel;
      }
      urlProvider.setCacheability(this.cacheLevel);
   }

   public String getCacheability() {
      return urlProvider.getCacheability();
   }

   public void setCacheability(String cacheLevel) {
      ArgumentUtility.validateNotEmpty("cachelevel", cacheLevel);
      if (FULL.equals(cacheLevel)) {
         // always OK
      } else if (PORTLET.equals(cacheLevel)) {
         if (FULL.equals(this.cacheLevel)) {
            throw new IllegalStateException(
                  "Current request cacheablility is FULL: URLs with cacheability PORTLET not allowed");
         }

      } else if (PAGE.equals(cacheLevel)) {
         if (FULL.equals(this.cacheLevel)) {
            throw new IllegalStateException(
                  "Current request cacheablility is FULL: URLs with cacheability PORTLET not allowed");
         } else if (PORTLET.equals(this.cacheLevel)) {
            throw new IllegalStateException(
                  "Current request cacheablility is PORTLET: URLs with cacheability PAGE not allowed");
         }
      } else {
         throw new IllegalArgumentException("Unknown cacheLevel: " + cacheLevel);
      }
      urlProvider.setCacheability(cacheLevel);
   }

   public void setResourceID(String resourceID) {
      urlProvider.setResourceID(resourceID);
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.portlet.ResourceURL#getResourceParameters()
    */
   public MutableResourceParameters getResourceParameters() {
      return new MutableResourceParametersImpl(urlProvider, windowId);
   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.portlet.ResourceURL#getResourceID()
    */
   public String getResourceID() {
      return urlProvider.getResourceID();
   }

}
