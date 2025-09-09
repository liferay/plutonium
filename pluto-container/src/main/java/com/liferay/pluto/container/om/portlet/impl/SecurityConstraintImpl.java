/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.pluto.container.om.portlet.DisplayName;
import com.liferay.pluto.container.om.portlet.SecurityConstraint;
import com.liferay.pluto.container.om.portlet.UserDataConstraint;

/**
 * Security Constraint
 * 
 * @author Scott Nicklous
 *
 */
public class SecurityConstraintImpl implements SecurityConstraint {
   
   private UserDataConstraint udc;
   private final List<DisplayName> dispNames = new ArrayList<DisplayName>();
   private final ArrayList<String> portletNames = new ArrayList<String>();
   
   
   /**
    * Copy constructor
    */
   public SecurityConstraintImpl(SecurityConstraint sc) {
      this.udc = sc.getUserDataConstraint();
      for (DisplayName disp : sc.getDisplayNames()) {
         dispNames.add(new DisplayNameImpl(disp));
      }
      for (String pn : sc.getPortletNames()) {
         this.portletNames.add(new String(pn));
      }
   }

   /**
    * Constructor
    */
   public SecurityConstraintImpl(UserDataConstraint udc) {
      this.udc = new UserDataConstraintImpl(udc);
   }
   
   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityConstraint#getUserDataConstraint()
    */
   @Override
   public UserDataConstraint getUserDataConstraint() {
      return new UserDataConstraintImpl(udc);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityConstraint#getDisplayName(java.util.Locale)
    */
   @Override
   public DisplayName getDisplayName(Locale locale) {
      DisplayName ret = null;
      for (DisplayName item : dispNames) {
         if (item.getLocale().equals(locale)) {
            ret = new DisplayNameImpl(item);
         }
      }
      return ret;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityConstraint#getDisplayNames()
    */
   @Override
   public List<DisplayName> getDisplayNames() {
      return new ArrayList<DisplayName>(dispNames);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityConstraint#addDisplayName(com.liferay.pluto.container.om.portlet.DisplayName)
    */
   @Override
   public void addDisplayName(DisplayName desc) {
      dispNames.add(desc);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityConstraint#getPortletNames()
    */
   @Override
   public List<String> getPortletNames() {
      return new ArrayList<String>(portletNames);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityConstraint#addPortletName(java.lang.String)
    */
   @Override
   public void addPortletName(String portletName) {
      portletNames.add(portletName);
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((udc == null) ? 0 : udc.hashCode());
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
      SecurityConstraintImpl other = (SecurityConstraintImpl) obj;
      if (udc == null) {
         if (other.udc != null) {
            return false;
         }
      } else if (!udc.equals(other.udc)) {
         return false;
      }
      return true;
   }

}
