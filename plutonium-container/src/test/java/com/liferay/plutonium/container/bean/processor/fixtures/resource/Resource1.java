/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures.resource;

import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.ServeResourceMethod;

import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott
 *
 */
public class Resource1 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @ServeResourceMethod(portletNames="portlet1")
   public void resource1a(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource1a");
   }
   
   @ServeResourceMethod(portletNames="portlet1")
   public void resource1b() {
      meths.addMethod(this.getClass().getSimpleName() + "#resource1b");
   }
   
   @ServeResourceMethod(portletNames="portlet1")
   public String resource1c() {
      meths.addMethod(this.getClass().getSimpleName() + "#resource1c");
      return null;
   }
   
   @ServeResourceMethod(portletNames="portlet2", resourceID="help")
   public void resource2a(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource2a");
   }
   
   @ServeResourceMethod(portletNames="portlet2", resourceID="edit", asyncSupported=false)
   public void resource2b(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource2b");
   }
   
   @ServeResourceMethod(portletNames="portlet2", resourceID="config")
   public void resource2c(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource2c");
   }
   
   @ServeResourceMethod(portletNames="portlet3", ordinal=200)
   public void resource3a(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource3a");
   }
   
   @ServeResourceMethod(portletNames="portlet3", ordinal=300, asyncSupported=true)
   public void resource3b() {
      meths.addMethod(this.getClass().getSimpleName() + "#resource3b");
   }
   
   @ServeResourceMethod(portletNames="portlet3", ordinal=-42)
   public String resource3c() {
      meths.addMethod(this.getClass().getSimpleName() + "#resource3c");
      return null;
   }
   
   @ServeResourceMethod(portletNames="portlet3", ordinal=200, resourceID="help")
   public String resource3e() {
      meths.addMethod(this.getClass().getSimpleName() + "#resource3e");
      return null;
   }
   

}
