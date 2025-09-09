/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.mvc.event.BeforeControllerEvent;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.UriInfo;


/**
 * @author  Neil Griffin
 */
public class BeforeControllerEventImpl extends BaseControllerEventImpl implements BeforeControllerEvent {

	public BeforeControllerEventImpl(ResourceInfo resourceInfo, UriInfo uriInfo) {
		super(resourceInfo, uriInfo);
	}
}
