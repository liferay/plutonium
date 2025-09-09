/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.UriInfo;


/**
 * @author  Neil Griffin
 */
public class BaseControllerEventImpl {

	private ResourceInfo resourceInfo;
	private UriInfo uriInfo;

	public BaseControllerEventImpl(ResourceInfo resourceInfo, UriInfo uriInfo) {
		this.resourceInfo = resourceInfo;
		this.uriInfo = uriInfo;
	}

	public ResourceInfo getResourceInfo() {
		return resourceInfo;
	}

	public UriInfo getUriInfo() {
		return uriInfo;
	}
}
