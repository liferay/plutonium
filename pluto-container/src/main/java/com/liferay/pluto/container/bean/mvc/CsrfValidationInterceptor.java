/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.mvc;

import java.io.Serializable;
import java.lang.reflect.Method;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.mvc.MvcContext;
import jakarta.mvc.security.Csrf;
import jakarta.mvc.security.CsrfProtected;
import jakarta.portlet.ClientDataRequest;
import jakarta.ws.rs.core.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@Interceptor
@CsrfValidationInterceptorBinding
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class CsrfValidationInterceptor implements Serializable {

	private static final long serialVersionUID = 1348567603498123441L;

	private static final Logger LOG = LoggerFactory.getLogger(CsrfValidationInterceptor.class);

	@Inject
	private Configuration configuration;

	@Inject
	private MvcContext mvcContext;

	@AroundInvoke
	public Object validateMethodInvocation(InvocationContext invocationContext) throws Exception {

		Csrf.CsrfOptions csrfOptions = Csrf.CsrfOptions.EXPLICIT;

		Object csrfProtection = configuration.getProperty(Csrf.CSRF_PROTECTION);

		if (csrfProtection != null) {

			if (csrfProtection instanceof Csrf.CsrfOptions) {
				csrfOptions = (Csrf.CsrfOptions) csrfProtection;
			}
			else {

				try {
					csrfOptions = Csrf.CsrfOptions.valueOf(csrfProtection.toString());
				}
				catch (IllegalArgumentException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}

		if (csrfOptions == Csrf.CsrfOptions.OFF) {
			return invocationContext.proceed();
		}

		Method targetMethod = invocationContext.getMethod();

		if ((csrfOptions == Csrf.CsrfOptions.EXPLICIT) && !targetMethod.isAnnotationPresent(CsrfProtected.class)) {
			return invocationContext.proceed();
		}

		boolean proceed = false;

		Object[] parameters = invocationContext.getParameters();

		if (parameters.length == 2) {

			if (parameters[0] instanceof ClientDataRequest) {

				ClientDataRequest clientDataRequest = (ClientDataRequest) parameters[0];

				String method = clientDataRequest.getMethod();

				if (method.equalsIgnoreCase("post")) {

					Csrf csrf = mvcContext.getCsrf();

					String csrfRequestParameterValue = clientDataRequest.getParameter(csrf.getName());

					String csrfToken = csrf.getToken();

					if ((csrfToken != null) && csrfToken.equalsIgnoreCase(csrfRequestParameterValue)) {
						proceed = true;
					}
					else {
						LOG.error("Invalid CSRF token");
					}

				}
				else {
					proceed = true;
				}
			}
			else {
				LOG.error("First parameter of method signature must be ActionRequest or ResourceRequest");
			}
		}
		else {
			LOG.error("Method signature must include ActionRequest,ActionResponse or ResourceRequest,ResourceResponse");
		}

		if (proceed) {
			return invocationContext.proceed();
		}

		return null;
	}
}
