/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.namespace.QName;

import com.liferay.pluto.container.om.portlet.Description;
import com.liferay.pluto.container.om.portlet.DisplayName;
import com.liferay.pluto.container.om.portlet.PublicRenderParameter;

/**
 * Public render parameters
 * 
 * @author Scott Nicklous
 *
 */
public class PublicRenderParameterImpl implements PublicRenderParameter {
   
   private QName qname;
   private String id;
   private final List<QName> aliases = new ArrayList<QName>();
   private final List<Description> descs = new ArrayList<Description>();
   private final List<DisplayName> dispNames = new ArrayList<DisplayName>();

   /**
    * Copy constructor
    * @param edi
    */
   public PublicRenderParameterImpl(PublicRenderParameter pri) {
      QName priqn = pri.getQName();
      this.qname = new QName(priqn.getNamespaceURI(), priqn.getLocalPart());
      this.id = pri.getIdentifier();
      for (QName qn : pri.getAliases()) {
         this.aliases.add(new QName(qn.getNamespaceURI(), qn.getLocalPart()));
      }
      for (Description desc : pri.getDescriptions()) {
         descs.add(new DescriptionImpl(desc));
      }
      for (DisplayName dn : pri.getDisplayNames()) {
         dispNames.add(new DisplayNameImpl(dn));
      }
   }
   
   /**
    * Basic constructor
    */
   public PublicRenderParameterImpl(QName qn, String id) {
      this.qname = qn;
      this.id = id;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#getQName()
    */
   @Override
   public QName getQName() {
      return qname;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#getIdentifier()
    */
   @Override
   public String getIdentifier() {
      return id;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#getDescription(java.util.Locale)
    */
   @Override
   public Description getDescription(Locale locale) {
      Description ret = null;
      for (Description desc : descs) {
         if (desc.getLocale().equals(locale)) {
            ret = desc;
         }
      }
      return ret;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#getDescriptions()
    */
   @Override
   public List<Description> getDescriptions() {
      return new ArrayList<Description>(descs);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#addDescription(com.liferay.pluto.container.om.portlet.Description)
    */
   @Override
   public void addDescription(Description desc) {
      descs.add(desc);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#getDisplayName(java.util.Locale)
    */
   @Override
   public DisplayName getDisplayName(Locale locale) {
      DisplayName ret = null;
      for (DisplayName dn : dispNames) {
         if (dn.getLocale().equals(locale)) {
            ret = dn;
         }
      }
      return ret;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#getDisplayNames()
    */
   @Override
   public List<DisplayName> getDisplayNames() {
      return new ArrayList<DisplayName>(dispNames);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#addDisplayName(com.liferay.pluto.container.om.portlet.DisplayName)
    */
   @Override
   public void addDisplayName(DisplayName dn) {
      dispNames.add(dn);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#getAliases()
    */
   @Override
   public List<QName> getAliases() {
      return new ArrayList<QName>(aliases);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.PublicRenderParameter#addAlias(javax.xml.namespace.QName)
    */
   @Override
   public void addAlias(QName qName) {
      aliases.add(qName);
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
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
      PublicRenderParameterImpl other = (PublicRenderParameterImpl) obj;
      if (id == null) {
         if (other.id != null) {
            return false;
         }
      } else if (!id.equals(other.id)) {
         return false;
      }
      return true;
   }

}
