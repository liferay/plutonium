/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import jakarta.portlet.ActionParameters;
import jakarta.portlet.ActionRequest;

/**
 * @author Scott
 *
 */
public class MockActionRequest extends MockClientDataRequest implements ActionRequest {

   private String actionName;
   

   
   /**
    * @return the actionName
    */
   public String getActionName() {
      return actionName;
   }



   /**
    * @param actionName the actionName to set
    */
   public void setActionName(String actionName) {
      this.actionName = actionName;
   }

   @Override
   public ActionParameters getActionParameters() {
      return new MockActionParameters(actionName);
   }

   @Override
   public String getParameter(String name) {
      return actionName;
   }

}
