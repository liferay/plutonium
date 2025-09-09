/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.hub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.liferay.pluto.demo.hub.Constants.PARAM_BORDER_COLOR;
import static com.liferay.pluto.demo.hub.Constants.PARAM_COLOR;
import static com.liferay.pluto.demo.hub.Constants.PARAM_IMGNAME;
import static com.liferay.pluto.demo.hub.Constants.DEFAULT_IMAGE;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.PortletURL;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.Dependency;
import jakarta.portlet.annotations.EventDefinition;
import jakarta.portlet.annotations.LocaleString;
import jakarta.portlet.annotations.PortletApplication;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletQName;
import jakarta.portlet.annotations.PublicRenderParameterDefinition;
import jakarta.portlet.annotations.Supports;
import static com.liferay.pluto.demo.hub.Constants.IMG_MAP;


/**
 * A demo portlet that displays images
 */
@PortletApplication(
      events = {
            @EventDefinition(
                  qname = @PortletQName(
                        namespaceURI = "http://www.apache.org/portals/pluto/ResourcePortlet", 
                        localPart = "Message"),
                  payloadType = String.class)},
      publicParams = {
            @PublicRenderParameterDefinition(
                  qname = @PortletQName(
                        namespaceURI = "http://www.apache.org/portals/pluto/ResourcePortlet", 
                        localPart = "imgName"),
                  identifier = "imgName"
                  ),
            @PublicRenderParameterDefinition(
                  qname = @PortletQName(
                        namespaceURI = "http://www.apache.org/portals/pluto/ResourcePortlet", 
                        localPart = "color"),
                  identifier = "color") }
      )
@PortletConfiguration(portletName = "PH-ResourcePortlet-PRP",
      title={@LocaleString("PH Resource Portlet")},
      dependencies = @Dependency(name="PortletHub", scope="jakarta.portlet", version="3.0.0"),
      supportedLocales = {"en"},
      supports = @Supports(mimeType = "text/html", portletModes = "VIEW"),
      publicParams = {"color", "imgName"}
      )
public class ResourcePortlet extends GenericPortlet {

   // Set up logging
   private final Logger logger = LoggerFactory.getLogger(ResourcePortlet.class);


   protected void doView(RenderRequest req, RenderResponse resp)
         throws PortletException, IOException {
      
      if (logger.isDebugEnabled()) {
         logger.debug(this.getClass().getName(), "doView", "Entry");
      }
      
      resp.setContentType("text/html");

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(
         "/WEB-INF/jsp/view-ivp.jsp");
      rd.include(req, resp);


   }
   
   /* (non-Javadoc)
    * @see jakarta.portlet.GenericPortlet#serveResource(jakarta.portlet.ResourceRequest, jakarta.portlet.ResourceResponse)
    */
   @Override
   public void serveResource(ResourceRequest req, ResourceResponse resp)
         throws PortletException, IOException {

      String key = req.getRenderParameters().getValue(PARAM_IMGNAME);
      String imgDir = DEFAULT_IMAGE;
      if ((key != null) && IMG_MAP.containsKey(key)) {
         imgDir = IMG_MAP.get(key);
      }
      
      String bc = req.getResourceParameters().getValue(PARAM_BORDER_COLOR);
      String imgStyle = "";
      if (bc != null) {
         imgStyle = " style='border:1px solid " + bc + ";' ";
      }
      
      String ctx = req.getContextPath();
      String ca = req.getCacheability();
      
      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();
      
      String clr = req.getRenderParameters().getValue(PARAM_COLOR);
      clr = (clr == null) ? "#FFFFFF" : clr;
      
      // add action button if cacheability allows -
      PortletURL aurl = null;
      String bmu = "<p>Action URL could not be created.</p>";
      try {
         aurl = resp.createActionURL();
      } catch (Exception e) {}
      if (aurl != null) {
         bmu = "<form  METHOD='POST' ACTION='" + aurl + "'>" +
               "<input id='" + resp.getNamespace() + "-clear' type='submit' name='action' value='Action' /></form>";
      }

      writer.write("<div style='background-color:" + clr + ";'>\n");
      writer.write("   <table style='background-color:" + clr + ";'>");
      writer.write("   <tr><td align='center' style='background-color:" + clr + ";'>");
      writer.write("   <img src='" + ctx + imgDir + "'" + imgStyle + ">\n");
      writer.write("   </td><td style='background-color:" + clr + ";'>");
      writer.write("   <p>" + bmu + "</p>");
      writer.write("   <p>Cacheability: " + ca + "</p>");
      writer.write("   </td></tr>");
      writer.write("   </table>");
      writer.write("</div>\n");


   }

   public void processAction(ActionRequest req, ActionResponse resp)
         throws PortletException, IOException {
   }

}
