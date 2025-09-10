/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.portlet.Event;
import jakarta.portlet.MutableRenderParameters;
import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.plutonium.container.EventProvider;
import com.liferay.plutonium.container.PortletContainer;
import com.liferay.plutonium.container.PortletRequestContext;
import com.liferay.plutonium.container.PortletStateAwareResponseContext;
import com.liferay.plutonium.container.PortletURLProvider;
import com.liferay.plutonium.container.PortletWindow;
import com.liferay.plutonium.container.driver.PlutoniumServices;
import com.liferay.plutonium.container.impl.MutableRenderParametersImpl;
import com.liferay.plutonium.driver.core.PortalRequestContext;
import com.liferay.plutonium.driver.url.PortalURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version $Id$
 * 
 */
public abstract class PortletStateAwareResponseContextImpl extends PortletResponseContextImpl implements
      PortletStateAwareResponseContext {
   private final Logger           LOGGER  = LoggerFactory.getLogger(PortletStateAwareResponseContextImpl.class);
   private final boolean          isDebug = LOGGER.isDebugEnabled();

   private List<Event>            events;
   private PortletURLProviderImpl portletURLProvider;
   private EventProviderImpl      eventProviderImpl;

   public PortletStateAwareResponseContextImpl(PortletContainer container, HttpServletRequest containerRequest,
         HttpServletResponse containerResponse, PortletWindow window, PortletRequestContext requestContext) {
      super(container, containerRequest, containerResponse, window, requestContext);
      this.portletURLProvider = new PortletURLProviderImpl(getPortalURL(), PortletURLProvider.TYPE.RENDER, window,
            getRequestContext());
      this.eventProviderImpl = new EventProviderImpl(getPortletWindow(), PlutoniumServices.getServices()
            .getPortletRegistryService());

      if (isDebug) {
         LOGGER.debug("Initialized.");
      }
   }

   protected PortletURLProvider getPortletURLProvider() {
      return portletURLProvider;
   }

   @Override
   public void close() {
      if (!isClosed()) {
         super.close();

         if (isDebug) {
            LOGGER.debug("Applying the changes.");
         }

         PortalURL url = portletURLProvider.apply();

         if (isDebug) {
            LOGGER.debug("Merging.");
         }

         PortalRequestContext.getContext(getServletRequest()).mergePortalURL(url,
               getPortletWindow().getId().getStringId());

         if (isDebug) {
            LOGGER.debug("exiting.");
         }
      }
   }

   @Override
   public void release() {
      events = null;
      portletURLProvider = null;
      super.release();
   }

   /**
    * called to discard any set events or render parameters
    */
   @Override
   public void reset() {
      events = null;
      portletURLProvider = new PortletURLProviderImpl(getPortalURL(), PortletURLProvider.TYPE.RENDER,
            getPortletWindow(), getRequestContext());
   }

   @Override
   public List<Event> getEvents() {
      if (isReleased()) {
         return null;
      }
      if (events == null) {
         events = new ArrayList<Event>();
      }
      return events;
   }

   @Override
   public PortletMode getPortletMode() {
      return isClosed() ? null : portletURLProvider.getPortletMode();
   }

   @Override
   public WindowState getWindowState() {
      return isClosed() ? null : portletURLProvider.getWindowState();
   }

   @Override
   public void setPortletMode(PortletMode portletMode) {
      if (!isClosed()) {
         portletURLProvider.setPortletMode(portletMode);
      }
   }

   @Override
   public void setWindowState(WindowState windowState) {
      if (!isClosed()) {
         portletURLProvider.setWindowState(windowState);
      }
   }

   @Override
   public EventProvider getEventProvider() {
      return isClosed() ? null : eventProviderImpl;
   }

   /**
    * Add a public render parameter for given window ID and parameter name
    * 
    * @param qn
    *           QName
    * @param identifier
    *           Identifier for PRP
    * @param values
    *           values array
    */
   @Override
   public void addPublicRenderParameter(String windowId, String name, String[] values) {
      if (!isClosed()) {
         portletURLProvider.addPublicRenderParameter(windowId, name, values);
      }
   }

   /**
    * Remove the PRP for the given window ID and parameter name
    * 
    * @param windowId
    * @param name
    */
   @Override
   public void removePublicRenderParameter(String windowId, String name) {
      if (!isClosed()) {
         portletURLProvider.removePublicRenderParameter(windowId, name);
      }
   }

   /**
    * Returns <code>true</code> if the given name representa a public render parameter for the given window.
    * 
    * @param windowId
    * @param name
    * @return
    */
   @Override
   public boolean isPublicRenderParameter(String windowId, String name) {
      boolean ret = false;
      if (!isClosed()) {
         ret = portletURLProvider.isPublicRenderParameter(windowId, name);
      }
      return ret;
   }

   /**
    * Retrieves the available private parameter names for the given window ID
    * 
    * @param windowId
    * @return
    */
   @Override
   public Set<String> getPrivateParameterNames(String windowId) {
      Set<String> pns = new HashSet<String>();
      if (!isClosed()) {
         pns = portletURLProvider.getPrivateParameterNames(windowId);
      }
      return pns;
   }

   /**
    * Gets the values array for the given window ID and private parameter name
    * 
    * @param windowId
    * @param name
    * @return
    */
   @Override
   public String[] getParameterValues(String windowId, String name) {
      String[] vals = new String[0];
      if (!isClosed()) {
         vals = portletURLProvider.getParameterValues(windowId, name);
      }
      return vals;
   }

   /**
    * Adds the specified private parameter if not already present, or updates the values for the parameter if it is
    * already present.
    * 
    * @param windowId
    * @param name
    * @param values
    */
   @Override
   public void setParameter(String windowId, String name, String[] values) {
      if (!isClosed()) {
         portletURLProvider.setParameter(windowId, name, values);
      }
   }

   /**
    * Removes the private parameter for the given window and name. Does nothing if the given parameter is not present.
    * 
    * @param windowId
    * @param name
    */
   @Override
   public void removeParameter(String windowId, String name) {
      if (!isClosed()) {
         portletURLProvider.removeParameter(windowId, name);
      }
   }

   /**
    * Gets the mutable render parameters. V3 method.
    */
   @Override
   public MutableRenderParameters getRenderParameters(String windowId) {
      return new MutableRenderParametersImpl(portletURLProvider, windowId);
   }

}
