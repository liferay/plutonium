/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.portlet.MutablePortletParameters;
import jakarta.portlet.PortletParameters;

import com.liferay.plutonium.container.PortletURLProvider;
import com.liferay.plutonium.container.PortletURLProvider.ParamType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Scott Nicklous
 *
 */
public abstract class PortletParametersImpl implements PortletParameters {
   private static final Logger   LOGGER     = LoggerFactory.getLogger(PortletParametersImpl.class);
   //private static final boolean  isDebug    = LOGGER.isDebugEnabled();
   private static final boolean  isTrace    = LOGGER.isTraceEnabled();
   
   protected final PortletURLProvider  urlProvider;
   protected final String              windowId;
   protected final ParamType           type;

   protected final Map<String, String[]>  params;
   
   /**
    * Regular constructor used by extending classes for instantiation.
    * @param urlProvider
    * @param windowId
    * @param type
    */
   protected PortletParametersImpl(PortletURLProvider urlProvider, String windowId, ParamType type) {
      this.urlProvider = urlProvider;
      this.windowId = windowId;
      this.type = type;
      this.params = this.urlProvider.getParameterMap(windowId, type);
      if (isTrace) {
         LOGGER.debug("Created PortletParameters object for window: " + windowId + ", Type: " + type);
      }
   }
   
   /**
    * Copy constructor used when cloning. Use of this constructor disconnects the 
    * object from the underlying URL provider.
    * 
    * @param params
    * @param windowId
    * @param type
    */
   protected PortletParametersImpl(Map<String, String[]> params, String windowId, ParamType type) {
      this.urlProvider = null;
      this.type = type;
      this.windowId = windowId;
      
      this.params = new HashMap<String, String[]>();
      for (String name : params.keySet()) {
         this.params.put(name, params.get(name).clone());
      }
   }
   
   /**
    * Throws exception if argument is null.
    * @param msg
    * @param val
    */
   protected void checkNull(String msg, Object val) {
      if (val == null) {
         throw new IllegalArgumentException("Argument " + msg + " cannot be null.");
      }
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#getValue(java.lang.String)
    */
   public String getValue(String name) {
      checkNull("name", name);
      String[] vals = params.get(name);
      String val = (vals == null || vals.length == 0) ? null : vals[0];
      if (isTrace) {
         LOGGER.debug("Name: " + name + ", Value: " + val);
      }
      return val;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#getNames()
    */
   public Set<String> getNames() {
      HashSet<String> names = new HashSet<String>();
      for (String name : params.keySet()) {
         names.add(name);
      }
      if (isTrace) {
         LOGGER.debug("Parameter Names: " + names.toString());
      }
      return names;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#getValues(java.lang.String)
    */
   public String[] getValues(String name) {
      checkNull("name", name);
      String[] vals = (params.get(name) == null) ? null : params.get(name).clone();
      if (isTrace) {
         LOGGER.debug("Name: " + name + ", Values: " + Arrays.toString(vals));
      }
      return vals;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#isEmpty()
    */
   public boolean isEmpty() {
      boolean e = params.isEmpty();
      if (isTrace) {
         LOGGER.debug("Parameters is empty: " + e);
      }
      return e;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#size()
    */
   public int size() {
      return params.size();
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.PortletParameters#mutableClone()
    */
   public MutablePortletParameters clone() {
      if (isTrace) {
         LOGGER.debug("Window ID: " + windowId + ", ParameterType: " + type);
      }

      // create a mutable clone, breaking link to the underlying URL provider.
      MutablePortletParametersImpl mpp = new MutablePortletParametersImpl(params, windowId, type);
      
      return mpp;
   }

}
