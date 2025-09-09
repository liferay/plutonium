/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.portlets;

import java.io.IOException;

import jakarta.portlet.GenericPortlet;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;


public abstract class GenericPlutoPortlet extends GenericPortlet {

    public abstract String getViewPage();
    public abstract String getEditPage();
    public abstract String getHelpPage(RenderRequest request);

    public void doView(RenderRequest request, RenderResponse response)
    throws PortletException, IOException {
        PortletContext context = getPortletContext();
        PortletRequestDispatcher requestDispatcher =
        		context.getRequestDispatcher(getViewPage());
        requestDispatcher.include(request, response);
    }

    protected void doEdit(RenderRequest request, RenderResponse response)
    throws PortletException, IOException {
        PortletContext context = getPortletContext();
        PortletRequestDispatcher requestDispatcher =
        		context.getRequestDispatcher(getEditPage());
        requestDispatcher.include(request, response);
    }

    protected void doHelp(RenderRequest request, RenderResponse response)
    throws PortletException, IOException {
    	PortletContext context = getPortletContext();
    	PortletRequestDispatcher requestDispatcher =
    			context.getRequestDispatcher(getHelpPage(request));
    	requestDispatcher.include(request, response);
    }
}
