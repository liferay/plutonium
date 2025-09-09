/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

import java.io.IOException;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventPortlet;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.HeaderPortlet;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;

/**
 * Manage the initialization and doFilter for the filter which are
 * declareted in the deployment descriptor.
 * @since 05/29/2007
 * @version 2.0
 */
public interface FilterManager {

    void processFilter(ActionRequest req, ActionResponse res, Portlet portlet, PortletContext portletContext) throws PortletException, IOException;
    void processFilter(HeaderRequest req, HeaderResponse res, HeaderPortlet portlet, PortletContext portletContext) throws PortletException, IOException;
    void processFilter(RenderRequest req, RenderResponse res, Portlet portlet, PortletContext portletContext) throws PortletException, IOException;
    void processFilter(ResourceRequest req, ResourceResponse res, ResourceServingPortlet resourceServingPortlet, PortletContext portletContext)throws PortletException, IOException;
    void processFilter(EventRequest req, EventResponse res, EventPortlet eventPortlet, PortletContext portletContext)throws PortletException, IOException;

    /**
    * To enable contextual support in filters
    */
   void setBeanManager(BeanManager bm);
}
