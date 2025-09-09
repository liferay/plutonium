/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags;

import java.io.IOException;

import jakarta.portlet.PortletMode;
import jakarta.portlet.WindowState;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTagSupport;
import jakarta.servlet.jsp.tagext.TagSupport;

import com.liferay.plutonium.driver.core.PortalRequestContext;
import com.liferay.plutonium.driver.url.PortalURL;

/**
 * The portlet URL tag is used to generate portal URL pointing to the current
 * portlet with specified portlet mode and window state.
 *
 * @version 1.0
 * @since Oct 4, 2004
 */
public class PortletPortalURLTag extends BodyTagSupport {

    // Private Member Variables ------------------------------------------------

    /** The window state to be encoded in the portal URL. */
    private String windowState;

    /** The portlet mode to be encoded in the portal URL. */
    private String portletMode;


    // Tag Attribute Accessors -------------------------------------------------

    public String getWindowState() {
        return windowState;
    }

    public void setWindowState(String windowState) {
        this.windowState = windowState;
    }

    public String getPortletMode() {
        return portletMode;
    }

    public void setPortletMode(String portletMode) {
        this.portletMode = portletMode;
    }


    // BodyTagSupport Impl -----------------------------------------------------

    /**
     * Creates the portal URL pointing to the current portlet with specified
     * portlet mode and window state, and print the URL to the page.
     * @see PortletTag
     */
    public int doStartTag() throws JspException {

        // Ensure that the portlet render tag resides within a portlet tag.
        PortletTag parentTag = (PortletTag) TagSupport.findAncestorWithClass(
                this, PortletTag.class);
        if (parentTag == null) {
            throw new JspException("Portlet window controls may only reside "
                    + "within a plutonium:portlet tag.");
        }

        // Create portal URL.
        HttpServletRequest request = (HttpServletRequest)
                pageContext.getRequest();

        HttpServletResponse response = (HttpServletResponse)
                pageContext.getResponse();

        PortalRequestContext ctx = PortalRequestContext.getContext(request);

        PortalURL portalUrl =  ctx.createPortalURL();

        // Encode window state of the current portlet in the portal URL.
        String portletId = parentTag.getEvaluatedPortletId();
        if (windowState != null) {
            portalUrl.setWindowState(portletId, new WindowState(windowState));
        }

        // Encode portlet mode of the current portlet in the portal URL.
        if (portletMode != null) {
            portalUrl.setPortletMode(portletId, new PortletMode(portletMode));
        }

        // Print the portal URL as a string to the page.
        try {
            pageContext.getOut().print(response.encodeURL(portalUrl.toString()));
        } catch (IOException ex) {
            throw new JspException(ex);
        }

        // Skip the tag body.
        return SKIP_BODY;
    }


}

