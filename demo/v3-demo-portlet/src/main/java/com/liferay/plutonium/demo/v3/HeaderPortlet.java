/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3;

import static com.liferay.plutonium.demo.v3.Constants.ATTRIB_PROPS;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.PortalContext;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.LocaleString;
import jakarta.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

/**
 * Portlet for testing the redirect funtionality, including the new getRedirectURL API.
 */
@PortletConfiguration(portletName="V3HeaderPortlet", 
                      title=@LocaleString("Header Phase Test Portlet"))
public class HeaderPortlet extends GenericPortlet {

   private static final Logger logger  = LoggerFactory.getLogger(HeaderPortlet.class);
   private static final boolean isDebug = logger.isDebugEnabled();
   
   @Override
   public void renderHeaders(HeaderRequest req, HeaderResponse resp) throws PortletException, IOException {
      
      if (isDebug) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Doing the headers. ");
         txt.append("portal ctx prop names: ");
         txt.append(Collections.list(req.getPortalContext().getPropertyNames()).toString());
         txt.append(", markup head prop: ");
         txt.append(req.getPortalContext().getProperty(PortalContext.MARKUP_HEAD_ELEMENT_SUPPORT));
         txt.append(", RENDER_PART: ");
         txt.append((String)req.getAttribute(PortletRequest.RENDER_PART));
         logger.debug(txt.toString());
      }

      // Add link tag to head section to include the style sheet
      Element link = resp.createElement("link");
      link.setAttribute("rel", "stylesheet");
      link.setAttribute("type", "text/css");
      String contextRoot = req.getContextPath();
      link.setAttribute("href", contextRoot + "/resources/css/styles.css");
      resp.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, link);
      
      // Add cookies
      Cookie c = new Cookie(this.getPortletName(), "something-special");
      c.setMaxAge(60);
      resp.addProperty(c);
      c = new Cookie("Author", "Scott");
      c.setComment("test cookie");
      resp.addProperty(c);

      // Set header
      resp.addProperty("Portlet", this.getPortletName());
      resp.setProperty("Portal", "Pluto");
      resp.addProperty("Portal", "Apache");
      
      // get header info
      
      Collection<String> names = resp.getPropertyNames();
      List<String> hdrInfo = new ArrayList<String>();
      for (String name: names) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Property name: ").append(name);
         txt.append(", value: ").append(resp.getProperty(name));
         txt.append(", values: ").append(resp.getPropertyValues(name));
         hdrInfo.add(txt.toString());
      }
      req.getPortletSession().setAttribute(ATTRIB_PROPS, hdrInfo);

      PrintWriter writer = resp.getWriter();
      writer.println("<!-- before JSP include -->");
      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/headSectionMarkup.jsp");
      rd.include(req, resp);
      writer.println("<!-- after JSP include -->");

   }

   @Override
   protected void doView(RenderRequest req, RenderResponse resp) throws PortletException, IOException {
      
      if (isDebug) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Rendering. ");
         txt.append("RENDER_PART: ");
         txt.append((String)req.getAttribute(PortletRequest.RENDER_PART));
         logger.debug(txt.toString());
      }

      resp.setContentType("text/html");

      PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/view-hdp.jsp");
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
   }

}
