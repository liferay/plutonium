/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.reconcile.fixtures;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.DestroyMethod;
import jakarta.portlet.annotations.EventMethod;
import jakarta.portlet.annotations.InitMethod;
import jakarta.portlet.annotations.PortletQName;
import jakarta.portlet.annotations.RenderMethod;
import jakarta.portlet.annotations.ServeResourceMethod;

import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;

/**
 * A bean portlet with no corresponding configuration. 
 * 
 * @author Scott Nicklous
 *
 */
@ApplicationScoped
public class TestPortlet4 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   private PortletConfig config;

   @InitMethod("Portlet4")
   public void init(PortletConfig config) {
      this.config = config;
      meths.addMethod(this.getClass().getSimpleName() + "#init");
      meths.setConfigExists(config != null);
   }
  
   @DestroyMethod("Portlet4")
   public void destroy() {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy");
      meths.setConfigExists(config != null);
   }   

   @RenderMethod(portletNames="Portlet4", portletMode="")
   public String myView() {
      meths.addMethod(this.getClass().getSimpleName() + "#myView");
      meths.setConfigExists(config != null);
      return null;
   }
   
   @ActionMethod(portletName="Portlet4", publishingEvents = {
         @PortletQName(namespaceURI="http://www.apache.org/", localPart="event1"),
         @PortletQName(namespaceURI="", localPart="event4"),
   })
   public void doAction(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#doAction");
      meths.setConfigExists(config != null);
   }
   
   @EventMethod(portletName="Portlet4", 
         processingEvents = {
         @PortletQName(namespaceURI="http://www.apache.org/", localPart="event2"),
         @PortletQName(namespaceURI="", localPart="event4"),
   },
         publishingEvents = {
         @PortletQName(namespaceURI="", localPart="event3"),
   })
   public void doEvent(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#doEvent");
      meths.setConfigExists(config != null);
   }

   @ServeResourceMethod(portletNames="Portlet4")
   public void res(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#res");
      meths.setConfigExists(config != null);
   }

}
