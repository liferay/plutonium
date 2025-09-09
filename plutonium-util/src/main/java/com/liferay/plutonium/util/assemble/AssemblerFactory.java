/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.util.assemble;

import java.io.File;

import com.liferay.plutonium.util.assemble.ear.EarAssembler;
import com.liferay.plutonium.util.assemble.file.FileAssembler;
import com.liferay.plutonium.util.assemble.file.FileAssemblerAnt;
import com.liferay.plutonium.util.assemble.war.WarAssembler;

/**
 * The plutonium assembler factory that creates an assembler.
 * @version 1.0
 * @since Nov 8, 2004
 */
public class AssemblerFactory {

	/** The singleton factory instance. */
    private static final AssemblerFactory FACTORY = new AssemblerFactory();

    /**
     * Private constructor that prevents external instantiation.
     */
    private AssemblerFactory() {
    	// Do nothing.
    }

    /**
     * Returns the singleton factory instance.
     * @return the singleton factory instance.
     */
    public static AssemblerFactory getFactory() {
        return FACTORY;
    }


    // Public Methods ----------------------------------------------------------

    /**
     * Creates an assembler to assemble a portlet app WAR file to a web app WAR
     * file deployable to plutonium.
     * @param config  the assembler configuration.
     * @return an assembler instance.
     */
    public Assembler createAssembler(AssemblerConfig config) {
        File source = config.getSource();
        
        if ( source == null ) {
            return new FileAssembler();
        }

        if ( source.getName().toLowerCase().endsWith( ".war" ) ) {
            return new WarAssembler();
        }
        
        if ( source.getName().toLowerCase().endsWith( ".ear" ) ) {
            return new EarAssembler();
        }
        
        return new FileAssembler();
    }
    
    public Assembler createAssemblerAnt(AssemblerConfig config){
    	return new FileAssemblerAnt();
    }

}
