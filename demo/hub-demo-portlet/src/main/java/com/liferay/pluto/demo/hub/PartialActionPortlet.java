/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.hub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.liferay.pluto.demo.hub.Constants.*;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;

import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.Dependency;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletConfiguration;

/**
 * A demo portlet exercising the partial action processing sequence
 */
@PortletConfiguration(portletName = "PartialActionPortlet", publicParams = "color",
                        title = @LocaleString("PH Partial Action Portlet"),
                        dependencies = @Dependency(name="PortletHub", scope="jakarta.portlet", version="3.0.0"))
public class PartialActionPortlet extends GenericPortlet {

   // Set up logging
   private final Logger logger = LoggerFactory.getLogger(PartialActionPortlet.class);

   protected void doView(RenderRequest req, RenderResponse resp)
         throws PortletException, IOException {
      
      if (logger.isDebugEnabled()) {
         logger.debug(this.getClass().getName(), "doView", "Entry");
      }
      
      resp.setContentType("text/html");

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(
            "/WEB-INF/jsp/view-pap.jsp");
      rd.include(req, resp);

   }

   @SuppressWarnings("deprecation")
   @Override
   public void processAction(ActionRequest req, ActionResponse resp)
         throws PortletException, IOException {

      // the only action for this portlet is to increment the execition counter

      String val = req.getParameter(PARAM_NUM_ACTIONS);
      int na = 1;
      if (val != null) {
         try {
            na = Integer.parseInt(val) + 1;
         } catch (Exception e) {}
      }
      
      String actionName = req.getParameter("action");
      logger.debug("PAP: executing partial action. action number = " + na + ", name =  " + actionName);

      resp.setRenderParameter(PARAM_NUM_ACTIONS, Integer.toString(na));
   }
   
   @Override
   public void processEvent(EventRequest req, EventResponse resp) 
         throws PortletException ,IOException {
   };
   
   /* (non-Javadoc)
    * @see jakarta.portlet.GenericPortlet#serveResource(jakarta.portlet.ResourceRequest, jakarta.portlet.ResourceResponse)
    */
   @SuppressWarnings("deprecation")
   @Override
   public void serveResource(ResourceRequest req, ResourceResponse resp)
         throws PortletException, IOException {
      
      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();

      String num = req.getParameter(PARAM_NUM_ACTIONS);
      num = (num == null) ? "error" : num;
      
      writer.write("<p>Partial Action has been executed " + num + " times.</p>\n");
   }

}
