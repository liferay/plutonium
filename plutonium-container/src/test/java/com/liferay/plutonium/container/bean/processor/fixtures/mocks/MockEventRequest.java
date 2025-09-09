/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures.mocks;

import jakarta.portlet.Event;
import jakarta.portlet.EventRequest;
import javax.xml.namespace.QName;


/**
 * @author Scott Nicklous
 *
 */
public class MockEventRequest extends MockClientDataRequest implements EventRequest {
   
   private QName qn;

   /**
    * @return the qn
    */
   public QName getQn() {
      return qn;
   }

   /**
    * @param qn the qn to set
    */
   public void setQn(QName qn) {
      this.qn = qn;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.EventRequest#getEvent()
    */
   @Override
   public Event getEvent() {
      return new MockEvent(qn);
   }

}
