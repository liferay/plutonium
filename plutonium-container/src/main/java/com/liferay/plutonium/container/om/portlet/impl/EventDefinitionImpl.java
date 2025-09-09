/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.namespace.QName;

import com.liferay.plutonium.container.om.portlet.Description;
import com.liferay.plutonium.container.om.portlet.DisplayName;
import com.liferay.plutonium.container.om.portlet.EventDefinition;

/**
 * A single event declaration
 * 
 * @author Scott Nicklous
 */
public class EventDefinitionImpl implements EventDefinition {
   
   private QName qn;
   private final ArrayList<QName> aliases = new ArrayList<QName>();
   private final List<Description> descs = new ArrayList<Description>();
   private final List<DisplayName> dispNames = new ArrayList<DisplayName>();
   private String valType;

   /**
    * Copy constructor
    * @param edi
    */
   public EventDefinitionImpl(EventDefinition edi) {
      this.qn = new QName(edi.getQName().getNamespaceURI(), edi.getQName().getLocalPart());
      this.valType = edi.getValueType();
      for (QName qn : edi.getAliases()) {
         this.aliases.add(new QName(qn.getNamespaceURI(), qn.getLocalPart()));
      }
      for (Description desc : edi.getDescriptions()) {
         descs.add(new DescriptionImpl(desc));
      }
      for (DisplayName disp : edi.getDisplayNames()) {
         dispNames.add(new DisplayNameImpl(disp));
      }
   }
   
   /**
    * Basic constructor
    * @param qn
    */
   public EventDefinitionImpl(QName qn) {
      this.qn = qn;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#getQName()
    */
   @Override
   public QName getQName() {
      return qn;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#setQName(javax.xml.namespace.QName)
    */
   @Override
   public void setQName(QName qn) {
      this.qn = qn;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#getAliases()
    */
   @Override
   public List<QName> getAliases() {
      return new ArrayList<QName>(aliases);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#addAlias(javax.xml.namespace.QName)
    */
   @Override
   public void addAlias(QName qName) {
      aliases.add(qName);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#getDescription(java.util.Locale)
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
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#getDescriptions()
    */
   @Override
   public List<Description> getDescriptions() {
      return new ArrayList<Description>(descs);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#addDescription(com.liferay.plutonium.container.om.portlet.Description)
    */
   @Override
   public void addDescription(Description desc) {
      descs.add(desc);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#getDisplayName(java.util.Locale)
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
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#getDisplayNames()
    */
   @Override
   public List<DisplayName> getDisplayNames() {
      return new ArrayList<DisplayName>(dispNames);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#addDisplayName(com.liferay.plutonium.container.om.portlet.DisplayName)
    */
   @Override
   public void addDisplayName(DisplayName desc) {
      dispNames.add(desc);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#getValueType()
    */
   @Override
   public String getValueType() {
      return valType;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.EventDefinition#setValueType(java.lang.String)
    */
   @Override
   public void setValueType(String valueType) {
      valType = valueType;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((qn == null) ? 0 : qn.hashCode());
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
      EventDefinitionImpl other = (EventDefinitionImpl) obj;
      if (qn == null) {
         if (other.qn != null) {
            return false;
         }
      } else if (!qn.equals(other.qn)) {
         return false;
      }
      return true;
   }

}
