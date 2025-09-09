/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.impl.resource;

import java.util.logging.Logger;

import com.liferay.pluto.container.driver.PortletRegistryService;
import com.liferay.pluto.driver.services.portal.PageConfig;
import com.liferay.pluto.driver.services.portal.PublicRenderParameterMapper;
import com.liferay.pluto.driver.services.portal.PublicRenderParameterQNameMapper;
import com.liferay.pluto.driver.services.portal.PublicRenderParameterService;
import com.liferay.pluto.driver.services.portal.RenderConfigService;

/**
 * Provides public render parameter mappings for a specified page. 
 * 
 * @author msnicklous
 * @since  16/01/2015
 */
public class PublicRenderParameterServiceImpl implements PublicRenderParameterService {
   private static final String LOG_CLASS = PublicRenderParameterServiceImpl.class.getName();
   private final Logger        LOGGER    = Logger.getLogger(LOG_CLASS);
   
   private final RenderConfigService reco;
   private final PortletRegistryService pore;
   
   public PublicRenderParameterServiceImpl(RenderConfigService reco, PortletRegistryService pore) {
      String ok = ((reco != null) && (pore != null)) ? "initialized sucessfully." : "pore or reco is null.";
      LOGGER.fine("Constructor - " + ok);
      this.reco = reco;
      this.pore = pore;
   }


   /* (non-Javadoc)
    * @see com.liferay.pluto.driver.services.portal.PublicRenderParameterService#getPRPMapper(java.lang.String)
    */
   public PublicRenderParameterMapper getPRPMapper(String page) {
      LOGGER.fine("Getting PRP mapper for page = " + page);
      PageConfig paco = reco.getPage(page);
      PublicRenderParameterMapper prpm = new PublicRenderParameterQNameMapper(paco, pore);
      return prpm;
   }

}
