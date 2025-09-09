/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.util.assemble.ear;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stores assembled bytes in an underlying <code>File</code>.
 */
class FileAssemblySink extends AssemblySink {
    
    private static final Logger LOG = LoggerFactory.getLogger( FileAssemblySink.class );
    private File sink = null;
    
    FileAssemblySink(File file) throws FileNotFoundException {
        super(new FileOutputStream(file));
        this.sink = file;        
    }
    
    public void writeTo(OutputStream out, int buflen) throws IOException {
        byte[] buf = new byte[buflen];
        int read = 0;
        InputStream sinkReader = new BufferedInputStream( 
                new FileInputStream(sink), buflen );
        while ( ( read = sinkReader.read(buf) ) != -1 ) {
            out.write(buf, 0, read);
        }
    }
    
}
