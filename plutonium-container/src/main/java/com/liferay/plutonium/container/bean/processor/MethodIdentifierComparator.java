/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor;

import java.util.Comparator;


/**
 * Simple comparator based on the method identifier portlet name and method type fields.
 * Used for ordering the annotated method store string output for display & debug.
 *  
 * @author Scott Nicklous
 *
 */
public class MethodIdentifierComparator implements Comparator<MethodIdentifier> {

   @Override
   public int compare(MethodIdentifier o1, MethodIdentifier o2) {
      assert (o1 != null) && (o2 != null);
      int rc = o1.getName().compareTo(o2.getName());
      if (rc == 0) {
         rc = o1.getType().compareTo(o2.getType());
      }
      return rc;
   }

}
