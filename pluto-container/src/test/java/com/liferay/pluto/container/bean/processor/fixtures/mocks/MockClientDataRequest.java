/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import jakarta.portlet.ClientDataRequest;
import jakarta.portlet.PortletException;
import jakarta.servlet.http.Part;

/**
 * @author Scott
 *
 */
public class MockClientDataRequest extends MockPortletRequest implements ClientDataRequest {

   /* (non-Javadoc)
    * @see jakarta.portlet.ClientDataRequest#getCharacterEncoding()
    */
   @Override
   public String getCharacterEncoding() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ClientDataRequest#getContentLength()
    */
   @Override
   public int getContentLength() {
      return 0;
   }

   @Override
   public long getContentLengthLong() {
      return 0;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ClientDataRequest#getContentType()
    */
   @Override
   public String getContentType() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ClientDataRequest#getMethod()
    */
   @Override
   public String getMethod() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ClientDataRequest#getPortletInputStream()
    */
   @Override
   public InputStream getPortletInputStream() throws IOException {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ClientDataRequest#getReader()
    */
   @Override
   public BufferedReader getReader() throws UnsupportedEncodingException, IOException {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ClientDataRequest#setCharacterEncoding(java.lang.String)
    */
   @Override
   public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
   }

   @Override
   public Part getPart(String name) throws IOException, PortletException {
      return null;
   }

   @Override
   public Collection<Part> getParts() throws IOException, PortletException {
      return null;
   }

}
