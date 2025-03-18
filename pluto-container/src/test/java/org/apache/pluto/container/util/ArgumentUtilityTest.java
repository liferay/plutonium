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
package org.apache.pluto.container.util;

import org.junit.Before;
import org.junit.Test;

public class ArgumentUtilityTest extends PlutoTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testValidateNotNullWhenNull() {
        assertException(new ArgumentUtility(), "validateNotNull", IllegalArgumentException.class, "arg", null);
    }

    @Test
    public void testValidateNotNullWhenNotNull() {
        ArgumentUtility.validateNotNull("arg", "notnull");
    }

    @Test
    public void testValidateNotNullOrEmptyWhenNull() {
        assertException(new ArgumentUtility(), "validateNotEmpty", IllegalArgumentException.class, "arg", null);
    }

    @Test
    public void testValidateNotNullOrEmptyWhenEmpty() {
        assertException(new ArgumentUtility(), "validateNotEmpty", IllegalArgumentException.class, "arg", "");
    }

    @Test
    public void testValidateNotNullOrEmptyWhenValid() {
        ArgumentUtility.validateNotEmpty("arg", "notempty");
    }
}
