/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package com.liferay.pluto.container.om.portlet.impl.fixtures;

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
