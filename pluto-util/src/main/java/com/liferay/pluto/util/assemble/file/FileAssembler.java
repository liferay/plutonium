/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.assemble.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import com.liferay.pluto.util.UtilityException;
import com.liferay.pluto.util.assemble.AssemblerConfig;
import com.liferay.pluto.util.assemble.WebXmlRewritingAssembler;

/**
 *
 * @version 1.0
 * @since Nov 8, 2004
 */
public class FileAssembler extends WebXmlRewritingAssembler {
    // Constructor -------------------------------------------------------------

    /**
     * Default no-arg constructor.
     */
    public FileAssembler() {
    	// Do nothing.
    }


    // Assembler Impl ----------------------------------------------------------

    public void assemble(AssemblerConfig config) throws UtilityException {
        try {
            final File webappDescriptor = config.getWebappDescriptor();
            InputStream webXmlIn = new FileInputStream(webappDescriptor);

            final File portletDescriptor = config.getPortletDescriptor();
            InputStream portletXmlIn = new FileInputStream(portletDescriptor);

            final File destinationDescriptor = config.getDestination();
            if (webappDescriptor.equals(destinationDescriptor)) {
                final File tempXml = File.createTempFile(webappDescriptor.getName() + ".", ".tmp");
                final FileOutputStream webXmlOut = new FileOutputStream(tempXml);

                this.updateWebappDescriptor(webXmlIn, portletXmlIn, webXmlOut, config.getDispatchServletClass());

                //Move the temp file to the destination location
                destinationDescriptor.delete();
                // renameTo() is impl-specific
                boolean success = tempXml.renameTo(destinationDescriptor);
                if (! success) {
                    FileUtils.copyFile( tempXml, destinationDescriptor );
                }
            }
            else {
                destinationDescriptor.getParentFile().mkdirs();
                final FileOutputStream webXmlOut = new FileOutputStream(destinationDescriptor);
                this.updateWebappDescriptor(webXmlIn, portletXmlIn, webXmlOut, config.getDispatchServletClass());
            }
        } catch (IOException ex) {
            throw new UtilityException(ex.getMessage(), ex, null);
        }
    }
}

