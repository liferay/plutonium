/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.portlet.RenderParameters;
import jakarta.portlet.MutableRenderParameters;

import com.liferay.pluto.container.PortletURLProvider;
import com.liferay.pluto.container.PortletURLProvider.ParamType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nick
 *
 */
public class RenderParametersImpl extends PortletParametersImpl implements
      RenderParameters {
   private static final Logger   LOGGER     = LoggerFactory.getLogger(RenderParametersImpl.class);
   private static final boolean  isTrace    = LOGGER.isTraceEnabled();

   // called to force class loading in Container thread
   public static final void load() {
      if (isTrace) {
         LOGGER.debug("Loaded.");
      }
   };
   
   private final Set<String> prpNames;

   /**
    * For creating a new object from scratch
    * 
    * @param urlProvider
    * @param windowId
    */
   public RenderParametersImpl(PortletURLProvider urlProvider, String windowId) {
      super(urlProvider, windowId, ParamType.RENDER);
      prpNames = urlProvider.getPublicParameterNames(windowId);
   }
   
   /**
    * For cloning an object
    * 
    * @param params
    * @param windowId
    */
   protected RenderParametersImpl(Map<String, String[]> params, String windowId, Set<String> prpNames) {
      super(params, windowId, ParamType.RENDER);
      this.prpNames = new HashSet<String>(prpNames);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#mutableClone()
    */
   @Override
   public MutableRenderParameters clone() {
      if (isTrace) {
         LOGGER.debug("Window ID: " + windowId + ", ParameterType: " + type);
      }

      // create a mutable clone, breaking link to the underlying URL provider.
      MutableRenderParametersImpl map = new MutableRenderParametersImpl(params, windowId, prpNames);
      
      return map;
   }

   public boolean isPublic(String name) {
      checkNull("name", name);
      boolean isPublic = prpNames.contains(name); 
      
      if (isTrace) {
         StringBuilder txt = new StringBuilder();
         txt.append("Window ID: ").append(windowId)
            .append(", Name: ").append(name)
            .append(", isPublic: ").append(isPublic);
         LOGGER.debug(txt.toString());
      }
      
      return isPublic;
   }

}
