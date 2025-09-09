/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A test method can be marked to be run during a specific portlet lifecycle
 * phase using this annotation.  It will override the value of DefaultTestPhase
 * if DefaultTestPhase is used on the enclosing class.
 * 
 * @author Benjamin C. Gould
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestPhase {

    public String value();
    
}
