/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import jakarta.mvc.engine.ViewEngine;


/**
 * @author  Neil Griffin
 */
public class BaseProcessViewEventImpl {

	private Class<? extends ViewEngine> viewEngine;
	private String view;

	public BaseProcessViewEventImpl(String view, Class<? extends ViewEngine> viewEngine) {
		this.view = view;
		this.viewEngine = viewEngine;
	}

	public Class<? extends ViewEngine> getEngine() {
		return viewEngine;
	}

	public String getView() {
		return view;
	}
}
