/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures.event;

import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.annotations.EventDefinition;
import jakarta.portlet.annotations.EventMethod;
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
            @EventDefinition(qname=@PortletQName(namespaceURI="https://www.java.net/", localPart="pub4")),
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="proc1")),
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="proc2")),
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="proc3a")),
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="proc3c")),
            @EventDefinition(qname=@PortletQName(namespaceURI="https://www.java.net/", localPart="proc3b")),
            @EventDefinition(qname=@PortletQName(namespaceURI="http://www.apache.org", localPart="proc6")),
            @EventDefinition(qname=@PortletQName(namespaceURI="", localPart="event4"))
      }
      )
public class Event1 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @EventMethod(portletName="portlet1", 
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc1")
         },
         publishingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub1")
         })
   public void event1(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event1");
   }
   
   @EventMethod(portletName="portlet2", 
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc2")
         })
   public void event2(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event2");
   }
   
   // dummy render method to keep config processor happy
   
   @RenderMethod(portletNames= {"portlet1", "portlet2"})
   public void render1() {}
   

}
