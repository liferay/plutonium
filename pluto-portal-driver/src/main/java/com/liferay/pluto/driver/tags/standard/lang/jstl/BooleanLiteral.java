/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>An expression representing a boolean literal value
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class BooleanLiteral
        extends Literal {
    //-------------------------------------
    // Member variables
    //-------------------------------------

    public static final BooleanLiteral TRUE = new BooleanLiteral("true");
    public static final BooleanLiteral FALSE = new BooleanLiteral("false");

    //-------------------------------------

    /**
     * Constructor
     */
    public BooleanLiteral(String pToken) {
        super(getValueFromToken(pToken));
    }

    //-------------------------------------

    /**
     * Parses the given token into the literal value
     */
    static Object getValueFromToken(String pToken) {
        return
                ("true".equals(pToken)) ?
                        Boolean.TRUE :
                        Boolean.FALSE;
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        return (getValue() == Boolean.TRUE) ? "true" : "false";
    }

    //-------------------------------------
}
