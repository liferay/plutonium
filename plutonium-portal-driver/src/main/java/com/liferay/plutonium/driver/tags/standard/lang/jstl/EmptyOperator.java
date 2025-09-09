/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;


import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;


/**
 * <p>The implementation of the empty operator
 *
 * @author Nathan Abramson - Art Technology Group
 */


public class EmptyOperator

        extends UnaryOperator

{

    //-------------------------------------

    // Singleton

    //-------------------------------------


    public static final EmptyOperator SINGLETON =

            new EmptyOperator();


    //-------------------------------------

    /**
     * Constructor
     */

    public EmptyOperator()

    {

    }


    //-------------------------------------

    // Expression methods

    //-------------------------------------

    /**
     * Returns the symbol representing the operator
     */

    public String getOperatorSymbol()

    {

        return "empty";

    }


    //-------------------------------------

    /**
     * Applies the operator to the given value
     */

    public Object apply(Object pValue,

                        Object pContext,

                        Logger pLogger)

            throws ELException

    {

        // See if the value is null

        if (pValue == null) {

            return PrimitiveObjects.getBoolean(true);

        }


        // See if the value is a zero-length String

        else if ("".equals(pValue)) {

            return PrimitiveObjects.getBoolean(true);

        }


        // See if the value is a zero-length array

        else if (pValue.getClass().isArray() &&

                Array.getLength(pValue) == 0) {

            return PrimitiveObjects.getBoolean(true);

        }


        // See if the value is an empty Collection

        else if (pValue instanceof Collection &&

                ((Collection) pValue).isEmpty()) {

            return PrimitiveObjects.getBoolean(true);

        }


        // See if the value is an empty Map

        else if (pValue instanceof Map &&

                ((Map) pValue).isEmpty()) {

            return PrimitiveObjects.getBoolean(true);

        }


        // Otherwise, not empty

        else {

            return PrimitiveObjects.getBoolean(false);

        }

    }


    //-------------------------------------

}

