/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.util.List;
import java.util.Map;

/**
 * <p>An expression representing a binary operator on a value
 *
 * @author Nathan Abramson - Art Technology Group
 * @author Shawn Bayern
 */

public class BinaryOperatorExpression
        extends Expression {
    //-------------------------------------
    // Properties
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
    // property operators

    List mOperators;

    public List getOperators() {
        return mOperators;
    }

    public void setOperators(List pOperators) {
        mOperators = pOperators;
    }

    //-------------------------------------
    // property expressions

    List mExpressions;

    public List getExpressions() {
        return mExpressions;
    }

    public void setExpressions(List pExpressions) {
        mExpressions = pExpressions;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public BinaryOperatorExpression(Expression pExpression,
                                    List pOperators,
                                    List pExpressions) {
        mExpression = pExpression;
        mOperators = pOperators;
        mExpressions = pExpressions;
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
        buf.append(mExpression.getExpressionString());
        for (int i = 0; i < mOperators.size(); i++) {
            BinaryOperator operator = (BinaryOperator) mOperators.get(i);
            Expression expression = (Expression) mExpressions.get(i);
            buf.append(" ");
            buf.append(operator.getOperatorSymbol());
            buf.append(" ");
            buf.append(expression.getExpressionString());
        }
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
        for (int i = 0; i < mOperators.size(); i++) {
            BinaryOperator operator = (BinaryOperator) mOperators.get(i);

            // For the And/Or operators, we need to coerce to a boolean
            // before testing if we shouldEvaluate
            if (operator.shouldCoerceToBoolean()) {
                value = Coercions.coerceToBoolean(value, pLogger);
            }

            if (operator.shouldEvaluate(value)) {
                Expression expression = (Expression) mExpressions.get(i);
                Object nextValue = expression.evaluate(pContext, pResolver,
                        functions, defaultPrefix,
                        pLogger);

                value = operator.apply(value, nextValue, pContext, pLogger);
            }
        }
        return value;
    }

    //-------------------------------------
}
