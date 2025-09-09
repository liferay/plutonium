/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import jakarta.portlet.ClientDataRequest;
import jakarta.portlet.PortletException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

import com.liferay.pluto.container.PortletRequestContext;
import com.liferay.pluto.container.PortletResponseContext;

/**
 * Implementation of the <code>jakarta.portlet.ClientDataRequest</code> interface.
 */
public abstract class ClientDataRequestImpl extends PortletRequestImpl implements ClientDataRequest
{
    /** Flag indicating if the HTTP body reader has been accessed. */
    protected boolean readerAccessed = false;

    public ClientDataRequestImpl(PortletRequestContext requestContext, PortletResponseContext responseContext, String lifecyclePhase)
    {
        super(requestContext, responseContext, lifecyclePhase);
    }

    private void checkPostedFormData() 
    {
        if (getMethod().equals("POST"))
        {
            String contentType = getContentType();
            if (contentType == null || contentType.equals("application/x-www-form-urlencoded"))
            {
                throw new IllegalStateException("User request HTTP POST data is of type "
                                                + "application/x-www-form-urlencoded. "
                                                + "This data has been already processed "
                                                + "by the portlet-container and is available "
                                                + "as request parameters.");
            }
        }
    }
        
    public java.lang.String getCharacterEncoding()
    {
        return getServletRequest().getCharacterEncoding();
    }

    public int getContentLength()
    {
        return getServletRequest().getContentLength();
    }

    @Override
    public long getContentLengthLong()
    {
        return getServletRequest().getContentLengthLong();
    }

    @Override
    public Part getPart(String name) throws IOException, PortletException
    {
       try {
          return getServletRequest().getPart(name);
       } catch (ServletException e) {
          throw new PortletException(e);
       }
    }

    @Override
    public Collection<Part> getParts() throws IOException, PortletException
    {
       try {
          return getServletRequest().getParts();
       } catch (ServletException e) {
          throw new PortletException(e);
       }
    }

    public java.lang.String getContentType()
    {
        return getServletRequest().getContentType();
    }
    
    public String getMethod()
    {
        return getServletRequest().getMethod();
    }

    public InputStream getPortletInputStream() throws IOException
    {
        checkPostedFormData();
        // the HttpServletRequest will ensure that a IllegalStateException is thrown
        //   if getReader() was called earlier        
        return getServletRequest().getInputStream();
    }

    public BufferedReader getReader()
    throws UnsupportedEncodingException, IOException 
    {
        checkPostedFormData();
        // the HttpServletRequest will ensure that a IllegalStateException is thrown
        //   if getInputStream() was called earlier
        BufferedReader reader = getServletRequest().getReader();
        readerAccessed = true;
        return reader;

    }
    
    public void setCharacterEncoding(String encoding)
    throws UnsupportedEncodingException 
    {
        if (readerAccessed) 
        {
            throw new IllegalStateException("Cannot set character encoding "
                    + "after HTTP body reader is accessed.");
        }
        getServletRequest().setCharacterEncoding(encoding);
    }
}
