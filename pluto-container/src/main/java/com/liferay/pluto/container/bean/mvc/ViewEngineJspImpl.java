/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import jakarta.enterprise.context.Dependent;
import jakarta.mvc.engine.ViewEngine;
import jakarta.mvc.engine.ViewEngineContext;
import jakarta.mvc.engine.ViewEngineException;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.PortletResponse;
import jakarta.ws.rs.core.Configuration;


/**
 * @author  Neil Griffin
 */
@Dependent
public class ViewEngineJspImpl implements ViewEngine {

	private final Configuration configuration;
	private final PortletContext portletContext;

	public ViewEngineJspImpl(Configuration configuration, PortletContext portletContext) {

		this.configuration = configuration;
		this.portletContext = portletContext;
	}

	@Override
	public void processView(ViewEngineContext viewEngineContext) throws ViewEngineException {

		String view = viewEngineContext.getView();

		String viewFolder = (String) configuration.getProperty(ViewEngine.VIEW_FOLDER);

		if (viewFolder == null) {
			viewFolder = ViewEngine.DEFAULT_VIEW_FOLDER;
		}

		String viewPath = viewFolder.concat(view);

		PortletRequestDispatcher requestDispatcher = portletContext.getRequestDispatcher(viewPath);

		try {
			requestDispatcher.include(viewEngineContext.getRequest(PortletRequest.class),
				viewEngineContext.getResponse(PortletResponse.class));
		}
		catch (Exception e) {
			throw new ViewEngineException(e);
		}
	}

	@Override
	public boolean supports(String view) {
		return (view != null) && (view.endsWith(".jsp") || view.endsWith(".jspx"));
	}
}
