/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.mvc.MvcContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ElementKind;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.QueryParam;


/**
 * @author  Neil Griffin
 */
@Interceptor
@BeanValidationInterceptorBinding
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class BeanValidationInterceptor implements Serializable {

	private static final long serialVersionUID = 2378576156374329311L;

	@BeanValidationMessageInterpolator
	@Inject
	private MessageInterpolator messageInterpolator;

	@Inject
	private MutableBindingResult mutableBindingResult;

	@Inject
	private MvcContext mvcContext;

	@Inject
	private ValidatorFactory validatorFactory;

	@AroundInvoke
	public Object validateMethodInvocation(InvocationContext invocationContext) throws Exception {

		Validator validator = validatorFactory.getValidator();

		if (validator == null) {
			return invocationContext.proceed();
		}

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(invocationContext.getTarget());

		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {

			Path propertyPath = constraintViolation.getPropertyPath();

			Path.Node lastPathNode = null;

			for (Path.Node pathNode : propertyPath) {
				lastPathNode = pathNode;
			}

			if ((lastPathNode != null) && (lastPathNode.getKind() == ElementKind.PROPERTY)) {

				Object leafBean = constraintViolation.getLeafBean();
				Class<?> leafBeanClass = leafBean.getClass();

				BeanInfo beanInfo = Introspector.getBeanInfo(leafBean.getClass());

				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					String propertyDescriptorName = propertyDescriptor.getName();

					if (propertyDescriptorName.equals("targetClass")) {
						Method readMethod = propertyDescriptor.getReadMethod();
						leafBeanClass = (Class<?>) readMethod.invoke(leafBean);
					}
				}

				Field leafBeanField = leafBeanClass.getDeclaredField(lastPathNode.getName());

				String paramName = null;

				Annotation[] annotations = leafBeanField.getAnnotations();

				for (Annotation annotation : annotations) {

					if (annotation instanceof CookieParam) {
						CookieParam cookieParam = (CookieParam) annotation;
						paramName = cookieParam.value();

						break;
					}
					else if (annotation instanceof FormParam) {
						FormParam formParam = (FormParam) annotation;
						paramName = formParam.value();

						break;
					}
					else if (annotation instanceof HeaderParam) {
						HeaderParam headerParam = (HeaderParam) annotation;
						paramName = headerParam.value();

						break;
					}
					else if (annotation instanceof QueryParam) {
						QueryParam queryParam = (QueryParam) annotation;
						paramName = queryParam.value();
					}
				}

				String interpolatedMessage = constraintViolation.getMessage();

				if (messageInterpolator != null) {
					interpolatedMessage = messageInterpolator.interpolate(constraintViolation.getMessageTemplate(),
							new MessageInterpolatorContextImpl(constraintViolation), mvcContext.getLocale());
				}

				mutableBindingResult.addValidationError(new ValidationErrorImpl(paramName, interpolatedMessage,
						constraintViolation));
			}

		}

		return invocationContext.proceed();
	}
}
