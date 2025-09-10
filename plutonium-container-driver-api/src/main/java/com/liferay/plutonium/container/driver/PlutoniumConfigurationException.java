/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

/**
 * Exception thrown when Plutonium configuration fails.
 *
 * @since Jul 2, 2005
 */
public class PlutoniumConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 8159327269127050615L;

    public PlutoniumConfigurationException(String message) {
        super(message);
    }

    public PlutoniumConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
