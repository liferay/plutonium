/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.thymeleaf.convert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.portlet.PortletPreferences;
import jakarta.ws.rs.ext.ParamConverter;


/**
 * @author  Neil Griffin
 */
public class DateParamConverter implements ParamConverter<Date> {

	private DateFormat dateFormat;

	public DateParamConverter(PortletPreferences portletPreferences) {
		this.dateFormat = new SimpleDateFormat(portletPreferences.getValue("datePattern", null));
	}

	@Override
	public Date fromString(String value) {

		try {
			return dateFormat.parse(value);
		}
		catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public String toString(Date date) {
		return dateFormat.format(date);
	}
}
