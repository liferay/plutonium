/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.ws.rs.SeBootstrap;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.Variant;
import jakarta.ws.rs.ext.RuntimeDelegate;

import java.util.concurrent.CompletionStage;


/**
 * @author  Neil Griffin
 */
public class MvcRuntimeDelegate extends RuntimeDelegate {

	@Override
	public <T> T createEndpoint(Application application, Class<T> aClass) throws IllegalArgumentException,
		UnsupportedOperationException {
		return null;
	}

	@Override
	public <T> HeaderDelegate<T> createHeaderDelegate(Class<T> aClass) throws IllegalArgumentException {
		return new HeaderDelegate<T>() {
				@Override
				public T fromString(String s) {
					return (T) s;
				}

				@Override
				public String toString(T t) {
					return t.toString();
				}
			};
	}

	@Override
	public Link.Builder createLinkBuilder() {
		return null;
	}

	@Override
	public SeBootstrap.Configuration.Builder createConfigurationBuilder() {
		return null;
	}

	@Override
	public CompletionStage<SeBootstrap.Instance> bootstrap(
		Application application, SeBootstrap.Configuration configuration) {
		return null;
	}

	@Override
	public CompletionStage<SeBootstrap.Instance> bootstrap(
		Class<? extends Application> aClass,
		SeBootstrap.Configuration configuration) {
		return null;
	}

	@Override
	public EntityPart.Builder createEntityPartBuilder(String s)
		throws IllegalArgumentException {
		return null;
	}

	@Override
	public Response.ResponseBuilder createResponseBuilder() {
		return null;
	}

	@Override
	public UriBuilder createUriBuilder() {
		return null;
	}

	@Override
	public Variant.VariantListBuilder createVariantListBuilder() {
		return null;
	}
}
