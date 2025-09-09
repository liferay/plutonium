/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>This is the superclass for all binary operators
 *
 * @author Nathan Abramson - Art Technology Group
 */

public abstract class BinaryOperator {
    //-------------------------------------

    /**
     * Constructor
     */
    public BinaryOperator() {
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
     * Applies the operator to the given pair of values
     */
    public abstract Object apply(Object pLeft,
                                 Object pRight,
                                 Object pContext,
                                 Logger pLogger)
            throws ELException;

    //-------------------------------------

    /**
     * Returns true if evaluation is necessary given the specified Left
     * value.  The And/OrOperators make use of this
     */
    public boolean shouldEvaluate(Object pLeft) {
        return true;
    }

    //-------------------------------------

    /**
     * Returns true if the operator expects its arguments to be coerced
     * to Booleans.  The And/Or operators set this to true.
     */
    public boolean shouldCoerceToBoolean() {
        return false;
    }

    //-------------------------------------
}
