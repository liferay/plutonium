/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.text.MessageFormat;
import java.util.Map;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.Tag;

/**
 * <p>This is the expression evaluator "adapter" that customizes it
 * for use with the JSP Standard Tag Library.  It uses a
 * VariableResolver implementation that looks up variables from the
 * PageContext and also implements its implicit objects.  It also
 * wraps ELExceptions in JspExceptions that describe the attribute
 * name and value causing the error.
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public class Evaluator
        implements ExpressionEvaluator {
    //-------------------------------------
    // Properties
    //-------------------------------------

    //-------------------------------------
    // Member variables
    //-------------------------------------

    /**
     * The singleton instance of the evaluator *
     */
    static ELEvaluator sEvaluator =
            new ELEvaluator
                    (new JSTLVariableResolver());

    //-------------------------------------
    // ExpressionEvaluator methods
    //-------------------------------------

    /**
     * Translation time validation of an attribute value.  This method
     * will return a null String if the attribute value is valid;
     * otherwise an error message.
     */
    public String validate(String pAttributeName,
                           String pAttributeValue) {
        try {
            sEvaluator.setBypassCache(true);
            sEvaluator.parseExpressionString(pAttributeValue);
            sEvaluator.setBypassCache(false);
            return null;
        }
        catch (ELException exc) {
            return
                    MessageFormat.format
                            (Constants.ATTRIBUTE_PARSE_EXCEPTION,
                                    "" + pAttributeName,
                                    "" + pAttributeValue,
                                    exc.getMessage());
        }
    }

    //-------------------------------------

    /**
     * Evaluates the expression at request time
     */
    public Object evaluate(String pAttributeName,
                           String pAttributeValue,
                           Class pExpectedType,
                           Tag pTag,
                           PageContext pPageContext,
                           Map functions,
                           String defaultPrefix)
            throws JspException {
        try {
            return sEvaluator.evaluate
                    (pAttributeValue,
                            pPageContext,
                            pExpectedType,
                            functions,
                            defaultPrefix);
        }
        catch (ELException exc) {
            throw new JspException
                    (MessageFormat.format
                            (Constants.ATTRIBUTE_EVALUATION_EXCEPTION,
                                    "" + pAttributeName,
                                    "" + pAttributeValue,
                                    exc.getMessage(),
                                    exc.getRootCause()), exc.getRootCause());
        }
    }

    /**
     * Conduit to old-style call for convenience.
     */
    public Object evaluate(String pAttributeName,
                           String pAttributeValue,
                           Class pExpectedType,
                           Tag pTag,
                           PageContext pPageContext)
            throws JspException {
        return evaluate(pAttributeName,
                pAttributeValue,
                pExpectedType,
                pTag,
                pPageContext,
                null,
                null);
    }


    //-------------------------------------
    // Testing methods
    //-------------------------------------

    /**
     * Parses the given attribute value, then converts it back to a
     * String in its canonical form.
     */
    public static String parseAndRender(String pAttributeValue)
            throws JspException {
        try {
            return sEvaluator.parseAndRender(pAttributeValue);
        }
        catch (ELException exc) {
            throw new JspException
                    (MessageFormat.format
                            (Constants.ATTRIBUTE_PARSE_EXCEPTION,
                                    "test",
                                    "" + pAttributeValue,
                                    exc.getMessage()));
        }
    }

    //-------------------------------------

}
