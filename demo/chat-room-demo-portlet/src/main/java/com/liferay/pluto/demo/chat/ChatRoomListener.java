/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.chat;

import java.io.IOException;

import jakarta.portlet.PortletAsyncEvent;
import jakarta.portlet.PortletAsyncListener;
import jakarta.portlet.annotations.PortletRequestScoped;

/**
 * Simple async listener to watch for timeouts. 
 * 
 * @author Scott Nicklous
 *
 */
@PortletRequestScoped
public class ChatRoomListener implements PortletAsyncListener {
   
   private volatile boolean timeout = false;
   
   public boolean isTimeout() {
      return timeout;
   }

   @Override
   public void onComplete(PortletAsyncEvent evt) throws IOException {
   }
   @Override
   public void onError(PortletAsyncEvent evt) throws IOException {
   }

   @Override
   public void onStartAsync(PortletAsyncEvent evt) throws IOException {
   }

   @Override
   public void onTimeout(PortletAsyncEvent evt) throws IOException {
      timeout = true;
      evt.getPortletAsyncContext().complete();
   }

}
