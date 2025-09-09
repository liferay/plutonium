/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.portlet.servlet.adapter;

import jakarta.portlet.ActionResponse;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.ResourceResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

/**
 * Provides a way to decorate a {@link PortletResponse} as an {@link HttpServletResponse}. Methods that have no
 * correspondence to {@link HttpServletResponse} throw {@link UnsupportedOperationException}.
 *
 * @author Neil Griffin
 */
public class HttpServletResponseAdapter implements HttpServletResponse {

	private PortletResponse portletResponse;

	public HttpServletResponseAdapter(PortletResponse portletResponse) {
		this.portletResponse = portletResponse;
	}

	@Override
	public void addCookie(Cookie cookie) {
		portletResponse.addProperty(cookie);
	}

	@Override
	public boolean containsHeader(String name) {
		return false;
	}

	@Override
	public String encodeURL(String url) {
		return portletResponse.encodeURL(url);
	}

	@Override
	public String encodeRedirectURL(String url) {
		return url;
	}

	@Override
	public void sendError(int statusCode, String message) throws IOException {
		setStatus(statusCode);
	}

	@Override
	public void sendError(int statusCode) throws IOException {
		setStatus(statusCode);
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		if (portletResponse instanceof ActionResponse) {
			((ActionResponse)portletResponse).sendRedirect(location);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public void setDateHeader(String name, long date) {
		setHeader(name, Long.toString(date));
	}

	@Override
	public void addDateHeader(String name, long date) {
		addHeader(name, Long.toString(date));
	}

	@Override
	public void setHeader(String name, String value) {
		portletResponse.setProperty(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		portletResponse.addProperty(name, value);
	}

	@Override
	public void setIntHeader(String name, int value) {
		setHeader(name, Integer.toString(value));
	}

	@Override
	public void addIntHeader(String name, int value) {
		addHeader(name, Integer.toString(value));
	}

	@Override
	public void setStatus(int statusCode) {

		if (portletResponse instanceof ResourceResponse) {
			((ResourceResponse)portletResponse).setStatus(statusCode);
		}
		else {
			// no-op
		}
	}

	@Override
	public int getStatus() {

		if (portletResponse instanceof ResourceResponse) {
			return ((ResourceResponse)portletResponse).getStatus();
		}

		return HttpServletResponse.SC_OK;
	}

	@Override
	public String getHeader(String name) {
		return portletResponse.getProperty(name);
	}

	@Override
	public Collection<String> getHeaders(String name) {
		return portletResponse.getPropertyValues(name);
	}

	@Override
	public Collection<String> getHeaderNames() {
		return portletResponse.getPropertyNames();
	}

	@Override
	public String getCharacterEncoding() {

		if (portletResponse instanceof MimeResponse) {
			return ((MimeResponse)portletResponse).getCharacterEncoding();
		}

		return "UTF-8";
	}

	@Override
	public String getContentType() {

		if (portletResponse instanceof MimeResponse) {
			return ((MimeResponse)portletResponse).getContentType();
		}
		else {
			return "";
		}
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {

		if (portletResponse instanceof MimeResponse) {
			return new ServletOutputStreamAdapter(((MimeResponse)portletResponse).getPortletOutputStream());
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public PrintWriter getWriter() throws IOException {

		if (portletResponse instanceof MimeResponse) {
			return ((MimeResponse)portletResponse).getWriter();
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public void setCharacterEncoding(String charset) {
		if (portletResponse instanceof ResourceResponse) {
			((ResourceResponse) portletResponse).setCharacterEncoding(charset);
		}
		else {
			// no-op
		}
	}

	@Override
	public void setContentLength(int length) {

		if (portletResponse instanceof ResourceResponse) {
			((ResourceResponse)portletResponse).setContentLength(length);
		}
		else {
			// no-op
		}
	}

	@Override
	public void setContentLengthLong(long length) {

		if (portletResponse instanceof ResourceResponse) {
			((ResourceResponse)portletResponse).setContentLengthLong(length);
		}
		else {
			// no-op
		}
	}

	@Override
	public void setContentType(String contentType) {

		if (portletResponse instanceof MimeResponse) {
			((MimeResponse)portletResponse).setContentType(contentType);
		}
		else {
			// no-op
		}
	}

	@Override
	public void setBufferSize(int size) {

		if (portletResponse instanceof MimeResponse) {
			((MimeResponse)portletResponse).setBufferSize(size);
		}
		else {
			// no-op
		}
	}

	@Override
	public int getBufferSize() {

		if (portletResponse instanceof MimeResponse) {
			return ((MimeResponse)portletResponse).getBufferSize();
		}

		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {

		if (portletResponse instanceof MimeResponse) {
			((MimeResponse)portletResponse).flushBuffer();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public void resetBuffer() {

		if (portletResponse instanceof MimeResponse) {
			((MimeResponse)portletResponse).resetBuffer();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public boolean isCommitted() {

		if (portletResponse instanceof MimeResponse) {
			return ((MimeResponse)portletResponse).isCommitted();
		}

		return false;
	}

	@Override
	public void reset() {

		if (portletResponse instanceof MimeResponse) {
			((MimeResponse)portletResponse).reset();
		}
		else {
			// no-op
		}
	}

	@Override
	public void setLocale(Locale locale) {

		if (portletResponse instanceof ResourceResponse) {
			((ResourceResponse)portletResponse).setLocale(locale);
		}
		else {
			// no-op
		}
	}

	@Override
	public Locale getLocale() {

		if (portletResponse instanceof MimeResponse) {
			return ((MimeResponse)portletResponse).getLocale();
		}
		else {
			return null;
		}
	}

	private static class ServletOutputStreamAdapter extends ServletOutputStream {

		private OutputStream portletOutputStream;

		public ServletOutputStreamAdapter(OutputStream portletOutputStream) {
			this.portletOutputStream = portletOutputStream;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setWriteListener(WriteListener writeListener) {
		}

		@Override
		public void write(int b) throws IOException {
			portletOutputStream.write(b);
		}
	}
}
