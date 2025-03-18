/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package org.apache.pluto.container.reconcile.fixtures;

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

import org.apache.pluto.container.bean.processor.fixtures.InvocationResults;

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
