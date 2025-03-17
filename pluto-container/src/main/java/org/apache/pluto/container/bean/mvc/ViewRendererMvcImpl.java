/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pluto.container.bean.mvc;

import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.mvc.binding.ParamError;
import jakarta.mvc.engine.ViewEngine;
import jakarta.mvc.engine.ViewEngineException;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequest;
import jakarta.ws.rs.core.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class ViewRendererMvcImpl implements ViewRenderer {

	private static final Logger LOG = LoggerFactory.getLogger(ViewRendererMvcImpl.class);

	@Inject
	private BeanManager beanManager;

	@Inject
	private Configuration configuration;

	@Inject
	private Models models;

	@Inject
	private MutableBindingResult mutableBindingResult;

	@Inject
	@ViewEngines
	private List<ViewEngine> viewEngines;

	@Override
	public void render(PortletRequest portletRequest, MimeResponse mimeResponse, PortletConfig portletConfig)
		throws PortletException {

		Map<String, Object> modelMap = models.asMap();

		for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
			portletRequest.setAttribute(entry.getKey(), entry.getValue());
		}

		String viewName = (String) portletRequest.getAttribute(VIEW_NAME);

		if (viewName != null) {

			if (!viewName.contains(".")) {
				String defaultViewExtension = (String) configuration.getProperty(
						ConfigurationImpl.DEFAULT_VIEW_EXTENSION);

				viewName = viewName.concat(".").concat(defaultViewExtension);
			}

			ViewEngine supportingViewEngine = null;

			for (ViewEngine viewEngine : viewEngines) {

				if (viewEngine.supports(viewName)) {
					supportingViewEngine = viewEngine;

					break;
				}
			}

			if (supportingViewEngine == null) {
				throw new PortletException(new ViewEngineException("No ViewEngine found that supports " + viewName));
			}

			try {
				beanManager.getEvent()
					.select(BeforeProcessViewEventImpl.class)
					.fire(new BeforeProcessViewEventImpl(viewName, supportingViewEngine.getClass()));

				supportingViewEngine.processView(new ViewEngineContextImpl(configuration, portletRequest, mimeResponse,
						models, portletRequest.getLocale()));

				beanManager.getEvent()
					.select(AfterProcessViewEventImpl.class)
					.fire(new AfterProcessViewEventImpl(viewName, supportingViewEngine.getClass()));
			}
			catch (ViewEngineException vee) {
				throw new PortletException(vee);
			}
		}

		if ((mutableBindingResult != null) && !mutableBindingResult.isConsulted()) {

			Set<ParamError> allErrors = mutableBindingResult.getAllErrors();

			for (ParamError paramError : allErrors) {

				if (LOG.isWarnEnabled()) {
					LOG.warn("BindingResult error not processed for " + paramError.getParamName() + ": " +
						paramError.getMessage());
				}
			}
		}
	}
}
