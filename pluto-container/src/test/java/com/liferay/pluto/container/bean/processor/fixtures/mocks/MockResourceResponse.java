/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import java.util.Locale;

import jakarta.portlet.ResourceResponse;

/**
 * @author Scott Nicklous
 *
 */
public class MockResourceResponse extends MockMimeResponse implements ResourceResponse {

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceResponse#setCharacterEncoding(java.lang.String)
    */
   @Override
   public void setCharacterEncoding(String arg0) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceResponse#setContentLength(int)
    */
   @Override
   public void setContentLength(int arg0) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceResponse#setLocale(java.util.Locale)
    */
   @Override
   public void setLocale(Locale arg0) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceResponse#setStatus(int)
    */
   @Override
   public void setStatus(int arg0) {
   }

   @Override
   public int getStatus() {
      return 0;
   }

   @Override
   public void setContentLengthLong(long len) {
   }

}
