/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;

/**
 * <p>This class is used to customize the way the evaluator resolves
 * variable references.  For example, instances of this class can
 * implement their own variable lookup mechanisms, or introduce the
 * notion of "implicit variables" which override any other variables.
 * An instance of this class should be passed to the evaluator's
 * constructor.
 * <p>Whenever the evaluator is invoked, it is passed a "context"
 * Object from the application.  For example, in a JSP environment,
 * the "context" is a PageContext.  That context object is eventually
 * passed to this class, so that this class has a context in which to
 * resolve variables.
 *
 * @author Nathan Abramson - Art Technology Group
 */
public interface VariableResolver {
    //-------------------------------------

    /**
     * Resolves the specified variable within the given context.
     * Returns null if the variable is not found.
     */
    public Object resolveVariable(String pName,
                                  Object pContext)
            throws ELException;

    //-------------------------------------
}
