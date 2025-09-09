/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures.mocks;

import java.io.Serializable;

import jakarta.portlet.Event;
import javax.xml.namespace.QName;

/**
 * @author Scott Nicklous
 *
 */
public class MockEvent implements Event {
   
   private final QName qn;
   
   public MockEvent(QName qn) {
      this.qn = qn;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.Event#getName()
    */
   @Override
   public String getName() {
      return qn.getLocalPart();
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.Event#getQName()
    */
   @Override
   public QName getQName() {
      return qn;
   }

   /* (non-Javadoc)
    * @see jakarta.portlet.Event#getValue()
    */
   @Override
   public Serializable getValue() {
      return null;
   }

}
