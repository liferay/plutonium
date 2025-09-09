/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures.mocks;

import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;

/**
 * @author Scott Nicklous
 *
 */
public class MockEventResponse extends MockStateAwareResponse implements EventResponse {

   /* (non-Javadoc)
    * @see jakarta.portlet.EventResponse#setRenderParameters(jakarta.portlet.EventRequest)
    */
   @Override
   public void setRenderParameters(EventRequest arg0) {

   }

}
