/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.thymeleaf.portlet;

/**
 * This is a convenient abstract base class that implements the {@link VariableValidator} interface. Sub-classes
 * typically override the @{@link #isValidName(String, boolean, boolean, boolean)} method in order to add customizations
 * for different use-cases.
 *
 * @author  Neil Griffin
 */
public abstract class VariableValidatorBase implements VariableValidator {

	/**
	 * Called by the {@link #isValidName(String, boolean, boolean, boolean)} method in order to determine whether or not
	 * standard bean names should be considered as valid names.
	 */
	public abstract boolean isIncludeStandardBeans();

	@Override
	public boolean isValidName(String name, boolean headerPhase, boolean renderPhase, boolean resourcePhase) {

		if (name == null) {
			return false;
		}

		name = name.trim();

		if (name.length() == 0) {
			return false;
		}

		if (name.equals("actionParams") || name.equals("actionRequest") || name.equals("actionResponse") ||
				name.equals("eventRequest") || name.equals("eventResponse") || name.equals("stateAwareResponse")) {
			return false;
		}
		else if ((name.equals("clientDataRequest") || name.equals("mutableRenderParams") ||
					name.equals("resourceParams") || name.equals("resourceRequest") ||
					name.equals("resourceResponse")) && !resourcePhase) {
			return false;
		}
		else if ((name.equals("headerRequest") || name.equals("headerResponse")) && !headerPhase) {
			return false;
		}
		else if ((name.equals("renderRequest") || name.equals("renderResponse")) && !renderPhase) {
			return false;
		}
		else if (!isIncludeStandardBeans()) {

			if (name.equals("namespace") || name.equals("contextPath")) {
				return true;
			}
			else if (name.equals("clientDataRequest") || name.equals("cookies") || name.equals("locales") ||
					name.equals("mimeResponse") || name.equals("mutableRenderParams") || name.equals("portletConfig") ||
					name.equals("portletContext") || name.equals("portletName") || name.equals("portletMode") ||
					name.equals("portletPreferences") || name.equals("portletRequest") ||
					name.equals("portletResponse") || name.equals("portletSession") || name.equals("renderParams") ||
					name.equals("renderRequest") || name.equals("renderResponse") || name.equals("resourceParams") ||
					name.equals("resourceRequest") || name.equals("resourceResponse") || name.equals("windowId") ||
					name.equals("windowState")) {
				return false;
			}
		}

		return true;
	}
}
