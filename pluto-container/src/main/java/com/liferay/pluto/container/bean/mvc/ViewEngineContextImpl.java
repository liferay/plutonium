/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import jakarta.enterprise.context.Dependent;
import jakarta.mvc.Models;
import jakarta.mvc.engine.ViewEngineContext;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.PortletRequest;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
public class ViewEngineContextImpl implements ViewEngineContext {

	private static final Logger LOG = LoggerFactory.getLogger(ViewEngineContextImpl.class);

	private Configuration configuration;
	private Locale locale;
	private MediaType mediaType;
	private MimeResponse mimeResponse;
	private Models models;
	private PortletRequest portletRequest;

	public ViewEngineContextImpl(Configuration configuration, PortletRequest portletRequest, MimeResponse mimeResponse,
		Models models, Locale locale) {
		this.configuration = configuration;
		this.portletRequest = portletRequest;
		this.mimeResponse = mimeResponse;
		this.models = models;
		this.locale = locale;
	}

	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public MediaType getMediaType() {

		if (mediaType == null) {
			String contentType = mimeResponse.getContentType();

			if (contentType == null) {
				mediaType = MediaType.TEXT_HTML_TYPE;
			}
			else {

				String type = contentType;
				String subtype = null;

				int pos = contentType.indexOf("/");

				if (pos > 0) {
					type = contentType.substring(0, pos);
					subtype = contentType.substring(pos + 1);
				}

				mediaType = new MediaType(type, subtype, mimeResponse.getCharacterEncoding());
			}
		}

		return mediaType;
	}

	@Override
	public Models getModels() {
		return models;
	}

	@Override
	public OutputStream getOutputStream() {

		try {
			return mimeResponse.getPortletOutputStream();
		}
		catch (IOException e) {
			LOG.error(e.getMessage(), e);

			return null;
		}
	}

	@Override
	public <T> T getRequest(Class<T> type) {
		return (T) portletRequest;
	}

	@Override
	public ResourceInfo getResourceInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T getResponse(Class<T> type) {
		return (T) mimeResponse;
	}

	@Override
	public MultivaluedMap<String, Object> getResponseHeaders() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UriInfo getUriInfo() {
		return new UriInfoImpl();
	}

	@Override
	public String getView() {
		return (String) portletRequest.getAttribute(ViewRenderer.VIEW_NAME);
	}
}
