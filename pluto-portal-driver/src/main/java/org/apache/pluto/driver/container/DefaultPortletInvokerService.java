/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pluto.driver.container;

import java.io.IOException;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.pluto.container.FilterManager;
import org.apache.pluto.container.PortletContainerException;
import org.apache.pluto.container.PortletInvokerService;
import org.apache.pluto.container.PortletRequestContext;
import org.apache.pluto.container.PortletWindow;
import org.apache.pluto.container.driver.PortletContextService;
import org.apache.pluto.container.driver.PortletServlet3;
import org.apache.pluto.container.util.StringManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used internally to invoke/dispatch requests from the container to
 * the portlet application.
 *
 */
public class DefaultPortletInvokerService implements PortletInvokerService {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DefaultPortletInvokerService.class);

    /**
     * Exception Messages.
     */
    private static final StringManager EXCEPTIONS = StringManager.getManager(
            DefaultPortletInvokerService.class.getPackage().getName());

    // Private Member Variables ------------------------------------------------

    private PortletContextService portletContextService;

    // Constructor -------------------------------------------------------------

    /**
     * Default Constructor.  Create a new invoker which
     * is initialized for the given <code>PortletWindow</code>.
     */
    public DefaultPortletInvokerService(PortletContextService portletContextService) {
        this.portletContextService = portletContextService;
    }

    // Public Methods ----------------------------------------------------------

    /**
     * Invoke the portlet with an action request.
     *
     * @param context request context used for the invocation
     * @param request  action request used for the invocation.
     * @param response action response used for the invocation.
     * @see PortletServlet3
     * @see jakarta.portlet.Portlet#processAction(jakarta.portlet.ActionRequest,jakarta.portlet.ActionResponse)
     */
    public void action(PortletRequestContext context, ActionRequest request, ActionResponse response, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performing Action Invocation");
        }
        invoke(context, request, response, filterManager, PortletInvokerService.METHOD_ACTION);
    }

    /**
     * Invoke the portlet with a header request.
     *
     * @param request  header request used for the invocation.
     * @param response header response used for the invocation.
     * @see PortletServlet3
     * @see jakarta.portlet.Portlet#header(jakarta.portlet.headerRequest,jakarta.portlet.headerResponse)
     */
    public void header(PortletRequestContext context, HeaderRequest request, HeaderResponse response, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performing header Invocation");
        }
        invoke(context, request, response, filterManager, PortletInvokerService.METHOD_HEADER);
    }

    /**
     * Invoke the portlet with a render request.
     *
     * @param request  render request used for the invocation.
     * @param response render response used for the invocation.
     * @see PortletServlet3
     * @see jakarta.portlet.Portlet#render(jakarta.portlet.RenderRequest,jakarta.portlet.RenderResponse)
     */
    public void render(PortletRequestContext context, RenderRequest request, RenderResponse response, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performing Render Invocation");
        }
        invoke(context, request, response, filterManager, PortletInvokerService.METHOD_RENDER);
    }

    /**
     * Invoke the portlet with a render request.
     *
     * @param request  event request used for the invocation.
     * @param response event response used for the invocation.
     * @see PortletServlet3
     * @see jakarta.portlet.Portlet#render(jakarta.portlet.RenderRequest,jakarta.portlet.RenderResponse)
     */
    public void event(PortletRequestContext context, EventRequest request, EventResponse response, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performing Event Invocation");
        }
        invoke(context, request, response, filterManager, PortletInvokerService.METHOD_EVENT);
    }

    /**
     * Invoke the portlet with a resource request.
     *
     * @param request  resource request used for the invocation.
     * @param response resource response used for the invocation.
     * @see PortletServlet3
     * @see jakarta.portlet.Portlet#resource(jakarta.portlet.ResourceRequest,jakarta.portlet.ResourceResponse)
     */
    public void serveResource(PortletRequestContext context, ResourceRequest request, ResourceResponse response, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performing Resource Invocation");
        }
        invoke(context, request, response, filterManager, PortletInvokerService.METHOD_RESOURCE);
    }

    /**
     * Invoke the portlet with a load request.
     *
     * @param request  action request used for the invocation.
     * @param response action response used for the invocation.
     * @see PortletServlet3
     */
    public void load(PortletRequestContext context, PortletRequest request, PortletResponse response)
    throws IOException, PortletException, PortletContainerException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performing Load Invocation.");
        }
        invoke(context, request, response, PortletInvokerService.METHOD_LOAD);
    }

    public void admin(PortletRequestContext context, PortletRequest request, PortletResponse response)
    throws IOException, PortletException, PortletContainerException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Performing Admin Invocation.");
        }

        invoke(context, request, response, PortletInvokerService.METHOD_ADMIN);
    }

    // Private Invoke Method ---------------------------------------------------

    private final void invoke(PortletRequestContext context,
            PortletRequest request,
            PortletResponse response,
            Integer methodID)
    throws PortletException, IOException, PortletContainerException {

        invoke(context, request, response, null, methodID);
    }

    /**
     * Perform the invocation.
     *
     * @param request       portlet request
     * @param response      portlet response
     * @param portletWindow internal portlet window
     * @param methodID      method identifier
     * @throws PortletException if a portlet exception occurs.
     * @throws IOException      if an error occurs writing to the response.
     */
    protected final void invoke(PortletRequestContext context,
            PortletRequest request,
            PortletResponse response,
            FilterManager filterManager,
            Integer methodID)
    throws PortletException, IOException, PortletContainerException {

        PortletWindow portletWindow = context.getPortletWindow();
        String appName = portletWindow.getPortletDefinition().getApplication().getName();
        ServletContext servletContext = portletContextService.getPortletContext(appName).getServletContext();

        String uri =  PortletInvokerService.URIPREFIX + portletWindow.getPortletDefinition().getPortletName();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Dispatching to portlet servlet at: " + uri);
        }

        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(uri);

        if (dispatcher != null) {

            HttpServletRequest containerRequest = context.getContainerRequest();
            HttpServletResponse containerResponse = context.getContainerResponse();

            try {

                containerRequest.setAttribute(PortletInvokerService.METHOD_ID, methodID);
                containerRequest.setAttribute(PortletInvokerService.PORTLET_REQUEST, request);
                containerRequest.setAttribute(PortletInvokerService.PORTLET_RESPONSE, response);
                containerRequest.setAttribute(PortletInvokerService.FILTER_MANAGER, filterManager);

                if (methodID.equals(PortletInvokerService.METHOD_RESOURCE))
                {
                    LOG.trace("Request dispatcher forward resource request to portlet servlet.");
                    dispatcher.forward(containerRequest, containerResponse);
                    LOG.trace("Dispatch complete.");
                }
                else
                {
                    dispatcher.include(containerRequest, containerResponse);
                }


            } catch (jakarta.servlet.UnavailableException ex) {
                int seconds = ex.isPermanent() ? -1 : ex.getUnavailableSeconds();
                String message = EXCEPTIONS.getString(
                        "error.portlet.invoker.unavailable",
                        uri, new String[]{String.valueOf(seconds)}
                );
                if (LOG.isErrorEnabled()) {
                    LOG.error(message, ex);
                }
                throw new jakarta.portlet.UnavailableException(
                        message, seconds);

            } catch (jakarta.servlet.ServletException ex) {
                String message = EXCEPTIONS.getString("error.portlet.invoker");
                if (LOG.isErrorEnabled()) {
                    LOG.error(message);
                }

                if (ex.getRootCause() != null &&
                        ex.getRootCause() instanceof PortletException) {
                    throw (PortletException) ex.getRootCause();
                } else if (ex.getRootCause() != null) {
                    throw new PortletException(ex.getRootCause());
                } else {
                    throw new PortletException(ex);
                }

            } finally {
                // If async is running, resources will be released by the PortletAsyncListener
                if (!containerRequest.isAsyncSupported() || !containerRequest.isAsyncStarted()) {
                   LOG.trace("After invocation, removing attributes.");
                   containerRequest.removeAttribute(PortletInvokerService.METHOD_ID);
                   containerRequest.removeAttribute(PortletInvokerService.PORTLET_REQUEST);
                   containerRequest.removeAttribute(PortletInvokerService.PORTLET_RESPONSE);
                   containerRequest.removeAttribute(PortletInvokerService.FILTER_MANAGER);
                } else {
                   LOG.debug("After invocation, async started for resource request. attributes not removed.");
                }
            }
        } else {
            String msg = EXCEPTIONS.getString(
                    "error.portlet.invoker.dispatcher",
                    new String[]{servletContext.getServletContextName(), uri}
            );
            if (LOG.isErrorEnabled()) {
                LOG.error(msg);
            }
            throw new PortletException(msg);
        }
    }

}
