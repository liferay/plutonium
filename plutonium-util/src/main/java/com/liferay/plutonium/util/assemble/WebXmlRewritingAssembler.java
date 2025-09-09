/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.util.assemble;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.liferay.plutonium.util.assemble.io.WebXmlStreamingAssembly;

/**
 * @version $Revision$
 * @todo fix direct dependency on plutonium-descriptor-impl
 */
public abstract class WebXmlRewritingAssembler implements Assembler {
    
    //protected final JarStreamingAssembly jarAssembler = new JarStreamingAssembly();
    //protected final WebXmlStreamingAssembly webXmlAssembler = new WebXmlStreamingAssembly();

    /**
     * Updates the webapp descriptor by injecting portlet wrapper servlet
     * definitions and mappings.
     *
     * @param webXmlIn  input stream to the webapp descriptor, it will be closed before the web xml is written out.
     * @param portletXmlIn  input stream to the portlet app descriptor, it will be closed before the web xml is written out.
     * @param webXmlOut output stream to the webapp descriptor, it will be flushed and closed.
     * @param dispatchServletClass The name of the servlet class to use for
     *                         handling portlet requests
     * @throws IOException
     */
    protected void updateWebappDescriptor(InputStream webXmlIn,
                                              InputStream portletXmlIn,
                                              OutputStream webXmlOut,
                                              String dispatchServletClass)
    throws IOException {

        WebXmlStreamingAssembly.assembleStream(webXmlIn, portletXmlIn, webXmlOut, dispatchServletClass);
        
    }
}
