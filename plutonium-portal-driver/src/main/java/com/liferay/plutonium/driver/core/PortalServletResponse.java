/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import jakarta.servlet.http.HttpServletResponse;

public class PortalServletResponse
    extends jakarta.servlet.http.HttpServletResponseWrapper {

    private StringWriter buffer = null;
    private PrintWriter writer = null;

    public PortalServletResponse(HttpServletResponse response) {
        super(response);
        buffer = new StringWriter();
        writer = new PrintWriter(buffer);
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public StringWriter getInternalBuffer() {
        return buffer;
    }

    public PrintWriter getInternalResponseWriter()
        throws IOException {
        return super.getWriter();
    }

    @Override
    public void flushBuffer() throws IOException
    {
    }

    @Override
    public int getBufferSize()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isCommitted()
    {
        return false;
    }

    @Override
    public void reset()
    {
        resetBuffer();
    }

    @Override
    public void resetBuffer()
    {
        buffer.getBuffer().setLength(0);
    }

    @Override
    public void setBufferSize(int size)
    {
    }
}
