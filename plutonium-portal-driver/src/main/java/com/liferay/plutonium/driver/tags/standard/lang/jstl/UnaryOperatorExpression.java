/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.util.List;
import java.util.Map;

/**
 * <p>An expression representing one or more unary operators on a
 * value
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public class UnaryOperatorExpression
        extends Expression {
    //-------------------------------------
    // Properties
    //-------------------------------------
    // property operator

    UnaryOperator mOperator;

    public UnaryOperator getOperator() {
        return mOperator;
    }

    public void setOperator(UnaryOperator pOperator) {
        mOperator = pOperator;
    }

    //-------------------------------------
    // property operators

    List mOperators;

    public List getOperators() {
        return mOperators;
    }

    public void setOperators(List pOperators) {
        mOperators = pOperators;
    }

    //-------------------------------------
    // property expression

    Expression mExpression;

    public Expression getExpression() {
        return mExpression;
    }

    public void setExpression(Expression pExpression) {
        mExpression = pExpression;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public UnaryOperatorExpression(UnaryOperator pOperator,
                                   List pOperators,
                                   Expression pExpression) {
        mOperator = pOperator;
        mOperators = pOperators;
        mExpression = pExpression;
    }

    //-------------------------------------
    // Expression methods
    //-------------------------------------

    /**
     * Returns the expression in the expression language syntax
     */
    public String getExpressionString() {
        StringBuffer buf = new StringBuffer();
        buf.append("(");
        if (mOperator != null) {
            buf.append(mOperator.getOperatorSymbol());
            buf.append(" ");
        } else {
            for (int i = 0; i < mOperators.size(); i++) {
                UnaryOperator operator = (UnaryOperator) mOperators.get(i);
                buf.append(operator.getOperatorSymbol());
                buf.append(" ");
            }
        }
        buf.append(mExpression.getExpressionString());
        buf.append(")");
        return buf.toString();
    }

    //-------------------------------------

    /**
     * Evaluates to the literal value
     */
    public Object evaluate(Object pContext,
                           VariableResolver pResolver,
                           Map functions,
                           String defaultPrefix,
                           Logger pLogger)
            throws ELException {
        Object value = mExpression.evaluate(pContext, pResolver, functions,
                defaultPrefix, pLogger);
        if (mOperator != null) {
            value = mOperator.apply(value, pContext, pLogger);
        } else {
            for (int i = mOperators.size() - 1; i >= 0; i--) {
                UnaryOperator operator = (UnaryOperator) mOperators.get(i);
                value = operator.apply(value, pContext, pLogger);
            }
        }
        return value;
    }

    //-------------------------------------
}
