/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.portlet.MimeResponse;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequest;


/**
 * @author  Neil Griffin
 */
public interface ViewRenderer {

	public static final String REDIRECT_PREFIX = "redirect:";

	public static final String REDIRECTED_VIEW = "redirectedView";

	public static final String VIEW_NAME = "viewName";

	public void render(PortletRequest portletRequest, MimeResponse mimeResponse, PortletConfig portletConfig)
		throws PortletException;
}
