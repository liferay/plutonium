/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import com.liferay.plutonium.container.PortletAsyncManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Runner that is launched in thread on behalf of the portlet application runnable 
 * in order to initialize contextual information.
 * 
 * @author Scott Nicklous
 *
 */
public class PortletAsyncContextualRunner implements Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(PortletAsyncContextualRunner.class);
   private static final boolean isTrace = LOG.isTraceEnabled();
   

   private PortletAsyncManager pactx;
   private Runnable targetRunner; 

   public PortletAsyncContextualRunner() {
   }
   
   public void init(PortletAsyncManager pactx, Runnable targetRunner) {
      this.pactx = pactx;
      this.targetRunner = targetRunner;
   }

   @Override
   public void run() {
      if (isTrace) {
         LOG.trace("Initializing contextual environment and launching runner in thread: " + Thread.currentThread().getId());
      }

      try {
         pactx.registerContext(false);
         targetRunner.run();
      } catch (Exception e) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Exception running thread: ").append(e.toString());
      } finally {
         if (isTrace) {
            LOG.trace("Shutting down contextual environment for thread: " + Thread.currentThread().getId());
         }
         pactx.deregisterContext(false);
      }
}

}
