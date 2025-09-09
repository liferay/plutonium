/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.pluto.driver.tags;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.portlet.PortletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import com.liferay.pluto.container.PortletWindow;
import com.liferay.pluto.driver.AttributeKeys;
import com.liferay.pluto.driver.config.DriverConfiguration;
import com.liferay.pluto.driver.services.portal.PortletWindowConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The portlet title tag is used to print the dynamic portlet title to the page.
 * 
 * @author <a href="mailto:ddewolf@apache.org">David H. DeWolf</a>
 * @author <a href="mailto:zheng@apache.org">ZHENG Zhong</a>
 * @version 1.0
 * @since Oct 4, 2004
 */
public class PortletTitleTag extends TagSupport {
   private static final long   serialVersionUID = 3120612251049604115L;

   private static final Logger LOG              = LoggerFactory.getLogger(PortletTitleTag.class);

   // TagSupport Impl ---------------------------------------------------------

   /**
    * Method invoked when the start tag is encountered. This method retrieves the portlet title and print it to the
    * page.
    * 
    * @see com.liferay.pluto.container.services.PortalCallbackService#setTitle(HttpServletRequest, PortletWindow, String)
    * @see com.liferay.pluto.driver.services.container.PortalCallbackServiceImpl#setTitle(HttpServletRequest,
    *      PortletWindow, String)
    * 
    * @throws JspException
    */
   @SuppressWarnings("unchecked")
   public int doStartTag() throws JspException {

      // Ensure that the portlet title tag resides within a portlet tag.
      PortletTag parentTag = (PortletTag) TagSupport.findAncestorWithClass(this, PortletTag.class);
      if (parentTag == null) {
         throw new JspException("Portlet title tag may only reside " + "within a pluto:portlet tag.");
      }

      // Print out the portlet title to page.
      try {

         Map<String, String> titles = (Map<String, String>) pageContext.getRequest().getAttribute(AttributeKeys.PORTLET_TITLE);

         String portletId = parentTag.getEvaluatedPortletId();

         String title = null;
         if (titles != null) {
            title = titles.get(portletId);
         }

         if (title == null) {

            PortletWindowConfig windowConfig = PortletWindowConfig.fromId(portletId);

            try {
               ServletContext servletContext = pageContext.getServletContext();
               DriverConfiguration driverConfig = (DriverConfiguration) servletContext.getAttribute(AttributeKeys.DRIVER_CONFIG);
               PortletConfig config = driverConfig.getPortletConfig(portletId);
               ServletRequest request = pageContext.getRequest();
               Locale defaultLocale = request.getLocale();
               ResourceBundle bundle = config.getResourceBundle(defaultLocale);
               title = bundle.getString("jakarta.portlet.title");
            } catch (Throwable th) {
               if (LOG.isDebugEnabled()) {
                  StringBuilder txt = new StringBuilder(128);
                  txt.append("Could not obtain title for: " + windowConfig.getPortletName() + "\n");
                  StringWriter sw = new StringWriter();
                  PrintWriter pw = new PrintWriter(sw);
                  th.printStackTrace(pw);
                  pw.flush();
                  txt.append(sw.toString());
                  LOG.warn(txt.toString());
               }
            }

            if (title == null) {
               title = "[ " + windowConfig.getPortletName() + " ]";
            }
         }

         pageContext.getOut().print(title);
      } catch (IOException ex) {
         throw new JspException(ex);
      }
      return SKIP_BODY;
   }
}
