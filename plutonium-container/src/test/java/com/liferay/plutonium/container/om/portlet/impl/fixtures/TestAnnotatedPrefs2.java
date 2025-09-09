/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl.fixtures;

import jakarta.portlet.PortletPreferences;
import jakarta.portlet.PreferencesValidator;
import jakarta.portlet.ValidatorException;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletConfigurations;
import jakarta.portlet.annotations.PortletPreferencesValidator;
import jakarta.portlet.annotations.Preference;

/**
 * Test for Portlet Preferences Validator annotation  
 *
 */
@PortletConfigurations({
   @PortletConfiguration(portletName = "portlet1", prefs= {
         @Preference(name="aPref", values="aValue"),
         @Preference(name="bPref", values="bValue")
   }),
   @PortletConfiguration(portletName = "portlet2", prefs = {
         @Preference(name="aPref", values="aValue", isReadOnly = true),
         @Preference(name="bPref", values= {"bValue", "cValue"}, isReadOnly = false)
   }),
   @PortletConfiguration(portletName = "portlet3", prefs = {
         @Preference(name="aPref", values="aValue", isReadOnly = true),
   }),
   @PortletConfiguration(portletName = "portlet5", prefs = {
         @Preference(name="aPref", values="aValue", isReadOnly = true),
   })
})

// validator applies to portlets 1, 3, & 5 only
@PortletPreferencesValidator(portletNames= {"portlet1", "portlet3", "portlet5"})
public class TestAnnotatedPrefs2 implements PreferencesValidator {

   @Override
   public void validate(PortletPreferences arg0) throws ValidatorException {
   }
}
