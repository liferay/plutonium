/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.testsuite.test.jsr286.event;

import java.io.Serializable;

/**
 * A serializable object for use with the event tests.
 */
public class ComposedObject implements Serializable {

    private static final long serialVersionUID = -116040162572322649L;
   
    private String color;

    /* Doesn't really work with JAXB; probably needs some type of annotation */
    //private Point point;
    
    private String point;
    
    public ComposedObject() {
        super();
    }

    public ComposedObject(String color, /*Point*/ String point) {
        super();
        this.point = point;
        this.color = color;
    }

    /*
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    */
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
    
}

 