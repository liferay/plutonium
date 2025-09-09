/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.dto;

import java.io.Serializable;


/**
 * This is a bean that represents a Province and implements the Transfer Object (formerly known as ValueObject/VO)
 * design pattern.
 *
 * @author  "Neil Griffin"
 */
public class Province implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 2342374742262228819L;

	// JavaBean Properties
	private final long provinceId;
	private final String provinceName;

	public Province(long provinceId, String provinceName) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}

	public long getProvinceId() {
		return provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}
}
