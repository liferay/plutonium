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
public class Header1 {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   @HeaderMethod(portletNames="portlet1")
   public void header1a(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header1a");
   }
   
   @HeaderMethod(portletNames="portlet1")
   public void header1b() {
      meths.addMethod(this.getClass().getSimpleName() + "#header1b");
   }
   
   @HeaderMethod(portletNames="portlet1")
   public String header1c() {
      meths.addMethod(this.getClass().getSimpleName() + "#header1c");
      return null;
   }
   
   @HeaderMethod(portletNames="portlet2", portletMode="help")
   public void header2a(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header2a");
   }
   
   @HeaderMethod(portletNames="portlet2", portletMode="edit")
   public void header2b(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header2b");
   }
   
   @HeaderMethod(portletNames="portlet2", portletMode="config")
   public void header2c(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header2c");
   }
   
   @HeaderMethod(portletNames="portlet3", ordinal=200)
   public void header3a(HeaderRequest req, HeaderResponse resp) {
      meths.addMethod(this.getClass().getSimpleName() + "#header3a");
   }
   
   @HeaderMethod(portletNames="portlet3", ordinal=300)
   public void header3b() {
      meths.addMethod(this.getClass().getSimpleName() + "#header3b");
   }
   
   @HeaderMethod(portletNames="portlet3", ordinal=-42)
   public String header3c() {
      meths.addMethod(this.getClass().getSimpleName() + "#header3c");
      return null;
   }
   
   @HeaderMethod(portletNames="portlet3", ordinal=300, portletMode="help")
   public void header3e() {
      meths.addMethod(this.getClass().getSimpleName() + "#header3e");
   }
   

}
