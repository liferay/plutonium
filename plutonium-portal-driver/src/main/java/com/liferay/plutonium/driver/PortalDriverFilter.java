/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.plutonium.container.PortletContainer;
import com.liferay.plutonium.container.PortletContainerException;
import com.liferay.plutonium.driver.core.PortalRequestContext;
import com.liferay.plutonium.driver.core.PortletWindowImpl;
import com.liferay.plutonium.driver.services.portal.PortletWindowConfig;
import com.liferay.plutonium.driver.url.PortalURL;
import com.liferay.plutonium.driver.url.PortalURL.URLType;

import jakarta.portlet.PortletException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The controller filter used to drive static portlet pages (see
 * <a href="http://liferay.com/plutonium/faq.html#simple-embed">
 * http://liferay.com/plutonium/faq.html#simple-embed</a> in Plutonium FAQ).
 *
 * @version 1.0
 * @since March 28, 2006
 */
public class PortalDriverFilter implements Filter {

    /**
     * Internal Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PortalDriverFilter.class);

    /**
     * The Portal Driver sServlet Context
     */
    private ServletContext servletContext;

    /**
     * The portlet container to which we
     * will forward all portlet requests.
     */
    protected PortletContainer container;


    public ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * Initialize the Portal Driver.
     * This method retrieves the portlet container instance
     * from the servlet context scope.
     *
     * @see com.liferay.plutonium.container.PortletContainer
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        container = (PortletContainer) servletContext.getAttribute(
            AttributeKeys.PORTLET_CONTAINER);
    }


    /**
     * Release the container and the context.
     */
    public void destroy() {
        container = null;
        servletContext = null;
    }

    /**
     * Intercept requests in order to perform any actions.
     *
     * @param request  the incoming ServletRequest.
     * @param response the incoming ServletResponse.
     * @throws jakarta.servlet.ServletException if an internal error occurs.
     * @throws java.io.IOException            if an error occurs writing to the response.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            // Since we must support a 2.3 environment, we can't use
            //  filter dispatchers.  B/C of this, we make sure we haven't
            //  allready processed this request. No infinite loops for us!!!!
            if (PortalRequestContext.getContext(req) == null) {
                boolean actionRequestProcessed = 
                	doPortletPrepare(req, (HttpServletResponse) response);

                if (actionRequestProcessed) {
                    return;
                }

            }

            String path = req.getServletPath();
            int idx = path.indexOf(".jsp");
            if (!path.endsWith(".jsp") && idx > 0) {
                String realPath = path.substring(0, idx + ".jsp".length());
                if (realPath.startsWith(req.getContextPath())) {
                    realPath = realPath.substring(req.getContextPath().length());
                }
                LOG.info("Forwarding to realPath: " + realPath);
                request.getRequestDispatcher(realPath).forward(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Perform Portlet Preparation
     *
     * @param request
     * @param response
     * @throws java.io.IOException            if an io exception occurs
     * @throws jakarta.servlet.ServletException if a servlet exception occurs
     * @return A boolean flag indicating whether or not an action request was
     * processed. A value of true indicates than an action request was 
     * processed while a value of false indicates that an action request was
     * NOT processed.
     */
    public boolean doPortletPrepare(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

    	boolean actionRequestProcessed = false;
    	
        PortalRequestContext portalRequestContext =
            new PortalRequestContext(getServletContext(), request, response);

        PortalURL portalURL = portalRequestContext.getRequestedPortalURL();

        PortletWindowConfig actionWindowConfig = null;
        if (portalURL.getType() == URLType.Action) {
           actionWindowConfig = PortletWindowConfig.fromId(portalURL.getTargetWindow());
        }

        // Action window config will only exist if there is an action request.
        if (actionWindowConfig != null) {
            PortletWindowImpl portletWindow = new PortletWindowImpl(container,
                actionWindowConfig, portalURL);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Processing action request for window: "
                    + portletWindow.getId().getStringId());
            }
            try {
                container.doAction(portletWindow, request, response, true);
            } catch (PortletContainerException ex) {
                throw new ServletException(ex);
            } catch (PortletException ex) {
                throw new ServletException(ex);
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("Action request processed.\n\n");
            }
            
            actionRequestProcessed = true;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Render Path: " + portalURL.getRenderPath());
            LOG.debug("Servlet Path: " + portalURL.getServletPath());        	
        }

        return actionRequestProcessed;
    }
}
