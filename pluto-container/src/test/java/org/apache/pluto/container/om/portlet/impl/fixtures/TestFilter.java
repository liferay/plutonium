/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package org.apache.pluto.container.om.portlet.impl.fixtures;

import jakarta.portlet.PortletException;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.PortletFilter;

/**
 * @author Scott Nicklous
 *
 */
public class TestFilter implements PortletFilter {

   /* (non-Javadoc)
    * @see jakarta.portlet.filter.PortletFilter#destroy()
    */
   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

   /* (non-Javadoc)
    * @see jakarta.portlet.filter.PortletFilter#init(jakarta.portlet.filter.FilterConfig)
    */
   @Override
   public void init(FilterConfig arg0) throws PortletException {
      // TODO Auto-generated method stub

   }

}
