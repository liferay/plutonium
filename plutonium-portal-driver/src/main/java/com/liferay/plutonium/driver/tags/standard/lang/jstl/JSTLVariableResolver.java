/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import jakarta.servlet.jsp.PageContext;

/**
 * <p>This is the JSTL-specific implementation of VariableResolver.
 * It looks up variable references in the PageContext, and also
 * recognizes references to implicit objects.
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class JSTLVariableResolver
        implements VariableResolver {
    //-------------------------------------

    /**
     * Resolves the specified variable within the given context.
     * Returns null if the variable is not found.
     */
    public Object resolveVariable(String pName,
                                  Object pContext)
            throws ELException {
        PageContext ctx = (PageContext) pContext;

        // Check for implicit objects
        if ("pageContext".equals(pName)) {
            return ctx;
        } else if ("pageScope".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getPageScopeMap();
        } else if ("requestScope".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getRequestScopeMap();
        } else if ("sessionScope".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getSessionScopeMap();
        } else if ("applicationScope".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getApplicationScopeMap();
        } else if ("param".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getParamMap();
        } else if ("paramValues".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getParamsMap();
        } else if ("header".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getHeaderMap();
        } else if ("headerValues".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getHeadersMap();
        } else if ("initParam".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getInitParamMap();
        } else if ("cookie".equals(pName)) {
            return ImplicitObjects.
                    getImplicitObjects(ctx).
                    getCookieMap();
        }

        // Otherwise, just look it up in the page context
        else {
            return ctx.findAttribute(pName);
        }
    }

    //-------------------------------------
}
