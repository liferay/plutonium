/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet;

import java.util.List;

public interface Supports {

   String getMimeType();
   void setMimeType(String mt);

   List<String> getPortletModes();
   void addPortletMode(String portletMode);

   List<String> getWindowStates();
   void addWindowState(String windowState);
}