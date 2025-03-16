/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package org.apache.pluto.container.bean.processor.fixtures.mocks;

import java.util.Enumeration;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;
import javax.xml.namespace.QName;

/**
 * @author Scott
 *
 */
public class MockPortletConfig implements PortletConfig {
   
   private class PortletRes extends ListResourceBundle {
      protected Object[][] getContents() {
          return new Object[][] {
              {"jakarta.portlet.title", "Some Title"},
          };
      }
  }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getContainerRuntimeOptions()
    */
   @Override
   public Map<String, String[]> getContainerRuntimeOptions() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getDefaultNamespace()
    */
   @Override
   public String getDefaultNamespace() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getInitParameter(java.lang.String)
    */
   @Override
   public String getInitParameter(String arg0) {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getInitParameterNames()
    */
   @Override
   public Enumeration<String> getInitParameterNames() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getPortletContext()
    */
   @Override
   public PortletContext getPortletContext() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getPortletModes(java.lang.String)
    */
   @Override
   public Enumeration<PortletMode> getPortletModes(String arg0) {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getPortletName()
    */
   @Override
   public String getPortletName() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getProcessingEventQNames()
    */
   @Override
   public Enumeration<QName> getProcessingEventQNames() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getPublicRenderParameterDefinitions()
    */
   @Override
   public Map<String, QName> getPublicRenderParameterDefinitions() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getPublicRenderParameterNames()
    */
   @Override
   public Enumeration<String> getPublicRenderParameterNames() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getPublishingEventQNames()
    */
   @Override
   public Enumeration<QName> getPublishingEventQNames() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getResourceBundle(java.util.Locale)
    */
   @Override
   public ResourceBundle getResourceBundle(Locale arg0) {
      return new PortletRes();
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getSupportedLocales()
    */
   @Override
   public Enumeration<Locale> getSupportedLocales() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletConfig#getWindowStates(java.lang.String)
    */
   @Override
   public Enumeration<WindowState> getWindowStates(String arg0) {
      return null;
   }

}
