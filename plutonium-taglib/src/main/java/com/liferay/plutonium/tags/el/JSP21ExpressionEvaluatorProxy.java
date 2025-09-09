/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.tags.el;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jakarta.servlet.ServletContext;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;

class JSP21ExpressionEvaluatorProxy extends ExpressionEvaluatorProxy {

    public static Method jspApplicationContextGetter;

    public static Method expressionFactoryGetter;

    public static Method elContextGetter;

    public static Method valueExpressionGetter;

    public static Method evalMethod;

    private static Object jspFactory;

    static {
        try {
            jspFactory = Class.forName("jakarta.servlet.jsp.JspFactory")
                .getMethod("getDefaultFactory", new Class[0]).invoke(null);
            jspApplicationContextGetter = 
                jspFactory.getClass().getMethod("getJspApplicationContext",
                    new Class[] { ServletContext.class });
            expressionFactoryGetter = 
                Class.forName("jakarta.servlet.jsp.JspApplicationContext")
                    .getMethod("getExpressionFactory", new Class[0]);
            elContextGetter = 
                PageContext.class.getMethod("getELContext", new Class[0]);
            valueExpressionGetter = 
                Class.forName("jakarta.el.ExpressionFactory").getMethod(
                    "createValueExpression", new Class[] 
                    { Class.forName("jakarta.el.ELContext"), String.class, Class.class });
            evalMethod = Class.forName("jakarta.el.ValueExpression").getMethod(
                    "getValue", new Class[] { Class.forName("jakarta.el.ELContext") });
        } catch (Exception e) {
            throw new RuntimeException("Unable to find JSP2.1 methods.", e);
        }
    }

    public String evaluate(String value, PageContext pageContext)
            throws JspException {
        try {
            Object jspApplicationContext = jspApplicationContextGetter.invoke(
                    jspFactory,
                    new Object[] { pageContext.getServletContext() });

            Object expressionFactory = expressionFactoryGetter.invoke(
                    jspApplicationContext, new Object[] {});

            Object elContext = elContextGetter.invoke(pageContext);

            Object valueExpression = valueExpressionGetter.invoke(
                    expressionFactory, new Object[] { elContext, value,
                            Object.class });

            Object evaluated = evalMethod.invoke(valueExpression,
                    new Object[] { elContext });

            if (evaluated != null) {
                value = evaluated.toString();
            }
        } catch (IllegalAccessException e) {
            throw new JspException(e);
        } catch (InvocationTargetException e) {
            throw new JspException(e);
        }
        return value;
    }

}

