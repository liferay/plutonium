/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.liferay.pluto.container.om.portlet.Preference;
import com.liferay.pluto.container.om.portlet.Preferences;

/**
 * @author Scott Nicklous
 *
 */
public class PreferencesImpl implements Preferences {
   
   private final Map<String, Preference> prefs = new HashMap<String, Preference>();
   private String prefVal;
   private boolean isNullValidator = false;

   /**
    * Default constructor
    */
   public PreferencesImpl() {
   }
   
   /**
    * Copy constructor
    * @param pr
    */
   public PreferencesImpl(Preferences pr) {
      for (Preference pref : pr.getPortletPreferences()) {
         prefs.put(pref.getName(), new PreferenceImpl(pref));
      }
      prefVal = pr.getPreferencesValidator();
      isNullValidator = pr.isNullValidator();
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.Preferences#getPortletPreference(java.lang.String)
    */
   @Override
   public Preference getPortletPreference(String name) {
      Preference pref = (prefs.get(name) == null) ? 
            null : new PreferenceImpl(prefs.get(name));
      return pref;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.Preferences#getPortletPreferences()
    */
   @Override
   public List<Preference> getPortletPreferences() {
      ArrayList<Preference> rPrefs = new ArrayList<Preference>();
      for (Preference pref : prefs.values()) {
         rPrefs.add(new PreferenceImpl(pref));
      }
      return rPrefs;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.Preferences#addPreference(com.liferay.pluto.container.om.portlet.Preference)
    */
   @Override
   public void addPreference(Preference pref) {
      prefs.put(pref.getName(), new PreferenceImpl(pref));
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.Preferences#getPreferencesValidator()
    */
   @Override
   public String getPreferencesValidator() {
      return prefVal;
   }

   /* (non-Javadoc)
    * @see com.liferay.pluto.container.om.portlet.Preferences#setPreferencesValidator(java.lang.String)
    */
   @Override
   public void setPreferencesValidator(String preferencesValidator) {
      prefVal = preferencesValidator;
   }

   /**
    * @return the isNullValidator
    */
   @Override
   public boolean isNullValidator() {
      return isNullValidator;
   }

   /**
    * @param isNullValidator the isNullValidator to set
    */
   @Override
   public void setNullValidator(boolean isNullValidator) {
      this.isNullValidator = isNullValidator;
   }

}
