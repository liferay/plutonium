/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.render;

import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.annotations.RenderMethod;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott Nicklous
 *
 */
public class Render2 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @RenderMethod(portletNames="portlet2", portletMode="edit", ordinal=-100)
   public void render2c(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render2c");
   }
   
   @RenderMethod(portletNames="portlet2", portletMode="edit", ordinal=100)
   public void render2d(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render2d");
   }
   
   // invalid signature
   @RenderMethod(portletNames="portlet4")
   public void render4(String x, RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render4");
   }
   
   // invalid signature
   @RenderMethod(portletNames="portlet5")
   public String render5(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render5");
      return null;
   }
   
   // invalid signature, bad exception
   @RenderMethod(portletNames="portlet8")
   public String render8(RenderRequest req, RenderResponse resp) throws IllegalStateException  {return null;}
   
   @RenderMethod(portletNames= {"portlet6", "portlet7"})
   public void render6and7(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render6and7");
   }
   
   // ignored asterisk
   @RenderMethod(portletNames= {"portlet6", "*"}, ordinal=100)
   public void render6andStar(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render6andStar");
   }
   
   @RenderMethod(portletNames="*", portletMode="admin")
   public void renderAll(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#renderAll");
   }
   

}
