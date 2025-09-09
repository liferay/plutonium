/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite;

/**
 * Indicates that a configuration error has been detected
 */
public class InvalidConfigurationException extends RuntimeException {

	private static final long serialVersionUID = -7010299791708559054L;

	public InvalidConfigurationException(String message) {
		super(message);
	}

}
