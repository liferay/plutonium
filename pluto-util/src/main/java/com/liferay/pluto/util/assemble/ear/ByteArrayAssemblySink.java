/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.assemble.ear;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stores assembled bytes in an underlying <code>ByteArrayOutputStream</code>
 */
class ByteArrayAssemblySink extends AssemblySink {
    
    private static final Logger LOG = LoggerFactory.getLogger( ByteArrayAssemblySink.class );
    
    ByteArrayAssemblySink(ByteArrayOutputStream out) {
        super(out);
    }
    
    ByteArrayAssemblySink(ByteArrayOutputStream out, int buflen) {
        this.out = new ByteArrayOutputStream( buflen );
    }
    
    public void writeTo(OutputStream out, int buflen) throws IOException {
        byte[] buf = new byte[buflen];
        int read = 0;
        InputStream sinkReader = new BufferedInputStream( 
                new ByteArrayInputStream( ((ByteArrayOutputStream)this.out).toByteArray()), buflen);
        while ( ( read = sinkReader.read(buf) ) != -1 ) {
            out.write(buf, 0, read);
        }
    }
    
}
