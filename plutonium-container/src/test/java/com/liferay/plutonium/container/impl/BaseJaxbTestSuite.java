/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Scott Nicklous
 *
 */
@RunWith(Suite.class)
@SuiteClasses({JaxbReadTest168NC.class, JaxbReadTest168Gen.class,
   JaxbReadTest286NC.class, JaxbReadTest286Gen.class,
   JaxbReadTest362Gen.class})
public class BaseJaxbTestSuite {

}
