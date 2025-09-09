/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>The implementation of the divide operator
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class DivideOperator
        extends BinaryOperator {
    //-------------------------------------
    // Singleton
    //-------------------------------------

    public static final DivideOperator SINGLETON =
            new DivideOperator();

    //-------------------------------------

    /**
     * Constructor
     */
    public DivideOperator() {
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the symbol representing the operator
     */
    public String getOperatorSymbol() {
        return "/";
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
        if (pLeft == null &&
                pRight == null) {
            if (pLogger.isLoggingWarning()) {
                pLogger.logWarning
                        (Constants.ARITH_OP_NULL,
                                getOperatorSymbol());
            }
            return PrimitiveObjects.getInteger(0);
        }

        double left =
                Coercions.coerceToPrimitiveNumber(pLeft, Double.class, pLogger).
                        doubleValue();
        double right =
                Coercions.coerceToPrimitiveNumber(pRight, Double.class, pLogger).
                        doubleValue();

        try {
            return PrimitiveObjects.getDouble(left / right);
        }
        catch (Exception exc) {
            if (pLogger.isLoggingError()) {
                pLogger.logError
                        (Constants.ARITH_ERROR,
                                getOperatorSymbol(),
                                "" + left,
                                "" + right);
            }
            return PrimitiveObjects.getInteger(0);
        }
    }

    //-------------------------------------
}
