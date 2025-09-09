/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.assemble.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.liferay.pluto.util.UtilityException;
import com.liferay.pluto.util.assemble.Assembler;
import com.liferay.pluto.util.assemble.AssemblerConfig;
import com.liferay.pluto.util.assemble.io.WebXmlStreamingAssembly;

/**
 *
 * @author <a href="mailto:ddewolf@apache.org">David H. DeWolf</a>
 * @version 1.0
 * @since Nov 8, 2004
 */
public class FileAssemblerAnt implements Assembler {
    
    // Constructor -------------------------------------------------------------
    
    /**
     * Default no-arg constructor.
     */
    public FileAssemblerAnt() {
    	// Do nothing.
    }
    
    
    // Assembler Impl ----------------------------------------------------------
    
    public void assemble(AssemblerConfig config) throws UtilityException {
        try {
            InputStream webXmlIn = new FileInputStream(
            		config.getWebappDescriptor());
            InputStream portletXmlIn = new FileInputStream(
            		config.getPortletDescriptor());
            FileOutputStream webXmlOut = new FileOutputStream(
            		config.getDestination());
            WebXmlStreamingAssembly.assembleStream(webXmlIn, portletXmlIn, webXmlOut, DISPATCH_SERVLET_CLASS);
        } catch (IOException ex) {
            throw new UtilityException(ex.getMessage(), ex, null);
        }
    }
}

