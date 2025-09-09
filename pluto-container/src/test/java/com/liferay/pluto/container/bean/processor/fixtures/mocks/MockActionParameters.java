/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import java.util.Collections;
import java.util.Set;

import jakarta.portlet.ActionParameters;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.MutableActionParameters;

/**
 * @author Scott
 *
 */
public class MockActionParameters implements ActionParameters {
   
   private String actionName;
   
   /**
    * Constructor 
    */
   public MockActionParameters(String name) {
      actionName = name;
   }
   
   public void setActionName(String name) {
      actionName = name;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#getNames()
    */
   @Override
   public Set<String> getNames() {
      return Collections.singleton(ActionRequest.ACTION_NAME);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#getValue(java.lang.String)
    */
   @Override
   public String getValue(String arg0) {
      if (arg0.equals(ActionRequest.ACTION_NAME)) {
         return actionName;
      }
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#getValues(java.lang.String)
    */
   @Override
   public String[] getValues(String arg0) {
      if (arg0.equals(ActionRequest.ACTION_NAME)) {
         return new String[] {actionName};
      }
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#isEmpty()
    */
   @Override
   public boolean isEmpty() {
      return false;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#size()
    */
   @Override
   public int size() {
      return 1;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.ActionParameters#clone()
    */
   @Override
   public MutableActionParameters clone() {
      return null;
   }

}
