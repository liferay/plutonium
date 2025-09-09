/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import jakarta.portlet.PortletMode;

public interface PortletDefinition {
    
   String getPortletName();

   PortletApplicationDefinition getApplication();

   InitParam getInitParam(String paramName);
   List<InitParam> getInitParams();
   void addInitParam(InitParam ip);
   
   String getPortletClass();
   void setPortletClass(String portletClass);

   PortletInfo getPortletInfo();
   void setPortletInfo(PortletInfo info);

   Preferences getPortletPreferences();
   void setPortletPreferences(Preferences prefs);

   List<EventDefinitionReference> getSupportedProcessingEvents();
   void addSupportedProcessingEvent(EventDefinitionReference edr);
   boolean removeSupportedProcessingEvent(EventDefinitionReference edr);
    
   List<EventDefinitionReference> getSupportedPublishingEvents();
   void addSupportedPublishingEvent(EventDefinitionReference edr);
   boolean removeSupportedPublishingEvent(EventDefinitionReference edr);

   List<String> getSupportedPublicRenderParameters();
   void addSupportedPublicRenderParameter(String identifier);
   boolean removeSupportedPublicRenderParameter(String identifier);

   String getResourceBundle();
   void setResourceBundle(String resourceBundle);

   SecurityRoleRef getSecurityRoleRef(String roleName);
   List<SecurityRoleRef> getSecurityRoleRefs();
   void addSecurityRoleRef(SecurityRoleRef srr);

   Supports getSupports(String mimeType);
   List<Supports> getSupports();
   void addSupports(Supports supps);
   Set<String> getConfiguredMimeTypes();
   boolean isPortletModeSupported(String mimeType, PortletMode pm);

   Description getDescription(Locale locale);
   List<Description> getDescriptions();
   void addDescription(Description desc);

   DisplayName getDisplayName(Locale locale);
   List<DisplayName> getDisplayNames();
   void addDisplayName(DisplayName dn);

   List<String> getSupportedLocales();
   void addSupportedLocale(String lang);

   int getExpirationCache();
   void setExpirationCache(int expirationCache);

   String getCacheScope();
   void setCacheScope(String cacheScope);

   ContainerRuntimeOption getContainerRuntimeOption(String name);
   List<ContainerRuntimeOption> getContainerRuntimeOptions();
   void addContainerRuntimeOption(ContainerRuntimeOption cro);

   void addDependency(Dependency dep);
   List<Dependency> getDependencies();
   Dependency getDependency(String name);

   boolean isAsyncSupported();
   void setAsyncSupported(boolean asyncSupported);
   
   // multipart config
   
   boolean isMultipartSupported();
   void setMultipartSupported(boolean multipartSupported);
   String getLocation();
   void setLocation(String location);
   Integer getFileSizeThreshold();
   void setFileSizeThreshold(Integer fileSizeThreshold);
   Long getMaxRequestSize();
   void setMaxRequestSize(Long maxRequestSize);
   Long getMaxFileSize();
   void setMaxFileSize(Long maxFileSize);

}