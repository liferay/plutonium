/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;

/**
 * Read/Write services for Portlet Application configuration
 * This service reads the portlet.xml and converts it to a
 * standard bean model.
 *
 * @author <a href="ddewolf@apache.org">David H. DeWolf</a>
 * @since Mar 6, 2005
 */
public interface PortletAppDescriptorService {

    /**
     * Retrieve the PortletApp deployment descriptor
     * (portlet.xml).
     * @return Object representation of the descriptor.
     * @throws IOException if an IO error occurs.
     */
    PortletApplicationDefinition read(String name, String contextPath, InputStream in) throws IOException;

    /**
     * Merge web.xml descriptor meta data with the PortletApplicationDefinition.
     * The Portlet container needs access to (at a minimum) the servlet-mapping url-patterns (PLT.19.3.8)
     * and the optional locale-encoding-mapping-list (PLT.12.7.1)
     * @param pa the PortletApplicationDefinition
     * @param webDescriptor the web.xml InputStream
     * @throws IOException
     */
    void mergeWebDescriptor(PortletApplicationDefinition pa, InputStream webDescriptor) throws Exception;

    /**
     * Write the PortletApp deployment descriptor
     * (portlet.xml).
     * @param portletDescriptor
     * @param out
     * @throws IOException if an IO error occurs.
     */
    void write(PortletApplicationDefinition portletDescriptor, OutputStream out) throws IOException;
}
