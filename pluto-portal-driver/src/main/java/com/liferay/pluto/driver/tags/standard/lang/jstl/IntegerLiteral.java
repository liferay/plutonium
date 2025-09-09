/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>An expression representing an integer literal value.  The value
 * is stored internally as a long.
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class IntegerLiteral
        extends Literal {
    //-------------------------------------

    /**
     * Constructor
     */
    public IntegerLiteral(String pToken) {
        super(getValueFromToken(pToken));
    }

    //-------------------------------------

    /**
     * Parses the given token into the literal value
     */
    static Object getValueFromToken(String pToken) {
        return new Long(pToken);
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
