/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.util;


import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * @author <a href="mailto:ate@douma.nu">Ate Douma</a>
 * @version $Id$
 */
public class NamespacedNamesEnumeration implements Enumeration<String>
{
    private Enumeration<String> namesEnumeration;
    private String      namespace;

    private String nextName;
    private boolean done;

    public NamespacedNamesEnumeration(Enumeration<String> namesEnumeration, String namespace)
    {
        this.namesEnumeration = namesEnumeration;
        this.namespace = namespace;
        hasMoreElements();
    }

    public boolean hasMoreElements()
    {
        if (!done)
        {
            if (nextName == null)
            {
                while (namesEnumeration.hasMoreElements())
                {
                    String name = namesEnumeration.nextElement();
                    if ( name.startsWith(namespace))
                    {
                        nextName = name.substring(namespace.length());
                        break;
                    }
                }
                done = nextName == null;
            }
        }
        return !done;
    }

    public String nextElement()
    {
        if (done)
        {
            throw new NoSuchElementException();
        }
        String name = nextName;
        nextName = null;
        return name;
    }
}
