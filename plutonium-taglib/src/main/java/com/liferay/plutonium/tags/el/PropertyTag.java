/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.tags.el;

import jakarta.servlet.jsp.JspException;


public class PropertyTag extends com.liferay.plutonium.tags.PropertyTag {

	private static final long serialVersionUID = 286L;

	public String getValue() throws JspException {
        return ExpressionEvaluatorProxy.getProxy().evaluate(super.getValue(), pageContext);
    }

}
