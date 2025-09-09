/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

/**
 * <p>The implementation of the or operator
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class OrOperator
        extends BinaryOperator {
    //-------------------------------------
    // Singleton
    //-------------------------------------

    public static final OrOperator SINGLETON =
            new OrOperator();

    //-------------------------------------

    /**
     * Constructor
     */
    public OrOperator() {
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the symbol representing the operator
     */
    public String getOperatorSymbol() {
        return "or";
    }

    //-------------------------------------

    /**
     * Applies the operator to the given value
     */
    public Object apply(Object pLeft,
                        Object pRight,
                        Object pContext,
                        Logger pLogger)
            throws ELException {
        // Coerce the values to booleans
        boolean left =
                Coercions.coerceToBoolean(pLeft, pLogger).booleanValue();
        boolean right =
                Coercions.coerceToBoolean(pRight, pLogger).booleanValue();

        return PrimitiveObjects.getBoolean(left || right);
    }

    //-------------------------------------

    /**
     * Returns true if evaluation is necessary given the specified Left
     * value.  The And/OrOperators make use of this
     */
    public boolean shouldEvaluate(Object pLeft) {
        return
                (pLeft instanceof Boolean) &&
                        ((Boolean) pLeft).booleanValue() == false;
    }

    //-------------------------------------

    /**
     * Returns true if the operator expects its arguments to be coerced
     * to Booleans.  The And/Or operators set this to true.
     */
    public boolean shouldCoerceToBoolean() {
        return true;
    }

    //-------------------------------------
}
