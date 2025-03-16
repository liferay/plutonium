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
package org.apache.pluto.container;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Event;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletSession;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Factory Service for creating Portlet request, responses and session.
 *
 * @since 1.1.0
 */
public interface PortletEnvironmentService
{    
    ActionRequest createActionRequest(PortletRequestContext requestContext, PortletActionResponseContext responseContext);
    ActionResponse createActionResponse(PortletActionResponseContext responseContext);

    EventRequest createEventRequest(PortletRequestContext requestContext, PortletEventResponseContext responseContext, Event event);
    EventResponse createEventResponse(PortletEventResponseContext responseContext);
    
    HeaderRequest createHeaderRequest(PortletRequestContext requestContext, PortletHeaderResponseContext responseContext);
    HeaderResponse createHeaderResponse(PortletHeaderResponseContext responseContext);
    
    RenderRequest createRenderRequest(PortletRequestContext requestContext, PortletRenderResponseContext responseContext);
    RenderResponse createRenderResponse(PortletRenderResponseContext responseContext);
    
    ResourceRequest createResourceRequest(PortletResourceRequestContext requestContext, PortletResourceResponseContext responseContext);
    ResourceResponse createResourceResponse(PortletResourceResponseContext responseContext, String requestCacheLevel);

    PortletSession createPortletSession(PortletContext portletContext, PortletWindow portletWindow, HttpSession session);
}
