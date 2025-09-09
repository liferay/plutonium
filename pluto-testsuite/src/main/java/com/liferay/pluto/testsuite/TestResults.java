/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class contains one or more test results.
 */
public class TestResults implements Serializable {

	private static final long serialVersionUID = 279901260532957514L;

	private String name;

    private ArrayList<TestResult> list = new ArrayList<TestResult>();

    private boolean failed = false;
    private boolean inQuestion = false;

    public TestResults(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(TestResult result) {
        if (result.getReturnCode() == TestResult.FAILED) {
            failed = true;
        } else if (result.getReturnCode() == TestResult.WARNING) {
            inQuestion = true;
        }
        list.add(result);
    }

    public boolean isFailed() {
        return failed;
    }

    public boolean isInQuestion() {
        return inQuestion;
    }

    public Collection<TestResult> getCollection() {
        return list;
    }

    /**
     * Override of toString() that prints out variable
     * names and values.
     *
     * @see java.lang.Object#toString()
     */
    public String toString(){
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName());
    	buffer.append("[name=").append(name);
    	buffer.append(";failed=").append(failed);
    	buffer.append(";inQuestion=").append(inQuestion);
    	buffer.append(";results={").append(list).append("}]");
    	return buffer.toString();
    }
}
