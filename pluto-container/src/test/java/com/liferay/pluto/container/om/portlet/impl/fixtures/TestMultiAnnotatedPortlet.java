/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.fixtures;

import jakarta.portlet.GenericPortlet;
import jakarta.portlet.annotations.InitParameter;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.Multipart;
import jakarta.portlet.annotations.PortletApplication;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletConfigurations;

/**
 * @author nick
 *
 */
@PortletApplication(
      defaultNamespaceURI="https://www.apache.org/",
      resourceBundle="com.liferay.pluto.container.om.portlet.GoodBundle",
      version = "3.0"
)
@PortletConfigurations( {
   @PortletConfiguration(portletName="Portlet1", 
   initParams = {
         @InitParameter(name="color", value="#cafeba"),
      },
      description={
         @LocaleString("Portlet displaying the time in different time zones"),
      }, displayName={
         @LocaleString("Time Zone Clock Portlet"),
      }, title={
         @LocaleString("Annotated Portlet"),
      }, shortTitle={
         @LocaleString("Anno Portlet"),
      }, keywords={
         @LocaleString("One, Two, Three"),
      }
   ),
   @PortletConfiguration(portletName="Portlet2", 
   initParams = {
         @InitParameter(name="color", value="#def"),
      },
      description={
         @LocaleString(locale="de", value="Dieses Portlet zeigt die Zeit in verschiedenen Zeitzonen an")
      }, displayName={
         @LocaleString(locale="de", value="ZeitzonenPortlet")
      }, title={
         @LocaleString(locale="DE", value="Annotiertes Portlet")
      }, shortTitle={
         @LocaleString(locale="DE", value="Ein Portlet")
      }, keywords={
         @LocaleString(locale="DE", value="Eins, Zwei, Drei")
      },
      asyncSupported=true,
      multipart = @Multipart(supported=false)
   ),
   @PortletConfiguration(portletName="Portlet3", 
   initParams = {
         @InitParameter(name="color", value="#def"),
      },
      description={
         @LocaleString(locale="de", value="Dieses Portlet zeigt die Zeit in verschiedenen Zeitzonen an")
      }, displayName={
         @LocaleString(locale="de", value="ZeitzonenPortlet")
      }, title={
         @LocaleString(locale="DE", value="Annotiertes Portlet")
      }, shortTitle={
         @LocaleString(locale="DE", value="Ein Portlet")
      }, keywords={
         @LocaleString(locale="DE", value="Eins, Zwei, Drei")
      },
      asyncSupported=false,
      multipart = @Multipart(supported=true, location="/home", 
            fileSizeThreshold=11, maxFileSize=22, maxRequestSize=33)
   ),
})
public class TestMultiAnnotatedPortlet extends GenericPortlet {
   // add portlet methods
}
