/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>The implementation of the plus operator
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class PlusOperator
        extends ArithmeticOperator {
    //-------------------------------------
    // Singleton
    //-------------------------------------

    public static final PlusOperator SINGLETON =
            new PlusOperator();

    //-------------------------------------

    /**
     * Constructor
     */
    public PlusOperator() {
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the symbol representing the operator
     */
    public String getOperatorSymbol() {
        return "+";
    }

    //-------------------------------------

    /**
     * Applies the operator to the given double values, returning a double
     */
    public double apply(double pLeft,
                        double pRight,
                        Logger pLogger) {
        return pLeft + pRight;
    }

    //-------------------------------------

    /**
     * Applies the operator to the given double values, returning a double
     */
    public long apply(long pLeft,
                      long pRight,
                      Logger pLogger) {
        return pLeft + pRight;
    }

    //-------------------------------------
}
