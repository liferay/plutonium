/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
