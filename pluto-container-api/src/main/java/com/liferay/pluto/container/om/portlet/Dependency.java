package com.liferay.pluto.container.om.portlet;

/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

public interface Dependency {

   /**
    * @return the name
    */
   String getName();

   /**
    * @param name the name to set
    */
   void setName(String name);

   /**
    * @return the version
    */
   String getVersion();

   /**
    * @param version the version to set
    */
   void setVersion(String version);

   /**
    * @return the scope name
    */
   String getScope();
   
   /**
    * @param scope - the scope name
    */
   void setScope(String scope);

}