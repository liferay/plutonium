/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletSession;

/**
 * @author Scott Nicklous
 *
 */
public class MockPortletSession implements PortletSession {
   
   private Map<String, Object> attrs = new HashMap<String, Object>();

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getAttribute(java.lang.String)
    */
   @Override
   public Object getAttribute(String arg0) {
      return attrs.get(arg0);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getAttribute(java.lang.String, int)
    */
   @Override
   public Object getAttribute(String arg0, int arg1) {
      return attrs.get(arg0);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getAttributeMap()
    */
   @Override
   public Map<String, Object> getAttributeMap() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getAttributeMap(int)
    */
   @Override
   public Map<String, Object> getAttributeMap(int arg0) {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getAttributeNames()
    */
   @Override
   public Enumeration<String> getAttributeNames() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getAttributeNames(int)
    */
   @Override
   public Enumeration<String> getAttributeNames(int arg0) {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getCreationTime()
    */
   @Override
   public long getCreationTime() {
      return 0;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getId()
    */
   @Override
   public String getId() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getLastAccessedTime()
    */
   @Override
   public long getLastAccessedTime() {
      return 0;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getMaxInactiveInterval()
    */
   @Override
   public int getMaxInactiveInterval() {
      return 0;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#getPortletContext()
    */
   @Override
   public PortletContext getPortletContext() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#invalidate()
    */
   @Override
   public void invalidate() {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#isNew()
    */
   @Override
   public boolean isNew() {
      return false;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#removeAttribute(java.lang.String)
    */
   @Override
   public void removeAttribute(String arg0) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#removeAttribute(java.lang.String, int)
    */
   @Override
   public void removeAttribute(String arg0, int arg1) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#setAttribute(java.lang.String, java.lang.Object)
    */
   @Override
   public void setAttribute(String arg0, Object arg1) {
      attrs.put(arg0, arg1);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#setAttribute(java.lang.String, java.lang.Object, int)
    */
   @Override
   public void setAttribute(String arg0, Object arg1, int arg2) {
      attrs.put(arg0, arg1);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletSession#setMaxInactiveInterval(int)
    */
   @Override
   public void setMaxInactiveInterval(int arg0) {
   }

}
