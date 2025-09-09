/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>The implementation of the not operator
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class NotOperator
        extends UnaryOperator {
    //-------------------------------------
    // Singleton
    //-------------------------------------

    public static final NotOperator SINGLETON =
            new NotOperator();

    //-------------------------------------

    /**
     * Constructor
     */
    public NotOperator() {
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the symbol representing the operator
     */
    public String getOperatorSymbol() {
        return "not";
    }

    //-------------------------------------

    /**
     * Applies the operator to the given value
     */
    public Object apply(Object pValue,
                        Object pContext,
                        Logger pLogger)
            throws ELException {
        // Coerce the value to a boolean
        boolean val = Coercions.coerceToBoolean(pValue, pLogger).booleanValue();

        return PrimitiveObjects.getBoolean(!val);
    }

    //-------------------------------------
}
