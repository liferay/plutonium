/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.util.Map;

/**
 * <p>Represents an operator that obtains the value of another value's
 * property.  This is a specialization of ArraySuffix - a.b is
 * equivalent to a["b"]
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public class PropertySuffix
        extends ArraySuffix {
    //-------------------------------------
    // Properties
    //-------------------------------------
    // property name

    String mName;

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public PropertySuffix(String pName) {
        super(null);
        mName = pName;
    }

    //-------------------------------------

    /**
     * Gets the value of the index
     */
    Object evaluateIndex(Object pContext,
                         VariableResolver pResolver,
                         Map functions,
                         String defaultPrefix,
                         Logger pLogger)
            throws ELException {
        return mName;
    }

    //-------------------------------------

    /**
     * Returns the operator symbol
     */
    String getOperatorSymbol() {
        return ".";
    }

    //-------------------------------------
    // ValueSuffix methods
    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        return "." + StringLiteral.toIdentifierToken(mName);
    }

    //-------------------------------------
}
