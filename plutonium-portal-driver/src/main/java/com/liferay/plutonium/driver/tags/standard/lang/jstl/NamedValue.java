/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.util.Map;

/**
 * <p>Represents a name that can be used as the first element of a
 * value.
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public class NamedValue
        extends Expression {
    //-------------------------------------
    // Constants
    //-------------------------------------

    //-------------------------------------
    // Properties
    //-------------------------------------
    // property name

    String mName;

    public String getName() {
        return mName;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public NamedValue(String pName) {
        mName = pName;
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        return StringLiteral.toIdentifierToken(mName);
    }

    //-------------------------------------

    /**
     * Evaluates by looking up the name in the VariableResolver
     */
    public Object evaluate(Object pContext,
                           VariableResolver pResolver,
                           Map functions,
                           String defaultPrefix,
                           Logger pLogger)
            throws ELException {
        if (pResolver == null) {
            return null;
        } else {
            return pResolver.resolveVariable(mName, pContext);
        }
    }

    //-------------------------------------
}
