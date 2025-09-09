/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * A container for the annotated method configuration.
 * 
 * @author Scott Nicklous
 *
 */
@ApplicationScoped
public class AnnotatedConfigBean {

   private AnnotatedMethodStore methodStore = null;
   private ConfigSummary summary = null;
   private RedirectScopedConfig redirectScopedConfig = null;
   private PortletStateScopedConfig stateScopedConfig = null;
   private PortletSessionScopedConfig sessionScopedConfig = null;
   
   /**
    * @return the methodStore
    */
   public AnnotatedMethodStore getMethodStore() {
      return methodStore;
   }

   /**
    * @param methodStore the methodStore to set
    */
   public void setMethodStore(AnnotatedMethodStore methodStore) {
      this.methodStore = methodStore;
   }

   /**
    * @return the summary
    */
   public ConfigSummary getSummary() {
      return summary;
   }

   /**
    * @param summary the summary to set
    */
   public void setSummary(ConfigSummary summary) {
      this.summary = summary;
   }

   /**
    * @return the redirectScopedConfig
    */
   public RedirectScopedConfig getRedirectScopedConfig() {
      return redirectScopedConfig;
   }

   /**
    * @param redirectScopedConfig the redirectScopedConfig to set
    */
   public void setRedirectScopedConfig(RedirectScopedConfig redirectScopedConfig) {
      this.redirectScopedConfig = redirectScopedConfig;
   }

   /**
    * @return the stateScopedConfig
    */
   public PortletStateScopedConfig getStateScopedConfig() {
      return stateScopedConfig;
   }

   /**
    * @param stateScopedConfig the stateScopedConfig to set
    */
   public void setStateScopedConfig(PortletStateScopedConfig stateScopedConfig) {
      this.stateScopedConfig = stateScopedConfig;
   }

   /**
    * @return the sessionScopedConfig
    */
   public PortletSessionScopedConfig getSessionScopedConfig() {
      return sessionScopedConfig;
   }

   /**
    * @param sessionScopedConfig the sessionScopedConfig to set
    */
   public void setSessionScopedConfig(PortletSessionScopedConfig sessionScopedConfig) {
      this.sessionScopedConfig = sessionScopedConfig;
   }
   
   
}
