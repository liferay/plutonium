/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.impl;

import jakarta.portlet.CacheControl;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.RenderRequest;

import com.liferay.pluto.container.PortletMimeResponseContext;
import com.liferay.pluto.container.PortletRenderResponseContext;
import com.liferay.pluto.container.PortletRequestContext;

/**
 * Implementation of the <code>jakarta.portlet.RenderRequest</code> interface.
 * 
 */
public class RenderRequestImpl extends PortletRequestImpl implements RenderRequest 
{
    private CacheControl cacheControl;
    
    public RenderRequestImpl(PortletRequestContext requestContext, PortletRenderResponseContext responseContext) 
    {
        super(requestContext, responseContext, PortletRequest.RENDER_PHASE);
        this.cacheControl = responseContext.getCacheControl();
    }
    
    /**
     * For use in HeaderRequestImpl constructor.
     * @param requestContext
     * @param responseContext
     * @param lifecyclePhase
     */
    public RenderRequestImpl(PortletRequestContext requestContext, PortletMimeResponseContext responseContext, String lifecyclePhase) 
    {
        super(requestContext, responseContext, lifecyclePhase);
        this.cacheControl = responseContext.getCacheControl();
    }

    @Override
    public String getProperty(String name)
    {
        String result = getMimeRequestProperty(name, cacheControl);
        return result != null ? result : super.getProperty(name);
   }

    public String getETag()
    {
        return cacheControl.getETag();
    }
}
