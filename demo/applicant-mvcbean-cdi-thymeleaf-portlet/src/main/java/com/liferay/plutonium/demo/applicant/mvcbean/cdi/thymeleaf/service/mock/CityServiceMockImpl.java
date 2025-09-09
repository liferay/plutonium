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
import jakarta.inject.Inject;

import com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.dto.City;
import com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.service.CityService;
import com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.service.ProvinceService;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class CityServiceMockImpl implements CityService {

	@Inject
	private ProvinceService provinceService;

	// Private Data Members
	private List<City> cities;

	@Override
	public City getCityByPostalCode(String postalCode) {

		for (City city : cities) {

			if (city.getPostalCode().equals(postalCode)) {
				return city;
			}
		}

		return null;
	}

	@PostConstruct
	public void postConstruct() {

		long cityId = 1;
		cities = new ArrayList<>();

		City city = new City(cityId++, provinceService.getProvinceId("DE"), "Wilmington", "19806");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("GA"), "Atlanta", "30329");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("FL"), "Orlando", "32801");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("MD"), "Baltimore", "21224");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("NC"), "Charlotte", "28202");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("NJ"), "Hoboken", "07030");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("NY"), "Albany", "12205");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("SC"), "Columbia", "29201");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("VA"), "Roanoke", "24013");
		cities.add(city);
		cities = Collections.unmodifiableList(cities);
	}
}
