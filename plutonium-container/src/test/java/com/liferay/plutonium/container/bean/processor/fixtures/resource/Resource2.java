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
public class Resource2 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @ServeResourceMethod(portletNames="portlet2", resourceID="edit", ordinal=-100)
   public void resource2c(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource2c");
   }
   
   @ServeResourceMethod(portletNames="portlet2", resourceID="edit", ordinal=100)
   public void resource2d(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource2d");
   }
   
   // invalid signature
   @ServeResourceMethod(portletNames="portlet4")
   public void resource4(String x, ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource4");
   }
   
   // invalid signature
   @ServeResourceMethod(portletNames="portlet5")
   public String resource5(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource5");
      return null;
   }
   
   // invalid signature, bad exception
   @ServeResourceMethod(portletNames="portlet8")
   public String resource8(ResourceRequest req, ResourceResponse resp) throws IllegalStateException  {
      meths.addMethod(this.getClass().getSimpleName() + "#resource8");
      return null;
   }
   
   @ServeResourceMethod(portletNames= {"portlet6", "portlet7"})
   public void resource6and7(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource6and7");
   }
   
   // ignored asterisk
   @ServeResourceMethod(portletNames= {"portlet6", "*"}, ordinal=100)
   public void resource6andStar(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resource6andStar");
   }
   
   @ServeResourceMethod(portletNames="*", resourceID="admin")
   public void resourceAll(ResourceRequest req, ResourceResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#resourceAll");
   }
   

}
