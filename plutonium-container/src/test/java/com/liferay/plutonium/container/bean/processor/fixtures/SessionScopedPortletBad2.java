/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures;

import java.io.Serializable;

import jakarta.portlet.annotations.PortletSessionScoped;

/**
 * PortletSessionScoped bean with PORTLET_SCOPE
 * @author Scott Nicklous
 *
 */
@PortletSessionScoped(1200)
public class SessionScopedPortletBad2 implements Serializable {
   private static final long serialVersionUID = -5293096810065550573L;

}
