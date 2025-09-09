/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

/**
 * <p>This is the superclass for all relational operators (except ==
 * or !=)
 *
 * @author Nathan Abramson - Art Technology Group
 */

public abstract class RelationalOperator
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
        return Coercions.applyRelationalOperator(pLeft, pRight, this, pLogger);
    }

    //-------------------------------------

    /**
     * Applies the operator to the given double values
     */
    public abstract boolean apply(double pLeft,
                                  double pRight,
                                  Logger pLogger);

    //-------------------------------------

    /**
     * Applies the operator to the given long values
     */
    public abstract boolean apply(long pLeft,
                                  long pRight,
                                  Logger pLogger);

    //-------------------------------------

    /**
     * Applies the operator to the given String values
     */
    public abstract boolean apply(String pLeft,
                                  String pRight,
                                  Logger pLogger);

    //-------------------------------------
}
