/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

/**
 * <p>An expression representing a floating point literal value.  The
 * value is stored internally as a double.
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class FloatingPointLiteral
        extends Literal {
    //-------------------------------------

    /**
     * Constructor
     */
    public FloatingPointLiteral(String pToken) {
        super(getValueFromToken(pToken));
    }

    //-------------------------------------

    /**
     * Parses the given token into the literal value
     */
    static Object getValueFromToken(String pToken) {
        return new Double(pToken);
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        return getValue().toString();
    }

    //-------------------------------------
}
