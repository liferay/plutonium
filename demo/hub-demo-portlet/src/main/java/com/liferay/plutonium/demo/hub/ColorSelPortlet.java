/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.hub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.liferay.plutonium.demo.hub.Constants.DELIM;
import static com.liferay.plutonium.demo.hub.Constants.PARAM_FG_BLUE;
import static com.liferay.plutonium.demo.hub.Constants.PARAM_FG_COLOR;
import static com.liferay.plutonium.demo.hub.Constants.PARAM_FG_GREEN;
import static com.liferay.plutonium.demo.hub.Constants.PARAM_FG_RED;
import static com.liferay.plutonium.demo.hub.Constants.PARAM_MSG_INPUT;
import static com.liferay.plutonium.demo.hub.Constants.PARAM_SUBTYPE;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletParameters;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.Dependency;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletQName;
import javax.xml.namespace.QName;


/**
 * An example color selection portlet that uses the portlet hub.
 */
@PortletConfiguration(portletName = "PH-ColorSelPortlet", publicParams = "color", 
                      title = @LocaleString("PH Color Selection Portlet"),
                      dependencies = @Dependency(name="PortletHub", scope="jakarta.portlet", version="3.0.0"))
public class ColorSelPortlet extends GenericPortlet {

   // Set up logging
   private final Logger logger = LoggerFactory.getLogger(ColorSelPortlet.class);

   protected void doView(RenderRequest req, RenderResponse resp)
         throws PortletException, IOException {

      logger.debug("Doing view.");
      
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
   
   final static Pattern validMsgChars = Pattern.compile("^[\\w ]+$");

   @ActionMethod(portletName="PH-ColorSelPortlet", publishingEvents= {
         @PortletQName(namespaceURI="http://www.apache.org/portals/plutonium/ResourcePortlet", localPart="Message")
   })
   public void processAction(ActionRequest req, ActionResponse resp)
         throws PortletException, IOException {
            
      dumpParameters("Action", req.getActionParameters());
      dumpParameters("Render", req.getRenderParameters());
      
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
      
      String subType = req.getActionParameters().getValue(PARAM_SUBTYPE);
      if (subType != null) {
         resp.getRenderParameters().setValue(PARAM_SUBTYPE, subType);
      }
      
      String text = req.getActionParameters().getValue(PARAM_MSG_INPUT);
      if (text != null) {
         Matcher m = validMsgChars.matcher(text);
         if (!m.matches()) {
            text = "invalid characters.";
         }
         resp.getRenderParameters().setValue(PARAM_MSG_INPUT, text);
      }
      
      String msg = text + DELIM + clr;

      // there should only be one publishing event, so take the first QName
      Enumeration<QName> eqn = getPublishingEventQNames();
      if (eqn.hasMoreElements()) {
         QName qn = eqn.nextElement(); 
         resp.setEvent(qn, msg);
         logger.debug("Firing event with QName: " + qn.toString());
      } else {
         logger.warn("No publishing event QName available. Check portlet configuration.");
      }
      
      StringBuilder sb = new StringBuilder("Color: ").append(Arrays.toString(vals));
      sb.append(", Submission type: ").append(subType);
      sb.append(", Text: ").append(text);
      logger.debug(sb.toString());
   }
   
   private void dumpParameters(String type, PortletParameters parms) {
      if (logger.isDebugEnabled()) {
         StringBuilder sb = new StringBuilder();
         sb.append("Portlet ").append(type).append(" parameters:");
         for (String name : parms.getNames()) {
            sb.append("\nName: ").append(name);
            sb.append(", Values: ").append(Arrays.toString(parms.getValues(name)));
         }
         logger.debug(sb.toString());
      }
   }

}
