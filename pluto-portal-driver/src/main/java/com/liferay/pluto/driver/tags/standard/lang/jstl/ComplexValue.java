/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

import java.util.List;
import java.util.Map;

/**
 * <p>Represents a dynamic value, which consists of a prefix and an
 * optional set of ValueSuffix elements.  A prefix is something like
 * an identifier, and a suffix is something like a "property of" or
 * "indexed element of" operator.
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public class ComplexValue
        extends Expression {
    //-------------------------------------
    // Properties
    //-------------------------------------
    // property prefix

    Expression mPrefix;

    public Expression getPrefix() {
        return mPrefix;
    }

    public void setPrefix(Expression pPrefix) {
        mPrefix = pPrefix;
    }

    //-------------------------------------
    // property suffixes

    List mSuffixes;

    public List getSuffixes() {
        return mSuffixes;
    }

    public void setSuffixes(List pSuffixes) {
        mSuffixes = pSuffixes;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public ComplexValue(Expression pPrefix,
                        List pSuffixes) {
        mPrefix = pPrefix;
        mSuffixes = pSuffixes;
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        StringBuffer buf = new StringBuffer();
        buf.append(mPrefix.getExpressionString());

        for (int i = 0; mSuffixes != null && i < mSuffixes.size(); i++) {
            ValueSuffix suffix = (ValueSuffix) mSuffixes.get(i);
            buf.append(suffix.getExpressionString());
        }

        return buf.toString();
    }

    //-------------------------------------

    /**
     * Evaluates by evaluating the prefix, then applying the suffixes
     */
    public Object evaluate(Object pContext,
                           VariableResolver pResolver,
                           Map functions,
                           String defaultPrefix,
                           Logger pLogger)
            throws ELException {
        Object ret = mPrefix.evaluate(pContext, pResolver, functions,
                defaultPrefix, pLogger);

        // Apply the suffixes
        for (int i = 0; mSuffixes != null && i < mSuffixes.size(); i++) {
            ValueSuffix suffix = (ValueSuffix) mSuffixes.get(i);
            ret = suffix.evaluate(ret, pContext, pResolver, functions,
                    defaultPrefix, pLogger);
        }

        return ret;
    }

    //-------------------------------------
}
