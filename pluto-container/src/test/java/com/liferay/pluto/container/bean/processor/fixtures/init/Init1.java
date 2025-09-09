/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.init;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.annotations.InitMethod;
import jakarta.portlet.annotations.RenderMethod;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott
 *
 */
public class Init1 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @InitMethod("portlet1")
   public void init1(PortletConfig config) {
      meths.addMethod(this.getClass().getSimpleName() + "#init1");
   }
   
   @InitMethod("portlet2")
   public void init2(PortletConfig config) {
      meths.addMethod(this.getClass().getSimpleName() + "#init2");
   }
   
   // dummy render method to keep config processor happy
   
   @RenderMethod(portletNames= {"portlet1", "portlet2", "portlet3"})
   public void render1() {}

}
