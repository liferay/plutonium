/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3annotated;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Named servlet to display the path info when included through a named
 * dispatcher.
 * 
 * @author Scott Nicklous
 *
 */
@WebServlet(urlPatterns = "/Named/Bob/*", name = "Bob")
public class PathDisplayServlet extends HttpServlet {
   private static final long serialVersionUID = -7767947528599563527L;

   private static final String JSP = "/WEB-INF/jsp/pathinfo.jsp?mix1=svtqval1&svtqp3=svtqval3&mix2=svtqval2";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String op = req.getParameter("op");
      PrintWriter writer = resp.getWriter();
      if (op != null) {
         if (op.equals("fwd")) {
            resp.setContentType("text/html");

            writer.write("<h3>Path Info Portlet</h3>");
            writer.write("<div class='infobox'>");

            RenderLink rl = (RenderLink) req.getAttribute("renderLink");
            if (rl != null) {
               rl.writeTo(writer);
            }
            
            PathDisplay pd = new PathDisplay(req, "Path Display Servlet");
            writer.write(pd.toMarkup());
            writer.write("</div>");

         } else if (op.equals("nested")) {
            
            PathDisplay pd = new PathDisplay(req, "PD Servlet (Before)");
            writer.write(pd.toMarkup());
            
            req.setAttribute("jsptitle", "Included by servlet.");
            RequestDispatcher rd = req.getRequestDispatcher(JSP);
            rd.include(req, resp);
            
            pd = new PathDisplay(req, "PD Servlet (After)");
            writer.write(pd.toMarkup());
         } else if (op.equals("named")) {
            PathDisplay pd = new PathDisplay(req, "Path Display Servlet");
            writer.write(pd.toMarkup());
         }
      } else {
         // assume the servlet is being included
         PathDisplay pd = new PathDisplay(req, "Path Display Servlet");
         writer.write(pd.toMarkup());
      }
   }
}
