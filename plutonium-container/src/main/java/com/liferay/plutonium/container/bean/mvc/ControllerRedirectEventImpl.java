/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.net.URI;

import jakarta.mvc.event.ControllerRedirectEvent;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.UriInfo;


/**
 * @author  Neil Griffin
 */
public class ControllerRedirectEventImpl extends BaseControllerEventImpl implements ControllerRedirectEvent {

	private URI location;

	public ControllerRedirectEventImpl(ResourceInfo resourceInfo, UriInfo uriInfo, URI location) {
		super(resourceInfo, uriInfo);
		this.location = location;
	}

	@Override
	public URI getLocation() {
		return location;
	}
}
