/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jakarta.portlet.CacheControl;
import jakarta.portlet.MimeResponse;

import static jakarta.portlet.MimeResponse.Copy.*;

import jakarta.portlet.RenderURL;
import jakarta.portlet.ActionURL;
import jakarta.portlet.ResourceURL;

import com.liferay.plutonium.container.PortletMimeResponseContext;
import com.liferay.plutonium.container.om.portlet.PortletDefinition;
import com.liferay.plutonium.container.om.portlet.Supports;

public class MimeResponseImpl extends PortletResponseImpl implements
      MimeResponse {
   /** Response content types. */
   private ArrayList<String>          responseContentTypes;

   private boolean                    usingWriter;
   private boolean                    usingStream;
   private boolean                    committed;
   private String                     contentType;

   protected PortletMimeResponseContext responseContext;

   public MimeResponseImpl(PortletMimeResponseContext responseContext) {
      super(responseContext);
      this.responseContext = responseContext;
      PortletDefinition portletDefinition = getPortletWindow()
            .getPortletDefinition();
      getCacheControl().setExpirationTime(
            portletDefinition.getExpirationCache());
      getCacheControl().setPublicScope(
            PUBLIC_SCOPE.equals(portletDefinition.getCacheScope()));
   }

   protected List<String> getResponseContentTypes() {
      if (responseContentTypes == null) {
         responseContentTypes = new ArrayList<String>();
         PortletDefinition dd = getPortletWindow().getPortletDefinition();
         for (Supports sup : dd.getSupports()) {
            responseContentTypes.add(sup.getMimeType());
         }
         if (responseContentTypes.isEmpty()) {
            responseContentTypes.add("text/html");
         }
      }
      return responseContentTypes;
   }

   @SuppressWarnings("unchecked")
   public ActionURL createActionURL() {
      return new ActionURLImpl(responseContext, PUBLIC);
   }

   @SuppressWarnings("unchecked")
   public RenderURL createRenderURL() {
      return new RenderURLImpl(responseContext, PUBLIC);
   }

   public ActionURL createActionURL(Copy option) {
      return new ActionURLImpl(responseContext, option);
   }

   public RenderURL createRenderURL(Copy option) {
      return new RenderURLImpl(responseContext, option);
   }

   public ResourceURL createResourceURL() {
      return new ResourceURLImpl(responseContext);
   }

   public void flushBuffer() throws IOException {
      committed = true;
      responseContext.flushBuffer();
   }

   public int getBufferSize() {
      return responseContext.getBufferSize();
   }

   public CacheControl getCacheControl() {
      return responseContext.getCacheControl();
   }

   public String getCharacterEncoding() {
      return responseContext.getCharacterEncoding();
   }

   public String getContentType() {
      return contentType;
   }

   public Locale getLocale() {
      return responseContext.getLocale();
   }

   public OutputStream getPortletOutputStream() throws IllegalStateException,
         IOException {
      if (usingWriter) {
         throw new IllegalStateException(
               "getPortletOutputStream can't be used after getWriter was invoked.");
      }
      if (getContentType() == null) {
         setContentType(getResponseContentTypes().get(0));
      }
      usingStream = true;
      return responseContext.getOutputStream();
   }

   public PrintWriter getWriter() throws IllegalStateException, IOException {
      if (usingStream) {
         throw new IllegalStateException(
               "getWriter can't be used after getOutputStream was invoked.");
      }
      if (getContentType() == null) {
         setContentType(getResponseContentTypes().get(0));
      }
      usingWriter = true;
      return responseContext.getWriter();
   }

   public boolean isCommitted() {
      return committed ? true : responseContext.isCommitted();
   }

   public void reset() {
      if (isCommitted()) {
         throw new IllegalStateException("Response is already committed");
      }
      responseContext.reset();
   }

   public void resetBuffer() {
      if (isCommitted()) {
         throw new IllegalStateException("Response is already committed");
      }
      responseContext.resetBuffer();
   }

   public void setBufferSize(int size) {
      if (isCommitted()) {
         throw new IllegalStateException("Response is already committed");
      }
      responseContext.setBufferSize(size);
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
      responseContext.setContentType(contentType);
   }

   @Override
   public void setProperty(String name, String value) {
      if (USE_CACHED_CONTENT.equals(name)) {
         getCacheControl().setUseCachedContent(value != null);
      } else if (EXPIRATION_CACHE.equals(name)) {
         int expirationTime;
         try {
            expirationTime = Integer.parseInt(value);
         } catch (NumberFormatException e) {
            expirationTime = 0;
         }
         getCacheControl().setExpirationTime(expirationTime);
      } else if (ETAG.equals(name)) {
         getCacheControl().setETag(value);
      } else if (CACHE_SCOPE.equals(name)) {
         getCacheControl().setPublicScope(PUBLIC_SCOPE.equals(value));
      } else {
         super.setProperty(name, value);
      }
   }
}
