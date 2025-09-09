/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.pluto.container.util;

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
