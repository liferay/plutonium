/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.reconcile.fixtures;

import java.io.IOException;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;

/**
 * @author Scott Nicklous
 *
 */
public class TestPortlet3 implements Portlet {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   private PortletConfig config;

   @Override
   public void destroy() {
      meths.addMethod(this.getClass().getSimpleName() + "#destroy");
      meths.setConfigExists(config != null);
   }

   @Override
   public void init(PortletConfig config) throws PortletException {
      this.config = config;
      meths.addMethod(this.getClass().getSimpleName() + "#init");
      meths.setConfigExists(config != null);
   }

   @Override
   public void processAction(ActionRequest arg0, ActionResponse arg1)
         throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#processAction");
      meths.setConfigExists(config != null);
   }

   @Override
   public void render(RenderRequest arg0, RenderResponse arg1)
         throws PortletException, IOException {
      meths.addMethod(this.getClass().getSimpleName() + "#render");
      meths.setConfigExists(config != null);
   }

}
