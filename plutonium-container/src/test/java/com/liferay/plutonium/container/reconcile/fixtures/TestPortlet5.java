/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.reconcile.fixtures;

import java.io.IOException;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.EventDefinition;
import jakarta.portlet.annotations.EventMethod;
import jakarta.portlet.annotations.InitParameter;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletApplication;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletConfigurations;
import jakarta.portlet.annotations.PortletQName;

import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;

/**
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
@PortletConfigurations( {
   @PortletConfiguration(portletName="Portlet5", 
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
})
public class TestPortlet5 extends GenericPortlet {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();

   @Override
   public void destroy() {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy");
      meths.setConfigExists(getPortletConfig() != null);
      super.destroy();
   }

   @Override
   public void init(PortletConfig config) throws PortletException {
      super.init(config);
      meths.addMethod(this.getClass().getSimpleName() + "#init");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   public void processAction(ActionRequest arg0, ActionResponse arg1) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#processAction");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   @EventMethod(portletName="Portlet5", processingEvents= {
         @PortletQName(namespaceURI="http://www.apache.org/", localPart="event1"),
         @PortletQName(namespaceURI="", localPart="event4"),
   })
   public void processEvent(EventRequest arg0, EventResponse arg1) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#processEvent");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   public void render(RenderRequest arg0, RenderResponse arg1) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#render");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   public void renderHeaders(HeaderRequest request, HeaderResponse response) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#renderHeaders");
      meths.setConfigExists(getPortletConfig() != null);
   }

   @Override
   public void serveResource(ResourceRequest arg0, ResourceResponse arg1) throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#serveResource");
      meths.setConfigExists(getPortletConfig() != null);
   }
   
}
