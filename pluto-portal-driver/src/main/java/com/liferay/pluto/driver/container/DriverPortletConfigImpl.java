/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.pluto.container.driver.DriverPortletConfig;
import com.liferay.pluto.container.impl.AbstractPortletConfigImpl;
import com.liferay.pluto.container.om.portlet.CustomWindowState;
import com.liferay.pluto.container.om.portlet.PortletDefinition;
import com.liferay.pluto.container.om.portlet.PublicRenderParameter;
import com.liferay.pluto.container.om.portlet.Supports;

public class DriverPortletConfigImpl extends AbstractPortletConfigImpl implements DriverPortletConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DriverPortletConfigImpl.class);

    protected ResourceBundleFactory bundles;
    
    public DriverPortletConfigImpl(PortletContext portletContext,
                             PortletDefinition portletDD) {
        super(portletContext, portletDD);
    }

    public ResourceBundle getResourceBundle(Locale locale) {
        if(LOG.isDebugEnabled()) {
            LOG.debug("Resource Bundle requested: "+locale);
        }
        if (bundles == null) {
            bundles = new ResourceBundleFactory(portlet, portlet.getPortletInfo());
        }
        return bundles.getResourceBundle(locale);
    }

   public Enumeration<PortletMode> getPortletModes(String mimeType) {
      HashSet<PortletMode> portletModes = getPMList(mimeType);
      return Collections.enumeration(portletModes);
   }

   public Enumeration<WindowState> getWindowStates(String mimeType) {
      HashSet<WindowState> windowStates = getWSList(mimeType);
      return Collections.enumeration(windowStates);
   }

   public Map<String, QName> getPublicRenderParameterDefinitions() {
      HashMap<String, QName> prpdefs = new HashMap<String, QName>();
      HashMap<String, PublicRenderParameter> prps = new HashMap<String, PublicRenderParameter>();
      
      // Get the public render parameter definitions for the portlet app
      
      for (PublicRenderParameter prp : portlet.getApplication().getPublicRenderParameters()) {
         prps.put(prp.getIdentifier(), prp);
      }
      
      // Get the supported PRPs for this portlet
      
      for (String prpname : portlet.getSupportedPublicRenderParameters()) {
         PublicRenderParameter prp = prps.get(prpname);
         if (prp != null) {
            QName qn = prp.getQName();
            prpdefs.put(prpname, qn);
         } else {
            LOG.warn("Could not get public render parameter definition for identifier: " + prpname);
         }
      }
      
      return prpdefs;
   }
   
   private HashSet<PortletMode> getPMList(String mimeType) {
      HashSet<PortletMode> pms = new HashSet<PortletMode>();
      
      pms.add(PortletMode.VIEW);       // all portlets support view
      
      // Add the portlet mode to the list 
      
      for (Supports s : portlet.getSupports()) {
         if (mimeType.equalsIgnoreCase(s.getMimeType())) {
            for (String pmname : s.getPortletModes()) {
               PortletMode pm = new PortletMode(pmname);
               pms.add(pm);
            }
         }
      }

      return pms;
   }
   
   private HashSet<WindowState> getWSList(String mimeType)  {
      HashSet<WindowState> winstates = new HashSet<WindowState>();
      HashSet<WindowState> custWS = new HashSet<WindowState>();
      
      // all portlets support normal, maximized, & minimized window states
      
      winstates.add(WindowState.NORMAL);
      winstates.add(WindowState.MAXIMIZED);
      winstates.add(WindowState.MINIMIZED);
      
      // get list of custom window states for the portlet app
      
      for (CustomWindowState cws : portlet.getApplication().getCustomWindowStates()) {
         custWS.add(new WindowState(cws.getWindowState()));
      }
      
      // See if a restricted list of Window States is specified
      
      boolean restricted = false;
      for (Supports s : portlet.getSupports()) {
         if (mimeType.equalsIgnoreCase(mimeType)) {
            for (String wsname : s.getWindowStates()) {
               WindowState ws = new WindowState(wsname);
               winstates.add(ws);
               restricted = true;
            }
         }
      }
      
      // If the window state list has not been restricted in the deployment descriptor,
      // add all of the custom window states
      
      if (!restricted) {
         winstates.addAll(custWS);
      }
      
      return winstates;
   }
}
