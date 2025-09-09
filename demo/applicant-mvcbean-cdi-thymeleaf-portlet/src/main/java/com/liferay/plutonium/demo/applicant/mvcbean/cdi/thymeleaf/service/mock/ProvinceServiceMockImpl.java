/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.service.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.dto.Province;
import com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.service.ProvinceService;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class ProvinceServiceMockImpl implements ProvinceService {

	private List<Province> provinces;

	@Override
	public List<Province> getAllProvinces() {
		return provinces;
	}

	@Override
	public long getProvinceId(String provinceName) {
		long provinceId = 0;
		List<Province> provinces = getAllProvinces();

		for (Province province : provinces) {

			if (province.getProvinceName().equals(provinceName)) {
				provinceId = province.getProvinceId();

				break;
			}
		}

		return provinceId;
	}

	@PostConstruct
	public void postConstruct() {

		long provinceId = 1;
		provinces = new ArrayList<>();

		Province province = new Province(provinceId++, "DE");
		provinces.add(province);
		province = new Province(provinceId++, "GA");
		provinces.add(province);
		province = new Province(provinceId++, "FL");
		provinces.add(province);
		province = new Province(provinceId++, "MD");
		provinces.add(province);
		province = new Province(provinceId++, "NC");
		provinces.add(province);
		province = new Province(provinceId++, "NJ");
		provinces.add(province);
		province = new Province(provinceId++, "NY");
		provinces.add(province);
		province = new Province(provinceId++, "SC");
		provinces.add(province);
		province = new Province(provinceId++, "VA");
		provinces.add(province);
		provinces = Collections.unmodifiableList(provinces);
	}
}
