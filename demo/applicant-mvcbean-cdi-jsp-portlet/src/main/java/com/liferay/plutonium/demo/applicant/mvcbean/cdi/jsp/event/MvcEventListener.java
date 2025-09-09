/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.applicant.mvcbean.cdi.jsp.event;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.mvc.event.AfterControllerEvent;
import jakarta.mvc.event.AfterProcessViewEvent;
import jakarta.mvc.event.BeforeControllerEvent;
import jakarta.mvc.event.BeforeProcessViewEvent;
import jakarta.mvc.event.ControllerRedirectEvent;
import jakarta.mvc.event.MvcEvent;
import jakarta.ws.rs.container.ResourceInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class MvcEventListener {

	private static final Logger logger = LoggerFactory.getLogger(MvcEventListener.class);

	public void afterControllerEventListener(@Observes AfterControllerEvent afterControllerEvent) {
		ResourceInfo resourceInfo = afterControllerEvent.getResourceInfo();

		logger.debug("Observed AfterControllerEvent class=[{}], method=[{}]", resourceInfo.getResourceClass(),
			resourceInfo.getResourceMethod());
	}

	public void afterProcessViewEventListener(@Observes AfterProcessViewEvent afterProcessViewEvent) {
		logger.debug("Observed AfterProcesssViewEvent view=[{}]", afterProcessViewEvent.getView());
	}

	public void beforeControllerEventListener(@Observes BeforeControllerEvent beforeControllerEvent) {
		ResourceInfo resourceInfo = beforeControllerEvent.getResourceInfo();

		logger.debug("Observed BeforeControllerEvent class=[{}], method=[{}]", resourceInfo.getResourceClass(),
			resourceInfo.getResourceMethod());
	}

	public void beforeProcessViewEventListener(@Observes BeforeProcessViewEvent beforeProcessViewEvent) {
		logger.debug("Observed BeforeProcesssViewEvent view=[{}]", beforeProcessViewEvent.getView());
	}

	public void controllerRedirectEventListener(@Observes ControllerRedirectEvent controllerRedirectEvent) {
		ResourceInfo resourceInfo = controllerRedirectEvent.getResourceInfo();

		logger.debug("Observed ControllerRedirectEvent class=[{}], method=[{}] location=[{}]",
			resourceInfo.getResourceClass(), resourceInfo.getResourceMethod(), controllerRedirectEvent.getLocation());
	}

	public void mvcEventListener(@Observes MvcEvent mvcEvent) {
		logger.trace("Observed MvcEvent=[{}]", mvcEvent);
	}
}
