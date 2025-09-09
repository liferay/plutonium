/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test.jsr286.filter;

import jakarta.portlet.PortletException;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.PortletFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides simple implementations of the PortletFilter callback methods.
 * 
 * @author bgould
 * @since 2.0
 */
public abstract class BaseFilter implements PortletFilter {

    private FilterConfig filterConfig;

    private Logger LOG = LoggerFactory.getLogger(getClass());
    
    public void destroy() {
        LOG.debug("destroy called on " + 
                filterConfig.getFilterName() + "(" + getClass() + ")");
    }

    /**
     * Stores the FilterConfig instance to be retrieved with getFilterConfig()
     */
    public void init(FilterConfig filterConfig) throws PortletException {
        LOG.debug("Initializing " + filterConfig.getFilterName() 
                + "(" + getClass() + ")");
        this.filterConfig = filterConfig;
        this.init();
    }
    
    /**
     * Called after the standard lifecycle initialization method.
     * Subclasses should override this method instead of init(FilterConfig)
     */
    protected void init() throws PortletException {
    }
    
    /**
     * Returns the FilterConfig instance.
     */
    public FilterConfig getFilterConfig() {
        return filterConfig;
    }
}
