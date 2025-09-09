/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3annotated;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.ResourceParameters;
import jakarta.portlet.ResourceURL;
import jakarta.portlet.annotations.RenderMethod;
import jakarta.portlet.annotations.ServeResourceMethod;
import jakarta.portlet.MimeResponse;

/**
 * Contains a resource method and associated markup generator to insert 
 * an image.
 */
@Dependent
public class HelloWorldImage {

   public static final String RESPARAM_DISPLAY = "display";
   
   @Inject private            MimeResponse mimeresp;
   
   @Inject
   PortletRequest req;

   @Inject
   NameBean nameBean;
   
   // Some chimp pictures to associate with a name
   private final static String[] chimps = new String[] {
         "/resources/images/umonkey1.gif", 
         "/resources/images/umonkey2.gif", 
         "/resources/images/umonkey3.gif", 
         "/resources/images/umonkey4.gif", 
         "/resources/images/umonkey5.gif", 
         "/resources/images/umonkey6.gif", 
         "/resources/images/umonkey7.gif", 
         "/resources/images/umonkey8.gif", 
         "/resources/images/umonkey9.gif", 
         "/resources/images/umonkeyA.gif", 
   };

   @RenderMethod(portletNames = {"BeanPortlet"}, ordinal=100)
   public String getImageInclude() {
      
      StringBuilder txt = new StringBuilder(128);
      ResourceURL resurl = mimeresp.createResourceURL();
      if (nameBean.getName() != null) {
         resurl.getResourceParameters().setValue(RESPARAM_DISPLAY, "true");
      }
      
      String pid = mimeresp.getNamespace();
      txt.append("<div class='infobox' id='").append(pid).append("-putResourceHere'></div>\n");
      txt.append("<script>\n");
      txt.append("(function () {\n");
      txt.append("   var xhr = new XMLHttpRequest();\n");
      txt.append("   xhr.onreadystatechange=function() {\n");
      txt.append("      if (xhr.readyState==4 && xhr.status==200) {\n");
      txt.append("         document.getElementById('").append(pid).append("-putResourceHere').innerHTML=xhr.responseText;\n");
      txt.append("      }\n");
      txt.append("   };\n");
      txt.append("   xhr.open(\"GET\",\"").append(resurl.toString()).append("\",true);\n");
      txt.append("   xhr.send();\n");
      txt.append("})();\n");
      txt.append("</script>\n");

      return txt.toString();
   }

   // Inject the resource parameter to see if image should be displayed.
   @Inject  
   private ResourceParameters resparams;
   
   /**
    *  This resource method generates some output directly, then includes output
    *  from a JSP as specified in the annotation.
    *  
    * @return  The string for inclusion in the output.
    */
   @ServeResourceMethod(portletNames={"BeanPortlet"}, include="/WEB-INF/jsp/res-simple.jsp")
   public String getImage() {
      String result = "";
      String display = resparams.getValue(RESPARAM_DISPLAY);
      
      // illustrate use of the injected display resource parameter
      if (display != null) {
         
         // pick a chimp
         
         int ind = (int) (Math.random() * chimps.length);
         String imgSrc = req.getContextPath() + chimps[ind];
         imgSrc = mimeresp.encodeURL(imgSrc);
         req.setAttribute("imgSrc", imgSrc);
         
         // set the output
         
         result = "<p>Your image appears here:</p>";
      }
      return result;
   }

}
