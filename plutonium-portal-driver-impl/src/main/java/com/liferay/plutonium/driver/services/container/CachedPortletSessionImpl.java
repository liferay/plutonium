/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import jakarta.portlet.PortletSession;
import jakarta.portlet.filter.PortletSessionWrapper;

public class CachedPortletSessionImpl extends PortletSessionWrapper implements CachedPortletSession {

   private boolean invalidated;

   public CachedPortletSessionImpl(PortletSession wrapped) {
      super(wrapped);
   }

   @Override
   public boolean isInvalidated() {
      return invalidated;
   }

   @Override
   public void invalidate() {
      invalidated = true;
      CachedPortletSessionUtil.INVALIDATED_SESSIONS.put(getId(), true);
      super.invalidate();
   }
}
