/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.portlet.ActionURL;
import jakarta.portlet.BaseURL;
import jakarta.portlet.MimeResponse.Copy;
import jakarta.portlet.PortalContext;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletMode;
import jakarta.portlet.PortletSecurityException;
import jakarta.portlet.PortletURLGenerationListener;
import jakarta.portlet.RenderParameters;
import jakarta.portlet.RenderURL;
import jakarta.portlet.ResourceURL;
import jakarta.portlet.WindowState;

import com.liferay.plutonium.container.PortletResponseContext;
import com.liferay.plutonium.container.PortletURLListenerService;
import com.liferay.plutonium.container.PortletURLProvider;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.util.ArgumentUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Scott Nicklous
 *
 */
public abstract class BaseURLImpl implements BaseURL {
   private static final Logger    LOGGER  = LoggerFactory.getLogger(BaseURLImpl.class);
   private static final boolean   isTrace = LOGGER.isTraceEnabled();

   protected PortletResponseContext responseContext;
   protected PortalContext          portalContext;
   protected boolean                filtering;
   protected boolean                renderURL;

   protected final String         windowId;
   protected PortletURLProvider   urlProvider;

   // Constructor
   // ------------------------------------------------------------

   protected BaseURLImpl(PortletResponseContext responseContext, PortletURLProvider urlProvider) {
      this.responseContext = responseContext;
      this.portalContext = responseContext.getContainer()
            .getContainerServices().getPortalContext();
      this.windowId = responseContext.getPortletWindow().getId().getStringId();
      this.urlProvider = urlProvider;
      renderURL = PortletURLProvider.TYPE.RENDER == urlProvider.getType();
      if (isTrace) {
         StringBuilder txt = new StringBuilder();
         txt.append("URL provider type=").append(urlProvider.getType());
         txt.append(", WindowId=").append(windowId);
         txt.append(", isRender=").append(renderURL);
         LOGGER.debug(txt.toString());
      }
   }

   public BaseURLImpl(PortletResponseContext responseContext,
         PortletURLProvider portletURLProvider, Copy copy) {
      this(responseContext, portletURLProvider);
      
      switch (copy) {
      case NONE:
         urlProvider.clearParameters(windowId, PortletURLProvider.ParamType.PUBLIC);
      case PUBLIC:
         urlProvider.clearParameters(windowId, PortletURLProvider.ParamType.RENDER);
         break;
      case ALL:
      }
   }

   // Private methods
   // ------------------------------------------------------------

   /** 
    * implements the filter mechanism when the URLs are generated.
    */
   @SuppressWarnings("unchecked")
   private void filterURL() {
      if (filtering) {
         throw new IllegalStateException(
               "Calling PortletURL toString or write methods from a PortletURLGenerationListener not allowed");
      }
      filtering = true;
      try {
         PortletURLListenerService service = responseContext.getContainer()
               .getContainerServices().getPortletURLListenerService();
         PortletApplicationDefinition portletApp = responseContext
               .getPortletWindow().getPortletDefinition().getApplication();
         for (PortletURLGenerationListener<RenderURL, ActionURL> listener : service
               .getPortletURLGenerationListeners(portletApp)) {
            if (this instanceof ActionURL) {
               listener.filterActionURL((ActionURL)this);
            } else if (this instanceof ResourceURL) {
               listener.filterResourceURL((ResourceURL)this);
            } else if (this instanceof RenderURL)  {
               listener.filterRenderURL((RenderURL)this);
            } else {
               LOGGER.warn("Could not filter URL. Bad URL type: " + this.getClass().getSimpleName());
            }
         }
      } finally {
         filtering = false;
      }
   }

   // BaseURL implementation
   // ------------------------------------------------------------

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#write(java.io.Writer, boolean)
    */
   public void write(Writer out, boolean escapeXML) throws IOException {
      filterURL();
      urlProvider.write(out, escapeXML);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#write(java.io.Writer)
    */
   public void write(Writer out) throws IOException {
      write(out, true);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#toString()
    */
   public String toString() {
      filterURL();
      return urlProvider.toURL();
   }


   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#append(java.lang.Appendable)
    */
   public Appendable append(Appendable out) throws IOException {
      return append(out, true);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#append(java.lang.Appendable, boolean)
    */
   public Appendable append(Appendable out, boolean escapeXML) throws IOException {
      filterURL();
      urlProvider.append(out, escapeXML);
      return out;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.RenderState#getRenderParameters()
    */
   public RenderParameters getRenderParameters() {
      return new RenderParametersImpl(urlProvider, windowId);
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.RenderState#getPortletMode()
    */
   public PortletMode getPortletMode() {
      return urlProvider.getPortletMode();
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.RenderState#getWindowState()
    */
   public WindowState getWindowState() {
      return urlProvider.getWindowState();
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#getParameterMap()
    */
   public Map<String, String[]> getParameterMap() {
      Map<String, String[]> parameters = new HashMap<String, String[]>();
      
      Set<String> names = urlProvider.getPrivateParameterNames(windowId);
      for (String name : names) {
         String[] vals = urlProvider.getParameterValues(windowId, name);
         parameters.put(name, vals);
      }

      if (renderURL) {
         names = urlProvider.getPublicParameterNames(windowId);
         for (String name : names) {
            String[] vals = urlProvider.getParameterValues(windowId, name);
            if (vals != null) {
               parameters.put(name, vals);
            }
         }
      }

      return parameters;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#setParameter(java.lang.String, java.lang.String)
    */
   public void setParameter(String name, String value) {
      ArgumentUtility.validateNotEmpty("name", name);
      if (value == null) {
         urlProvider.removeParameter(windowId, name);
      } else {
         String[] values = new String[] { value };
         urlProvider.setParameter(windowId, name, values);
      }
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#setParameter(java.lang.String, java.lang.String[])
    */
   public void setParameter(String name, String... values) {
      ArgumentUtility.validateNotEmpty("name", name);
      if (values == null) {
         if (getPortletMajorVersion() < 3) {
            throw new IllegalArgumentException();
         }
         else {
            urlProvider.removeParameter(windowId, name);
         }
      } else {
         urlProvider.setParameter(windowId, name, values.clone());
      }
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#setParameters(java.util.Map)
    */
   public void setParameters(Map<String, String[]> parameters) {
      ArgumentUtility.validateNotNull("parameters", parameters);

      // validate map first
      for (String key : parameters.keySet()) {
         String[] vals = parameters.get(key);
         if (key == null || vals == null) {
            throw new IllegalArgumentException(
                  "parameters map contains a null key or values array");
         }
      }

      // Remove the parameters that are gone
      Set<String> currNames = urlProvider.getPrivateParameterNames(windowId);
      currNames.removeAll(parameters.keySet());
      for (String name : currNames) {
         urlProvider.removeParameter(windowId, name);
      }

      // Now set the new values
      for (String key : parameters.keySet()) {
         urlProvider.setParameter(windowId, key, parameters.get(key));
      }
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#addProperty(java.lang.String, java.lang.String)
    */
   public void addProperty(String key, String value) {
      ArgumentUtility.validateNotEmpty("key", key);
      if (value != null) {
         Map<String, List<String>> properties = urlProvider.getProperties();
         List<String> values = properties.get(key);
         if (values == null) {
            values = new ArrayList<String>();
            urlProvider.getProperties().put(key, values);
         }
         values.add(value);
      }
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#setProperty(java.lang.String, java.lang.String)
    */
   public void setProperty(String key, String value) {
      ArgumentUtility.validateNotEmpty("key", key);
      Map<String, List<String>> properties = urlProvider.getProperties();
      if (value == null) {
         properties.remove(key);
      } else {
         List<String> values = properties.get(key);
         if (values == null) {
            values = new ArrayList<String>();
            properties.put(key, values);
         } else {
            values.clear();
         }
         values.add(value);
      }
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.BaseURL#setSecure(boolean)
    */
   public void setSecure(boolean secure) throws PortletSecurityException {
      urlProvider.setSecure(secure);
   }

   private int getPortletMajorVersion() {
      PortletConfig portletConfig = responseContext.getPortletConfig();
      PortletContext portletContext = portletConfig.getPortletContext();
      return portletContext.getEffectiveMajorVersion();
   }

}
