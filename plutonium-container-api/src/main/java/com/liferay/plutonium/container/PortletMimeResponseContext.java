/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import jakarta.portlet.CacheControl;

/**
 * @author <a href="mailto:ate@douma.nu">Ate Douma</a>
 * @version $Id$
 */
public interface PortletMimeResponseContext extends PortletResponseContext
{
    CacheControl getCacheControl();
    PortletURLProvider getPortletURLProvider(PortletURLProvider.TYPE type);
    Locale getLocale();
    String getContentType();
    void setContentType(String contentType);
    String getCharacterEncoding();
    OutputStream getOutputStream() throws IOException, IllegalStateException;
    PrintWriter getWriter() throws IOException, IllegalStateException;
    int getBufferSize();
    void setBufferSize(int size);
    void reset();
    void resetBuffer();
    void flushBuffer() throws IOException;
    boolean isCommitted();
}
