/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

/**
 * <p>The implementation of the not equals operator
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class NotEqualsOperator
        extends EqualityOperator {
    //-------------------------------------
    // Singleton
    //-------------------------------------

    public static final NotEqualsOperator SINGLETON =
            new NotEqualsOperator();

    //-------------------------------------

    /**
     * Constructor
     */
    public NotEqualsOperator() {
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the symbol representing the operator
     */
    public String getOperatorSymbol() {
        return "!=";
    }

    //-------------------------------------

    /**
     * Applies the operator given the fact that the two elements are
     * equal.
     */
    public boolean apply(boolean pAreEqual,
                         Logger pLogger) {
        return !pAreEqual;
    }

    //-------------------------------------
}
