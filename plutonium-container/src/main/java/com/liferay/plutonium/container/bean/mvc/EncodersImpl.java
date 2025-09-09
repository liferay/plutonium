/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import jakarta.enterprise.context.Dependent;
import jakarta.mvc.security.Encoders;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * @author  Neil Griffin
 */
@Dependent
public class EncodersImpl implements Encoders {

	@Override
	public String html(String markup) {
		return StringEscapeUtils.escapeHtml4(markup);
	}

	@Override
	public String js(String code) {
		return StringEscapeUtils.escapeEcmaScript(code);
	}
}
