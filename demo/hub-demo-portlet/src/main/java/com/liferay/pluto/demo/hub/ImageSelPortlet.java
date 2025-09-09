/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.hub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.liferay.pluto.demo.hub.Constants.PARAM_IMGNAME;
import static com.liferay.pluto.demo.hub.Constants.PARAM_SELTYPE;
import static com.liferay.pluto.demo.hub.Constants.PARAM_SELTYPE_RADIO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
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
import static com.liferay.pluto.demo.hub.Constants.IMG_MAP;


/**
 * A demo portlet for selecting images
 */
@PortletConfiguration(portletName = "ImageSelPortlet", publicParams = "imgName", 
                      title = @LocaleString("PH Image Selection Portlet"),
                      dependencies = @Dependency(name="PortletHub", scope="jakarta.portlet", version="3.0.0"))
public class ImageSelPortlet extends GenericPortlet {

   // Set up logging
   private final Logger logger = LoggerFactory.getLogger(ImageSelPortlet.class);

   protected void doView(RenderRequest req, RenderResponse resp)
         throws PortletException, IOException {
      
      if (logger.isDebugEnabled()) {
         logger.debug(this.getClass().getName(), "doView", "Entry");
      }
      
      resp.setContentType("text/html");

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(
            "/WEB-INF/jsp/view-isp.jsp");
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

	      String pid = resp.getNamespace();
	      Set<String> names = IMG_MAP.keySet();
	      String selType = req.getRenderParameters().getValue(PARAM_SELTYPE);
	      selType = (selType == null) ? PARAM_SELTYPE_RADIO : selType;
	      String imgName = req.getRenderParameters().getValue(PARAM_IMGNAME);
	      imgName = (imgName == null) ? "default" : imgName;

	      if (selType.equals(PARAM_SELTYPE_RADIO)) {
	         for (String name : names) {
	            writer.write("   <input type='radio' name='" + PARAM_IMGNAME + "' value='" + 
	               name + "'" + (name.equals(imgName) ? "checked" : "") + ">" + name);
	         }
	      } else {
	         writer.write("<select id='" + pid + "-selBox' name='" + PARAM_IMGNAME + "' size='1'>");
	         writer.write("   <option value='default' " + 
	               ("default".equals(imgName) ? "selected" : "") + ">-</option>");
	         for (String name : names) {
	            writer.write("   <option value='" + name + "'" + 
	               (name.equals(imgName) ? "selected" : "") + ">" + name + "</option>");
	         }
	         writer.write("</select>");
	      }
	}

   public void processAction(ActionRequest req, ActionResponse resp)
         throws PortletException, IOException {
   }

}
