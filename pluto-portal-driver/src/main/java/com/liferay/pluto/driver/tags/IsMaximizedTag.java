/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags;

import java.util.Iterator;
import java.util.Map;

import jakarta.portlet.WindowState;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import com.liferay.pluto.driver.core.PortalRequestContext;
import com.liferay.pluto.driver.url.PortalURL;

/**
 * Determine whether the one of the portlets
 * for the given url is maximized.
 */
public class IsMaximizedTag extends TagSupport {

    private String var;

    public int doStartTag() throws JspException {
        PortalRequestContext portalEnv = PortalRequestContext.getContext(
                (HttpServletRequest) pageContext.getRequest());

        PortalURL portalURL = portalEnv.getRequestedPortalURL();

        // Check if someone else is maximized. If yes, don't show content.
        Map windowStates = portalURL.getWindowStates();
        for (Iterator it = windowStates.values().iterator(); it.hasNext();) {
            WindowState windowState = (WindowState) it.next();
            if (WindowState.MAXIMIZED.equals(windowState)) {
                pageContext.setAttribute(var, Boolean.TRUE);
                break;
            }
        }
        return SKIP_BODY;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

}
