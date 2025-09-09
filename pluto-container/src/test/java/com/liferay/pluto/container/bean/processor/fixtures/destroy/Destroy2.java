/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.destroy;

import jakarta.portlet.annotations.DestroyMethod;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott Nicklous
 *
 */
public class Destroy2 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @DestroyMethod("portlet3")
   public void destroy1() {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy1");
   }
   
   // duplicate method
   @DestroyMethod("portlet2")
   public void destroy2() {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy2");
   }
   
   // invalid signature
   @DestroyMethod("portlet4")
   public void destroy4(String x) {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy4");
   }
   
   // invalid signature
   @DestroyMethod("portlet5")
   public String destroy5() {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy5");
      return null;
   }
   

}
