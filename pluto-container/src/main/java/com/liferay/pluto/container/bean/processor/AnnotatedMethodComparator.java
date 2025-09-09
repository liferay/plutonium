/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor;

import java.util.Comparator;


/**
 * Simple comparator based on the filter ordinal number. used for ordering
 * the annotated method store properly.
 *  
 * @author Scott Nicklous
 *
 */
public class AnnotatedMethodComparator implements Comparator<AnnotatedMethod> {

   @Override
   public int compare(AnnotatedMethod o1, AnnotatedMethod o2) {
      assert (o1 != null) && (o2 != null);
      return Integer.compare(o1.getOrdinal(), o2.getOrdinal());
   }

}
