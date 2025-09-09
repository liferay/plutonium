/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.mvc;

import java.util.Iterator;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class BeanValidationProducer {

	private static final Logger LOG = LoggerFactory.getLogger(BeanValidationProducer.class);

	private MessageInterpolator messageInterpolator;
	private Validator validator;

	@Inject
	private Instance<ValidatorFactory> validatorFactoryInstance;

	@BeanValidationMessageInterpolator
	@Dependent
	@Produces
	public MessageInterpolator getMessageInterpolator() {
		return messageInterpolator;
	}

	@BeanValidationValidator
	@Dependent
	@Produces
	public Validator getValidator() {
		return validator;
	}

	@PostConstruct
	public void postConstruct() {
		ValidatorFactory validatorFactory = null;

		Iterator<ValidatorFactory> iterator = validatorFactoryInstance.iterator();

		if (iterator.hasNext()) {
			validatorFactory = iterator.next();
		}

		if (validatorFactory == null) {

			if (LOG.isWarnEnabled()) {
				LOG.warn("ValidatorFactory was not injected -- if using Hibernate " + "Validator, please include the " +
					"hibernate-validator-cdi dependency.");
			}

			try {
				validatorFactory = Validation.buildDefaultValidatorFactory();
			}
			catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}

		if (validatorFactory != null) {
			messageInterpolator = validatorFactory.getMessageInterpolator();

			if ((messageInterpolator == null) && LOG.isWarnEnabled()) {
				LOG.warn("Bean validation MessageInterpolator not available");
			}

			validator = validatorFactory.getValidator();

			if ((validator == null) && LOG.isWarnEnabled()) {
				LOG.warn("Bean validation validator not available");
			}
		}
	}

}
