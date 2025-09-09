/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures;

import jakarta.portlet.annotations.PortletSerializable;
import jakarta.portlet.annotations.RenderStateScoped;

/**
 * Verifies that an {@literal @}RenderStateScoped bean is recognized.
 *  
 * @author Scott Nicklous
 */
@RenderStateScoped
public class PortletStateScopedNoParamNameClass implements PortletSerializable {

   @Override
   public void deserialize(String[] arg0) {
   }

   @Override
   public String[] serialize() {
      return null;
   }

}
