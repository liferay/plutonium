/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.url;

import java.util.Collection;
import java.util.Map;

import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;
import jakarta.servlet.ServletContext;

import com.liferay.plutonium.container.PortletRequestContext;
import com.liferay.plutonium.driver.services.portal.PageConfig;
import com.liferay.plutonium.driver.services.portal.PublicRenderParameterMapper;

/**
 * Created by IntelliJ IDEA.
 * User: ddewolf
 * Date: Sep 4, 2006
 * Time: 5:17:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PortalURL extends Cloneable {

   enum URLType {
      Render,
      Action,
      Resource,
      AjaxAction,
      PartialAction,
      Portal;
   }
   
   public PortletParameterFactory getPortletParameterFactory(PortletRequestContext reqctx);
   
   /**
    * Sets the URL type
    * @param type
    */
   public void setType(URLType type);
   
   /**
    * Returns the URL Type
    * @return
    */
   public URLType getType();
   
   /**
    * Sets the target window ID for this URL
    * @param windowId
    */
   public void setTargetWindow(String windowId);
   
   /**
    * returns the target window. May be null.
    * @return
    */
   public String getTargetWindow();

   void setRenderPath(String renderPath);

   String getRenderPath();

   void setParameter(PortalURLParameter param);
   
   /**
    * Removes the given parameter
    * @param param
    */
   public void removeParameter(PortalURLParameter param);

   /**
    * Add the PRP mapper for the page being processed
    * @param prpm
    */
   public void setPublicRenderParameterMapper(PublicRenderParameterMapper prpm);

   /**
    * get the PRP mapper for the page being processed
    * @return
    */
   public PublicRenderParameterMapper getPublicRenderParameterMapper();

   /**
    * Stores the portlet window IDs for the portlets on the page
    * 
    * @param portletIds
    */
   public void setPortletIds(Collection<String> portletIds);

   /**
    * Returns the portlet Ids for the portlets on the page
    * 
    * @return
    */
   public Collection<String> getPortletIds();

   /**
    * Sets the version for the given portlet ID
    * @param portletId
    * @param version
    */
   public void setVersion(String portletId, String version);

   /**
    * gets the version for the given portlet ID
    * @param portletId
    * @return
    */
   public String getVersion(String portletId);

   /**
    * Returns <code>true</code> if the given portlet ID refers to a version 3 portlet
    * @param portletId
    * @return
    */
   public boolean isVersion3(String portletId);

   Collection<PortalURLParameter> getParameters();

   Map<String, PortletMode> getPortletModes();

   PortletMode getPortletMode(String windowId);

   void setPortletMode(String windowId, PortletMode portletMode);

   Map<String, WindowState> getWindowStates();

   WindowState getWindowState(String windowId);

   void setWindowState(String windowId, WindowState windowState);

   /**
    * 
    * @deprecated use toURL(boolean) instead
    */
   String toString();

   String toURL(boolean absolute);

   String getServerURI();

   String getServletPath();

   PortalURL clone();

   PageConfig getPageConfig(ServletContext servletContext);

   void setCacheability(String cacheLevel);
   String getCacheability();

   void setResourceID(String resourceID);
   String getResourceID();

   /**
    * @param window
    */
   public void clearParameters(String window, String paramType);

   public String getFragmentIdentifier();

   public void setFragmentIdentifier(String fragment);

   public boolean getAuthenticated();

   public void setAuthenticated(boolean authenticated);	
}
