/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import java.io.Serializable;
import java.util.Map;

import jakarta.portlet.MutableRenderParameters;
import jakarta.portlet.PortletMode;
import jakarta.portlet.PortletModeException;
import jakarta.portlet.StateAwareResponse;
import jakarta.portlet.WindowState;
import jakarta.portlet.WindowStateException;
import javax.xml.namespace.QName;

/**
 * @author Scott
 *
 */
public class MockStateAwareResponse extends MockPortletResponse implements StateAwareResponse {

   /* (non-Javadoc)
    * @see jakarta.portlet.MutableRenderState#getRenderParameters()
    */
   @Override
   public MutableRenderParameters getRenderParameters() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MutableRenderState#setPortletMode(jakarta.portlet.PortletMode)
    */
   @Override
   public void setPortletMode(PortletMode arg0) throws PortletModeException {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.MutableRenderState#setWindowState(jakarta.portlet.WindowState)
    */
   @Override
   public void setWindowState(WindowState arg0) throws WindowStateException {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.RenderState#getPortletMode()
    */
   @Override
   public PortletMode getPortletMode() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.RenderState#getWindowState()
    */
   @Override
   public WindowState getWindowState() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.StateAwareResponse#getRenderParameterMap()
    */
   @Override
   public Map<String, String[]> getRenderParameterMap() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.StateAwareResponse#removePublicRenderParameter(java.lang.String)
    */
   @Override
   public void removePublicRenderParameter(String arg0) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.StateAwareResponse#setEvent(javax.xml.namespace.QName, java.io.Serializable)
    */
   @Override
   public void setEvent(QName arg0, Serializable arg1) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.StateAwareResponse#setEvent(java.lang.String, java.io.Serializable)
    */
   @Override
   public void setEvent(String arg0, Serializable arg1) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.StateAwareResponse#setRenderParameter(java.lang.String, java.lang.String)
    */
   @Override
   public void setRenderParameter(String arg0, String arg1) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.StateAwareResponse#setRenderParameter(java.lang.String, java.lang.String[])
    */
   @Override
   public void setRenderParameter(String arg0, String... arg1) {
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.StateAwareResponse#setRenderParameters(java.util.Map)
    */
   @Override
   public void setRenderParameters(Map<String, String[]> arg0) {
   }

}
