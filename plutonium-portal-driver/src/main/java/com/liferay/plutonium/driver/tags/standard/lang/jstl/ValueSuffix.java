/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.util.Map;

/**
 * <p>Represents an element that can appear as a suffix in a complex
 * value, such as a property or index operator, or a method call (should
 * they ever need to be supported).
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public abstract class ValueSuffix {
    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public abstract String getExpressionString();

    //-------------------------------------

    /**
     * Evaluates the expression in the given context, operating on the
     * given value.
     */
    public abstract Object evaluate(Object pValue,
                                    Object pContext,
                                    VariableResolver pResolver,
                                    Map functions,
                                    String defaultPrefix,
                                    Logger pLogger)
            throws ELException;

    //-------------------------------------
}
