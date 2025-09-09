/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import java.io.IOException;

import jakarta.portlet.ActionResponse;
import jakarta.portlet.MimeResponse.Copy;
import jakarta.portlet.RenderURL;

import com.liferay.plutonium.container.PortletActionResponseContext;
import com.liferay.plutonium.container.ResourceURLProvider;
import com.liferay.plutonium.container.util.ArgumentUtility;

public class ActionResponseImpl extends StateAwareResponseImpl implements ActionResponse {
   private boolean   stateChanged;
   protected boolean redirected;

   public ActionResponseImpl(PortletActionResponseContext responseContext) {
      super(responseContext);
   }

   protected void checkSetRedirected() {
      if (stateChanged) {
         throw new IllegalStateException("sendRedirect no longer allowed after navigational state changes");
      }
      if (redirected) {
         throw new IllegalStateException("sendRedirect already called");
      }
      redirected = true;
   }

   protected void checkSetStateChanged() {
      if (redirected) {
         throw new IllegalStateException("State change no longer allowed after a sendRedirect");
      }
      stateChanged = true;
   }

   protected String getRedirectLocation(String location) {
      ArgumentUtility.validateNotEmpty("location", location);
      ResourceURLProvider provider = getResponseContext().getResourceURLProvider();

      if (location.indexOf("://") != -1) {
         provider.setAbsoluteURL(location);
      } else {
         provider.setFullPath(location);
      }
      location = getServletResponse().encodeRedirectURL(provider.toString());
      if (location.indexOf("/") == -1) {
         throw new IllegalArgumentException(
               "There is a relative path given, an IllegalArgumentException must be thrown.");
      }
      return location;
   }

   public void sendRedirect(String location) throws IOException {
      location = getRedirectLocation(location);
      checkSetRedirected();
      ((PortletActionResponseContext) getResponseContext()).setRedirect(location);
   }

   public void sendRedirect(String location, String renderUrlParamName) throws IOException {
      ArgumentUtility.validateNotEmpty("renderUrlParamName", renderUrlParamName);
      location = getRedirectLocation(location);
      if (!redirected) {
         stateChanged = false;
      }
      checkSetRedirected();
      ((PortletActionResponseContext) getResponseContext()).setRedirect(location, renderUrlParamName);
   }

   @Override
   public RenderURL createRedirectURL(Copy option) throws IllegalStateException {
      if (stateChanged) {
         throw new IllegalStateException("sendRedirect no longer allowed after navigational state changes");
      }
      return new RenderURLImpl(responseContext, option);
   }
}
