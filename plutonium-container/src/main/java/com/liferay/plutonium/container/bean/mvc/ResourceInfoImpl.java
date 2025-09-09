/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.lang.reflect.Method;

import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.container.ResourceInfo;


/**
 * @author  Neil Griffin
 */
@Dependent
public class ResourceInfoImpl implements ResourceInfo {

	private Method resourceMethod;
	private Class<?> resourceClass;

	public ResourceInfoImpl(Class<?> resourceClass, Method resourceMethod) {
		this.resourceClass = resourceClass;
		this.resourceMethod = resourceMethod;
	}

	@Override
	public Class<?> getResourceClass() {
		return resourceClass;
	}

	@Override
	public Method getResourceMethod() {
		return resourceMethod;
	}
}
