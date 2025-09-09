/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl.fixtures;

import jakarta.portlet.GenericPortlet;
import jakarta.portlet.annotations.InitParameter;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletConfiguration;

/**
 * This test class carries a portlet application annotation to define the portlet
 * app along with a portlet configurations annotation that contains configuration 
 * information for two portlets.
 * 
 * @author Scott Nicklous
 *
 */
@PortletConfiguration(portletName="Annotated Generic Portlet", 
initParams = {
      @InitParameter(name="AnnoName", value="value", description= {@LocaleString(locale="DE", value="Beschreibung")}),
      @InitParameter(name="ipname", value="ipvalue", description= {@LocaleString("description")}),
      @InitParameter(name="nullValueParam", value="")
   },
   description={
      @LocaleString("Portlet displaying the time in different time zones"),
      @LocaleString(locale="de", value="Dieses Portlet zeigt die Zeit in verschiedenen Zeitzonen an")
   }, displayName={
      @LocaleString("Time Zone Clock Portlet"),
      @LocaleString(locale="de", value="ZeitzonenPortlet")
   }, title={
      @LocaleString("Annotated Portlet"),
      @LocaleString(locale="DE", value="Annotiertes Portlet")
   }, shortTitle={
      @LocaleString("Anno Portlet"),
      @LocaleString(locale="DE", value="Ein Portlet")
   }, keywords={
      @LocaleString("One, Two, Three"),
      @LocaleString(locale="DE", value="Eins, Zwei, Drei")
   }
)
public class TestAnnotatedGenericPortlet extends GenericPortlet {
   // add portlet methods
}
