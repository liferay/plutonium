/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import jakarta.portlet.ActionURL;
import jakarta.portlet.CacheControl;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.PortletURL;
import jakarta.portlet.RenderURL;
import jakarta.portlet.ResourceURL;

/**
 * @author Scott Nicklous
 *
 */
public class MockMimeResponse extends MockPortletResponse implements MimeResponse {

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#createActionURL()
    */
   @Override
   public <T extends PortletURL & ActionURL> T createActionURL() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#createActionURL(jakarta.portlet.MimeResponse.Copy)
    */
   @Override
   public ActionURL createActionURL(Copy arg0) {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#createRenderURL()
    */
   @Override
   public <T extends PortletURL & RenderURL> T createRenderURL() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#createRenderURL(jakarta.portlet.MimeResponse.Copy)
    */
   @Override
   public RenderURL createRenderURL(Copy arg0) {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#createResourceURL()
    */
   @Override
   public ResourceURL createResourceURL() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#flushBuffer()
    */
   @Override
   public void flushBuffer() throws IOException {

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#getBufferSize()
    */
   @Override
   public int getBufferSize() {
      return 0;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#getCacheControl()
    */
   @Override
   public CacheControl getCacheControl() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#getCharacterEncoding()
    */
   @Override
   public String getCharacterEncoding() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#getContentType()
    */
   @Override
   public String getContentType() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#getLocale()
    */
   @Override
   public Locale getLocale() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#getPortletOutputStream()
    */
   @Override
   public OutputStream getPortletOutputStream() throws IOException {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#getWriter()
    */
   @Override
   public PrintWriter getWriter() throws IOException {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#isCommitted()
    */
   @Override
   public boolean isCommitted() {
      return false;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#reset()
    */
   @Override
   public void reset() {

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#resetBuffer()
    */
   @Override
   public void resetBuffer() {

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#setBufferSize(int)
    */
   @Override
   public void setBufferSize(int arg0) {

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MimeResponse#setContentType(java.lang.String)
    */
   @Override
   public void setContentType(String arg0) {

   }

}
