/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Set;

import jakarta.enterprise.inject.spi.AnnotatedField;
import jakarta.enterprise.inject.spi.AnnotatedType;


/**
 * @author  Neil Griffin
 */
public class ModifiedField<X> implements AnnotatedField<X> {

	private AnnotatedField annotatedField;
	private Set<Annotation> annotations;

	public ModifiedField(AnnotatedField<X> annotatedField, Set<Annotation> annotations) {
		this.annotatedField = annotatedField;
		this.annotations = annotations;
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {

		for (Annotation annotation : annotations) {

			if (annotationClass.isAssignableFrom(annotation.getClass())) {
				return (T) annotation;
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
		return annotatedField.getBaseType();
	}

	@Override
	public AnnotatedType<X> getDeclaringType() {
		return annotatedField.getDeclaringType();
	}

	@Override
	public Field getJavaMember() {
		return annotatedField.getJavaMember();
	}

	@Override
	public Set<Type> getTypeClosure() {
		return annotatedField.getTypeClosure();
	}

	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
		return getAnnotation(annotationClass) != null;
	}

	@Override
	public boolean isStatic() {
		return annotatedField.isStatic();
	}
}
