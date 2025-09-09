/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.assemble;

import java.io.IOException;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

/**
 * Dummy portlet for test
 * 
 * @author Scott Nicklous
 *
 */
public class WarTestPortletClass implements Portlet {

   /* (non-Javadoc)
    * @see jakarta.portlet.Portlet#destroy()
    */
   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.Portlet#init(jakarta.portlet.PortletConfig)
    */
   @Override
   public void init(PortletConfig arg0) throws PortletException {
      // TODO Auto-generated method stub

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.Portlet#processAction(jakarta.portlet.ActionRequest, jakarta.portlet.ActionResponse)
    */
   @Override
   public void processAction(ActionRequest arg0, ActionResponse arg1)
         throws PortletException, IOException {
      // TODO Auto-generated method stub

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.Portlet#render(jakarta.portlet.RenderRequest, jakarta.portlet.RenderResponse)
    */
   @Override
   public void render(RenderRequest arg0, RenderResponse arg1)
         throws PortletException, IOException {
      // TODO Auto-generated method stub

   }

}
