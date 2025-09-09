/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.util;

import org.jmock.Mockery;
import org.junit.Before;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.fail;

/**
 * Test Class
 *
 * @version 1.0
 * @since June 1, 2005
 */
public abstract class PlutoTestCase {

    protected Mockery context;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.defaultlog", "ERROR");
    }

    protected void assertException(Object target, String methodName, Class<? extends Throwable> exceptionType, Object... parameters) {
        try {
            // Fetch method with correct parameter types
            Method method = findMethod(target.getClass(), methodName, parameters);
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

    /**
     * Finds the correct method, handling null parameters properly.
     */
    private Method findMethod(Class<?> clazz, String methodName, Object... parameters) throws NoSuchMethodException {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName) && method.getParameterCount() == parameters.length) {
                Class<?>[] paramTypes = method.getParameterTypes();
                boolean match = true;
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i] != null && !paramTypes[i].isAssignableFrom(parameters[i].getClass())) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return method;
                }
            }
        }
        throw new NoSuchMethodException(clazz.getName() + "." + methodName);
    }

    protected void assertContains(String message, String expectedSubstring, String testString) {
        if (!testString.contains(expectedSubstring)) {
            fail(message);
        }
    }
}
