/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.mvc.engine.ViewEngine;
import jakarta.mvc.event.BeforeProcessViewEvent;


/**
 * @author  Neil Griffin
 */
public class BeforeProcessViewEventImpl extends BaseProcessViewEventImpl implements BeforeProcessViewEvent {

	public BeforeProcessViewEventImpl(String view, Class<? extends ViewEngine> viewEngine) {
		super(view, viewEngine);
	}
}
