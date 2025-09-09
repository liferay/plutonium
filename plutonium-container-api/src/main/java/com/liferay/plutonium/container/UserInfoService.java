/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container;

import java.util.Map;

import jakarta.portlet.PortletRequest;


/**
 * Used to access user information attributes as described in
 * PLT.17.2 of the JSR-168 specificiation. Attribute persistence is
 * not covered by the spec so it is not done here.
 *
 */
public interface UserInfoService {
    /**
     * Retrieve the user attribues and their values associated with the given
     * request and window. This can return null if the user associated with the
     * request is un-authenticated.
     * 
     * The result map will contain only the named attributes as defined on the
     * portlet definition for the PortletWindow.
     *  
     * @param request Used to extract the authenticated user name.
     * @param window The portlet window to get user attributes for.
     * @return A map of names and values of user information attributes
     *         for a particular authenticated user. null if the user is not authenticated.
     */
    Map<String, String> getUserInfo(PortletRequest request, PortletWindow window) throws PortletContainerException;
}