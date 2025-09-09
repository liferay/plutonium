/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.portlet.MutableRenderParameters;

import com.liferay.plutonium.container.PortletURLProvider;
import com.liferay.plutonium.container.PortletURLProvider.ParamType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nick
 *
 */
public class MutableRenderParametersImpl extends MutablePortletParametersImpl
      implements MutableRenderParameters {
   private static final Logger   LOGGER     = LoggerFactory.getLogger(MutableRenderParametersImpl.class);
   private static final boolean  isTrace    = LOGGER.isTraceEnabled();

   // called to force class loading in Container thread
   public static final void load() {
      if (isTrace) {
         LOGGER.debug("Loaded.");
      }
   };
   
   private final Set<String> prpNames;

   /**
    * @param urlProvider
    * @param windowId
    * @param type
    */
   public MutableRenderParametersImpl(PortletURLProvider urlProvider, String windowId) {
      super(urlProvider, windowId, ParamType.RENDER);
      prpNames = urlProvider.getPublicParameterNames(windowId);
   }

   /**
    * @param params
    * @param windowId
    * @param type
    */
   public MutableRenderParametersImpl(Map<String, String[]> params, String windowId, Set<String> prpNames) {
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

   public void clearPrivate() {
      int ctr = params.size();

      if (urlProvider != null) {
         for (String name : params.keySet()) {
            if (!prpNames.contains(name)) {
               urlProvider.removeParameter(windowId, name, type);
            }
         }
      }
      params.keySet().retainAll(prpNames);
      ctr -= params.size();

      if (isTrace) {
         StringBuilder txt = new StringBuilder();
         txt.append("Window ID: ").append(windowId)
            .append(", removed ").append(ctr).append(" values.");
         LOGGER.debug(txt.toString());
      }
   }

   public void clearPublic() {
      int ctr = params.size();

      if (urlProvider != null) {
         for (String name : params.keySet()) {
            if (prpNames.contains(name)) {
               urlProvider.removePublicRenderParameter(windowId, name);
            }
         }
      }
      params.keySet().removeAll(prpNames);
      ctr -= params.size();

      if (isTrace) {
         StringBuilder txt = new StringBuilder();
         txt.append("Window ID: ").append(windowId)
            .append(", removed ").append(ctr).append(" values.");
         LOGGER.debug(txt.toString());
      }
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
