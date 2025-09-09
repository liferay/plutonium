/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.impl.resource;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.plutonium.driver.services.portal.PageConfig;
import com.liferay.plutonium.driver.services.portal.RenderConfig;
import org.xml.sax.SAXException;

/**
 * @version 1.0
 * @since Sep 23, 2004
 */
public class ResourceConfigReader {

    private static final Logger LOG = LoggerFactory.getLogger(
        ResourceConfigReader.class
    );

    public static final String CONFIG_FILE =
        "/WEB-INF/plutonium-portal-driver-config.xml";


    private static ResourceConfigReader factory;

    public static ResourceConfigReader getFactory() {
        if (factory == null) {
            factory = new ResourceConfigReader();
        }
        return factory;
    }

    private Digester digester;

    private ResourceConfigReader() {
        digester = new Digester();
        // digester.setLogger(LOG);  // Too many log messages.
        digester.setClassLoader(Thread.currentThread().getContextClassLoader());
        init();
    }

    public ResourceConfig parse(InputStream in)
        throws IOException, SAXException {
        return (ResourceConfig) digester.parse(in);
    }

// Digester Setup

    private void init() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Setting up digester...");
        }
        digester.addObjectCreate(
            "plutonium-portal-driver",
            ResourceConfig.class
        );
        digester.addBeanPropertySetter(
            "plutonium-portal-driver/portal-name",
            "portalName"
        );
        digester.addBeanPropertySetter(
            "plutonium-portal-driver/portal-version",
            "portalVersion"
        );
        digester.addBeanPropertySetter(
            "plutonium-portal-driver/container-name",
            "containerName"
        );

        digester.addCallMethod(
            "plutonium-portal-driver/supports/portlet-mode",
            "addSupportedPortletMode", 0
        );
        digester.addCallMethod(
            "plutonium-portal-driver/supports/window-state",
            "addSupportedWindowState", 0
        );
        
        // Page resources
        digester.addCallMethod(
              "plutonium-portal-driver/page-resources/page-resource",
              "addResource", 5);
        digester.addCallParam(
             "plutonium-portal-driver/page-resources/page-resource/identifier/name", 0);
        digester.addCallParam(
              "plutonium-portal-driver/page-resources/page-resource/identifier/scope", 1);
        digester.addCallParam(
              "plutonium-portal-driver/page-resources/page-resource/identifier/version", 2);
        digester.addCallParam(
              "plutonium-portal-driver/page-resources/page-resource/source", 3, "type");
        digester.addCallParam(
              "plutonium-portal-driver/page-resources/page-resource/source", 4);

        
        // Default resources for each page
        digester.addCallMethod(
              "plutonium-portal-driver/page-resources/default-resource",
              "addDefaultPageDependency", 3);
        digester.addCallParam(
             "plutonium-portal-driver/page-resources/default-resource/name", 0);
        digester.addCallParam(
              "plutonium-portal-driver/page-resources/default-resource/scope", 1);
        digester.addCallParam(
              "plutonium-portal-driver/page-resources/default-resource/version", 2);

        
        // render configuration 
        digester.addObjectCreate(
            "plutonium-portal-driver/render-config",
            RenderConfig.class
        );
        digester.addSetProperties(
            "plutonium-portal-driver/render-config",
            "default", "defaultPageId"
        );
        
        // handle individual pages with portlets
        digester.addObjectCreate(
            "plutonium-portal-driver/render-config/page",
            PageConfig.class
        );
        digester.addSetProperties("plutonium-portal-driver/render-config/page");
        digester.addCallMethod(
            "plutonium-portal-driver/render-config/page/portlet", "addPortlet", 2
        );
        digester.addCallParam(
            "plutonium-portal-driver/render-config/page/portlet",
            0, "context"
        );
        digester.addCallParam(
            "plutonium-portal-driver/render-config/page/portlet",
            1, "name"
        );
        
        // Process any page-level dependencies
        digester.addCallMethod(
              "plutonium-portal-driver/render-config/page/dependency",
              "addPageDependency", 3);
        digester.addCallParam(
             "plutonium-portal-driver/render-config/page/dependency/name", 0);
        digester.addCallParam(
              "plutonium-portal-driver/render-config/page/dependency/scope", 1);
        digester.addCallParam(
              "plutonium-portal-driver/render-config/page/dependency/version", 2);
        
        digester.addSetNext(
            "plutonium-portal-driver/render-config/page",
            "addPage"
        );
        digester.addSetNext(
            "plutonium-portal-driver/render-config",
            "setRenderConfig"
        );
    }

}

