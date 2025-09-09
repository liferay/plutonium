/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.plutonium.container.om.portlet.ContainerRuntimeOption;

/**
 * Represents a single container runtime option
 * 
 * @author Scott Nicklous
 *
 */
public class ContainerRuntimeOptionImpl implements ContainerRuntimeOption {
   
   private String name;
   private final ArrayList<String> values = new ArrayList<String>();
   
   /**
    * Constructs container rt options object
    * @param name    Runtime option name
    * @param vals    Runtime option value
    */
   public ContainerRuntimeOptionImpl(String name, List<String> vals) {
      this.name = name;
      values.addAll(vals);
   }
   
   /**
    * Copy constructor
    * @param croi
    */
   public ContainerRuntimeOptionImpl(ContainerRuntimeOption croi) {
      this.name = croi.getName();
      values.addAll(croi.getValues());
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.ContainerRuntimeOption#getName()
    */
   @Override
   public String getName() {
      return name;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.ContainerRuntimeOption#getValues()
    */
   @Override
   public List<String> getValues() {
      return new ArrayList<String>(values);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.ContainerRuntimeOption#addValue(java.lang.String)
    */
   @Override
   public void addValue(String value) {
      values.add(value);
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
      ContainerRuntimeOptionImpl other = (ContainerRuntimeOptionImpl) obj;
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
