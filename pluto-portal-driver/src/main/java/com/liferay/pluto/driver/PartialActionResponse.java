/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver;

import com.liferay.pluto.driver.util.RenderData;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;


/**
 * @author  Neil Griffin
 */
public class PartialActionResponse extends HttpServletResponseWrapper {

	private String contentType;
	private PrintWriter printWriter;
	private StringWriter stringWriter;

	public PartialActionResponse(HttpServletResponse response) {
		super(response);
		this.stringWriter = new StringWriter();
		this.printWriter = new PrintWriter(stringWriter);
	}

	public RenderData getRenderData() {
		return new RenderData(stringWriter.toString(), contentType);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return printWriter;
	}

	@Override
	public void setContentLength(int contentLength) {
		// no-op
	}

	@Override
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
