/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.plutonium.container.om.portlet.CustomPortletMode;
import com.liferay.plutonium.container.om.portlet.Description;

/**
 * @author Scott Nicklous
 *
 */
public class CustomPortletModeImpl implements CustomPortletMode {
   
   private String pm;
   private boolean managed;
   private final List<Description> descs = new ArrayList<Description>();

   /**
    * constructor
    */
   public CustomPortletModeImpl(String cpm) {
      pm = cpm;
      managed = true;
   }
   
   /**
    * Copy constructor
    */
   public CustomPortletModeImpl(CustomPortletMode cpm) {
      pm = cpm.getPortletMode();
      managed = cpm.isPortalManaged();
      for (Description desc : cpm.getDescriptions()) {
         descs.add(new DescriptionImpl(desc));
      }
   }
   
   /**
    * Constructor
    * @param pm      portlet mode name
    * @param mngd    portal managed flag
    * @param descs   description map
    */
   public CustomPortletModeImpl(String pm, boolean mngd, List<Description> descs) {
      this.pm = pm;
      this.managed = mngd;
      this.descs.addAll(descs);
   }
   
   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.CustomPortletMode#getPortletMode()
    */
   @Override
   public String getPortletMode() {
      return pm;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.CustomPortletMode#isPortalManaged()
    */
   @Override
   public boolean isPortalManaged() {
      return managed;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.CustomPortletMode#setPortalManaged(boolean)
    */
   @Override
   public void setPortalManaged(boolean portalManaged) {
      managed = portalManaged;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.CustomPortletMode#getDescription(java.util.Locale)
    */
   @Override
   public Description getDescription(Locale locale) {
      Description ret = null;
      for (Description item : descs) {
         if (item.getLocale().equals(locale)) {
            ret = new DescriptionImpl(item);
         }
      }
      return ret;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.CustomPortletMode#getDescriptions()
    */
   @Override
   public List<Description> getDescriptions() {
      return new ArrayList<Description>(descs);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.CustomPortletMode#addDescription(Description)
    */
   @Override
   public void addDescription(Description desc) {
      descs.add(desc);
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((pm == null) ? 0 : pm.hashCode());
      return result;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      CustomPortletModeImpl other = (CustomPortletModeImpl) obj;
      if (pm == null) {
         if (other.pm != null) {
            return false;
         }
      } else if (!pm.equals(other.pm)) {
         return false;
      }
      return true;
   }

}
