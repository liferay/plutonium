/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.util.Map;

/**
 * <p>An expression representing a literal value
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public abstract class Literal
        extends Expression {
    //-------------------------------------
    // Properties
    //-------------------------------------
    // property value

    Object mValue;

    public Object getValue() {
        return mValue;
    }

    public void setValue(Object pValue) {
        mValue = pValue;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public Literal(Object pValue) {
        mValue = pValue;
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Evaluates to the literal value
     */
    public Object evaluate(Object pContext,
                           VariableResolver pResolver,
                           Map functions,
                           String defaultPrefix,
                           Logger pLogger)
            throws ELException {
        return mValue;
    }

    //-------------------------------------
}
