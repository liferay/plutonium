/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.pluto.container.om.portlet.FilterMapping;

/**
 * @author Scott Nicklous
 *
 */
public class FilterMappingImpl implements FilterMapping {
   
   private String filterName;
   
   ArrayList<String> portletNames = new ArrayList<String>();
   
   /**
    * Copy constructor
    * @param fm
    */
   public FilterMappingImpl(FilterMapping fm) {
      this.filterName = fm.getFilterName();
      this.portletNames.addAll(fm.getPortletNames());
   }
   
   /**
    * Constructor
    * @param filterName the filter name
    */
   public FilterMappingImpl(String filterName) {
      this.filterName = filterName;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.FilterMapping#getFilterName()
    */
   @Override
   public String getFilterName() {
      return filterName;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.FilterMapping#getPortletNames()
    */
   @Override
   public List<String> getPortletNames() {
      return new ArrayList<String>(portletNames);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.FilterMapping#addPortletName(java.lang.String)
    */
   @Override
   public void addPortletName(String portletName) {
      portletNames.add(portletName);
   }
   
   @Override
   public boolean removePortletName(String pn) {
      return portletNames.remove(pn);
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((filterName == null) ? 0 : filterName.hashCode());
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
      FilterMappingImpl other = (FilterMappingImpl) obj;
      if (filterName == null) {
         if (other.filterName != null) {
            return false;
         }
      } else if (!filterName.equals(other.filterName)) {
         return false;
      }
      return true;
   }

}
