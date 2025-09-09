/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.fixtures;

import jakarta.portlet.PortletPreferences;
import jakarta.portlet.PreferencesValidator;
import jakarta.portlet.ValidatorException;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletConfigurations;
import jakarta.portlet.annotations.PortletPreferencesValidator;

/**
 * Test for Portlet Preferences Validator annotation  
 *
 */
@PortletConfigurations({
   @PortletConfiguration(portletName = "portlet1"),
   @PortletConfiguration(portletName = "portlet2"),
   @PortletConfiguration(portletName = "portlet3")
})

// No portlet names specified, so validator should apply to all portlets in the app
@PortletPreferencesValidator
public class TestAnnotatedPrefs1 implements PreferencesValidator {

   @Override
   public void validate(PortletPreferences arg0) throws ValidatorException {
   }
}
