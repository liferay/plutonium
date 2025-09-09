/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.chat;

import jakarta.enterprise.context.Dependent;
import org.apache.commons.lang3.StringEscapeUtils;

import jakarta.inject.Inject;
import jakarta.portlet.annotations.RenderMethod;

/**
 * Simple hello world bean portlet
 */
@Dependent
public class HelloWorldRender {
   
   // This is an @RenderStateScoped bean containing the name entered through the form.
   @Inject
   private NameBean nameBean;

   /**
    * Bean portlet render method for "BeanHelloWorld" portlet. The portletNames
    * attribute specifies the portlet name or list of portlet names using the method.
    * This annotation implicitly defines a portlet or list of portlets. Further 
    * configuration can be provided, but is not required. 
    */
   @RenderMethod(portletNames = {"BeanPortletDemo"})
   public String methodName() {
      
      // In it's simplest form, the render method just returns text.
      // The content type is set through the annotation.
      
      StringBuilder txt = new StringBuilder(128);
      
      txt.append("<h3>Hello \n");
      // Get the name from the bean. If it hasn't been set, just greet the world.
      if (nameBean.getName() != null) {
         txt.append(StringEscapeUtils.escapeHtml4(nameBean.getName()));
      } else {
         txt.append("World\n");
      }
      txt.append("!!</h3>");
      
      return txt.toString();
   }

}
