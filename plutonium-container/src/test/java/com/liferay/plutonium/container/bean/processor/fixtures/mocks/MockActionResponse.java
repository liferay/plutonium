/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures.mocks;

import java.io.IOException;

import jakarta.portlet.ActionResponse;
import jakarta.portlet.MimeResponse.Copy;
import jakarta.portlet.RenderURL;

/**
 * @author Scott
 *
 */
public class MockActionResponse extends MockStateAwareResponse implements ActionResponse {

   /* (non-Javadoc)
    * @see jakarta.portlet.ActionResponse#getRedirectURL(jakarta.portlet.MimeResponse.Copy)
    */
   @Override
   public RenderURL createRedirectURL(Copy arg0) throws IllegalStateException {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ActionResponse#sendRedirect(java.lang.String)
    */
   @Override
   public void sendRedirect(String arg0) throws IOException {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ActionResponse#sendRedirect(java.lang.String, java.lang.String)
    */
   @Override
   public void sendRedirect(String arg0, String arg1) throws IOException {
   }

}
