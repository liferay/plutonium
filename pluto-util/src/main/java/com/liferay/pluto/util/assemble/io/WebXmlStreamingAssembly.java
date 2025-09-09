/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.assemble.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.liferay.pluto.container.impl.PortletAppDescriptorServiceImpl;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;
import com.liferay.pluto.container.om.portlet.PortletDefinition;
import com.liferay.pluto.util.assemble.Assembler;
import com.liferay.pluto.util.descriptors.web.PlutoWebXmlRewriter;

/**
 * Utility class responsible for accepting web.xml and portlet.xml as InputStreams, and assembling the web.xml to an
 * OutputStream.
 */
public class WebXmlStreamingAssembly
{
    /**
     * Assembles the web.xml represented by the <code>InputStream</code>.
     * 
     * @param webXmlIn
     *            the unassembled web.xml file
     * @param portletXmlIn
     *            the corresponding portlet.xml file
     * @param assembledWebXmlOut
     *            the assembled web.xml file
     * @param dispatchServletClass
     *            the dispatch servlet
     * @throws IOException
     */
    public static void assembleStream(InputStream webXmlIn, InputStream portletXmlIn, OutputStream assembledWebXmlOut,
                                      String dispatchServletClass) throws IOException
    {
        if (dispatchServletClass == null || dispatchServletClass.length() == 0 ||
            dispatchServletClass.trim().length() == 0)
        {
            dispatchServletClass = Assembler.DISPATCH_SERVLET_CLASS;
        }
        PlutoWebXmlRewriter webXmlRewriter = null;
        try
        {
            webXmlRewriter = new PlutoWebXmlRewriter(webXmlIn);
        }
        catch (Exception e)
        {
            if (e instanceof IOException)
            {
                throw (IOException) e;
            }
            throw new IOException(e.getMessage());
        }
        PortletApplicationDefinition portletAppDD = new PortletAppDescriptorServiceImpl().read("test", "/test", portletXmlIn);
        portletXmlIn.close();
        for (PortletDefinition portlet : portletAppDD.getPortlets())
        {
            webXmlRewriter.injectPortletServlet(dispatchServletClass, portlet.getPortletName());
        }
        webXmlRewriter.write(assembledWebXmlOut);
    }
}
