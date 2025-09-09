/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import jakarta.portlet.PortletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.plutonium.container.PortletActionResponseContext;
import com.liferay.plutonium.container.PortletContainer;
import com.liferay.plutonium.container.PortletRequestContext;
import com.liferay.plutonium.container.PortletWindow;
import com.liferay.plutonium.driver.core.PortalRequestContext;
import com.liferay.plutonium.driver.url.PortalURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version $Id$
 * 
 */
public class PortletActionResponseContextImpl extends PortletStateAwareResponseContextImpl implements PortletActionResponseContext {

   /** Logger. */
   private static final Logger  LOG     = LoggerFactory.getLogger(PortletActionResponseContextImpl.class);
   private static final boolean isTrace = LOG.isTraceEnabled();

   private boolean              redirect;
   private String               redirectLocation;
   private String               renderURLParamName;

   public PortletActionResponseContextImpl(PortletContainer container, HttpServletRequest containerRequest, HttpServletResponse containerResponse,
         PortletWindow window, PortletRequestContext requestContext) {
      super(container, containerRequest, containerResponse, window, requestContext);
      setLifecycle(PortletRequest.ACTION_PHASE);
   }

   public String getResponseURL() {
      if (!isReleased()) {
         close();
         if (!redirect || renderURLParamName != null) {
            PortalURL url = PortalRequestContext.getContext(getServletRequest()).createPortalURL();
            if (redirect) {
               try {
                  
                  // properly handle redirect location, which may have a query string and a fragment identifier.
                  // first, extract info from redirect location -
                  
                  String fragment = redirectLocation.replaceFirst("^([^#]*)(#.*)$", "$2");
                  String base = redirectLocation.replaceFirst("^([^#]*)(#.*)$", "$1");

                  boolean hasFragment = !fragment.equals(redirectLocation);
                  boolean hasQuery = base.indexOf("?") >= 0;
                  
                  // Now build the overall URL

                  StringBuilder urlBuilder = new StringBuilder(128);
                  urlBuilder.append(base);
                  urlBuilder.append(hasQuery ? "&" : "?");
                  urlBuilder.append(URLEncoder.encode(renderURLParamName, "UTF-8")).append("=");
                  urlBuilder.append(URLEncoder.encode(url.toURL(true), "UTF-8"));
                  urlBuilder.append(hasFragment ? fragment : "");
                  
                  if (isTrace) {
                     StringBuilder txt = new StringBuilder();
                     txt.append("hasFragment: ").append(hasFragment);
                     txt.append(", fragment: ").append(hasFragment ? fragment : "n/a");
                     txt.append(", hasQuery: ").append(hasQuery);
                     txt.append(", paramName: ").append(renderURLParamName);
                     txt.append("\n   Original redirect location: ").append(redirectLocation);
                     txt.append("\n   Complete URL: ").append(urlBuilder.toString());
                     LOG.debug(txt.toString());
                  }
                  
                  return urlBuilder.toString();
               } catch (UnsupportedEncodingException e) {
                  // Cannot happen: UTF-8 is a buildin/required encoder
                  return null;
               }
            } else {
               return url.toURL(false);
            }
         } else {
            return redirectLocation;
         }
      }
      return null;
   }

   public boolean isRedirect() {
      return redirect;
   }

   public void setRedirect(String location) {
      setRedirect(location, null);
   }

   public void setRedirect(String location, String renderURLParamName) {
      if (!isClosed()) {
         this.redirectLocation = location;
         this.renderURLParamName = renderURLParamName;
         this.redirect = true;
      }
   }
}
