/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.util.Comparator;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Vetoed;


/**
 * @author  Neil Griffin
 */
@Vetoed
public class DescendingPriorityComparator<T> implements Comparator<Object> {

	private int _defaultPriority;

	public DescendingPriorityComparator() {
		this(0);
	}

	public DescendingPriorityComparator(int defaultPriority) {
		_defaultPriority = defaultPriority;
	}

	@Override
	public int compare(Object object1, Object object2) {
		Class<?> class1 = object1.getClass();
		Priority priority1 = class1.getAnnotation(Priority.class);
		Class<?> class2 = object2.getClass();
		Priority priority2 = class2.getAnnotation(Priority.class);

		if ((priority1 == null) && (priority2 == null)) {
			return 0;
		}
		else if (priority1 == null) {
			return Integer.compare(priority2.value(), _defaultPriority);
		}
		else if (priority2 == null) {
			return Integer.compare(_defaultPriority, priority1.value());
		}
		else {
			return Integer.compare(priority1.value(), priority2.value());
		}
	}
}
