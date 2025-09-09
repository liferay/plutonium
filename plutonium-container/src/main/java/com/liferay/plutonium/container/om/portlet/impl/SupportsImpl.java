/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.plutonium.container.om.portlet.Supports;

/**
 * @author Scott Nicklous
 *
 */
public class SupportsImpl implements Supports {
   
   private String mime;
   private final ArrayList<String> portletModes = new ArrayList<String>();
   private final ArrayList<String> windowStates = new ArrayList<String>();
   
   /**
    * Copy constructor
    */
   public SupportsImpl(Supports sup) {
      mime = sup.getMimeType();
      for (String pm : sup.getPortletModes()) {
         portletModes.add(pm);
      }
      for (String ws : sup.getWindowStates()) {
         windowStates.add(ws);
      }
   }

   /**
    * Constructor
    */
   public SupportsImpl(String mt) {
      mime = mt;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Supports#getMimeType()
    */
   @Override
   public String getMimeType() {
      return mime;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Supports#setMimeType(java.lang.String)
    */
   @Override
   public void setMimeType(String mt) {
      mime = mt;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Supports#getPortletModes()
    */
   @Override
   public List<String> getPortletModes() {
      return new ArrayList<String>(portletModes);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Supports#addPortletMode(java.lang.String)
    */
   @Override
   public void addPortletMode(String portletMode) {
      portletModes.add(portletMode);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Supports#getWindowStates()
    */
   @Override
   public List<String> getWindowStates() {
      return new ArrayList<String>(windowStates);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Supports#addWindowState(java.lang.String)
    */
   @Override
   public void addWindowState(String windowState) {
      windowStates.add(windowState);
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((mime == null) ? 0 : mime.hashCode());
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
      SupportsImpl other = (SupportsImpl) obj;
      if (mime == null) {
         if (other.mime != null) {
            return false;
         }
      } else if (!mime.equals(other.mime)) {
         return false;
      }
      return true;
   }

}
