/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.mvc.engine.ViewEngineContext;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.PortletRequest;
import jakarta.ws.rs.core.Configuration;


/**
 * @author  Neil Griffin
 */
@Dependent
public class ViewEngineContextProducer {

	@Dependent
	@Produces
	ViewEngineContext getViewEngineContext(Configuration configuration, PortletRequest portletRequest,
		MimeResponse mimeResponse, ModelsImpl modelsImpl) {
		return new ViewEngineContextImpl(configuration, portletRequest, mimeResponse, modelsImpl,
				portletRequest.getLocale());
	}
}
