/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mvc.locale.LocaleResolver;
import jakarta.mvc.locale.LocaleResolverContext;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@Priority(0)
public class LocaleResolverImpl implements LocaleResolver {

	@Override
	public Locale resolveLocale(LocaleResolverContext localeResolverContext) {

		List<Locale> acceptableLanguages = localeResolverContext.getAcceptableLanguages();

		for (Locale acceptableLanguage : acceptableLanguages) {

			if (!Objects.equals(acceptableLanguage.getLanguage(), "*")) {
				return acceptableLanguage;
			}
		}

		return Locale.getDefault();
	}
}
