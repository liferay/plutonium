/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.inject.spi.AnnotatedMethod;
import jakarta.enterprise.inject.spi.AnnotatedParameter;
import jakarta.enterprise.inject.spi.AnnotatedType;


/**
 * @author  Neil Griffin
 */
public class ModifiedMethod<X> implements AnnotatedMethod<X> {

	private AnnotatedMethod annotatedMethod;
	private Set<Annotation> annotations;

	public ModifiedMethod(AnnotatedMethod<X> annotatedMethod, Set<Annotation> annotations) {
		this.annotatedMethod = annotatedMethod;
		this.annotations = annotations;
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {

		for (Annotation annotation : annotations) {
			Class<? extends Annotation> curAnnotationType = annotation.annotationType();

			if (curAnnotationType.equals(annotationType)) {
				return annotationType.cast(annotation);
			}
		}

		return null;
	}

	@Override
	public Set<Annotation> getAnnotations() {
		return annotations;
	}

	@Override
	public Type getBaseType() {
		return annotatedMethod.getBaseType();
	}

	@Override
	public AnnotatedType<X> getDeclaringType() {
		return annotatedMethod.getDeclaringType();
	}

	@Override
	public Method getJavaMember() {
		return annotatedMethod.getJavaMember();
	}

	@Override
	public List<AnnotatedParameter<X>> getParameters() {
		return annotatedMethod.getParameters();
	}

	@Override
	public Set<Type> getTypeClosure() {
		return annotatedMethod.getTypeClosure();
	}

	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {

		Annotation annotation = getAnnotation(annotationType);

		if (annotation == null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isStatic() {
		return annotatedMethod.isStatic();
	}
}
