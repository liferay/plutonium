/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.header;

import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.annotations.HeaderMethod;

import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott Nicklous
 *
 */
public class Header2 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @HeaderMethod(portletNames="portlet2", portletMode="edit", ordinal=-100)
   public void header2c(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header2c");
   }
   
   @HeaderMethod(portletNames="portlet2", portletMode="edit", ordinal=100)
   public void header2d(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header2d");
   }
   
   // invalid signature
   @HeaderMethod(portletNames="portlet4")
   public void header4(String x, HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header4");
   }
   
   // invalid signature
   @HeaderMethod(portletNames="portlet5", portletMode="edit")
   public String header5(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header5");
      return null;
   }
   
   // invalid signature, bad exception
   @HeaderMethod(portletNames="portlet8")
   public String header8(HeaderRequest req, HeaderResponse resp) throws IllegalStateException  {
      meths.addMethod(this.getClass().getSimpleName() + "#header8");
      return null;
   }
   
   @HeaderMethod(portletNames= {"portlet6", "portlet7"})
   public void header6and7(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header6and7");
   }
   
   // ignored asterisk
   @HeaderMethod(portletNames= {"portlet6", "*"}, ordinal=100)
   public void header6andStar(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header6andStar");
   }
   
   @HeaderMethod(portletNames="*", portletMode="admin")
   public void headerAll(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#headerAll");
   }
   

}
