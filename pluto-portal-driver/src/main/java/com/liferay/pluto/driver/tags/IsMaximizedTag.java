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
