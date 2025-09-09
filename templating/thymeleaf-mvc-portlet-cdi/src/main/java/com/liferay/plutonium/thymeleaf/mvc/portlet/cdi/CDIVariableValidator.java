/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.mvc.portlet.cdi;

import com.liferay.plutonium.thymeleaf.portlet.VariableValidatorBase;


/**
 * This class provides a CDI implementation of the {@link com.liferay.plutonium.thymeleaf.portlet.VariableValidator}
 * interface.
 *
 * @author  Neil Griffin
 */
public class CDIVariableValidator extends VariableValidatorBase {

	private boolean includeStandardBeans;

	public CDIVariableValidator(boolean includeStandardBeans) {
		this.includeStandardBeans = includeStandardBeans;
	}

	@Override
	public boolean isIncludeStandardBeans() {
		return includeStandardBeans;
	}

	@Override
	public boolean isValidName(String name, boolean headerPhase, boolean renderPhase, boolean resourcePhase) {

		boolean valid = super.isValidName(name, headerPhase, renderPhase, resourcePhase);

		if (!valid) {
			return false;
		}

		if (name.startsWith("jakarta.enterprise")) {
			return false;
		}

		return true;
	}
}
