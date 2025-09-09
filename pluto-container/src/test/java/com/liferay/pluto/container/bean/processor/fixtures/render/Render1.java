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
 * @author Scott
 *
 */
public class Render1 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @RenderMethod(portletNames="portlet1")
   public void render1a(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render1a");
   }
   
   @RenderMethod(portletNames="portlet1")
   public void render1b() {
      meths.addMethod(this.getClass().getSimpleName() + "#render1b");
   }
   
   @RenderMethod(portletNames="portlet1")
   public String render1c() {
      meths.addMethod(this.getClass().getSimpleName() + "#render1c");
      return null;
   }
   
   @RenderMethod(portletNames="portlet2", portletMode="help")
   public void render2a(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render2a");
   }
   
   @RenderMethod(portletNames="portlet2", portletMode="edit")
   public void render2b(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render2b");
   }
   
   @RenderMethod(portletNames="portlet2", portletMode="config")
   public void render2c(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render2c");
   }
   
   @RenderMethod(portletNames="portlet3", ordinal=200)
   public void render3a(RenderRequest req, RenderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#render3a");
   }
   
   @RenderMethod(portletNames="portlet3", ordinal=300)
   public void render3b() {
      meths.addMethod(this.getClass().getSimpleName() + "#render3b");
   }
   
   @RenderMethod(portletNames="portlet3", ordinal=-42)
   public String render3c() {
      meths.addMethod(this.getClass().getSimpleName() + "#render3c");
      return null;
   }
   
   @RenderMethod(portletNames="portlet3", ordinal=-420, portletMode="")
   public String render3d() {
      meths.addMethod(this.getClass().getSimpleName() + "#render3d");
      return null;
   }
   
   @RenderMethod(portletNames="portlet3", ordinal=300, portletMode="help")
   public void render3e() {
      meths.addMethod(this.getClass().getSimpleName() + "#render3e");
   }
   

}
