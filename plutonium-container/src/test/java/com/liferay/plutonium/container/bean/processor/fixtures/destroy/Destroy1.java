/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures.destroy;

import jakarta.portlet.annotations.DestroyMethod;
import jakarta.portlet.annotations.RenderMethod;

import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott Nicklous
 *
 */
public class Destroy1 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @DestroyMethod("portlet1")
   public void destroy1() {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy1");
   }
   
   @DestroyMethod("portlet2")
   public void destroy2() {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy2");
   }
   
   // dummy render method to keep config processor happy
   
   @RenderMethod(portletNames= {"portlet1", "portlet2", "portlet3"})
   public void render1() {}

}
