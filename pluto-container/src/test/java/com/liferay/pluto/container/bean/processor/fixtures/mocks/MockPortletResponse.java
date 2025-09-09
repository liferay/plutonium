/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import java.util.Collection;

import jakarta.portlet.PortletResponse;
import jakarta.servlet.http.Cookie;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/**
 * @author Scott
 *
 */
public class MockPortletResponse implements PortletResponse {

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletResponse#addProperty(jakarta.servlet.http.Cookie)
    */
   @Override
   public void addProperty(Cookie arg0) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletResponse#addProperty(java.lang.String, java.lang.String)
    */
   @Override
   public void addProperty(String arg0, String arg1) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletResponse#addProperty(java.lang.String, org.w3c.dom.Element)
    */
   @Override
   public void addProperty(String arg0, Element arg1) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletResponse#createElement(java.lang.String)
    */
   @Override
   public Element createElement(String arg0) throws DOMException {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletResponse#encodeURL(java.lang.String)
    */
   @Override
   public String encodeURL(String arg0) {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletResponse#getNamespace()
    */
   @Override
   public String getNamespace() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletResponse#setProperty(java.lang.String, java.lang.String)
    */
   @Override
   public void setProperty(String arg0, String arg1) {
   }

   @Override
   public String getProperty(String key) {
      return null;
   }

   @Override
   public Collection<String> getPropertyValues(String name) {
      return null;
   }

   @Override
   public Collection<String> getPropertyNames() {
      return null;
   }

}
