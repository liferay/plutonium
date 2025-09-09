/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.v3;

import jakarta.enterprise.context.Dependent;
import jakarta.portlet.ActionParameters;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.RenderMethod;
import jakarta.portlet.annotations.ServeResourceMethod;

/**
 * Portlet to test the portlet tag library URL tags.
 * 
 * @author Scott Nicklous
 *
 */
@Dependent
public class TagLibTagsPortlet {
   
   private static final String JSPURLS     = "/WEB-INF/jsp/tagLibTags.jsp";
   private static final String JSPRES      = "/WEB-INF/jsp/tagLibTagsResource.jsp";

   @RenderMethod(portletNames="TagLibTagsPortlet", include=JSPURLS)
   public void render() {
   }

   @ServeResourceMethod(portletNames="TagLibTagsPortlet", include=JSPRES)
   public void resource() {
   }
   
   @ActionMethod(portletName="TagLibTagsPortlet")
   public void action(ActionRequest req, ActionResponse resp) {
      ActionParameters ap = req.getActionParameters();
      req.getPortletSession().setAttribute("actparms", ap.clone());
   }

}
