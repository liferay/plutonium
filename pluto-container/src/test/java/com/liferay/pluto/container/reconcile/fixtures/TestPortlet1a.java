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
import jakarta.portlet.annotations.PortletQName;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * A bean portlet with configuration values.
 * 
 * @author Scott Nicklous
 *
 */
public class TestPortlet1a {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @ActionMethod(portletName="Portlet1", actionName="Fred", publishingEvents = {
         @PortletQName(namespaceURI="http://www.apache.org/", localPart="event2"),
         @PortletQName(namespaceURI="", localPart="event3"),
         @PortletQName(namespaceURI="unknown", localPart="anotherBad"),
   })
   public void doAction(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#doAction");
      meths.setConfigExists(false);
   }
   
   @EventMethod(portletName="Portlet1", 
         processingEvents = {
         @PortletQName(namespaceURI="http://www.apache.org/", localPart="event1"),
         @PortletQName(namespaceURI="", localPart="event3"),
         @PortletQName(namespaceURI="unknown", localPart="eventBad"),
   },
         publishingEvents = {
         @PortletQName(namespaceURI="", localPart="event4"),
   })
   public void doEvent(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#doEvent");
      meths.setConfigExists(false);
   }

}
