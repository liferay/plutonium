/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.http.HttpServletRequestWrapper;

public interface PortletAsyncManager {

   void setWrapped(AsyncContext actx);
   void registerContext(boolean isListener);
   void deregisterContext(boolean isListener);
   void removeContext();
   void launchRunner();
   boolean isComplete();
   void setComplete(boolean complete);
   void setContextInactive();
   HttpServletRequestWrapper getAsyncRequestWrapper();

}