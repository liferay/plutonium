/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.Locale;

import com.liferay.plutonium.container.om.portlet.Description;

/**
 * A single locale-specific description
 * 
 * @author Scott Nicklous
 *
 */
public class DescriptionImpl extends LocaleTextImpl implements Description {
   
   /**
    * default: lang = english
    */
   public DescriptionImpl() {
      super();
   }
   
   /**
    * Constructor
    * @param locale     Locale language tag according to BCP 47
    * @param disp       description string
    */
   public DescriptionImpl(Locale locale, String desc) {
      super(locale, desc);
   }
   
   /**
    * Copy constructor
    */
   public DescriptionImpl(Description di) {
      super(di);
   }

}
