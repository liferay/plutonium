/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.util;

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
