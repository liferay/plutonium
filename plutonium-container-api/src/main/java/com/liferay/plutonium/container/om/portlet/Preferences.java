/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet;

import java.util.List;

public interface Preferences {

   Preference getPortletPreference(String name);
   List<Preference> getPortletPreferences();
   void addPreference(Preference pref);

   String getPreferencesValidator();
   void setPreferencesValidator(String preferencesValidator);

   boolean isNullValidator();
   void setNullValidator(boolean isNullValidator);
}