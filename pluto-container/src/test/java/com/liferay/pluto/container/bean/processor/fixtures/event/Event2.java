/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.event;

import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.annotations.EventMethod;
import jakarta.portlet.annotations.PortletQName;
import jakarta.portlet.annotations.RenderMethod;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott
 *
 */
public class Event2 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @EventMethod(portletName="portlet3",
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc3a")
         },
         publishingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub1")
         })
   public void event1a(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event1a");
   }
   
   @EventMethod(portletName="portlet3",
         processingEvents= {
            @PortletQName(namespaceURI="", localPart="proc3b")
         },
         publishingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub2")
         })
   public void event1b(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event1b");
   }
   
   @EventMethod(portletName="portlet3",
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc3c")
         },
         publishingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="pub3"),
            @PortletQName(namespaceURI="", localPart="pub4")
         })
   public void event1c(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event1c");
   }
   
   // duplicate method
   @EventMethod(portletName="portlet2", 
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc2")
         })
   public void event2(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event2");
   }
   
   // invalid signature
   @EventMethod(portletName="portlet4", 
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc4")
         })
   public void event4(EventRequest req, EventResponse resp, int y) {
      meths.addMethod(this.getClass().getSimpleName() + "#event4");
   }
   
   // invalid signature
   @EventMethod(portletName="portlet5", 
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc5")
         })
   public String event5(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event5");
      return null;
   }

   // duplicate method in same class
   @EventMethod(portletName="portlet6", 
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc6")
         })
   public void event6(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event6");
   }

   // duplicate method in same class
   @EventMethod(portletName="portlet6", 
         processingEvents= {
            @PortletQName(namespaceURI="http://www.apache.org", localPart="proc6")
         })
   public void event7(EventRequest req, EventResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#event7");
   }
   
   // dummy render method to keep config processor happy
   
   @RenderMethod(portletNames= {"portlet3", "portlet6"})
   public void render1() {}


}
