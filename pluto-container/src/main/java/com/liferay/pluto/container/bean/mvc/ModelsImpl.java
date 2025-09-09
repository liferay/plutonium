/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import jakarta.inject.Named;
import jakarta.mvc.Models;
import jakarta.mvc.RedirectScoped;


/**
 * @author  Neil Griffin
 */
@Named("models")
@RedirectScoped
public class ModelsImpl implements Models, Serializable {

	private static final long serialVersionUID = 2433287856890024741L;

	private final Map<String, Object> modelsMap = new LinkedHashMap<>();

	@Override
	public Map<String, Object> asMap() {
		return Collections.unmodifiableMap(modelsMap);
	}

	public void clear() {
		modelsMap.clear();
	}

	@Override
	public Object get(String name) {
		return get(name, Object.class);
	}

	@Override
	public <T> T get(String name, Class<T> type) {
		return type.cast(modelsMap.get(name));
	}

	@Override
	public Iterator<String> iterator() {
		Set<String> keySet = modelsMap.keySet();

		return keySet.iterator();
	}

	@Override
	public Models put(String name, Object model) {
		modelsMap.put(name, model);

		return this;
	}
}
