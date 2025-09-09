/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.pluto.container.om.portlet.Description;
import com.liferay.pluto.container.om.portlet.SecurityRoleRef;

/**
 * A single event declaration
 * 
 * @author Scott Nicklous
 */
public class SecurityRoleRefImpl implements SecurityRoleRef {
   
   private String roleLink;
   private String roleName;
   private final List<Description> descs = new ArrayList<Description>();
   
   
   /**
    * Copy constructor
    * @param srr
    */
   public SecurityRoleRefImpl(SecurityRoleRef srr) {
      this.roleName = srr.getRoleName();
      this.roleLink = srr.getRoleLink();
      for (Description desc : srr.getDescriptions()) {
         descs.add(new DescriptionImpl(desc));
      }
   }

   /**
    * Constructor
    * @param roleName      Role name
    */
   public SecurityRoleRefImpl(String roleName) {
      this.roleName = roleName;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityRoleRef#getRoleName()
    */
   @Override
   public String getRoleName() {
      return roleName;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityRoleRef#getRoleLink()
    */
   @Override
   public String getRoleLink() {
      return roleLink;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityRoleRef#setRoleLink(String)
    */
   @Override
   public void setRoleLink(String roleLink) {
      this.roleLink = roleLink;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityRoleRef#getDescription(java.util.Locale)
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
    * @see com.liferay.pluto.container.om.portlet.SecurityRoleRef#getDescriptions()
    */
   @Override
   public List<Description> getDescriptions() {
      return new ArrayList<Description>(descs);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.SecurityRoleRef#addDescription(com.liferay.pluto.container.om.portlet.Description)
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
      result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
      SecurityRoleRefImpl other = (SecurityRoleRefImpl) obj;
      if (roleName == null) {
         if (other.roleName != null) {
            return false;
         }
      } else if (!roleName.equals(other.roleName)) {
         return false;
      }
      return true;
   }

}
