/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;

/**
 * PortletSessionScoped bean with APPLICATION_SCOPE
 * @author Scott Nicklous
 *
 */
@SessionScoped
public class SessionScopedApp2  implements Serializable {
   private static final long serialVersionUID = -65231174707289554L;
   
   public String sayHi() {return "Hi!";}

}
