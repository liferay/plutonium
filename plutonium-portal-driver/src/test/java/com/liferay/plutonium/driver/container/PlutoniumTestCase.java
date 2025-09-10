/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.container;

import org.jmock.Mockery;
import org.junit.Before;
import static org.junit.Assert.fail;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Test Class
 *
 * @version 1.0
 * @since June 1, 2005
 */
public abstract class PlutoniumTestCase {

    protected Mockery context;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.defaultlog", "ERROR");
    }

    @SuppressWarnings("unchecked")
    protected void assertException(Object target, String methodName, Object[] parameters, Class<? extends Throwable> exceptionType) {
        Class<?>[] parameterClasses = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterClasses[i] = (parameters[i] == null) ? Object.class : parameters[i].getClass();
        }
        try {
            Method method = target.getClass().getMethod(methodName, parameterClasses);
            method.invoke(target, parameters);
            fail("Expected exception of type " + exceptionType.getName() + " was not thrown.");
        } catch (InvocationTargetException ite) {
            Throwable t = ite.getTargetException();
            if (!exceptionType.isInstance(t)) {
                fail("Incorrect exception thrown. Expected: " + exceptionType.getName() + ", received: " + t.getClass().getName());
            }
        } catch (Exception e) {
            fail("Invalid test setup. Reflection invocation failed: " + e.getMessage());
        }
    }
}
