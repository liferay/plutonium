/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.util.assemble.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
import com.liferay.plutonium.container.PortletAppDescriptorService;
import com.liferay.plutonium.container.impl.PortletAppDescriptorServiceImpl;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.om.portlet.PortletDefinition;
import com.liferay.plutonium.util.assemble.Assembler;
import com.liferay.plutonium.util.descriptors.web.PlutoWebXmlRewriter;

/**
 * This test class directly tests the Jar streaming assembly,
 * bypassing the file assemblers (EarAssembler, WarAssembler).
 */
public class AssemblyStreamTest extends TestCase {
    
    private InputStream webXmlIn = null;
    private InputStream portletXmlIn = null;    
    private InputStream warIn = null;
    private JarStreamingAssembly jarAssemblerUnderTest = null;
    private WebXmlStreamingAssembly webXmlAssemblerUnderTest = null;
    
    private static final String webXmlToAssembleResource = "/com/liferay/plutonium/util/assemble/file/web.xml";
    private static final String portletXmlResource = "/com/liferay/plutonium/util/assemble/file/portlet.xml";
    private static final String warToAssembleResource = "/com/liferay/plutonium/util/assemble/war/WarDeployerTestPortlet.war";
    
    private static final String testPortletName = "WarTestPortletName";    
    
    protected void setUp() throws Exception {
        webXmlIn = getClass().getResourceAsStream( webXmlToAssembleResource );
        portletXmlIn = getClass().getResourceAsStream( portletXmlResource );
        warIn = getClass().getResourceAsStream( warToAssembleResource );
        jarAssemblerUnderTest = new JarStreamingAssembly();
        webXmlAssemblerUnderTest = new WebXmlStreamingAssembly();
    }
    
    public void testJarStreamingAssembly() throws Exception {
        File warFileOut = File.createTempFile( "streamingAssemblyWarTest", ".war" );
        JarInputStream jarIn = new JarInputStream( warIn );
        JarOutputStream warOut = new JarOutputStream( new FileOutputStream( warFileOut ) );
        jarAssemblerUnderTest.assembleStream( jarIn, warOut, Assembler.DISPATCH_SERVLET_CLASS );
        assertTrue( "Assembled WAR file was not created.", warFileOut.exists() );
        verifyAssembly( warFileOut );
        warFileOut.delete();
    }
    
    public void testWebXmlStreamingAssembly() throws Exception {
        File assembledWebXml = File.createTempFile( "streamingWebXmlTest", ".xml" );
        OutputStream assembledWebXmlOut = new FileOutputStream( assembledWebXml );
        webXmlAssemblerUnderTest.assembleStream(webXmlIn, portletXmlIn, assembledWebXmlOut, Assembler.DISPATCH_SERVLET_CLASS);
        verifyAssembly( new FileInputStream( assembledWebXml ), 
                getClass().getResourceAsStream( portletXmlResource ) );
        assembledWebXml.delete();
    }
    
    protected void verifyAssembly( InputStream webXml, InputStream portletXml ) throws Exception {
        PortletAppDescriptorService portletSvc = new PortletAppDescriptorServiceImpl();
        PlutoWebXmlRewriter webXmlRewriter = new PlutoWebXmlRewriter( webXml );
        PortletApplicationDefinition portletApp = portletSvc.read( "test", "/test", portletXml );
        
        assertNotNull( "Web Application Descripter was null.", webXmlRewriter );
        assertNotNull( "Portlet Application Descriptor was null.", portletApp );
        assertTrue( "Portlet Application Descriptor doesn't define any portlets.", portletApp.getPortlets().size() > 0 );
        assertTrue( "Web Application Descriptor doesn't define any servlets.", webXmlRewriter.hasServlets() );
        assertTrue( "Web Application Descriptor doesn't define any servlet mappings.", webXmlRewriter.hasServletMappings() );
        
        PortletDefinition portlet = (PortletDefinition) portletApp.getPortlets().iterator().next();
        assertTrue( "Unable to retrieve test portlet named [" + testPortletName + "]", portlet.getPortletName().equals( testPortletName ) );
        
        String servletClassName = webXmlRewriter.getServletClass( testPortletName );
        assertNotNull( "Unable to retrieve portlet dispatch for portlet named [" + testPortletName + "]", servletClassName );        
        assertEquals( "Dispatcher servlet incorrect for test portlet [" + testPortletName + "]",  Assembler.DISPATCH_SERVLET_CLASS, servletClassName );        
    }

    protected void verifyAssembly( File warFile ) throws Exception {
        PlutoWebXmlRewriter webXmlRewriter = null;
        PortletAppDescriptorService portletSvc = new PortletAppDescriptorServiceImpl();
        int entryCount = 0;
        ByteArrayOutputStream portletXmlBytes = new ByteArrayOutputStream();
        ByteArrayOutputStream webXmlBytes = new ByteArrayOutputStream();
        PortletApplicationDefinition portletApp = null;        
                
        JarInputStream assembledWarIn = new JarInputStream( new FileInputStream( warFile ) );
        JarEntry tempEntry;
        
        while ( ( tempEntry = assembledWarIn.getNextJarEntry() ) != null  ) {
            entryCount++;
            
            if ( Assembler.PORTLET_XML.equals( tempEntry.getName() ) ) {
                IOUtils.copy( assembledWarIn, portletXmlBytes );
                portletApp = portletSvc.read( "test", "/test", new ByteArrayInputStream( portletXmlBytes.toByteArray() ) );
            }
            if ( Assembler.SERVLET_XML.equals( tempEntry.getName() ) ) {
                IOUtils.copy( assembledWarIn, webXmlBytes );
                webXmlRewriter = new PlutoWebXmlRewriter( new ByteArrayInputStream( webXmlBytes.toByteArray() ) );
            }
        }
        
        assertTrue( "Assembled WAR file was empty.", entryCount > 0 );
        assertNotNull( "Web Application Descripter was null.", webXmlRewriter );
        assertNotNull( "Portlet Application Descriptor was null.", portletApp );
        assertTrue( "Portlet Application Descriptor doesn't define any portlets.", portletApp.getPortlets().size() > 0 );
        assertTrue( "Web Application Descriptor doesn't define any servlets.", webXmlRewriter.hasServlets() );
        assertTrue( "Web Application Descriptor doesn't define any servlet mappings.", webXmlRewriter.hasServletMappings() );

        PortletDefinition portlet = (PortletDefinition) portletApp.getPortlets().iterator().next();
        assertTrue( "Unable to retrieve test portlet named [" + testPortletName + "]", portlet.getPortletName().equals( testPortletName ) );

        String servletClassName = webXmlRewriter.getServletClass( testPortletName );
        assertNotNull( "Unable to retrieve portlet dispatch for portlet named [" + testPortletName + "]", servletClassName );        
        assertEquals( "Dispatcher servlet incorrect for test portlet [" + testPortletName + "]",  Assembler.DISPATCH_SERVLET_CLASS, servletClassName );
    }
}
