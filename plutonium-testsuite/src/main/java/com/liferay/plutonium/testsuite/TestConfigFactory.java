/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

/**
 * Test configuration factory that reads and parses testsuite config file using
 * Digester and constructs <code>TestConfig</code> objects.
 *
 * @see TestConfig
 *
 */
public class TestConfigFactory {

	/** Digester instance used to parse testsuite config file. */
    private Digester digester = new Digester();


    // Constructor -------------------------------------------------------------

    /**
     * Creates a factory instance.
     */
    public TestConfigFactory() {
        digester = new Digester();
        digester.setClassLoader(Thread.currentThread().getContextClassLoader());
        digester.addObjectCreate("testportlet-config", ArrayList.class);


        digester.addObjectCreate("testportlet-config/testsuite-config",
                                 TestConfig.class);

        digester.addBeanPropertySetter("testportlet-config/testsuite-config/name",
                                       "name");
        digester.addBeanPropertySetter("testportlet-config/testsuite-config/class",
                                       "testClassName");
        digester.addBeanPropertySetter("testportlet-config/testsuite-config/display-uri",
                                       "displayURI");

        digester.addCallMethod("testportlet-config/testsuite-config/init-param", "addInitParameter", 2);
        digester.addCallParam("testportlet-config/testsuite-config/init-param/name", 0);
        digester.addCallParam("testportlet-config/testsuite-config/init-param/value", 1);

        digester.addCallMethod("testportlet-config/testsuite-config/action-param", "addActionParameter", 2);
        digester.addCallParam("testportlet-config/testsuite-config/action-param/name", 0);
        digester.addCallParam("testportlet-config/testsuite-config/action-param/value", 1);

        digester.addCallMethod("testportlet-config/testsuite-config/render-param", "addRenderParameter", 2);
        digester.addCallParam("testportlet-config/testsuite-config/render-param/name", 0);
        digester.addCallParam("testportlet-config/testsuite-config/render-param/value", 1);

        digester.addSetRoot("testportlet-config/testsuite-config", "add");

    }


    // Public Methods ----------------------------------------------------------

    /**
     * Reads and parses testsuite config file, creates a list of
     * <code>TestConfig</code> objects.
     *
     * @param in  the input stream of the testsuite config file.
     * @return a list of <code>TestConfig</code> objects.
     * @throws SAXException  if a parsing error occurs.
     * @throws IOException  if an IO error occurs.
     * @see TestConfig
     */
    @SuppressWarnings("unchecked")
	public List<TestConfig> createTestConfigs(InputStream in)
    throws SAXException, IOException {
        return (List<TestConfig>) digester.parse(in);
    }

}
