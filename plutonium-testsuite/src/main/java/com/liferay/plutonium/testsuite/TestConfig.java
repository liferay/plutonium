/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration for <code>PortletTest</code>.
 *
 * @see TestConfigFactory
 * @see PortletTest
 *
 * @version 1.0
 * @since Sep 15, 2004
 */
public class TestConfig implements Serializable {

	private static final long serialVersionUID = -6878431356359186658L;

	// Private Member Variables ------------------------------------------------

	/** PortletTest class name. */
    private String testClassName;

    /** Test name. */
    private String name;

    private String displayURI;

    private Map<String, String> initParameters = new HashMap<String, String>();

    /**
     * The action parameters list holding TestConfig.Parameter objects.
     * We are not using Map to hold action parameters because parameters with
     * the same name are allowed.
     */
    private List<Parameter> actionParameters = new ArrayList<Parameter>();

    /**
     * The render parameters list holding TestConfig.Parameter objects.
     * We are not using Map to hold render parameters because parameters with
     * the same name are allowed.
     *
     * FIXME: when is this field used?
     */
//    private List renderParameters = new ArrayList();


    // Constructor -------------------------------------------------------------

    /**
     * Default constructor required by Digester.
     */
    public TestConfig() {
    	// Do nothing.
    }

    // Public Methods ----------------------------------------------------------

    public String getTestClassName() {
        return testClassName;
    }

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String testName) {
        this.name = testName;
    }

    public String getDisplayURI() {
        return displayURI;
    }

    public void setDisplayURI(String displayURI) {
        this.displayURI = displayURI;
    }

    public void addInitParameter(String parameter, String value) {
        initParameters.put(parameter, value);
    }

    public Map<String, String> getInitParameters() {
        return Collections.unmodifiableMap(initParameters);
    }

    public void addActionParameter(String name, String value) {
    	actionParameters.add(new Parameter(name, value));
    }

    public List<Parameter> getActionParameters() {
    	return actionParameters;
    }

    /**
     * FIXME: why is this method required?
     */
    /*
    public void addRenderParameter(String name, String value) {
    	renderParameters.add(new Parameter(name, value));
    }
    */

    /**
     * FIXME: when is this method used?
     */
    /*
    public List getRenderParameters() {
    	return renderParameters;
    }
    */

    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName());
    	buffer.append("[").append(getName()).append("]");
    	return buffer.toString();
    }

    public static class Parameter {
    	private String name = null;
    	private String value = null;
    	public Parameter(String name, String value) {
    		this.name = name;
    		this.value = value;
    	}

    	public String getName() {
    		return name;
    	}
    	public String getValue() {
    		return value;
    	}
    }

}

