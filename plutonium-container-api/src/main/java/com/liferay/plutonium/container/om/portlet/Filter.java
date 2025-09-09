/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet;

import java.util.List;
import java.util.Locale;

public interface Filter {

   String getFilterName();

   Description getDescription(Locale locale);
   List<Description> getDescriptions();
   void addDescription(Description desc);

   DisplayName getDisplayName(Locale locale);
   List<DisplayName> getDisplayNames();
   void addDisplayName(DisplayName dn);

   String getFilterClass();
   void setFilterClass(String filterClass);

   InitParam getInitParam(String paramName);
   List<InitParam> getInitParams();
   void addInitParam(InitParam ip);

   List<String> getLifecycles();
   void addLifecycle(String lifecycle);

   // establishes ordering for annotated filters
   int getOrdinal();
   void setOrdinal(int ordinal);

   // marks if the ordinal was set (annotated filter only)
   boolean isOrdinalSet();
}