/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.impl.resource;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletMode;

import com.liferay.plutonium.container.PortletContainerException;
import com.liferay.plutonium.container.driver.PortletContextService;
import com.liferay.plutonium.container.driver.PortletRegistryService;
import com.liferay.plutonium.container.om.portlet.CustomPortletMode;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.om.portlet.Supports;
import com.liferay.plutonium.driver.services.portal.PortletWindowConfig;
import com.liferay.plutonium.driver.services.portal.PropertyConfigService;
import com.liferay.plutonium.driver.services.portal.SupportedModesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows clients to determine if a particular PortletMode is supported by the portal, a particular portlet, or both.
 * <p/>
 * This implementation depends on {@link PropertyConfigService}.
 * <p/>
 * The service implementations are injected by Spring.
 * 
 * @version $Id$
 * @since September 9, 2006
 */
public class SupportedModesServiceImpl implements SupportedModesService {
   /**
    * Logger
    */
   private static final Logger          LOG                           = LoggerFactory.getLogger(SupportedModesServiceImpl.class);

   /**
    * PortletMode objects supported by the portal
    */
   private Set<PortletMode>             supportedPortletModesByPortal = new HashSet<PortletMode>();

   /**
    * PropertyConfig Service used to obtain supported portal modes
    */
   private final PropertyConfigService  propertyService;

   private final PortletContextService  portletContextService;

   /**
    * PortletRegistryService used to obtain PortletApplicationConfig objects
    */
   private final PortletRegistryService portletRegistry;

   /**
    * Constructs a SupportedModesService with its dependencies.
    * 
    * @param propertyService
    *           the PropertyConfigService
    */
   public SupportedModesServiceImpl(PropertyConfigService propertyService, PortletContextService portletContextService, PortletRegistryService portletRegistry) {
      this.propertyService = propertyService;
      this.portletContextService = portletContextService;
      this.portletRegistry = portletRegistry;
      loadPortalModes();
   }

   // SupportedModesService Implementation -----------------

   public boolean isPortletModeSupported(String portletId, String mode) {
      if (LOG.isDebugEnabled()) {
         StringBuilder txt = new StringBuilder();
         txt.append("Determining support for PM: ").append(mode);
         txt.append(", portletID: ").append(portletId);
         txt.append(", by portal: ").append(isPortletModeSupportedByPortal(mode));
         txt.append(", by portlet: ").append(isPortletModeSupportedByPortlet(portletId, mode));
         txt.append(", is portlet managed: ").append(isPortletManagedMode(portletId, mode));
         LOG.debug(txt.toString());
      }
      return (isPortletModeSupportedByPortal(mode) && isPortletModeSupportedByPortlet(portletId, mode) || isPortletManagedMode(portletId, mode));
   }

   public boolean isPortletModeSupportedByPortal(String mode) {
      return supportedPortletModesByPortal.contains(new PortletMode(mode));
   }

   public boolean isPortletModeSupportedByPortlet(String portletId, String mode) {

      // View mode is always supported
      if (mode.equalsIgnoreCase(PortletMode.VIEW.toString())) {
         return true;
      }

      String applicationId = PortletWindowConfig.parseContextPath(portletId);
      String applicationName = applicationId;
      String portletName = PortletWindowConfig.parsePortletName(portletId);

      try {
         if (portletRegistry == null) {
            LOG.error("Optional Portlet Registry Service not found.");
            throw new PortletContainerException("Optional Portlet Registry Service not found.");
         }
         PortletApplicationDefinition ctx = portletRegistry.getPortletApplication(applicationName);

         for (Supports sd : ctx.getPortlet(portletName).getSupports()) {
            if (sd.getMimeType().matches("(?:\\*|\\*/\\*|text/html|text/\\*)")) {
               for (String pm : sd.getPortletModes()) {
                  if (pm.equalsIgnoreCase(mode)) {
                     return true;
                  }
               }
            }
         }

      } catch (PortletContainerException e) {
         LOG.error("Error determining mode support.", e);
      }
      LOG.info("Portlet mode '" + mode + "' not found for portletId: '" + portletId + "'");
      return false;
   }

   /**
    * Populates the supportedPortletModesByPortal set.
    */
   private void loadPortalModes() {
      // Add the PortletModes supported by the portal to the
      // supportedPortletModesByPortal set.
      LOG.debug("Loading supported portal modes...");
      Iterator<String> modes = propertyService.getSupportedPortletModes().iterator();
      while (modes.hasNext()) {
         String mode = (String) modes.next();
         LOG.debug("Loading mode [" + mode + "]");
         supportedPortletModesByPortal.add(new PortletMode(mode));
      }
      LOG.debug("Loaded [" + supportedPortletModesByPortal.size() + "] supported portal modes");

   }

   public boolean isPortletManagedMode(String portletId, String mode) {
      String applicationId = PortletWindowConfig.parseContextPath(portletId);
      String applicationName = applicationId;
      // if (applicationName.length() > 0)
      // {
      // applicationName = applicationName.substring(1);
      // }
      try {
         PortletApplicationDefinition portletApp = portletRegistry.getPortletApplication(applicationName);
         Iterator<? extends CustomPortletMode> customModes = portletApp.getCustomPortletModes().iterator();
         while (customModes.hasNext()) {
            CustomPortletMode customMode = (CustomPortletMode) customModes.next();
            boolean isPortletManagedMode = !customMode.isPortalManaged();
            if (isPortletManagedMode && customMode.getPortletMode().equalsIgnoreCase(mode)) {
               return true;
            }
         }
      } catch (PortletContainerException e) {
         LOG.error("Error determining portlet managed mode support, so we assume that it is false.", e);
      }

      return false;
   }

   /**
    * Gets all modes supported by a portlet that are defined in the portlet's supports child element in portlet.xml.
    * 
    * @param portletId
    *           of interest.
    * @return all portlet modes supported by a portlet.
    */
   public Set<PortletMode> getSupportedPortletModes(String portletId) throws PortletContainerException {
      Set<PortletMode> modeSet = new HashSet<PortletMode>();
      modeSet.add(PortletMode.VIEW); // view is always supported

      String applicationId = PortletWindowConfig.parseContextPath(portletId);
      String applicationName = applicationId;
      String portletName = PortletWindowConfig.parsePortletName(portletId);

      if (portletRegistry == null) {
         LOG.error("Optional Portlet Registry Service not found.");
         throw new PortletContainerException("Optional Portlet Registry Service not found.");
      }
      PortletApplicationDefinition portletApp = portletRegistry.getPortletApplication(applicationName);

      for (Supports sd : portletApp.getPortlet(portletName).getSupports()) {
         if (sd.getMimeType().matches("(?:\\*|\\*/\\*|text/html|text/\\*)")) {
            for (String pm : sd.getPortletModes()) {
               modeSet.add((new PortletMode(pm)));
            }
         }
      }

      return modeSet;
   }

   public PortletConfig getPortletConfig(String portletId) throws PortletContainerException {
      String applicationId = PortletWindowConfig.parseContextPath(portletId);
      String applicationName = applicationId;
      // if (applicationName.length() > 0)
      // {
      // applicationName = applicationName.substring(1);
      // }
      String portletName = PortletWindowConfig.parsePortletName(portletId);

      return portletContextService.getPortletConfig(applicationName, portletName);
   }
}
