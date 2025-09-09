/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3;

import static com.liferay.plutonium.demo.v3.Constants.*;

import java.io.IOException;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import javax.xml.namespace.QName;


/**
 * A demo portlet for color selection.
 */
public class ColorSelPortlet extends GenericPortlet {

   protected void doView(RenderRequest req, RenderResponse resp)
         throws PortletException, IOException {

      
      resp.setContentType("text/html");

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(
            "/WEB-INF/jsp/view-csp.jsp");
         rd.include(req, resp);
      
}
   
   /* (non-Javadoc)
    * @see jakarta.portlet.GenericPortlet#serveResource(jakarta.portlet.ResourceRequest, jakarta.portlet.ResourceResponse)
    */
   @Override
   public void serveResource(ResourceRequest req, ResourceResponse resp)
         throws PortletException, IOException {
   }

   public void processAction(ActionRequest req, ActionResponse resp)
         throws PortletException, IOException {
            
      String[] vals = req.getActionParameters().getValues(PARAM_FG_COLOR);
      String r = "0";
      String g = "0";
      String b = "0";
      if (vals != null) {
         for (String v : vals) {
            if (v.equals(PARAM_FG_RED)) r = "F";
            if (v.equals(PARAM_FG_GREEN)) g = "F";
            if (v.equals(PARAM_FG_BLUE)) b = "F";
         }
      }
      String clr = "#" + r + g + b;
      
      // make sure the private parameter are all on the URL for 
      // potential back button support
      if (vals != null) {
         resp.getRenderParameters().setValues(PARAM_FG_COLOR, vals);
      }
      
      String val = req.getActionParameters().getValue(PARAM_SUBTYPE);
      if (val != null) {
         resp.getRenderParameters().setValue(PARAM_SUBTYPE, val);
      }
      
      val = req.getActionParameters().getValue(PARAM_MSG_INPUT);
      if (val != null) {
         resp.getRenderParameters().setValue(PARAM_MSG_INPUT, val);
      }
      
      String msg = val + DELIM + clr;
      QName qn = new QName(EVENT_NAMESPACE, EVENT_NAME);
      resp.setEvent(qn, msg);
      
   }

}
