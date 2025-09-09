/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.container;

import java.net.MalformedURLException;
import java.net.URL;

import jakarta.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultApplicationIdResolver implements ApplicationIdResolver {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultApplicationIdResolver.class);

    private static final String WEB_XML = "/WEB-INF/web.xml";
	private static final String JNDI_PREFIX = "jndi:/";

    public String resolveApplicationId(ServletContext context) {
        try {
            URL webXmlUrl = context.getResource(WEB_XML);
            String path = webXmlUrl.toExternalForm();
            path = path.substring(0, path.indexOf(WEB_XML));

			int slash = path.lastIndexOf('/');
			if ((slash < JNDI_PREFIX.length()) && path.startsWith(JNDI_PREFIX)) {
				// Tomcat resources look like "jndi:/hostname/contextPath/WEB-INF/web.xml"
				// where "/contextPath" is "" for the ROOT context.
				// So if the last slash is the one in "jndi:/", the correct
				// result is "" and not "/hostname"
				path = "";
			} else {
				path = path.substring(slash);
			}

            int id = path.indexOf(".war");
            if(id > 0) {
                path = path.substring(0, id);
            }
            return path;
        } catch (MalformedURLException e) {
            LOG.warn("Error retrieving web.xml from ServletContext. Unable to derive contextPath.");
            return null;
        }
    }

    public int getAuthority() {
        return DEFAULT;
    }
}
