/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.util.assemble.war;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import com.liferay.plutonium.util.assemble.ArchiveBasedAssemblyTest;
import com.liferay.plutonium.util.assemble.Assembler;
import com.liferay.plutonium.util.assemble.AssemblerConfig;

/**
 * @version $Revision$
 */
public class WarAssemblerTest extends ArchiveBasedAssemblyTest {

    private static final String portletResourceNoManifest = "/com/liferay/plutonium/util/assemble/war/WarDeployerTestPortletNoManifest.war";
    private static final String portletResource = "/com/liferay/plutonium/util/assemble/war/WarDeployerTestPortlet.war";
    private File portletFile = null;    
        

    
    protected void setUp() throws Exception {
        final URL portletUrl = this.getClass().getResource(portletResource);
        this.portletFile = new File(portletUrl.getFile());
    }

    protected void tearDown() throws Exception {
        this.portletFile = null;
    }

    public void testAssembleToNewDirectory() throws Exception {
        AssemblerConfig config = new AssemblerConfig();

        config.setSource(this.portletFile);

        final File tempDir = getTempDir();
        config.setDestination(tempDir);

        WarAssembler assembler = new WarAssembler();
        assembler.assemble(config);

        //How to validate it worked?
    }

    public void testAssembleOverSelf() throws Exception {
        AssemblerConfig config = new AssemblerConfig();

        final File portletCopy = File.createTempFile(this.portletFile.getName() + ".", ".war");
        portletCopy.deleteOnExit();
        FileUtils.copyFile(this.portletFile, portletCopy);

        config.setSource(portletCopy);
        config.setDestination(portletCopy.getParentFile());

        WarAssembler assembler = new WarAssembler();
        assembler.assemble(config);

        //How to validate it worked?
    }
    
    public void testAssembleWithNoManifest() throws Exception {
        
        final File warNoManifest = new File( this.getClass().getResource(portletResourceNoManifest).getFile() );
        assertNotNull( "Unable to locate the test war file with no manifest.", warNoManifest );
        
        final File tempDir = getTempDir();
        final AssemblerConfig config = new AssemblerConfig();
        config.setSource( warNoManifest );
        config.setDestination(tempDir);
        
        WarAssembler assembler = new WarAssembler();
        assembler.assemble(config);        
    }

    private File getTempDir() throws IOException {
        final File tempFile = File.createTempFile("DoesNotMatter", ".tmp");
        tempFile.delete();
        final File tempDir = tempFile.getParentFile();
        return tempDir;
    }

    protected Assembler getAssemblerUnderTest() {
        return new WarAssembler();
    }

    protected File getFileToAssemble() {
        return portletFile;
    }
}
