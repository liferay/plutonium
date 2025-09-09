/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.liferay.pluto.testsuite.InvalidConfigurationException;

/**
 * A Singleton which loads a properties file containing data expected by the
 * tests in the testsuite.
 */
public class ExpectedResults {

	/** The file name of properties holding expected results. */
	public static final String PROPERTY_FILENAME = "expectedResults.properties";

	/** The static singleton instance. */
	private static ExpectedResults instance;


	// Private Member Variables ------------------------------------------------

	/** The nested properties. */
	private Properties properties;


	// Constructor -------------------------------------------------------------

	/**
	 * Private constructor that prevents external instantiation.
	 * @throws IOException  if fail to load properties from file.
	 */
	private ExpectedResults() throws IOException {
		properties = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(PROPERTY_FILENAME);
		if (in != null) {
			properties.load(in);
		} else {
			throw new IOException("Could not find " + PROPERTY_FILENAME);
		}
	}

	/**
	 * Returns the singleton expected results instance.
	 * @return the singleton expected results instance.
	 * @throws InvalidConfigurationException  if fail to read properties file.
	 */
	public static ExpectedResults getInstance()
	throws InvalidConfigurationException {
		if (instance == null) {
			try {
				instance = new ExpectedResults();
			} catch (IOException ex) {
				throw new InvalidConfigurationException("Error reading file "
						+ PROPERTY_FILENAME + ": " + ex.getMessage());
			}
		}
		return instance;
	}


	// Public Methods ----------------------------------------------------------

	public String getMajorVersion() {
		return properties.getProperty("expected.version.major");
	}

	public String getMinorVersion() {
		return properties.getProperty("expected.version.minor");
	}

	public String getServerInfo() {
		return properties.getProperty("expected.serverInfo");
	}

	public String getPortalInfo() {
		return properties.getProperty("expected.portalInfo");
	}

	public String getMappedSecurityRole() {
		return properties.getProperty("expected.security.role.mapped");
	}

	public String getUnmappedSecurityRole() {
        return properties.getProperty("expected.security.role");
	}

}


