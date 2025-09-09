/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.Locale;

import com.liferay.plutonium.container.om.portlet.DisplayName;

/**
 * A single locale-specific display name
 * 
 * @author Scott Nicklous
 *
 */
public class DisplayNameImpl extends LocaleTextImpl implements DisplayName {
   
   /**
    * default: lang = english
    */
   public DisplayNameImpl() {
      super();
   }
   
   /**
    * Constructor
    * @param locale     Locale language tag according to BCP 47
    * @param disp       description string
    */
   public DisplayNameImpl(Locale locale, String desc) {
      super(locale, desc);
   }
   
   /**
    * Copy constructor
    */
   public DisplayNameImpl(DisplayName di) {
      super(di);
   }

}
