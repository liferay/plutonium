/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.service;

import com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.dto.City;


/**
 * @author  Neil Griffin
 */
public interface CityService {

	public City getCityByPostalCode(String postalCode);
}
