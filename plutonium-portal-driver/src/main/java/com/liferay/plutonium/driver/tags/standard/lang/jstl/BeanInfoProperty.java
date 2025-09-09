/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags.standard.lang.jstl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * <p>This contains the information for one property in a BeanInfo -
 * PropertyDescriptor, read method, and write method.  This class is
 * necessary because the read/write methods in the PropertyDescriptor
 * may not be accessible if the bean given to the introspector is not
 * a public class.  In this case, a publicly accessible version of the
 * method must be found by searching for a public superclass/interface
 * that declares the method (this searching is done by the
 * BeanInfoManager).
 *
 * @author Nathan Abramson - Art Technology Group
 */

public class BeanInfoProperty {
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

    PropertyDescriptor mPropertyDescriptor;

    public PropertyDescriptor getPropertyDescriptor() {
        return mPropertyDescriptor;
    }

    //-------------------------------------

    /**
     * Constructor
     */
    public BeanInfoProperty(Method pReadMethod,
                            Method pWriteMethod,
                            PropertyDescriptor pPropertyDescriptor) {
        mReadMethod = pReadMethod;
        mWriteMethod = pWriteMethod;
        mPropertyDescriptor = pPropertyDescriptor;
    }

    //-------------------------------------
}
