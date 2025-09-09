/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.assemble.war;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import com.liferay.pluto.util.UtilityException;
import com.liferay.pluto.util.assemble.AbstractArchiveAssembler;
import com.liferay.pluto.util.assemble.AssemblerConfig;
import com.liferay.pluto.util.assemble.io.JarStreamingAssembly;

/**
 *
 * @version 1.0
 * @since Nov 8, 2004
 */
public class WarAssembler extends AbstractArchiveAssembler {
    // Constructor -------------------------------------------------------------

    /**
     * Default no-arg constructor.
     */
    public WarAssembler() {
    	// Do nothing.
    }


    // Assembler Impl ----------------------------------------------------------

    public void assembleInternal(AssemblerConfig config) 
        throws UtilityException, IOException {
        
        this.assembleWar(config.getSource(), config.getDestination(), config.getDispatchServletClass());
        
    }

    /**
     * Reads the source JAR copying entries to the dest JAR. The web.xml and portlet.xml are cached
     * and after the entire archive is copied (minus the web.xml) a re-written web.xml is generated
     * and written to the destination JAR.
     */
    protected void assembleWar(File source, File dest, String dispatchServletClass) throws IOException {
        final JarInputStream jarIn = new JarInputStream(new FileInputStream(source));
        //Create the output JAR stream, copying the Manifest
        final Manifest manifest = jarIn.getManifest();
        //TODO add pluto notes to the Manifest?
        
        final JarOutputStream jarOut;
        if (manifest != null) {
            jarOut = new JarOutputStream(new FileOutputStream(dest), manifest);
        }
        else {
            jarOut = new JarOutputStream(new FileOutputStream(dest));
        }
        
        try {            
            JarStreamingAssembly.assembleStream(jarIn, jarOut, dispatchServletClass);
        } finally {
            jarIn.close();
            jarOut.close();
        }
    }

}

