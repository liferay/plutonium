/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;


import java.beans.IndexedPropertyDescriptor;
import java.lang.reflect.Method;


/**
 * <p>This contains the information for one indexed property in a
 * <p>BeanInfo - IndexedPropertyDescriptor, read method, and write
 * method.  This class is necessary because the read/write methods in
 * the IndexedPropertyDescriptor may not be accessible if the bean
 * given to the introspector is not a public class.  In this case, a
 * publicly accessible version of the method must be found by
 * searching for a public superclass/interface that declares the
 * method (this searching is done by the BeanInfoManager).
 *
 * @author Nathan Abramson - Art Technology Group
 */
public class BeanInfoIndexedProperty {

    //-------------------------------------
    // Properties

    //-------------------------------------
    // property readMethod

    Method mReadMethod;

    public Method getReadMethod() {
        return mReadMethod;
    }

    //-------------------------------------
    // property writeMethod

    Method mWriteMethod;

    public Method getWriteMethod() {
        return mWriteMethod;
    }

    //-------------------------------------
    // property propertyDescriptor

    IndexedPropertyDescriptor mIndexedPropertyDescriptor;

    public IndexedPropertyDescriptor getIndexedPropertyDescriptor() {
        return mIndexedPropertyDescriptor;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public BeanInfoIndexedProperty
            (Method pReadMethod,
             Method pWriteMethod,
             IndexedPropertyDescriptor pIndexedPropertyDescriptor)
    {
        mReadMethod = pReadMethod;
        mWriteMethod = pWriteMethod;
        mIndexedPropertyDescriptor = pIndexedPropertyDescriptor;
    }

    //-------------------------------------
}

