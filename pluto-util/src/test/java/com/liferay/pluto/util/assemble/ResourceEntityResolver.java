/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.assemble;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Resolves entities by parsing the path from the System ID and resolving
 * the file using {@link Class#getResourceAsStream}.
 */
public class ResourceEntityResolver implements EntityResolver
{
    public InputSource resolveEntity( String publicId, String systemId ) throws SAXException, IOException
    {
        if ( systemId.lastIndexOf( "/" ) == systemId.trim().length() )
        {
            return null;
        }

        String path;

        if ( systemId.indexOf( "/" ) > -1 )
        {
            path = systemId.substring( systemId.lastIndexOf( "/" ) + 1 );
        }
        else
        {
            path = systemId.trim();
        }

        InputStream in = this.getClass().getResourceAsStream( path );
        return ( in == null ) ? null : new InputSource( in );
    }
}
