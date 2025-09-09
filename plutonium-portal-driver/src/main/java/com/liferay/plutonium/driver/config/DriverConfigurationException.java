/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.config;

/**
 * @todo Document
 * @since Aug 10, 2005
 */
public class DriverConfigurationException extends RuntimeException {

    public DriverConfigurationException() {
    }

    public DriverConfigurationException(String message) {
        super(message);
    }

    public DriverConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverConfigurationException(Throwable cause) {
        super(cause);
    }
}
