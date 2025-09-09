/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.util.Date;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Variant;


/**
 * @author  Neil Griffin
 */
@Dependent
public class RequestImpl implements Request {

	@Override
	public Response.ResponseBuilder evaluatePreconditions() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Response.ResponseBuilder evaluatePreconditions(EntityTag entityTag) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Response.ResponseBuilder evaluatePreconditions(Date date) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Response.ResponseBuilder evaluatePreconditions(Date date, EntityTag entityTag) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getMethod() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variant selectVariant(List<Variant> list) {
		throw new UnsupportedOperationException();
	}
}
