/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.Locale;

import com.liferay.plutonium.container.om.portlet.LocaleText;

/**
 * A single locale-specific text
 * 
 * @author Scott Nicklous
 *
 */
public class LocaleTextImpl implements LocaleText {
   
   private Locale locale;
   private String text;
   
   /**
    * default: lang = english
    */
   public LocaleTextImpl() {
      locale = Locale.ENGLISH;
      text = "";
   }
   
   /**
    * Constructor
    * @param locale     Locale language tag according to BCP 47
    * @param disp       text string
    */
   public LocaleTextImpl(Locale locale, String text) {
      this.locale = locale;
      this.text = text;
   }
   
   /**
    * Copy constructor
    */
   public LocaleTextImpl(LocaleText lt) {
      this.locale = (Locale) lt.getLocale().clone();
      this.text = lt.getText();
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Text#getLang()
    */
   @Override
   public String getLang() {
      return locale.toLanguageTag();
   }
   
   @Override
   public void setLang(String lang) {
      this.locale = Locale.forLanguageTag(lang);
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Text#getText()
    */
   @Override
   public String getText() {
      return text;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Text#setText(java.lang.String)
    */
   @Override
   public void setText(String text) {
      this.text = text;
   }

   /* (non-Javadoc)
    * @see com.liferay.plutonium.container.om.portlet.Text#getLocale()
    */
   @Override
   public Locale getLocale() {
      return Locale.forLanguageTag(locale.toLanguageTag());
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((locale == null) ? 0 : locale.hashCode());
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
      LocaleTextImpl other = (LocaleTextImpl) obj;
      if (locale == null) {
         if (other.locale != null) {
            return false;
         }
      } else if (!locale.equals(other.locale)) {
         return false;
      }
      return true;
   }

}
