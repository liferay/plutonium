/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.liferay.pluto.driver.config.AdminConfiguration;
import com.liferay.pluto.driver.services.portal.admin.DriverAdministrationException;
import com.liferay.pluto.driver.services.portal.admin.PortletRegistryAdminService;


/**
 * Publishing administrative servlet.
 * Allows external clients to connect and notify the portal
 * of available portlet applications.
 *
 * @version 1.0
 * @since Nov 23, 2005
 */
public class PublishServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
    throws ServletException, IOException {

        String context = req.getParameter("context");
        try {
            doPublish(context);
        }
        catch(Throwable t) {
            StringBuffer sb = new StringBuffer();
            sb.append("Unable to publish portlet application bound to context '"+context+"'.");
            sb.append("Reason: ").append(t.getMessage());
            res.getWriter().println(sb.toString());
        }
    }

    private void doPublish(String context) throws DriverAdministrationException {
        AdminConfiguration adminConfig = (AdminConfiguration)getServletContext()
            .getAttribute(AttributeKeys.DRIVER_ADMIN_CONFIG);

        PortletRegistryAdminService admin = adminConfig.getPortletRegistryAdminService();

        admin.addPortletApplication(context);
    }
}
