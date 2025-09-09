/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.dto;

import java.io.Serializable;


/**
 * This is a bean that represents a City and implements the Transfer Object (formerly known as ValueObject/VO) design
 * pattern.
 *
 * @author  "Neil Griffin"
 */
public class City implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 3312342177113327761L;

	// JavaBean Properties
	private final long cityId;
	private final String cityName;
	private final String postalCode;
	private final long provinceId;

	public City(long cityId, long provinceId, String cityName, String postalCode) {
		this.cityId = cityId;
		this.provinceId = provinceId;
		this.cityName = cityName;
		this.postalCode = postalCode;
	}

	public long getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public long getProvinceId() {
		return provinceId;
	}
}
