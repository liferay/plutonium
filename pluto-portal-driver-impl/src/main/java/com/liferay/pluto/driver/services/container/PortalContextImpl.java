/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import jakarta.portlet.PortalContext;
import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;

import com.liferay.pluto.driver.config.DriverConfiguration;

/**
 * <code>PortalContext</code> implementation for the Pluto Portal Driver.
 */
public class PortalContextImpl implements PortalContext {
   

    /**
     * The <code>DriverConfigurationImpl</code> from which this
     * <code>PortalContext</code> recieves it's configuration information.
     */
    private DriverConfiguration config;

    /**
     * Portal information.
     */
    private String info = null;

    /**
     * Portal Properties
     */
    private HashMap<String, String> properties = new HashMap<String, String>();

    /**
     * Supported PortletModes.
     */
    private Vector<PortletMode> portletModes;

    /**
     * Supported WindowStates.
     */
    private Vector<WindowState> windowStates;


    /**
     * Default Constructor.
     * @param config
     */
    public PortalContextImpl(DriverConfiguration config) {
        this.config = config;
        reset();

        // we now support render headers
        properties.put(PortalContext.MARKUP_HEAD_ELEMENT_SUPPORT, "true");
    }

    /**
     * Get a dynamic portal property.
     * @param name
     * @return the property value associated with the given key.
     * @throws IllegalArgumentException if the specified name is null.
     */
    public String getProperty(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Property name == null");
        }

        return (String) properties.get(name);
    }


    /**
     * Get an enumeration containing all names of the portal properties.
     * @return an enumeration of all keys to which properties are bound.
     */
    public Enumeration<String> getPropertyNames() {
        Vector<String> names = new Vector<String>(properties.keySet());
        return names.elements();
    }


    /**
     * Get an enumeration of all <code>PortletMode</code>s supported by this
     * portal.
     * @return enumeration of all supported portlet modes.
     */
    public Enumeration<PortletMode> getSupportedPortletModes() {
        if (portletModes == null) {
            portletModes = new Vector<PortletMode>();
            @SuppressWarnings({ "unchecked", "rawtypes" })
            Enumeration enumeration = new Vector(config.getSupportedPortletModes()).elements();
            while (enumeration.hasMoreElements()) {
                portletModes.add(
                    new PortletMode(enumeration.nextElement().toString()));
            }
        }
        return portletModes.elements();
    }

    /**
     * Get an enumeration of all <code>WindowState</code>s supported by this
     * portal.
     * @return an enumeration of all supported window states.
     */
    public Enumeration<WindowState> getSupportedWindowStates() {
        if (windowStates == null) {
            windowStates = new Vector<WindowState>();
            @SuppressWarnings({ "unchecked", "rawtypes" })
            Enumeration enumeration = new Vector(config.getSupportedWindowStates()).elements();
            while (enumeration.hasMoreElements()) {
                windowStates.add(
                    new WindowState(enumeration.nextElement().toString()));
            }
        }
        return windowStates.elements();
    }

    /**
     * Get the portal info for this portal.
     * @return the portal information for this context.
     */
    public String getPortalInfo() {
        if(info == null) {
            info = config.getPortalName() + "/" + config.getPortalVersion();
        }
        return info;
    }


    // additional methods.
    // methods used container internally to set

    public void setProperty(String name, String value) {
        if (name == null) {
            throw new IllegalArgumentException("Property name == null");
        }

        properties.put(name, value);
    }


    /**
     * reset all values to default portlet modes and window states; delete all
     * properties and set the given portlet information as portlet info string.
     */
    public void reset() {
        info = null;
        properties.clear();
    }

    public DriverConfiguration getDriverConfiguration() {
        return config;
    }

}
