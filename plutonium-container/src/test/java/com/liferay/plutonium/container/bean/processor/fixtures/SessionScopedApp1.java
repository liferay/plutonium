/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures;

import java.io.Serializable;

import jakarta.portlet.PortletSession;
import jakarta.portlet.annotations.PortletSessionScoped;

/**
 * PortletSessionScoped bean with APPLICATION_SCOPE
 * @author Scott Nicklous
 *
 */
@PortletSessionScoped(PortletSession.APPLICATION_SCOPE)
public class SessionScopedApp1  implements Serializable {
   private static final long serialVersionUID = -65231174707289554L;
   
   public String sayHi() {return "Hi!";}

}
