/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Event;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.plutonium.container.ContainerServices;
import com.liferay.plutonium.container.FilterManager;
import com.liferay.plutonium.container.HeaderData;
import com.liferay.plutonium.container.PortletActionResponseContext;
import com.liferay.plutonium.container.PortletContainer;
import com.liferay.plutonium.container.PortletContainerException;
import com.liferay.plutonium.container.PortletEnvironmentService;
import com.liferay.plutonium.container.PortletEventResponseContext;
import com.liferay.plutonium.container.PortletHeaderResponseContext;
import com.liferay.plutonium.container.PortletInvokerService;
import com.liferay.plutonium.container.PortletRenderResponseContext;
import com.liferay.plutonium.container.PortletRequestContext;
import com.liferay.plutonium.container.PortletRequestContextService;
import com.liferay.plutonium.container.PortletResourceRequestContext;
import com.liferay.plutonium.container.PortletResourceResponseContext;
import com.liferay.plutonium.container.PortletWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Default Pluto Container implementation.
 *
 * @version 1.0
 * @since Sep 18, 2004
 */
public class PortletContainerImpl implements PortletContainer
{

    /** Internal logger. */
    private static final Logger LOG = LoggerFactory.getLogger(PortletContainerImpl.class);


    // Private Member Variables ------------------------------------------------

    /** The portlet container name. */
    private final String name;

    /** The container services associated with this container. */
    private final ContainerServices containerServices;

    /** Flag indicating whether or not we've been initialized. */
    private boolean initialized = false;


    // Constructor -------------------------------------------------------------

    /** Default Constructor.  Create a container implementation
     *  whith the given name and given services.
     *
     * @param name  the name of the container.
     * @param requiredServices  the required container services implementation.
     */
    public PortletContainerImpl(String name,
            ContainerServices requiredServices) {
        this.name = name;
        this.containerServices = requiredServices;
    }


    // PortletContainer Impl ---------------------------------------------------

    /**
     * Initialize the container for use within the given configuration scope.
     */
    public void init()
    throws PortletContainerException {
        this.initialized = true;
        infoWithName("Container initialized successfully.");
    }

    /**
     * Determine whether this container has been initialized or not.
     * @return true if the container has been initialized.
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Destroy this container.
     */
    public void destroy() {
        this.initialized = false;
        infoWithName("Container destroyed.");
    }


    /**
     * Performs Header request for the portlet associated with the specified portlet window.
     * @param portletWindow  the portlet window.
     * @param request  the servlet request.
     * @param response  the servlet response.
     * @throws IllegalStateException  if the container is not initialized.
     * @throws PortletException
     * @throws IOException
     * @throws PortletContainerException
     * 
     * @see jakarta.portlet.Portlet#header(HeaderRequest, HeaderResponse)
     */
    @Override
    public HeaderData doHeader(PortletWindow portletWindow,
            HttpServletRequest request,
            HttpServletResponse response)
    throws PortletException, IOException, PortletContainerException
    {
        ensureInitialized();

        debugWithName("Header request received for portlet: "
                + portletWindow.getPortletDefinition().getPortletName());

        PortletRequestContextService rcService = getContainerServices().getPortletRequestContextService();
        PortletEnvironmentService envService = getContainerServices().getPortletEnvironmentService();
        PortletInvokerService invoker = getContainerServices().getPortletInvokerService();

        PortletRequestContext requestContext = rcService.getPortletHeaderRequestContext(this, request, response, portletWindow);
        PortletHeaderResponseContext responseContext = rcService.getPortletHeaderResponseContext(this, request, response, portletWindow, requestContext);
        responseContext.setPropsAllowed(true);
        HeaderRequest portletRequest = envService.createHeaderRequest(requestContext, responseContext);
        HeaderResponse portletResponse = envService.createHeaderResponse(responseContext);
        HeaderData headerData = new HeaderData();

        FilterManager filterManager = filterInitialisation(portletWindow,PortletRequest.HEADER_PHASE);

        try
        {
            invoker.header(requestContext, portletRequest, portletResponse, filterManager);
            headerData = responseContext.getHeaderData();
            responseContext.close();
        }
        finally
        {
            responseContext.release();
        }

        debugWithName("Portlet header done for: " + portletWindow.getPortletDefinition().getPortletName());
        return headerData;
    }


    /**
     * Renders the portlet associated with the specified portlet window.
     * @param portletWindow  the portlet window.
     * @param request  the servlet request.
     * @param response  the servlet response.
     * @throws IllegalStateException  if the container is not initialized.
     * @throws PortletException
     * @throws IOException
     * @throws PortletContainerException
     * 
     * @see jakarta.portlet.Portlet#render(RenderRequest, RenderResponse)
     */
    @Override
    public HeaderData doRender(PortletWindow portletWindow,
            HttpServletRequest request,
            HttpServletResponse response,
            String renderHeaders)
    throws PortletException, IOException, PortletContainerException
    {
        ensureInitialized();

        debugWithName("Render request received for portlet: "
                + portletWindow.getPortletDefinition().getPortletName());

        PortletRequestContextService rcService = getContainerServices().getPortletRequestContextService();
        PortletEnvironmentService envService = getContainerServices().getPortletEnvironmentService();
        PortletInvokerService invoker = getContainerServices().getPortletInvokerService();

        PortletRequestContext requestContext = rcService.getPortletRenderRequestContext(this, request, response, portletWindow);
        PortletRenderResponseContext responseContext = rcService.getPortletRenderResponseContext(this, request, response, portletWindow, requestContext);

        if (renderHeaders != null && renderHeaders.equals(PortletRequest.RENDER_HEADERS)) {
           responseContext.setPropsAllowed(true);
        } else {
           responseContext.setPropsAllowed(false);
        }
        requestContext.setRenderHeaders(renderHeaders);
        HeaderData headerData = new HeaderData();

        RenderRequest portletRequest = envService.createRenderRequest(requestContext, responseContext);
        RenderResponse portletResponse = envService.createRenderResponse(responseContext);

        FilterManager filterManager = filterInitialisation(portletWindow,PortletRequest.RENDER_PHASE);

        try
        {
            invoker.render(requestContext, portletRequest, portletResponse, filterManager);
            headerData = responseContext.getHeaderData();
            responseContext.close();
        }
        finally
        {
            responseContext.release();
        }

        debugWithName("Portlet render done for: " + portletWindow.getPortletDefinition().getPortletName());
        return headerData;
    }

    /**
     * Indicates that a portlet resource Serving occured in the current request and calls
     * the processServeResource method of this portlet.
     * @param portletWindow the portlet Window
     * @param request               the servlet request
     * @param response              the servlet response
     * @throws PortletException          if one portlet has trouble fulfilling
     *                                   the request
     * @throws PortletContainerException if the portlet container implementation
     *                                   has trouble fulfilling the request
     */
    public void doServeResource(PortletWindow portletWindow,
            HttpServletRequest request,
            HttpServletResponse response)
    throws PortletException, IOException, PortletContainerException
    {
        ensureInitialized();

        debugWithName("Resource request received for portlet: "
                + portletWindow.getPortletDefinition().getPortletName());

        PortletRequestContextService rcService = getContainerServices().getPortletRequestContextService();
        PortletEnvironmentService envService = getContainerServices().getPortletEnvironmentService();
        PortletInvokerService invoker = getContainerServices().getPortletInvokerService();

        PortletResourceRequestContext requestContext = rcService.getPortletResourceRequestContext(this, request, response, portletWindow);
        PortletResourceResponseContext responseContext = rcService.getPortletResourceResponseContext(this, request, response, portletWindow, requestContext);
        responseContext.setPropsAllowed(true);
        ResourceRequest portletRequest = envService.createResourceRequest(requestContext, responseContext);
        ResourceResponse portletResponse = envService.createResourceResponse(responseContext, requestContext.getCacheability());
        requestContext.setResponse(portletResponse);     // for async support

        FilterManager filterManager = filterInitialisation(portletWindow,PortletRequest.RESOURCE_PHASE);
        
        try
        {
           invoker.serveResource(requestContext, portletRequest, portletResponse, filterManager);
        }
        finally
        {
            if (!request.isAsyncSupported() || !request.isAsyncStarted()) {
                // Mark portlet interaction is completed: backend implementation can flush response state now
                responseContext.close();
                responseContext.release();
            } else {
               LOG.debug("Async started for resource request. responseContext not released.");
            }
        }

        debugWithName("Portlet resource done for: " + portletWindow.getPortletDefinition().getPortletName());
    }

    /**
     * Process action for the portlet associated with the given portlet window.
     * @param portletWindow  the portlet window.
     * @param request      the servlet request.
     * @param response     the servlet response.
     * @param isRedirect   Flag indicating whether redirect is to be performed. 
     *                     should be true for Action request and false for Ajax Action or 
     *                     Partial Action requests.
     * @throws PortletException
     * @throws IOException
     * @throws PortletContainerException
     * 
     * @see jakarta.portlet.Portlet#processAction(ActionRequest, ActionResponse)
     */
    public void doAction(PortletWindow portletWindow,
            HttpServletRequest request,
            HttpServletResponse response,
            boolean isRedirect)
    throws PortletException, IOException, PortletContainerException
    {
        ensureInitialized();

        debugWithName("Action request received for portlet: "
                + portletWindow.getPortletDefinition().getPortletName());

        PortletRequestContextService rcService = getContainerServices().getPortletRequestContextService();
        PortletEnvironmentService envService = getContainerServices().getPortletEnvironmentService();
        PortletInvokerService invoker = getContainerServices().getPortletInvokerService();

        PortletRequestContext requestContext = rcService.getPortletActionRequestContext(this, request, response, portletWindow);
        PortletActionResponseContext responseContext = rcService.getPortletActionResponseContext(this, request, response, portletWindow, requestContext);
        responseContext.setPropsAllowed(true);
        ActionRequest portletRequest = envService.createActionRequest(requestContext, responseContext);
        ActionResponse portletResponse = envService.createActionResponse(responseContext);

        FilterManager filterManager = filterInitialisation(portletWindow,PortletRequest.ACTION_PHASE);

        String location = null;
        String logtxt = "Portlet action";
        if (!isRedirect) {
           logtxt = "Portlet Ajax or Partial action";
        }

        try
        {
            invoker.action(requestContext, portletRequest, portletResponse, filterManager);

            debugWithName(logtxt + " processed for: "
                    + portletWindow.getPortletDefinition().getPortletName());

            // add http headers to response
            responseContext.processHttpHeaders();
            
            // Mark portlet interaction is completed: backend implementation can flush response state now
            responseContext.close();

            if (!responseContext.isRedirect())
            {
                List<Event> events = responseContext.getEvents();
                if (!events.isEmpty())
                {
                    getContainerServices().getEventCoordinationService().processEvents(this, portletWindow, request, response, events);
                }
            }
        } catch (Throwable t) {
           
           // Throw away events and parameters that were set
           
           responseContext.reset();
           
           // just swallow the exception, ignoring changes to the response
           
           StringBuilder txt = new StringBuilder(128);
           txt.append("Exception during action request processing. Exception: ");
           
           StringWriter sw = new StringWriter();
           PrintWriter pw = new PrintWriter(sw);
           t.printStackTrace(pw);
           pw.flush();
           txt.append(sw.toString());
           
           LOG.warn(txt.toString());

        }
        finally
        {

            // After processing action and possible event handling, retrieve the target response URL to be redirected to
            // This can either be a renderURL or an external URL (optionally containing a future renderURL as query parameter
            location = response.encodeRedirectURL(responseContext.getResponseURL());
   
            responseContext.release();
        }
        if (isRedirect) {
           redirect(request, response, location);
        }

        debugWithName(logtxt + " done for: " + portletWindow.getPortletDefinition().getPortletName());
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String location) throws IOException
    {
        // Here we intentionally use the original response
        // instead of the wrapped internal response.
        response.sendRedirect(location);
        debugWithName("Redirect URL sent.");
    }

    /**
     * Loads the portlet associated with the specified portlet window.
     * @param portletWindow  the portlet window.
     * @param request  the servlet request.
     * @param response  the servlet response.
     * @throws PortletException
     * @throws IOException
     * @throws PortletContainerException
     */
    public void doLoad(PortletWindow portletWindow,
            HttpServletRequest request,
            HttpServletResponse response)
    throws PortletException, IOException, PortletContainerException
    {
        ensureInitialized();

        debugWithName("Load request received for portlet: "
                + portletWindow.getPortletDefinition().getPortletName());

        PortletRequestContextService rcService = getContainerServices().getPortletRequestContextService();
        PortletEnvironmentService envService = getContainerServices().getPortletEnvironmentService();
        PortletInvokerService invoker = getContainerServices().getPortletInvokerService();

        PortletRequestContext requestContext = rcService.getPortletRenderRequestContext(this, request, response, portletWindow);
        PortletRenderResponseContext responseContext = rcService.getPortletRenderResponseContext(this, request, response, portletWindow, requestContext);
        RenderRequest portletRequest = envService.createRenderRequest(requestContext, responseContext);
        RenderResponse portletResponse = envService.createRenderResponse(responseContext);

        try
        {
            invoker.load(requestContext, portletRequest, portletResponse);
            // Mark portlet interaction is completed: backend implementation can flush response state now
            responseContext.close();
        }
        finally
        {
            responseContext.release();
        }

        debugWithName("Portlet load done for: " + portletWindow.getPortletDefinition().getPortletName());
    }


    public void doAdmin(PortletWindow portletWindow,
            HttpServletRequest request,
            HttpServletResponse response)
    throws PortletException, IOException, PortletContainerException
    {
        ensureInitialized();

        debugWithName("Admin request received for portlet: "
                +portletWindow.getPortletDefinition().getPortletName());

        PortletRequestContextService rcService = getContainerServices().getPortletRequestContextService();
        PortletEnvironmentService envService = getContainerServices().getPortletEnvironmentService();
        PortletInvokerService invoker = getContainerServices().getPortletInvokerService();

        PortletRequestContext requestContext = rcService.getPortletRenderRequestContext(this, request, response, portletWindow);
        PortletRenderResponseContext responseContext = rcService.getPortletRenderResponseContext(this, request, response, portletWindow, requestContext);
        RenderRequest portletRequest = envService.createRenderRequest(requestContext, responseContext);
        RenderResponse portletResponse = envService.createRenderResponse(responseContext);

        try
        {
            invoker.admin(requestContext, portletRequest, portletResponse);
            // Mark portlet interaction is completed: backend implementation can flush response state now
            responseContext.close();
        }
        finally
        {
            responseContext.release();
        }

        debugWithName("Portlet admin request done for: " + portletWindow.getPortletDefinition().getPortletName());
    }


    /**
     * @see com.liferay.plutonium.container.PortletContainer#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * @see com.liferay.plutonium.container.PortletContainer#getContainerServices()
     */
    public ContainerServices getContainerServices() {
        return containerServices;
    }

    /**
     * Fire Event for the portlet associated with the given portlet window and eventName
     * @param portletWindow  the portlet window.
     * @param request  the servlet request.
     * @param response  the servlet response.
     * @param event the event
     * @throws PortletException
     * @throws IOException
     * @throws PortletContainerException
     * 
     * @see jakarta.portlet.EventPortlet#processEvent(jakarta.portlet.EventRequest, jakarta.portlet.EventResponse)
     */
    public void doEvent(PortletWindow portletWindow,
            HttpServletRequest request,
            HttpServletResponse response,
            Event event)
    throws PortletException, IOException, PortletContainerException
    {
        ensureInitialized();

        debugWithName("Event: "+event.getName()+" received for portlet: "
                + portletWindow.getPortletDefinition().getPortletName());

        PortletRequestContextService rcService = getContainerServices().getPortletRequestContextService();
        PortletEnvironmentService envService = getContainerServices().getPortletEnvironmentService();
        PortletInvokerService invoker = getContainerServices().getPortletInvokerService();

        PortletRequestContext requestContext = rcService.getPortletEventRequestContext(this, request, response, portletWindow);
        PortletEventResponseContext responseContext = rcService.getPortletEventResponseContext(this, request, response, portletWindow, requestContext);
        responseContext.setPropsAllowed(true);
        EventRequest portletRequest = envService.createEventRequest(requestContext, responseContext, event);
        EventResponse portletResponse = envService.createEventResponse(responseContext);

        FilterManager filterManager = filterInitialisation(portletWindow,PortletRequest.EVENT_PHASE);

        List<Event> events = null;
        try
        {
            invoker.event(requestContext, portletRequest, portletResponse, filterManager);

            debugWithName("Portlet event processed for: "
                    + portletWindow.getPortletDefinition().getPortletName());

            // add http headers to response
            responseContext.processHttpHeaders();

            // Mark portlet interaction is completed: backend implementation can flush response state now
            responseContext.close();
            events = responseContext.getEvents();
        } catch (Throwable t) {
           
           // Throw away events and parameters that were set
           
           responseContext.reset();
           
           // just swallow the exception, ignoring changes to the response
           
           StringBuilder txt = new StringBuilder(128);
           txt.append("Exception during action request processing. Exception: ");
           
           StringWriter sw = new StringWriter();
           PrintWriter pw = new PrintWriter(sw);
           t.printStackTrace(pw);
           pw.flush();
           txt.append(sw.toString());
           
           LOG.warn(txt.toString());

        }
        finally
        {
            responseContext.release();
        }

        if (events != null && !events.isEmpty())
        {
            getContainerServices().getEventCoordinationService().processEvents(this, portletWindow, request, response, events);
        }

        debugWithName("Portlet event: "+ event.getName() +" fired for: " + portletWindow.getPortletDefinition().getPortletName());
    }

    // Private Methods ---------------------------------------------------------

    /**
     * Ensures that the portlet container is initialized.
     * @throws IllegalStateException  if the container is not initialized.
     */
    private void ensureInitialized() throws IllegalStateException {
        if (!isInitialized()) {
            throw new IllegalStateException(
                    "Portlet container [" + name + "] is not initialized.");
        }
    }

    /**
     * Prints a message at DEBUG level with the container name prefix.
     * @param message  log message.
     */
    private void debugWithName(String message) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Portlet Container [" + name + "]: " + message);
        }
    }

    /**
     * Prints a message at INFO level with the container name prefix.
     * @param message  log message.
     */
    private void infoWithName(String message) {
        if (LOG.isInfoEnabled()) {
            LOG.info("Portlet Container [" + name + "]: " + message);
        }
    }

    /**
     * The method initialise the FilterManager for later use in the PortletServlet
     * @param portletWindow the PortletWindow
     * @param lifeCycle like ACTION_PHASE, RENDER_PHASE,...
     * @return FilterManager
     * @throws PortletContainerException
     */
    private FilterManager filterInitialisation(PortletWindow portletWindow, String lifeCycle) throws PortletContainerException{
        return getContainerServices().getFilterManagerService().getFilterManager(portletWindow, lifeCycle);
    }
}

