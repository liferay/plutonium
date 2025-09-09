/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>This is the superclass for all equality operators (==, !=)
 *
 * @author Nathan Abramson - Art Technology Group
 */

public abstract class EqualityOperator
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
        return Coercions.applyEqualityOperator(pLeft, pRight, this, pLogger);
    }

    //-------------------------------------

    /**
     * Applies the operator given the fact that the two elements are
     * equal.
     */
    public abstract boolean apply(boolean pAreEqual,
                                  Logger pLogger);

    //-------------------------------------
}
