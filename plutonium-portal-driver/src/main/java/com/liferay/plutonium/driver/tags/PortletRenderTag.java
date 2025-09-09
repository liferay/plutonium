/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

/**
 * The portlet render tag is used to print portlet rendering result (or error details) to the page.
 * 
 * @version 1.0
 * @since Oct 4, 2004
 */
public class PortletRenderTag extends TagSupport {
   private static final long serialVersionUID = 1L;

   // TagSupport Impl ---------------------------------------------------------

   /**
    * 
    * @see PortletTag
    */
   public int doEndTag() throws JspException {

      // Ensure that the portlet render tag resides within a portlet tag.
      PortletTag parentTag = (PortletTag) TagSupport.findAncestorWithClass(this, PortletTag.class);
      if (parentTag == null) {
         throw new JspException("Portlet render tag may only reside " + "within a plutonium:portlet tag.");
      }

      // If the portlet is rendered successfully, print the rendering result.

      try {
         if (parentTag.getStatus() == PortletTag.SUCCESS) {
            StringBuffer buffer = parentTag.getPortalServletResponse().getInternalBuffer().getBuffer();
            pageContext.getOut().print(buffer.toString());
         } else {

            // Otherwise, print the error messages

            List<String> msgs = parentTag.getMessages();

            if (msgs.isEmpty()) {
               pageContext.getOut().print(" Unknown error rendering portlet.");
            } else {

               for (String msg : msgs) {
                  pageContext.getOut().print("<p>");
                  pageContext.getOut().print(msg);
                  pageContext.getOut().print("</p>");
               }
            }
         }
      } catch (IOException ex) {
         throw new JspException(ex);
      }

      // Return.
      return SKIP_BODY;
   }

}
