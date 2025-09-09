/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

import java.util.Map;

import jakarta.portlet.MutableResourceParameters;
import jakarta.portlet.ResourceParameters;

import com.liferay.pluto.container.PortletURLProvider;
import com.liferay.pluto.container.PortletURLProvider.ParamType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nick
 *
 */
public class ResourceParametersImpl extends PortletParametersImpl implements
      ResourceParameters {
   private static final Logger   LOGGER     = LoggerFactory.getLogger(ResourceParametersImpl.class);
   private static final boolean  isTrace    = LOGGER.isTraceEnabled();

   // called to force class loading in Container thread
   public static final void load() {
      if (isTrace) {
         LOGGER.debug("Loaded.");
      }
   };

   /**
    * For creating a new object from scratch
    * 
    * @param urlProvider
    * @param windowId
    */
   public ResourceParametersImpl(PortletURLProvider urlProvider, String windowId) {
      super(urlProvider, windowId, ParamType.RESOURCE);
   }
   
   /**
    * For cloning an object
    * 
    * @param params
    * @param windowId
    */
   protected ResourceParametersImpl(Map<String, String[]> params, String windowId) {
      super(params, windowId, ParamType.RESOURCE);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#mutableClone()
    */
   @Override
   public MutableResourceParameters clone() {
      if (isTrace) {
         LOGGER.debug("Window ID: " + windowId + ", ParameterType: " + type);
      }

      // create a mutable clone, breaking link to the underlying URL provider.
      MutableResourceParametersImpl map = new MutableResourceParametersImpl(params, windowId);
      
      return map;
   }

}
