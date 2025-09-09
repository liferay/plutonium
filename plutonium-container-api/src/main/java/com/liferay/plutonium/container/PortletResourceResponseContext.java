/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container;

import java.util.Locale;

/**
 * @author <a href="mailto:ate@douma.nu">Ate Douma</a>
 * @version $Id$
 */
public interface PortletResourceResponseContext extends PortletMimeResponseContext
{
    void setLocale(Locale locale);
    void setCharacterEncoding(String charset);
    void setContentLength(int len);
    void setStatus(int sc);
    int getStatus();
    void setContentLengthLong(long len);
}
