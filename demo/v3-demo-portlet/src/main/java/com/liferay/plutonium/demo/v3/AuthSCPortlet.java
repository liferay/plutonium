/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.liferay.plutonium.demo.v3.Constants.PARAM_STATUSCODE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.inject.Inject;
import jakarta.portlet.ActionParameters;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.MutableRenderParameters;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;


/**
 * A portlet that causes the resource request status code to be set.
 * 
 * @author Scott Nicklous
 */
public class AuthSCPortlet extends GenericPortlet {

   // Set up logging
   private static final Logger logger = LoggerFactory.getLogger(AuthSCPortlet.class);
   private static final boolean isDebug = logger.isDebugEnabled();

   @Override
   public void init() throws PortletException {
      super.init();
   }

   protected void doView(RenderRequest req, RenderResponse resp)
         throws PortletException, IOException {

      resp.setContentType("text/html");

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(
            "/WEB-INF/jsp/view-ascp.jsp");
      rd.include(req, resp);

   }
   
   /* (non-Javadoc)
    * @see jakarta.portlet.GenericPortlet#serveResource(jakarta.portlet.ResourceRequest, jakarta.portlet.ResourceResponse)
    */
   @Override
   public void serveResource(ResourceRequest req, ResourceResponse resp)
         throws PortletException, IOException {

      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();

      StringBuilder txt = new StringBuilder();
      
      String scText = req.getRenderParameters().getValue(PARAM_STATUSCODE);
      if (scText != null) {
         int sc = 400;     // bad request
         if (scText.matches("\\d+")) {
            sc = Integer.parseInt(scText);
            if (isDebug) {
               logger.debug("Setting HTTP status code to: " + sc);
            }
         }
         resp.setStatus(sc);
      }
      
      txt.append("<p>serveResource</p>");
      txt.append("<p>Status code: ").append(scText);
      txt.append("<br>Status code from getStatus(): ").append(resp.getStatus());
      txt.append("<br>Content length: ");
      int len = txt.length() + "</p>".length() + 3;
      txt.append(String.format("%3d", len));
      txt.append("</p>");
      
      resp.setContentLengthLong(txt.length());
      writer.write(txt.toString());
   }

   @Inject private ActionParameters ap;
   @Inject private MutableRenderParameters mrp;

   public void processAction(ActionRequest req, ActionResponse resp)
         throws PortletException, IOException {
   
      String scText = ap.getValue(PARAM_STATUSCODE);
      if (scText != null && scText.matches("\\d+")) {
         mrp.set(ap);
      }

      if (isDebug) {
         StringBuffer sb = new StringBuffer();
         sb.append("Long portlet action parameters:");
         for (String k : ap.getNames()) {
            sb.append("\nName: ").append(k);
            sb.append(", Values: ").append(Arrays.toString(ap.getValues(k)));
         }
         logger.debug(sb.toString());
      }

   }

}
