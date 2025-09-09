/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

/**
 * Listener interface implemented by observers of
 * the PortletRegistry which intend to be notified
 * of new portlet application registrations.
 *
 * @since 1.1.0
 */
public interface PortletRegistryListener {

    /**
     * Recieve notification of a newly registered application.
     *
     * @param event registry event information
     */
    void portletApplicationRegistered(PortletRegistryEvent event);

    /**
     * Recieve notification of an application which is
     * removed from service.
     *
     * @param event registry event information
     */
    void portletApplicationRemoved(PortletRegistryEvent event);

}