/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.init;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.annotations.InitMethod;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott
 *
 */
public class Init2 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @InitMethod("portlet3")
   public void init1(PortletConfig config) {
      meths.addMethod(this.getClass().getSimpleName() + "#init1");
   }
   
   // duplicate method
   @InitMethod("portlet2")
   public void init2(PortletConfig config) {
      meths.addMethod(this.getClass().getSimpleName() + "#init2");
   }
   
   // invalid signature
   @InitMethod("portlet4")
   public void init4(String x, PortletConfig config) {
      meths.addMethod(this.getClass().getSimpleName() + "#init4");
   }
   
   // invalid signature
   @InitMethod("portlet5")
   public String init5(PortletConfig config) {
      meths.addMethod(this.getClass().getSimpleName() + "#init5");
      return null;
   }
   

}
