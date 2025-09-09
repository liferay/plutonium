/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.ListResourceBundle;

import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class encapsulating access to portlet mode and windowstate tooltips.
 *
 * @since Feb 23, 2007
 * @version $Id$
 */
class ToolTips
{
    private static final ResourceBundle BUNDLE;
    static {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("ToolTips");
        } catch (MissingResourceException e) {
            bundle = new ListResourceBundle() {
                protected Object[][] getContents() {
                    return new Object[][] {
                            {"tooltip.windowstate.maximized", "Maximize"},
                            {"tooltip.windowstate.minimized", "Minimize"},
                            {"tooltip.windowstate.normal", "Restore"},

                            {"tooltip.mode.view", "View"},
                            {"tooltip.mode.help", "Help"},
                            {"tooltip.mode.edit", "Edit"},

                            {"tooltip.css.classname", "tooltip"},
                    };
                }
            };
        }
        BUNDLE = bundle;
    }
    private static final Logger LOG = LoggerFactory.getLogger(ToolTips.class);

    static final ToolTips MAXIMIZE = new ToolTips(BUNDLE.getString("tooltip.windowstate.maximized"));
    static final ToolTips MINIMIZE = new ToolTips(BUNDLE.getString("tooltip.windowstate.minimized"));
    static final ToolTips NORMAL = new ToolTips(BUNDLE.getString("tooltip.windowstate.normal"));

    static final ToolTips VIEW = new ToolTips(BUNDLE.getString("tooltip.mode.view"));
    static final ToolTips EDIT = new ToolTips(BUNDLE.getString("tooltip.mode.edit"));
    static final ToolTips HELP = new ToolTips(BUNDLE.getString("tooltip.mode.help"));

    static final String CSS_CLASS_NAME = BUNDLE.getString("tooltip.css.classname");

    private String tooltip;

    private ToolTips(String tooltip) {
        this.tooltip = tooltip;
    }

    static ToolTips forMode(PortletMode mode) {
        StringBuffer tip = new StringBuffer("");
        try {
            tip.append(BUNDLE.getString("tooltip.mode." + mode));
        } catch (MissingResourceException e) {
            LOG.warn("No tooltip found for portlet mode [" + mode + "]", e);
        }
        return new ToolTips(tip.toString());
    }

    static ToolTips forWindowState(WindowState state) {
        StringBuffer tip = new StringBuffer("");
        try {
            tip.append(BUNDLE.getString("tooltip.windowstate." + state));
        } catch (MissingResourceException e) {
            LOG.warn("No tooltip found for window state [" + state + "]", e);
        }
        return new ToolTips(tip.toString());
    }

    public String toString() {
        return tooltip;
    }
}
