/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import jakarta.enterprise.inject.spi.AnnotatedConstructor;
import jakarta.enterprise.inject.spi.AnnotatedField;
import jakarta.enterprise.inject.spi.AnnotatedMethod;
import jakarta.enterprise.inject.spi.AnnotatedType;


/**
 * @author  Neil Griffin
 */
public class ModifiedAnnotatedType<X> implements AnnotatedType<X> {

	private final AnnotatedType<X> annotatedType;
	private final Set<AnnotatedField<? super X>> fields;
	private final Set<AnnotatedMethod<? super X>> methods;

	public ModifiedAnnotatedType(AnnotatedType<X> annotatedType, Set<AnnotatedField<? super X>> fields,
		Set<AnnotatedMethod<? super X>> methods) {
		this.annotatedType = annotatedType;
		this.fields = fields;
		this.methods = methods;
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		return annotatedType.getAnnotation(annotationType);
	}

	@Override
	public Set<Annotation> getAnnotations() {
		return annotatedType.getAnnotations();
	}

	@Override
	public Type getBaseType() {
		return annotatedType.getBaseType();
	}

	@Override
	public Set<AnnotatedConstructor<X>> getConstructors() {
		return annotatedType.getConstructors();
	}

	@Override
	public Set<AnnotatedField<? super X>> getFields() {
		return fields;
	}

	@Override
	public Class<X> getJavaClass() {
		return annotatedType.getJavaClass();
	}

	@Override
	public Set<AnnotatedMethod<? super X>> getMethods() {
		return methods;
	}

	@Override
	public Set<Type> getTypeClosure() {
		return annotatedType.getTypeClosure();
	}

	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
		return annotatedType.isAnnotationPresent(annotationType);
	}

}
