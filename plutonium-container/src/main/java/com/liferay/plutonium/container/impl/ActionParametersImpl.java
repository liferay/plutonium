/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import java.util.Map;

import jakarta.portlet.ActionParameters;
import jakarta.portlet.MutableActionParameters;

import com.liferay.plutonium.container.PortletURLProvider;
import com.liferay.plutonium.container.PortletURLProvider.ParamType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nick
 *
 */
public class ActionParametersImpl extends PortletParametersImpl implements
      ActionParameters {
   private static final Logger   LOGGER     = LoggerFactory.getLogger(ActionParametersImpl.class);
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
   public ActionParametersImpl(PortletURLProvider urlProvider, String windowId) {
      super(urlProvider, windowId, ParamType.ACTION);
   }
   
   /**
    * For cloning an object
    * 
    * @param params
    * @param windowId
    */
   protected ActionParametersImpl(Map<String, String[]> params, String windowId) {
      super(params, windowId, ParamType.ACTION);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#mutableClone()
    */
   @Override
   public MutableActionParameters clone() {
      if (isTrace) {
         LOGGER.debug("Window ID: " + windowId + ", ParameterType: " + type);
      }

      // create a mutable clone, breaking link to the underlying URL provider.
      MutableActionParametersImpl map = new MutableActionParametersImpl(params, windowId);
      
      return map;
   }

}
