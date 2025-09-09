/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures.action;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.PortletQName;

import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott Nicklous
 *
 */
public class Action2 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @ActionMethod(portletName="portlet3",
         publishingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub1")
         })
   public void action1a(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action1a");
   }
   
   @ActionMethod(portletName="portlet3", actionName="Fred",
         publishingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub2")
         })
   public void action1b(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action1b");
   }
   
   @ActionMethod(portletName="portlet3", actionName="Barney",
         publishingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub3"),
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub4")
         })
   public void action1c(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action1c");
   }
   
   // duplicate method
   @ActionMethod(portletName="portlet2")
   public void action2(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action2");
   }
   
   // invalid signature
   @ActionMethod(portletName="portlet4")
   public void action4(ActionRequest req, ActionResponse resp, int y) {
      meths.addMethod(this.getClass().getSimpleName() + "#action3");
   }
   
   // invalid signature
   @ActionMethod(portletName="portlet5")
   public String action5(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action5");
      return null;
   }

   // duplicate method, same action name
   @ActionMethod(portletName="portlet6", actionName="Wilma")
   public void action6(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action6");
   }

   // duplicate method, same action name
   @ActionMethod(portletName="portlet6", actionName="Wilma")
   public void action7(ActionRequest req, ActionResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#action7");
   }

}
