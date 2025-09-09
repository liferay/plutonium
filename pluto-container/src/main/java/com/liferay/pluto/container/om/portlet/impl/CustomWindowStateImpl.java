/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.pluto.container.om.portlet.CustomWindowState;
import com.liferay.pluto.container.om.portlet.Description;

/**
 * @author Scott Nicklous
 *
 */
public class CustomWindowStateImpl implements CustomWindowState {
   
   private String ws;
   private final List<Description> descs = new ArrayList<Description>();

   /**
    * constructor
    */
   public CustomWindowStateImpl(String cws) {
      ws = cws;
   }
   
   /**
    * Copy constructor
    */
   public CustomWindowStateImpl(CustomWindowState cws) {
      ws = cws.getWindowState();
      for (Description desc : cws.getDescriptions()) {
         descs.add(new DescriptionImpl(desc));
      }
   }
   
   /**
    * Constructor
    * @param ws      portlet mode name
    * @param mngd    portal managed flag
    * @param descs   description map
    */
   public CustomWindowStateImpl(String ws, List<Description> descs) {
      this.ws = ws;
      this.descs.addAll(descs);
   }
   
   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.CustomWindowState#getWindowState()
    */
   @Override
   public String getWindowState() {
      return ws;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.CustomWindowState#getDescription(java.util.Locale)
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
    * @see com.liferay.pluto.container.om.portlet.CustomWindowState#getDescriptions()
    */
   @Override
   public List<Description> getDescriptions() {
      return new ArrayList<Description>(descs);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.CustomWindowState#addDescription(Description)
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
      result = prime * result + ((ws == null) ? 0 : ws.hashCode());
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
      CustomWindowStateImpl other = (CustomWindowStateImpl) obj;
      if (ws == null) {
         if (other.ws != null) {
            return false;
         }
      } else if (!ws.equals(other.ws)) {
         return false;
      }
      return true;
   }

}
