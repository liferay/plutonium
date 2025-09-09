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
