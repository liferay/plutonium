/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.v3annotated;

import jakarta.portlet.annotations.PortletRequestScoped;

/**
 * Requestscoped bean to mark if timout occurs.
 * 
 * @author Scott Nicklous
 */
@PortletRequestScoped
public class AsyncCompleteBean {

   private boolean complete = false;
   
   public AsyncCompleteBean() {
   }

   /**
    * @return the complete
    */
   public boolean isComplete() {
      return complete;
   }

   /**
    * @param complete the complete to set
    */
   public void setComplete(boolean complete) {
      this.complete = complete;
   }

}
