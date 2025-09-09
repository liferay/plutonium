/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import jakarta.portlet.HeaderResponse;

/**
 * @author Scott Nicklous
 *
 */
public class MockHeaderResponse extends MockMimeResponse implements HeaderResponse {

   @Override
   public void setTitle(String arg0) {
   }

   @Override
   public void addDependency(String name, String scope, String version) {
   }

   @Override
   public void addDependency(String name, String scope, String version, String markup) {
   }

}
