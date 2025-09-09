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
package com.liferay.pluto.container;

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


/**
 * Service used to invoke portlets.
 */
public interface PortletInvokerService {


   /** 
    * URI prefix of the portlet invoker servlet for generating the servlet mapping.
    */
   static final String URIPREFIX = "/PlutoInvoker3/";
         
    /**
     * The key used to bind the <code>PortletRequest</code> to the underlying
     * <code>HttpServletRequest</code>.
     */
    String PORTLET_REQUEST = "jakarta.portlet.request";

    /**
     * The key used to bind the <code>PortletResponse</code> to the underlying
     * <code>HttpServletRequest</code>.
     */
    String PORTLET_RESPONSE = "jakarta.portlet.response";

    /**
     * The key used to bind the <code>PortletConfig</code> to the underlying
     * PortletConfig.
     */
    String PORTLET_CONFIG = "jakarta.portlet.config";

    /**
     * The key used to bind the <code>MethodIdentifier</code> to the ResourceRequest
     * for asyc processing
     */
    String ASYNC_METHOD = "jakarta.portlet.asyncMethod";

    /**
     * The request attribute key used to retrieve the <code>PortletRequestContext</code> instance
     */
    String REQUEST_CONTEXT = PortletRequestContext.class.getName();

    /**
     * The request attribute key used to retrieve the <code>PortletRequestContext</code> instance
     */
    String RESPONSE_CONTEXT = PortletResponseContext.class.getName();

    /**
     * The key used to bind the method of processing being requested by the
     * container to the underlying <code>PortletRquest</code>.
     */
    String METHOD_ID = "com.liferay.pluto.core.method";

    /**
     * The unique method identifier for header requests.  Header requests are
     * requested through a call to the {@link PortletContainer#doHeader(com.liferay.pluto.container.PortletWindow,
     * jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)}
     * method.
     */
    Integer METHOD_HEADER = new Integer(101);

    /**
     * The unique method identifier for render requests.  Render requests are
     * requested through a call to the {@link PortletContainer#doRender(com.liferay.pluto.container.PortletWindow,
     * jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)}
     * method.
     */
    Integer METHOD_RENDER = new Integer(1);

    /**
     * The unique method identifier for action requests.  Action requests are
     * requested through a call to the {@link PortletContainer#doAction(com.liferay.pluto.container.PortletWindow,
     * jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)}
     * method.
     */
    Integer METHOD_ACTION = new Integer(3);

    /**
     * The unique method identifier for load requests.  Load requests are
     * requested through a call to the {@link PortletContainer#doLoad(com.liferay.pluto.container.PortletWindow,
     * jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)}
     * method.
     */
    Integer METHOD_LOAD = new Integer(5);

    /**
     * The unique method identifier for resource Serving requests.  Resource requests are
     * requested through a call to the {@link PortletContainer#doServeResource(PortletWindow,
     *  jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)}
     * method.
     */
    Integer METHOD_RESOURCE = new Integer(7);

    /**
     * The unique method identifier for render requests.  Render requests are
     * requested through a call to the {@link PortletContainer#doEvent(com.liferay.pluto.container.PortletWindow,
     * jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse, jakarta.portlet.Event)}
     * method.
     */
    Integer METHOD_EVENT = new Integer(9);

    /**
     * The unique method identifier for admin requests.  Admin requests
     * are requested through a call to the {@link PortletContainer#doAdmin(PortletWindow, jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)}
     * method.
     */
    Integer  METHOD_ADMIN = new Integer(11);

    /**
     * The public key, to store the FilterManager in the request.
     */
    String FILTER_MANAGER = "FilterManager";

    void action(PortletRequestContext ctx, ActionRequest req, ActionResponse res, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException;

    void event(PortletRequestContext ctx, EventRequest request, EventResponse response, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException;

    void render(PortletRequestContext ctx, RenderRequest req, RenderResponse res, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException;

    void header(PortletRequestContext ctx, HeaderRequest req, HeaderResponse res, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException;

    void serveResource(PortletRequestContext ctx, ResourceRequest req, ResourceResponse res, FilterManager filterManager)
    throws IOException, PortletException, PortletContainerException;

    void load(PortletRequestContext ctx, PortletRequest req, PortletResponse res)
    throws IOException, PortletException, PortletContainerException;

    void admin(PortletRequestContext ctx, PortletRequest req, PortletResponse res)
    throws IOException, PortletException, PortletContainerException;
}
