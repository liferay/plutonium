/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.chat;

import jakarta.inject.Inject;
import jakarta.portlet.ActionParameters;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.ActionURL;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.MimeResponse.Copy;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.Namespace;
import jakarta.portlet.annotations.PortletSerializable;
import jakarta.portlet.annotations.RenderMethod;
import jakarta.portlet.annotations.RenderStateScoped;

/**
 * Render state scoped bean. The bean is stored as a render parameter,
 * so it needs to be portlet serializable. The parameter name can be left unspecified,
 * in which case, the portlet container will choose a name, or it can be specified 
 * using the paramName attribute. If a parameter name is specified, the parameter
 * value will be available on the client through the portlet hub JavaScript interface.
 */
@RenderStateScoped(paramName="name")
public class NameBean implements PortletSerializable {
   
   //========== bean state in this block =======================
   private String name;

   /**
    * This method is called by the portlet container to 
    * initialize the bean at the beginning of a request.
    */
   @Override
   public void deserialize(String[] state) {
      if (state.length == 1) {
         name = state[0];
      }
   }

   /**
    * Called by the portlet container at the end of an action or event request
    * to store the serialized data as a portlet render parameter.
    */
   @Override
   public String[] serialize() {
      return new String[] {name};
   }
   //===========================================================
   
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   
   // Inject the portlet namespace, which identifies the specific portlet window on the page 
   @Inject @Namespace private String pid;
   
   // Inject the MimeResponse to allow URL creation
   @Inject private            MimeResponse mimeresp;

   
   /**
    * writes the name entry form as string. 
    */
   @RenderMethod(portletNames = {"BeanPortletDemo"}, ordinal=200)
   public String getActionForm() {
      StringBuilder txt = new StringBuilder(128);
      
      ActionURL aurl = mimeresp.createActionURL(Copy.ALL);
      txt.append("<div class='parmbox'>");
      txt.append("<FORM id='").append(pid).append("-setParams' METHOD='POST' ACTION='").append(aurl);
      txt.append("' enctype='application/x-www-form-urlencoded' accept-charset='UTF-8'>");
      txt.append("   <table><tr><td align='left'>");
      txt.append("   Enter your name:");
      txt.append("   </td><td>");
      txt.append("   <input name='").append("name");
      txt.append("' type='text' value='").append((name == null) ? "" : name);
      txt.append("' size='50' maxlength='50'>");
      txt.append("   <input type='hidden' name='").append(ActionRequest.ACTION_NAME);
      txt.append("' value='setName'>");
      txt.append("   </td></tr><tr><td>");
      txt.append("   <INPUT VALUE='send' TYPE='submit'>");
      txt.append("   </td></tr></table>");
      txt.append("</FORM></div>");

      return txt.toString();
   }

   // illustrates injection of the action parameters. The inject object is a 
   // @PortletRequestScoped bean. It may only be accessed during action request
   // processing.
   
   @Inject private ActionParameters actionParams;
   
   /**
    * Bean portlet action method for processing the name entry form.
    * If the action request contains an action parameter with the reserved
    * name 'jakarta.portlet.action' and value matching the actionName attribute,
    * the portlet the portlet container routes the request to this method.
    */
   @ActionMethod(portletName = "BeanPortletDemo", actionName="setName")
   public void setName(ActionRequest req, ActionResponse resp) {
      
      // Use the injected action parameters to set the name 
      name = actionParams.getValue("name");
   }

}
