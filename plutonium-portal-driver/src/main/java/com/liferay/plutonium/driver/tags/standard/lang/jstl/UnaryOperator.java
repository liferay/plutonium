/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

/**
 * <p>This is the superclass for all unary operators
 *
 * @author Nathan Abramson - Art Technology Group
 */

public abstract class UnaryOperator {
    //-------------------------------------

    /**
     * Constructor
     */
    public UnaryOperator() {
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the symbol representing the operator
     */
    public abstract String getOperatorSymbol();

    //-------------------------------------

    /**
     * Applies the operator to the given value
     */
    public abstract Object apply(Object pValue,
                                 Object pContext,
                                 Logger pLogger)
            throws ELException;

    //-------------------------------------
}
