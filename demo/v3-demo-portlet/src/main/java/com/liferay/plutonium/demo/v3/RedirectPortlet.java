/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import jakarta.portlet.ActionParameters;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.ActionURL;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.RenderParameters;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.RenderURL;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletConfiguration;

import static jakarta.portlet.MimeResponse.Copy.*;
import static com.liferay.plutonium.demo.v3.Constants.*;

/**
 * Portlet for testing the redirect funtionality, including the new getRedirectURL API.
 */
@PortletConfiguration(portletName="V3RedirectPortlet", title = @LocaleString(value = "Redirect Test Portlet"))
public class RedirectPortlet extends GenericPortlet {

   private static final Logger logger  = LoggerFactory.getLogger(RedirectPortlet.class);
   private static final boolean isDebug = logger.isDebugEnabled();

   protected void doView(RenderRequest req, RenderResponse resp) throws PortletException, IOException {

      resp.setContentType("text/html");
      
      // provide an action URL to the JSP as a workaround, since the tag library is still JSR 286
      ActionURL aurl = resp.createActionURL(ALL);
      req.setAttribute(ATTRIB_ACTURL, aurl.toString());

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/view-rdp.jsp");
      rd.include(req, resp);

   }

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.portlet.GenericPortlet#serveResource(jakarta.portlet.ResourceRequest, jakarta.portlet.ResourceResponse)
    */
   @Override
   public void serveResource(ResourceRequest req, ResourceResponse resp) throws PortletException, IOException {
   }

   public void processAction(ActionRequest req, ActionResponse resp) throws PortletException, IOException {

      ActionParameters ap = req.getActionParameters();
      RenderParameters rp = req.getRenderParameters();

      StringBuilder txt = new StringBuilder(128);
      if (isDebug) {
         txt.append("Action parms: ").append(ap.getNames());
         txt.append(", Render parms: ").append(rp.getNames());
         txt.append(", Color: ").append(rp.getValue(PARAM_COLOR));
      }

      String color = ap.getValue(PARAM_COLOR);
      if (color != null && color.length() > 0) {
         if (!color.matches("^#(?:[A-Fa-f0-9]{3}){1,2}$")) {
            color = "FDD";
         }
      }
      
      String url = ap.getValue(PARAM_URL_INPUT);
      if (url == null || url.length() <= 0) {
         RenderURL rurl = resp.createRedirectURL(ALL);
         rurl.getRenderParameters().setValue(PARAM_COLOR, color);
         url = rurl.toString();
         if (isDebug) {
            txt.append(", redirecting to redirect URL with new color=").append(color);
         }
      }
      resp.sendRedirect(url);
      
      if (isDebug) {
         logger.debug(txt.toString());
      }

   }

}
