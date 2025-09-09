/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.util.Map;

/**
 * <p>The abstract class from which all expression types
 * derive.
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public abstract class Expression {
    //-------------------------------------
    // Member variables
    //-------------------------------------

    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public abstract String getExpressionString();

    //-------------------------------------

    /**
     * Evaluates the expression in the given context
     */
    public abstract Object evaluate(Object pContext,
                                    VariableResolver pResolver,
                                    Map functions,
                                    String defaultPrefix,
                                    Logger pLogger)
            throws ELException;

    //-------------------------------------

}
