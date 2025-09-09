/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.dto;

import org.hibernate.validator.constraints.NotBlank;


/**
 * @author  Neil Griffin
 */
public class Preferences {

	@NotBlank
	private String datePattern;

	public Preferences() {
	}

	public Preferences(String datePattern) {
		this.datePattern = datePattern;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
}
