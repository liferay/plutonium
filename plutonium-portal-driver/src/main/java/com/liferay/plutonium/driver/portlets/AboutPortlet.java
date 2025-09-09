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

/**
 * The plutonium portal driver about portlet.
 * @since 2006-02-09
 */
public class AboutPortlet extends GenericPortlet {
	
	private static final String VIEW_PAGE = "/WEB-INF/fragments/about/view.jsp";
	private static final String EDIT_PAGE = "/WEB-INF/fragments/about/edit.jsp";
	private static final String HELP_PAGE = "/WEB-INF/fragments/about/help.jsp";
	
	// GenericPortlet Impl -----------------------------------------------------
	
    public void doView(RenderRequest request, RenderResponse response)
    throws PortletException, IOException {
    	response.setContentType("text/html");
        PortletContext context = getPortletContext();
        PortletRequestDispatcher requestDispatcher =
        		context.getRequestDispatcher(VIEW_PAGE);
        requestDispatcher.include(request, response);
    }

    protected void doEdit(RenderRequest request, RenderResponse response)
    throws PortletException, IOException {
    	response.setContentType("text/html");
        PortletContext context = getPortletContext();
        PortletRequestDispatcher requestDispatcher =
        		context.getRequestDispatcher(EDIT_PAGE);
        requestDispatcher.include(request, response);
    }
    
    protected void doHelp(RenderRequest request, RenderResponse response)
    throws PortletException, IOException {
    	response.setContentType("text/html");
    	PortletContext context = getPortletContext();
    	PortletRequestDispatcher requestDispatcher =
    			context.getRequestDispatcher(HELP_PAGE);
    	requestDispatcher.include(request, response);
    }

}
