/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.core;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.plutonium.driver.AttributeKeys;
import com.liferay.plutonium.driver.config.DriverConfiguration;
import com.liferay.plutonium.driver.url.PortalURL;
import com.liferay.plutonium.driver.url.PortalURLParser;

/**
 * Defines the context of the currentl portal request.
 * Allows for the retrieval of the original request
 * and response throughout the lifetime of the request.
 *
 * Provides a consistent interface for parsing/creating
 * PortalURLs to the outside world.
 *
 */
public class PortalRequestContext {

    /** Internal Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(PortalRequestContext.class);

    /**
     * The attribute key to bind the portal environment instance to servlet
     * request.
     */
    private final static String REQUEST_KEY =
            PortalRequestContext.class.getName();

    /** The servletContext of execution. **/
    private ServletContext servletContext;

    /** The incoming servlet request. */
    private HttpServletRequest request;

    /** The incoming servlet response. */
    private HttpServletResponse response;

    /** The requested portal URL. */
    private PortalURL requestedPortalURL;


    // Constructor -------------------------------------------------------------

    /**
     * Creates a PortalRequestContext instance.
     * @param request  the incoming servlet request.
     * @param response  the incoming servlet response.
     */
    public PortalRequestContext(ServletContext servletContext,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        this.servletContext = servletContext;
        this.request = request;
        this.response = response;

        // Bind the instance to servlet request for later use.
        request.setAttribute(REQUEST_KEY, this);
    }

    /**
     * Returns the portal environment from the servlet request. The portal
     * envirionment instance is saved in the request scope.
     * @param request  the servlet request.
     * @return the portal environment.
     */
    public static PortalRequestContext getContext(
            HttpServletRequest request) {
        return (PortalRequestContext) request.getAttribute(REQUEST_KEY);
    }

    /**
     * Returns the servlet request.
     * @return the servlet request.
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Returns the servlet response.
     * @return the servlet response.
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Returns the requested portal URL.
     * @return the requested portal URL.
     */
    public synchronized PortalURL getRequestedPortalURL() {
        if(requestedPortalURL == null) {
            DriverConfiguration config = (DriverConfiguration)
                servletContext.getAttribute(AttributeKeys.DRIVER_CONFIG);
            if (config != null) {
            	PortalURLParser parser = config.getPortalUrlParser();
            	requestedPortalURL = parser.parse(request);
            } else {
            	String msg = "Driver configuration not found while parsing portal URL!";
            	LOG.error(msg);
            	throw new IllegalStateException(msg);
            }
        }
        return requestedPortalURL;
    }

    public PortalURL createPortalURL() {
        return getRequestedPortalURL();
    }

    public synchronized void mergePortalURL(PortalURL portalURL, String windowId){
       requestedPortalURL = portalURL;
    }

	public ServletContext getServletContext() {
		return servletContext;
	}
}
