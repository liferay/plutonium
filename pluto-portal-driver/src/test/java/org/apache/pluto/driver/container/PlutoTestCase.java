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
package org.apache.pluto.driver.container;

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
public abstract class PlutoTestCase {

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
