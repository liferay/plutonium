/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import java.util.Locale;

import jakarta.portlet.PortletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.plutonium.container.PortletContainer;
import com.liferay.plutonium.container.PortletRequestContext;
import com.liferay.plutonium.container.PortletResourceResponseContext;
import com.liferay.plutonium.container.PortletWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version $Id$
 *
 */
public class PortletResourceResponseContextImpl extends PortletMimeResponseContextImpl implements
                PortletResourceResponseContext
{
   private static final Logger    LOGGER  = LoggerFactory.getLogger(PortletResourceResponseContextImpl.class);
   private static final boolean   isTrace = LOGGER.isTraceEnabled();
    
    public PortletResourceResponseContextImpl(PortletContainer container, HttpServletRequest containerRequest,
          HttpServletResponse containerResponse, PortletWindow window, PortletRequestContext requestContext)
    {        
        super(container, containerRequest, containerResponse, window, requestContext);
        setLifecycle(PortletRequest.RESOURCE_PHASE);
    }

    public void setCharacterEncoding(String charset)
    {
        if (isTrace) {
           StringBuilder txt = new StringBuilder("Setting character encoding.");
           txt.append(" charset: ").append(charset);
           txt.append(" isClosed: ").append(isClosed());
           LOGGER.trace(txt.toString());
        }
        if (!isClosed())
        {
            getServletResponse().setCharacterEncoding(charset);
        }
    }

    public void setContentLength(int len)
    {
        if (!isClosed())
        {
            getServletResponse().setContentLength(len);
        }
    }

    @Override
    public void setContentLengthLong(long len)
    {
        if (!isClosed())
        {
            getServletResponse().setContentLengthLong(len);
        }
    }

    public void setLocale(Locale locale)
    {
        if (!isClosed())
        {
            getServletResponse().setLocale(locale);
        }
    }

   public void setStatus(int sc) {
      if (isTrace) {
         StringBuilder txt = new StringBuilder("Setting character encoding.");
         txt.append(" status code: ").append(sc);
         txt.append(" isClosed: ").append(isClosed());
         LOGGER.trace(txt.toString());
      }
      if (!isClosed()) {
         getServletResponse().setStatus(sc);
      }
   }

   @Override
   public int getStatus() {
      return getServletResponse().getStatus();
   }
}
