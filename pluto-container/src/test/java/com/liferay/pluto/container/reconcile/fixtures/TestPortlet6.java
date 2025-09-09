/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.reconcile.fixtures;

import java.io.IOException;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.ProcessAction;
import jakarta.portlet.ProcessEvent;
import jakarta.portlet.RenderMode;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.annotations.EventDefinition;
import jakarta.portlet.annotations.PortletApplication;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletQName;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * Portlet for testing invocation of GenericPortlet method features.
 *  
 * @author Scott Nicklous
 *
 */
@PortletApplication(
      defaultNamespaceURI="https://www.java.net/",
      events = {
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org/", localPart="event1")),
            @EventDefinition(qname=@PortletQName(namespaceURI="", localPart="event4"))
      }
)
@PortletConfiguration(portletName="Portlet6")
public class TestPortlet6 extends GenericPortlet {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();

   @Override
   public void init() {
      meths.addMethod(this.getClass().getSimpleName() + "#init");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   public void doView(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#doView");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#doEdit");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   public void doHeaders(RenderRequest request, RenderResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#doHeaders");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#doHelp");
      meths.setConfigExists(getPortletConfig() != null);
   }
 
   @RenderMode(name="config")
   public void doConfig(RenderRequest request, RenderResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#doConfig");
      meths.setConfigExists(getPortletConfig() != null);
   }
   
   @RenderMode(name="admin")
   public void doAdmin(RenderRequest request, RenderResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#doAdmin");
      meths.setConfigExists(getPortletConfig() != null);
   }
   
   @ProcessAction(name="Fred")
   public void doFred(ActionRequest request, ActionResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#doFred");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @ProcessEvent(qname="{http://www.apache.org/}event1")
   public void doEvent1(EventRequest request, EventResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#doEvent1");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @ProcessEvent(qname="{https://www.java.net/}event4")
   public void doEvent4(EventRequest request, EventResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#doEvent4");
      meths.setConfigExists(getPortletConfig() != null);
   }

}
