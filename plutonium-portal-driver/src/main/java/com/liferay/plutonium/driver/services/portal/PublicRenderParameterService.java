/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.portal;


/**
 * Provides public render parameter mappings for a specified page. 
 * Assumes that all definitions with the same QName refer to the same 
 * public render parameter. 
 * 
 * @author msnicklous
 * @since  16/01/2015
 */
public interface PublicRenderParameterService {

   /**
    * Returns a public render parameter mapper for the specified page name.
    * 
    * @param page    Page name
    * @return        A PRP mapper for the page
    */
   public PublicRenderParameterMapper getPRPMapper(String page);
}
