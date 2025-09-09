/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures;

import jakarta.portlet.annotations.RenderStateScoped;

/**
 * Verifies that an {@literal @}RenderStateScoped bean not implementing 
 * <code>PortletSerializable</code> is recognized.
 *  
 * @author Scott Nicklous
 */
@RenderStateScoped
public class PortletStateScopedBadClass {

}
