/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import java.util.Map;

import jakarta.portlet.PortletAsyncContext;
import jakarta.portlet.ResourceParameters;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.servlet.DispatcherType;

/**
 * @author Scott Nicklous
 *
 */
public class MockResourceRequest extends MockClientDataRequest implements ResourceRequest {
   
   private String resourceId;

   /**
    * @return the resourceId
    */
   public String getResourceId() {
      return resourceId;
   }

   /**
    * @param resourceId the resourceId to set
    */
   public void setResourceId(String resourceId) {
      this.resourceId = resourceId;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceRequest#getCacheability()
    */
   @Override
   public String getCacheability() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceRequest#getETag()
    */
   @Override
   public String getETag() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceRequest#getPrivateRenderParameterMap()
    */
   @Override
   public Map<String, String[]> getPrivateRenderParameterMap() {
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceRequest#getResourceID()
    */
   @Override
   public String getResourceID() {
      return resourceId;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ResourceRequest#getResourceParameters()
    */
   @Override
   public ResourceParameters getResourceParameters() {
      return null;
   }

   @Override
   public PortletAsyncContext startPortletAsync() throws IllegalStateException {
      return null;
   }

   @Override
   public PortletAsyncContext startPortletAsync(ResourceRequest request, ResourceResponse response) throws IllegalStateException {
      return null;
   }

   @Override
   public boolean isAsyncStarted() {
      return false;
   }

   @Override
   public boolean isAsyncSupported() {
      return false;
   }

   @Override
   public PortletAsyncContext getPortletAsyncContext() {
      return null;
   }

   @Override
   public DispatcherType getDispatcherType() {
      return null;
   }

}
