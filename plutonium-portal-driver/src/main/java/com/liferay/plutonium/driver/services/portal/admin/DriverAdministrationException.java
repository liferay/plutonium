/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.portal.admin;

import jakarta.servlet.ServletException;

/**
 *
 * @author <a href="mailto:ddewolf@apache.org">David H. DeWolf</a>:
 * @version 1.0
 * @since Nov 23, 2005
 */
public class DriverAdministrationException extends ServletException {

    public DriverAdministrationException(String string) {
        super(string);
    }

    public DriverAdministrationException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public DriverAdministrationException(Throwable cause) {
        super(cause);
    }


}
