/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.chat;

import java.io.IOException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.annotations.HeaderMethod;

/**
 * Header method to include a css file.
 */
@ApplicationScoped
public class CSSIncludingHeaderMethod {
   
   // The header method can apply to a list of portlets. If the asterisk is specified 
   // as first portlet name, the header method is executed for all portlets in
   // the portlet application.
   @HeaderMethod(portletNames="*")
   public void header(HeaderRequest req, HeaderResponse resp) throws IOException {

      // Add link tag to head section to include the style sheet.

      String contextRoot = req.getContextPath();
      StringBuilder txt = new StringBuilder(128);
      txt.append("<link href='").append(contextRoot);
      txt.append("/resources/css/infobox.css' rel='stylesheet' type='text/css'>");
      
      // Illustrates the use of the V3 HeaderResponse#addDependency method.
      // The first three arguments represent the resource ID, corresponding to the 
      // resource name, scope, and version. The portlet container will assure that
      // the resource  is placed in the document HEAD section, even if the dependency
      // is added multiple times by different portlets. The final argument can be used
      // to provide the dependency, potentially for use by other portlets as well.
      
      resp.addDependency("infobox", "com.liferay.pluto", "0.3.0", txt.toString());
      
   }

}
