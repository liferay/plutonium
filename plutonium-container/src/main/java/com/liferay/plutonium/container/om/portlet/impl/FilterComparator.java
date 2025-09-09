/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl;

import java.util.Comparator;

import com.liferay.plutonium.container.om.portlet.Filter;


/**
 * Simple comparator based on the filter ordinal number. used for ordering
 * filter execution properly.
 *  
 * @author Scott Nicklous
 *
 */
public class FilterComparator implements Comparator<Filter> {

   @Override
   public int compare(Filter o1, Filter o2) {
      assert (o1 != null) && (o2 != null);
      return Integer.compare(o1.getOrdinal(), o2.getOrdinal());
   }

}
