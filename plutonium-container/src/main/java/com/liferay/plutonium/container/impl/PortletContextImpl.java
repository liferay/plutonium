/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.servlet.ServletContext;

import com.liferay.plutonium.container.ContainerInfo;
import com.liferay.plutonium.container.RequestDispatcherService;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default Portlet Context Implementation.
 * 
 * @version $Id$
 */
public class PortletContextImpl implements PortletContext
{
   
   /** Logger. */
   private static final Logger LOG = LoggerFactory.getLogger(PortletContextImpl.class);
   private static final boolean isDebug = LOG.isDebugEnabled();
   
    // Private Member Variables ------------------------------------------------
    
    protected ServletContext servletContext;
    protected PortletApplicationDefinition portletApp;
    protected ContainerInfo containerInfo;
    protected List<String> supportedContainerRuntimeOptions;
    protected RequestDispatcherService rdService;

    // Constructor -------------------------------------------------------------
    
    /**
     * Constructs an instance.
     * @param servletContext  the servlet context in which we are contained.
     * @param portletApp  the portlet application descriptor.
     */
    public PortletContextImpl(ServletContext servletContext,
                              PortletApplicationDefinition portletApp, 
                              ContainerInfo containerInfo, 
                              List<String> supportedContainerRuntimeOptions,
                              RequestDispatcherService rdService)
    {
        this.servletContext = servletContext;
        this.portletApp = portletApp;
        this.containerInfo = containerInfo;
        this.supportedContainerRuntimeOptions = supportedContainerRuntimeOptions;
        this.rdService = rdService;
    }
    
    // PortletContext Impl -----------------------------------------------------
    
    /**
     * Retrieve the PortletContainer's server info.
     * @return the server info in the form of <i>Server/Version</i>
     */
    public String getServerInfo() {
        return containerInfo.getServerInfo();
    }
    
    public PortletRequestDispatcher getRequestDispatcher(String path)
    {
        return rdService.getRequestDispatcher(servletContext, portletApp, path);
    }
    
    public PortletRequestDispatcher getNamedDispatcher(String name)
    {
        return rdService.getNamedDispatcher(servletContext, portletApp, name);
    }

    public InputStream getResourceAsStream(String path) {
        return servletContext.getResourceAsStream(path);
    }

    public int getMajorVersion() {
        return containerInfo.getMajorSpecificationVersion();
    }

    public int getMinorVersion() {
        return containerInfo.getMinorSpecificationVersion();
    }

    public String getMimeType(String file) {
        return servletContext.getMimeType(file);
    }

    public String getRealPath(String path) {
        return servletContext.getRealPath(path);
    }

    public Set<String> getResourcePaths(String path) {
        return servletContext.getResourcePaths(path);
    }

    public URL getResource(String path)
        throws java.net.MalformedURLException {
        if (path == null || !path.startsWith("/")) {
            throw new MalformedURLException("path must start with a '/'");
        }
        return servletContext.getResource(path);
    }

    public Object getAttribute(java.lang.String name) {
        if (name == null) {
            throw new IllegalArgumentException("Attribute name == null");
        }

        return servletContext.getAttribute(name);
    }

    public Enumeration<String> getAttributeNames() {
        return servletContext.getAttributeNames();
    }

    public String getInitParameter(java.lang.String name) {
        if (name == null) {
            throw new IllegalArgumentException("Parameter name == null");
        }

        return servletContext.getInitParameter(name);
    }

    public Enumeration<String> getInitParameterNames() {
        return servletContext.getInitParameterNames();
    }

    public void log(java.lang.String msg) {
        servletContext.log(msg);
    }

    public void log(java.lang.String message, Throwable throwable) {
        servletContext.log(message, throwable);
    }

    public void removeAttribute(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Attribute name == null");
        }

        servletContext.removeAttribute(name);
    }

    public void setAttribute(String name, Object object) {
        if (name == null) {
            throw new IllegalArgumentException("Attribute name == null");
        }

        servletContext.setAttribute(name, object);
    }

    public String getPortletContextName() {
        return servletContext.getServletContextName();
    }
    
    
    public ServletContext getServletContext() {
        return servletContext;
    }

    public PortletApplicationDefinition getPortletApplicationDefinition() {
        return portletApp;
    }

	public Enumeration<String> getContainerRuntimeOptions() {
	    return Collections.enumeration(supportedContainerRuntimeOptions);
	}
	
	private int[] getVersion() {
	   int[] vers = {0, 0};
      String[] toks = portletApp.getVersion().split("\\.");
	   try {
	      if (toks.length != 2) {
	         if (isDebug) {
               StringBuilder txt = new StringBuilder();
               txt.append("Problem parsing version. Version string: ").append(portletApp.getVersion());
               txt.append(", tokens: ").append(Arrays.toString(toks));
               LOG.debug(txt.toString());
            }
	      } else {
	         vers[0] = Integer.parseInt(toks[0]);
	         vers[1] = Integer.parseInt(toks[1]);
	      }
	   } catch (Exception e) {
         StringBuilder txt = new StringBuilder();
         txt.append("Problem parsing version. Version string: ").append(portletApp.getVersion());
         txt.append(", tokens: ").append(Arrays.toString(toks));
         LOG.debug(txt.toString());
	   }
	   return vers;
	}

   @Override
   public int getEffectiveMajorVersion() {
      return getVersion()[0];
   }

   @Override
   public int getEffectiveMinorVersion() {
      return getVersion()[1];
   }

   @Override
   public String getContextPath() {
      return portletApp.getContextPath();
   }

   @Override
   public ClassLoader getClassLoader() {
      return servletContext.getClassLoader();
   }
}

