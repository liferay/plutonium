/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.jsp.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mvc.Controller;
import jakarta.mvc.View;
import jakarta.portlet.annotations.RenderMethod;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class HelpRenderController {

	@Controller
	@RenderMethod(portletNames = { "portlet1" }, portletMode = "help")
	@View("help.jspx")
	public void renderHelpMode() {
	}
}
