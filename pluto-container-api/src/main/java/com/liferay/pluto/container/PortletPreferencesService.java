/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import java.util.Map;

import jakarta.portlet.PortletRequest;
import jakarta.portlet.PreferencesValidator;
import jakarta.portlet.ValidatorException;

import com.liferay.pluto.container.om.portlet.PortletDefinition;

/**
 * Portlet preferences service that should be implemented by the portal.
 * This is an optional container service.
 *
 */
public interface PortletPreferencesService {

    /**
     * Returns the default portlet preferences.
     * @param portletWindow  the portlet window.
     * @param request  the portlet request.
     * @return the default portlet preferences.
     * @throws PortletContainerException  if fail to get default preferences.
     */
    Map<String, PortletPreference> getDefaultPreferences(
            PortletWindow portletWindow,
            PortletRequest request)
            throws PortletContainerException;

    /**
     * Returns the stored portlet preferences.
     * @param portletWindow  the portlet window.
     * @param request  the portlet request.
     * @return the stored portlet preferences.
     * @throws PortletContainerException  if fail to get stored preferences.
     */
    Map<String, PortletPreference> getStoredPreferences(
            PortletWindow portletWindow,
            PortletRequest request)
            throws PortletContainerException;

    /**
     * Stores the portlet references to the persistent storage.
     * @param portletWindow  the portlet window.
     * @param request  the portlet request.
     * @param preferences  the portlet preferences to store.
     * @throws PortletContainerException  if fail to store preferences.
     */
    void store(PortletWindow portletWindow,
            PortletRequest request,
            Map<String, PortletPreference> preferences)
    throws PortletContainerException;

    /**
     * Returns the preferences validator instance for the given portlet
     * definition. If no preferences validator class is defined for the portlet
     * definition, null is returned. This method caches the validator instances
     * in the cache to ensure that only one validator instance is created per
     * portlet definition.
     * @param portletDefinition  the portlet definition.
     * @return the preferences validator if defined for the portlet definition.
     * @throw ValidatorException  if fail to instantiate validator instance.
     */
    PreferencesValidator getPreferencesValidator(PortletDefinition portletDefinition)
    throws ValidatorException;

}
