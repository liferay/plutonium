/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>The implementation of the less than operator
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class LessThanOperator
        extends RelationalOperator {
    //-------------------------------------
    // Singleton
    //-------------------------------------

    public static final LessThanOperator SINGLETON =
            new LessThanOperator();

    //-------------------------------------

    /**
     * Constructor
     */
    public LessThanOperator() {
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the symbol representing the operator
     */
    public String getOperatorSymbol() {
        return "<";
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
        if (pLeft == pRight) {
            return Boolean.FALSE;
        } else if (pLeft == null ||
                pRight == null) {
            return Boolean.FALSE;
        } else {
            return super.apply(pLeft, pRight, pContext, pLogger);
        }
    }

    //-------------------------------------

    /**
     * Applies the operator to the given double values
     */
    public boolean apply(double pLeft,
                         double pRight,
                         Logger pLogger) {
        return pLeft < pRight;
    }

    //-------------------------------------

    /**
     * Applies the operator to the given long values
     */
    public boolean apply(long pLeft,
                         long pRight,
                         Logger pLogger) {
        return pLeft < pRight;
    }

    //-------------------------------------

    /**
     * Applies the operator to the given String values
     */
    public boolean apply(String pLeft,
                         String pRight,
                         Logger pLogger) {
        return pLeft.compareTo(pRight) < 0;
    }

    //-------------------------------------
}
