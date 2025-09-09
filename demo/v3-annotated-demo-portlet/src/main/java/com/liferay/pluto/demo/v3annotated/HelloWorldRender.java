/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.v3annotated;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.annotations.RenderMethod;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Simple hello world bean portlet
 */
@Dependent
public class HelloWorldRender {
   
   // This is an @RenderStateScoped bean containing the name 
   // entered through the form.
   @Inject
   private NameBean nameBean;
   
   @Inject
   private RequestRandomNumberBean reqrn;
   
   @Inject
   private PortletRequestRandomNumberBean pltreqrn;
   
   @Inject
   private PortletSessionRandomNumberBean pltsessrn;
   
   @Inject
   private AppSessionRandomNumberBean appsessrn;
   
   @Inject
   private SessionRandomNumberBean sessrn;
   
   @Inject
   private ApplicationRandomNumberBean apprn;
   
   @Inject
   private RenderRequest req;
   
   @Inject
   private RenderResponse res;

   /**
    * Bean portlet render method for "BeanHelloWorld" portlet.
    */
   @RenderMethod(portletNames = {"BeanPortlet"})
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
      txt.append("!!</h3>\n");
      
      txt.append("<p><table id=\"").append(res.getNamespace());
      txt.append("_scope_info\" cellspacing='2' cellpadding='0'><tr><td align='left'>\n");
      txt.append("Application Scoped number:</td><td>").append(apprn.getRandomNumber());
      txt.append("</td></tr><tr><td>\n");
      txt.append("Session scoped number:</td><td>").append(sessrn.getRandomNumber());
      txt.append("</td></tr><tr><td>\n");
      txt.append("Portlet session scoped (Application) number:</td><td>").append(appsessrn.getRandomNumber());
      txt.append("</td></tr><tr><td>\n");
      txt.append("Portlet session scoped (Portlet) number:</td><td>").append(pltsessrn.getRandomNumber());
      txt.append("</td></tr><tr><td>\n");
      txt.append("Portlet Request number:</td><td>").append(pltreqrn.getRandomNumber());
      txt.append("</td></tr><tr><td>\n");
      txt.append("Request number:</td><td>").append(reqrn.getRandomNumber());
      txt.append("</td></tr></table></p>\n");
      
      txt.append("<p>User agent: ").append(req.getUserAgent()).append("</p>");
      
      return txt.toString();
   }

}
