/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.plutonium.container.om.portlet.Preference;

/**
 * @author Scott Nicklous
 *
 */
public class PreferenceImpl implements Preference {
   
   private String name;
   private boolean isReadOnly;
   private final ArrayList<String> values = new ArrayList<String>();
   
   public PreferenceImpl() {
      name = "";
      isReadOnly = false;
   }
   
   /**
    * Copy constructor
    * @param pi
    */
   public PreferenceImpl(Preference pi) {
      this.name = pi.getName();
      this.isReadOnly = pi.isReadOnly();
      this.values.addAll(pi.getValues());
   }
   
   /**
    * Basic Constructor
    * @param name
    * @param isRO
    * @param vals
    */
   public PreferenceImpl(String name, boolean isRO, List<String> vals) {
      this.name = name;
      this.isReadOnly = isRO;
      values.addAll(vals);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Preference#getName()
    */
   @Override
   public String getName() {
      return name;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Preference#getValues()
    */
   @Override
   public List<String> getValues() {
      return new ArrayList<String>(values);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Preference#addValue(java.lang.String)
    */
   @Override
   public void addValue(String value) {
      values.add(value);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Preference#isReadOnly()
    */
   @Override
   public boolean isReadOnly() {
      return isReadOnly;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Preference#setReadOnly(boolean)
    */
   @Override
   public void setReadOnly(boolean readOnly) {
      isReadOnly = readOnly;
   }

}
