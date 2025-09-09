/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.reconcile.fixtures;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.EventMethod;
import jakarta.portlet.annotations.InitParameter;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletQName;
import jakarta.portlet.annotations.RenderMethod;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * A bean portlet with configuration values.
 * 
 * @author Scott Nicklous
 *
 */
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
   }
)
public class TestPortlet2 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @RenderMethod(portletNames="Portlet2")
   public String myView() {
      meths.addMethod(this.getClass().getSimpleName() + "#myView");
      return null;
   }
   
   @ActionMethod(portletName="Portlet2", publishingEvents = {
         @PortletQName(namespaceURI="http://www.apache.org/", localPart="event1"),
         @PortletQName(namespaceURI="", localPart="event4"),
   })
   public void doAction(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#doAction");
   }
   
   @EventMethod(portletName="Portlet2", 
         processingEvents = {
         @PortletQName(namespaceURI="http://www.apache.org/", localPart="event2"),
         @PortletQName(namespaceURI="", localPart="event4"),
   },
         publishingEvents = {
         @PortletQName(namespaceURI="", localPart="event3"),
   })
   public void doEvent(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#doEvent");
   }

}
