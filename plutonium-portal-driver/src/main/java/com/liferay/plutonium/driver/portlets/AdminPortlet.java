/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.portlets;

import jakarta.portlet.RenderRequest;

public class AdminPortlet extends GenericPlutoniumPortlet {

	private static final String VIEW_PAGE = "/WEB-INF/fragments/admin/view.jsp";
	private static final String EDIT_PAGE = "/WEB-INF/fragments/admin/edit.jsp";
	private static final String HELP_PAGE = "/WEB-INF/fragments/admin/help.jsp";

	// GenericPortlet Impl -----------------------------------------------------


    public String getViewPage() {
        return VIEW_PAGE;
    }

    public String getEditPage() {
        return EDIT_PAGE;
    }

    public String getHelpPage(RenderRequest request) {
        return HELP_PAGE;
    }
}
