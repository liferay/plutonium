/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures.action;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.EventDefinition;
import jakarta.portlet.annotations.PortletApplication;
import jakarta.portlet.annotations.PortletQName;
import jakarta.portlet.annotations.RenderMethod;

import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott Nicklous
 *
 */
@PortletApplication(
      defaultNamespaceURI="https://www.java.net/",
      events = {
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="pub1")),
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="pub2")),
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="pub3")),
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="pub4")),
            @EventDefinition(qname=@PortletQName(namespaceURI="", localPart="event4"))
      }
      )
public class Action1 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @ActionMethod(portletName="portlet1", 
         publishingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub1")
         })
   public void action1(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action1");
   }
   
   @ActionMethod(portletName="portlet2")
   public void action2(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action2");
   }
   
   // dummy render method to keep config processor happy
   
   @RenderMethod(portletNames= {"portlet1", "portlet2", "portlet3", "portlet6"})
   public void render1() {}

}
