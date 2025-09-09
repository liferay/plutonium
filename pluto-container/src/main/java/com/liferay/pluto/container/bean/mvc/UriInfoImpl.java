/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.net.URI;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.PathSegment;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;


/**
 * @author  Neil Griffin
 */
@Dependent
public class UriInfoImpl implements UriInfo {

	@Override
	public URI getAbsolutePath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UriBuilder getAbsolutePathBuilder() {
		throw new UnsupportedOperationException();
	}

	@Override
	public URI getBaseUri() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UriBuilder getBaseUriBuilder() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Object> getMatchedResources() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> getMatchedURIs() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> getMatchedURIs(boolean decode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPath(boolean decode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MultivaluedMap<String, String> getPathParameters() {
		throw new UnsupportedOperationException();
	}

	@Override
	public MultivaluedMap<String, String> getPathParameters(boolean decode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<PathSegment> getPathSegments() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<PathSegment> getPathSegments(boolean decode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MultivaluedMap<String, String> getQueryParameters() {
		throw new UnsupportedOperationException();
	}

	@Override
	public MultivaluedMap<String, String> getQueryParameters(boolean decode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public URI getRequestUri() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UriBuilder getRequestUriBuilder() {
		throw new UnsupportedOperationException();
	}

	@Override
	public URI relativize(URI uri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public URI resolve(URI uri) {
		throw new UnsupportedOperationException();
	}
}
