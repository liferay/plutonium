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
import com.liferay.pluto.container.om.portlet.InitParam;

/**
 * Init parameters for filters and portlets
 * 
 * @author Scott Nicklous
 *
 */
public class InitParamImpl implements InitParam {
   
   private String name;
   private String value;
   private final List<Description> descs = new ArrayList<Description>();
   
   /**
    * Copy Constructor
    * 
    * @param ipi
    */
   public InitParamImpl(InitParam ipi) {
      name = ipi.getParamName();
      value = ipi.getParamValue();
      for (Description desc : ipi.getDescriptions()) {
         descs.add(new DescriptionImpl(desc));
      }
   }
   
   /**
    * Constructor
    * @param name    init parameter name
    * @param value   init parameter value
    */
   public InitParamImpl(String name, String value) {
      this.name = name;
      this.value = value;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.InitParam#getParamName()
    */
   @Override
   public String getParamName() {
      return name;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.InitParam#getParamValue()
    */
   @Override
   public String getParamValue() {
      return value;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.InitParam#setParamValue(java.lang.String)
    */
   @Override
   public void setParamValue(String paramValue) {
      this.value = paramValue;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.InitParam#getDescription(java.util.Locale)
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
    * @see com.liferay.pluto.container.om.portlet.InitParam#getDescriptions()
    */
   @Override
   public List<Description> getDescriptions() {
      return new ArrayList<Description>(descs);
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.InitParam#addDescription(com.liferay.pluto.container.om.portlet.Description)
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
      result = prime * result + ((name == null) ? 0 : name.hashCode());
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
      InitParamImpl other = (InitParamImpl) obj;
      if (name == null) {
         if (other.name != null) {
            return false;
         }
      } else if (!name.equals(other.name)) {
         return false;
      }
      return true;
   }

}
