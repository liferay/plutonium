/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.Tag;

/**
 * <p>The interface for an expression-language validator and evaluator.
 * Classes that implement an expression language expose their functionality
 * via this interface.</p>
 * <p>The validate() and evaluate() methods must be thread-safe.  That is,
 * multiple threads may call these methods on the same ExpressionEvaluator
 * object simultaneously.  Implementations should synchronize access if
 * they depend on transient state.  Implementations should not, however,
 * assume that only one object of each ExpressionEvaluator type will be
 * instantiated; global caching should therefore be static.  No release()
 * mechanism or robust lifecycle is specified, for language-interpreter
 * pluggability is experimental in EA2.</p>
 * <p><b>WARNING</b>:  This class supports experimentation for the EA2
 * release of JSTL; it is not expected to be part of the final RI or
 * specification.</p>
 *
 * @author Shawn Bayern (based exactly on rev1 draft)
 */
public interface ExpressionEvaluator {

    /**
     * Translation time validation of an expression.
     * This method will return a null String if the expression is valid; otherwise an error message.
     */
    public String validate(String attributeName,
                           String expression);

    /**
     * Evaluates the expression at request time.
     */
    public Object evaluate(String attributeName,
                           String expression,
                           Class expectedType,
                           Tag tag,
                           PageContext pageContext)
            throws JspException;
} 
