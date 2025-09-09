/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.tags.standard.lang.jstl;


/**
 * Represents any of the exception conditions that arise during the
 * operation evaluation of the evaluator.
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class ELException
        extends Exception {
    //-------------------------------------
    // Member variables
    //-------------------------------------

    Throwable mRootCause;

    //-------------------------------------

    /**
     * Constructor
     */
    public ELException() {
        super();
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public ELException(String pMessage) {
        super(pMessage);
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public ELException(Throwable pRootCause) {
        mRootCause = pRootCause;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public ELException(String pMessage,
                       Throwable pRootCause) {
        super(pMessage);
        mRootCause = pRootCause;
    }

    //-------------------------------------

    /**
     * Returns the root cause
     */
    public Throwable getRootCause() {
        return mRootCause;
    }

    //-------------------------------------

    /**
     * String representation
     */
    public String toString() {
        if (getMessage() == null) {
            return mRootCause.toString();
        } else if (mRootCause == null) {
            return getMessage();
        } else {
            return getMessage() + ": " + mRootCause;
        }
    }

    //-------------------------------------
}
