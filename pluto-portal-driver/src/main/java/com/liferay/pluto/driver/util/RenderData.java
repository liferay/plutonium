/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.util;

/**
 * @author  Neil Griffin
 */
public class RenderData {

	private String contentType;
	private String content;

	public RenderData(String content, String contentType) {
		this.content = content;
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public String getContentType() {
		return contentType;
	}
}
