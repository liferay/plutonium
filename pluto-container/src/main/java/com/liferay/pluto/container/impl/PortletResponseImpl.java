/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.pluto.container.impl;

import java.util.Collection;
import java.util.Enumeration;

import jakarta.portlet.PortalContext;
import jakarta.portlet.PortletMode;
import jakarta.portlet.PortletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.pluto.container.PortletContainer;
import com.liferay.pluto.container.PortletResponseContext;
import com.liferay.pluto.container.PortletWindow;
import com.liferay.pluto.container.ResourceURLProvider;
import com.liferay.pluto.container.om.portlet.CustomPortletMode;
import com.liferay.pluto.container.om.portlet.PortletDefinition;
import com.liferay.pluto.container.om.portlet.Supports;
import com.liferay.pluto.container.util.ArgumentUtility;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/**
 * Abstract <code>jakarta.portlet.PortletResponse</code> implementation.
 */
public abstract class PortletResponseImpl implements PortletResponse
{
	
    private String namespace;    
    private PortletResponseContext responseContext;
    
    // Constructor -------------------------------------------------------------
    
    public PortletResponseImpl(PortletResponseContext responseContext) {
        this.responseContext = responseContext;
    }
    
    protected PortletResponseContext getResponseContext()
    {
        return responseContext;
    }
    
    protected PortletWindow getPortletWindow() 
    {
        return responseContext.getPortletWindow();
    }

    protected PortletContainer getPortletContainer() 
    {
        return responseContext.getContainer();
    }
    
    protected PortalContext getPortalContext()
    {
        return getPortletContainer().getContainerServices().getPortalContext();
    }

    protected HttpServletRequest getServletRequest()
    {
        return responseContext.getServletRequest();
    }
    
    protected HttpServletResponse getServletResponse()
    {
        return responseContext.getServletResponse();
    }

    protected boolean isPortletModeAllowed(PortletMode mode)
    {
        if(PortletMode.VIEW.equals(mode))
        {
            return true;
        }
        
        String modeName = mode.toString();

        PortletDefinition dd = getPortletWindow().getPortletDefinition();

        for (Supports sup : dd.getSupports())
        {
            for (String m : sup.getPortletModes())
            {
                if (m.equalsIgnoreCase(modeName)) 
                {
                    // check if a portlet managed mode which is always allowed.
                    CustomPortletMode cpm = dd.getApplication().getCustomPortletMode(modeName);
                    if (cpm != null && !cpm.isPortalManaged())
                    {
                        return true;
                    }
                    Enumeration<PortletMode> supportedModes = getPortalContext().getSupportedPortletModes();
                    while (supportedModes.hasMoreElements()) 
                    {
                        if (supportedModes.nextElement().equals(mode)) 
                        {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    // PortletResponse Impl ----------------------------------------------------
    
    public void addProperty(Cookie cookie)
    {
        ArgumentUtility.validateNotNull("cookie", cookie);
        responseContext.addProperty(cookie);
    }

    public void addProperty(String key, Element element)
    {
       ArgumentUtility.validateNotEmpty("key", key);
       ArgumentUtility.validateNotNull("element", element);
       responseContext.addProperty(key, element);
    }


    public void addProperty(String key, String value)
    {
    	ArgumentUtility.validateNotEmpty("key", key);
    	responseContext.addProperty(key, value);
    }
    
    public Element createElement(String tagName) throws DOMException
    {
        ArgumentUtility.validateNotEmpty("tagName", tagName);
        return responseContext.createElement(tagName);
    }
    
    public String encodeURL(String path)
    {
        if (path.indexOf("://") == -1 && !path.startsWith("/"))
        {
            throw new IllegalArgumentException("only absolute URLs or full path URIs are allowed");
        }
                
        ResourceURLProvider provider = responseContext.getResourceURLProvider();
        if (path.indexOf("://") != -1) {
            provider.setAbsoluteURL(path);
        } else {
            provider.setFullPath(path);
        }
        return getServletResponse().encodeURL(provider.toString());
    }
    
    public String getNamespace()
    {
        if (namespace == null)
        {
             namespace = getPortletContainer().getContainerServices().getNamespaceMapper().encode(getPortletWindow().getId(), "");
             StringBuffer validNamespace = new StringBuffer();
             for (int i = 0; i < namespace.length(); i++)
             {
                char ch = namespace.charAt(i);
                if (Character.isJavaIdentifierPart(ch))
                {
                    validNamespace.append(ch);
                } 
                else
                {
                    validNamespace.append('_');
                }
             }
             namespace = validNamespace.toString();
        }
        return namespace;
    }
    
    public void setProperty(String key, String value)
    {
        ArgumentUtility.validateNotEmpty("key", key);
        responseContext.setProperty(key, value);
    }

    @Override
    public String getProperty(String key) {
       return responseContext.getProperty(key);
    }

    @Override
    public Collection<String> getPropertyValues(String name) {
       return responseContext.getPropertyValues(name);
    }

    @Override
    public Collection<String> getPropertyNames() {
       return responseContext.getPropertyNames();
    }
}
