/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet;

import java.util.List;
import java.util.Locale;

public interface PortletInfo {

	String getTitle();
	void setTitle(String title);

   LocaleText getTitle(Locale locale);
   List<LocaleText> getTitles();
   void addTitle(LocaleText title);

	String getKeywords();
	void setKeywords(String keywords);

   LocaleText getKeywords(Locale locale);
   List<LocaleText> getKeywordsList();
   void addKeywords(LocaleText kw);

	String getShortTitle();
	void setShortTitle(String shortTitle);

   LocaleText getShortTitle(Locale locale);
   List<LocaleText> getShortTitles();
   void addShortTitle(LocaleText st);
}