/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet;

import java.util.List;
import java.util.Locale;

public interface Listener {
    
   String getListenerClass();
   void setListenerClass(String cls);

   Description getDescription(Locale locale);
   List<Description> getDescriptions();
   void addDescription(Description desc);
   
   DisplayName getDisplayName(Locale locale);
   List<DisplayName> getDisplayNames();
   void addDisplayName(DisplayName dispName);
   
   String getListenerName();
   void setListenerName(String listenerName);
   
   int getOrdinal();
   void setOrdinal(int ordinal);
}