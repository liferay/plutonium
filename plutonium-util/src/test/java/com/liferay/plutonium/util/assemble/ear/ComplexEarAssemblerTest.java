/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.util.assemble.ear;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.apache.commons.io.IOUtils;
import com.liferay.plutonium.container.PortletAppDescriptorService;
import com.liferay.plutonium.container.impl.PortletAppDescriptorServiceImpl;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.om.portlet.PortletDefinition;
import com.liferay.plutonium.util.assemble.ArchiveBasedAssemblyTest;
import com.liferay.plutonium.util.assemble.Assembler;
import com.liferay.plutonium.util.assemble.AssemblerConfig;
import com.liferay.plutonium.util.descriptors.web.PlutoWebXmlRewriter;

/**
 * This test assembles an EAR file which contains two portlet
 * web applications for assembly.  There is a third war file
 * which is not a portlet, and therefore shouldn't be assembled, 
 * just copied.
 */
public class ComplexEarAssemblerTest extends ArchiveBasedAssemblyTest {
    
    private static final String earResource = 
        "/com/liferay/plutonium/util/assemble/ear/ComplexEarDeployerTest.ear";
    
    private static final String[] testPortletNames;
    private static final String[] testWarEntryNames;
    private static final String[] unassembledWarEntryName;
    static {
        testPortletNames = new String[] { "WarTestPortletName", "SecondWarTestPortletName" };
        testWarEntryNames = new String[] { "WarDeployerTestPortlet.war", "SecondWarDeployerTestPortlet.war" };
        unassembledWarEntryName = new String[] { "WarWithNoPortlet.war" };
    }
    
    private File earFile = null;
    
    protected void setUp() throws Exception {
        super.setUp();
        earFile = new File( getClass().getResource( earResource ).getFile() );
    }
    
    public void testMultipleWarFileAssembly() throws Exception {
        File tmpFile = File.createTempFile( "earTest", "tmp" );
        File destDir = new File( tmpFile.getName() + ".dir" );
        destDir.mkdirs();
        
        assertTrue( "Source ear file doesn't exist! [" + earFile + "]", earFile.exists() );
        assertTrue( "Destination directory doesn't exist! [" + destDir + "]", 
                destDir.exists() && destDir.isDirectory() );
        
        AssemblerConfig config = new AssemblerConfig();
        config.setSource( earFile );
        config.setDestination( destDir );
        EarAssembler assembler = new EarAssembler();
        
        assembler.assemble( config );
        
        File destFile = new File( destDir, earFile.getName() );
        
        assertTrue( "Assembled ear file doesn't exist, assembly failed", destFile.exists() );
        
        validateEarAssembly( destFile );
        
        tmpFile.delete();
        destFile.delete();
        destDir.delete();
    }
    
    protected void validateEarAssembly( File earFile ) throws Exception {
        assertTrue( "EAR archive [" + earFile.getAbsolutePath() + "] cannot be found or cannot be read", 
                earFile.exists() && earFile.canRead() );
        
        PortletAppDescriptorService portletSvc = new PortletAppDescriptorServiceImpl();
        PortletApplicationDefinition portletApp = null;
        
        PlutoWebXmlRewriter webXmlRewriter = null;
        
        List<String> portletWarEntries = Arrays.asList( testWarEntryNames );
        List<String> unassembledWarEntries = Arrays.asList( unassembledWarEntryName );
        List<String> testPortlets = Arrays.asList( testPortletNames );
        
        int earEntryCount = 0;
        int totalWarEntryCount = 0;
        int portletWarEntryCount = 0;
        
        JarInputStream earIn = new JarInputStream( new FileInputStream( earFile ) );
        
        JarEntry earEntry;
        JarEntry warEntry;
        
        
        while ( ( earEntry = earIn.getNextJarEntry() ) != null ) {            
            earEntryCount++;
            
            if ( earEntry.getName().endsWith( ".war" ) ) {
                totalWarEntryCount++;
                JarInputStream warIn = new JarInputStream( earIn );
                
                while ( ( warEntry = warIn.getNextJarEntry() ) != null ) {
                    if ( Assembler.PORTLET_XML.equals( warEntry.getName() ) ) {
                        portletApp = portletSvc.read( "test", "/test",
                                new ByteArrayInputStream( IOUtils.toByteArray( warIn ) ) );
                    }
                    if ( Assembler.SERVLET_XML.equals( warEntry.getName() ) ) {
                        webXmlRewriter = new PlutoWebXmlRewriter( new ByteArrayInputStream( IOUtils.toByteArray( warIn ) ) );
                    }
                }
                
                if ( portletWarEntries.contains( earEntry.getName() ) ) {
                    portletWarEntryCount++;
                    assertNotNull( "WAR archive did not contain a portlet.xml", portletApp );
                    assertNotNull( "WAR archive did not contain a servlet.xml", webXmlRewriter );
                    assertTrue( "WAR archive did not contain any servlets", webXmlRewriter.hasServlets() );
                    assertTrue( "WAR archive did not contain any servlet mappings", webXmlRewriter.hasServletMappings() );
                    assertTrue( "WAR archive did not contain any portlets", portletApp.getPortlets().size() > 0 );
                    
                    for ( Iterator<? extends PortletDefinition> iter = portletApp.getPortlets().iterator(); iter.hasNext(); ) {
                        PortletDefinition portlet = iter.next();
                        if (! testPortlets.contains( portlet.getPortletName() ) ) {
                            fail( "Unexpected test portlet name encountered: [" + portlet.getPortletName() + "]" );
                        }
                        String servletClassName = webXmlRewriter.getServletClass( portlet.getPortletName() );
                        assertNotNull( "web.xml does not contain assembly for test portlet", servletClassName );
                        assertEquals( "web.xml does not contain correct dispatch servet", Assembler.DISPATCH_SERVLET_CLASS, 
                                servletClassName );
                    } 
                    
                }
                
                webXmlRewriter = null;
                portletApp = null;
                                
            }
            
        }
        
        assertTrue( "EAR archive did not contain any entries", earEntryCount > 0 );
        assertEquals( "EAR archive did not contain the expected test war entries.", portletWarEntries.size(), portletWarEntryCount );
        assertEquals( "WAR archive did not contain the correct number of entries",
                portletWarEntries.size() + unassembledWarEntries.size(), totalWarEntryCount );
                            
    }

    protected Assembler getAssemblerUnderTest() {
        return new EarAssembler();
    }

    protected File getFileToAssemble() {
        return earFile;
    }
}
