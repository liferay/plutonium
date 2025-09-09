/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>This is the superclass for all binary arithmetic operators
 *
 * @author Nathan Abramson - Art Technology Group
 */

public abstract class ArithmeticOperator
        extends BinaryOperator {
    //-------------------------------------

    /**
     * Applies the operator to the given value
     */
    public Object apply(Object pLeft,
                        Object pRight,
                        Object pContext,
                        Logger pLogger)
            throws ELException {
        return Coercions.applyArithmeticOperator(pLeft, pRight, this, pLogger);
    }

    //-------------------------------------

    /**
     * Applies the operator to the given double values, returning a double
     */
    public abstract double apply(double pLeft,
                                 double pRight,
                                 Logger pLogger);

    //-------------------------------------

    /**
     * Applies the operator to the given double values, returning a double
     */
    public abstract long apply(long pLeft,
                               long pRight,
                               Logger pLogger);

    //-------------------------------------
}
