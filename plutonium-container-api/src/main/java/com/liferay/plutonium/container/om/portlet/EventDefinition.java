/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet;

import java.util.List;
import java.util.Locale;

import javax.xml.namespace.QName;

public interface EventDefinition
{
    QName getQName();
   void setQName(QName qn);
    
	List<QName> getAliases();
	void addAlias(QName qName);

	Description getDescription(Locale locale);
   List<Description> getDescriptions();
   void addDescription(Description desc);
   DisplayName getDisplayName(Locale locale);
   List<DisplayName> getDisplayNames();
   void addDisplayName(DisplayName desc);
	
	String getValueType();
	void setValueType(String valueType);

}