/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

/**
 * <p>An expression representing a null literal value
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class NullLiteral
        extends Literal {
    //-------------------------------------
    // Member variables
    //-------------------------------------

    public static final NullLiteral SINGLETON = new NullLiteral();

    //-------------------------------------

    /**
     * Constructor
     */
    public NullLiteral() {
        super(null);
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        return "null";
    }

    //-------------------------------------
}
