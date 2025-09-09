/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.plutonium.container.om.portlet.Description;
import com.liferay.plutonium.container.om.portlet.DisplayName;
import com.liferay.plutonium.container.om.portlet.Listener;

/**
 * A single event declaration
 * 
 * @author Scott Nicklous
 */
public class ListenerImpl implements Listener {
   
   private final List<Description> descs = new ArrayList<Description>();
   private final List<DisplayName> dispNames = new ArrayList<DisplayName>();
   private String listenerClass = "";
   private String listenerName = "";
   private int ordinal = 0;

   
   /**
    * Copy constructor
    * 
    * @param lis
    */
   public ListenerImpl(Listener lis) {
      for (Description desc : lis.getDescriptions()) {
         descs.add(new DescriptionImpl(desc));
      }
      for (DisplayName disp : lis.getDisplayNames()) {
         dispNames.add(new DisplayNameImpl(disp));
      }
      listenerClass = lis.getListenerClass();
      listenerName = lis.getListenerName();
      ordinal = lis.getOrdinal();
   }
   
   /**
    * Constructor
    * @param fn   Listener name
    */
   public ListenerImpl(String cls) {
      this.listenerClass = cls;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Listener#getDescription(java.util.Locale)
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
    * @see com.liferay.plutonium.container.om.portlet.Listener#getDescriptions()
    */
   @Override
   public List<Description> getDescriptions() {
      return new ArrayList<Description>(descs);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Listener#addDescription(com.liferay.plutonium.container.om.portlet.Description)
    */
   @Override
   public void addDescription(Description desc) {
      descs.add(desc);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Listener#getDisplayName(java.util.Locale)
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
    * @see com.liferay.plutonium.container.om.portlet.Listener#getDisplayNames()
    */
   @Override
   public List<DisplayName> getDisplayNames() {
      return new ArrayList<DisplayName>(dispNames);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Listener#addDisplayName(com.liferay.plutonium.container.om.portlet.DisplayName)
    */
   @Override
   public void addDisplayName(DisplayName desc) {
      dispNames.add(desc);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Listener#getListenerClass()
    */
   @Override
   public String getListenerClass() {
      return listenerClass;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Listener#setListenerClass(java.lang.String)
    */
   @Override
   public void setListenerClass(String filterClass) {
      listenerClass = filterClass;
   }

   /**
    * @return the listenerName
    */
   @Override
   public String getListenerName() {
      return listenerName;
   }

   /**
    * @param listenerName the listenerName to set
    */
   @Override
   public void setListenerName(String listenerName) {
      this.listenerName = listenerName;
   }

   /**
    * @return the ordinal
    */
   @Override
   public int getOrdinal() {
      return ordinal;
   }

   /**
    * @param ordinal the ordinal to set
    */
   @Override
   public void setOrdinal(int ordinal) {
      this.ordinal = ordinal;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((listenerClass == null) ? 0 : listenerClass.hashCode());
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
      ListenerImpl other = (ListenerImpl) obj;
      if (listenerClass == null) {
         if (other.listenerClass != null) {
            return false;
         }
      } else if (!listenerClass.equals(other.listenerClass)) {
         return false;
      }
      return true;
   }

}
