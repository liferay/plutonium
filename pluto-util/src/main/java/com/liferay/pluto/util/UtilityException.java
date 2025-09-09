/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util;

import java.io.File;

/**
 * Exception thrown if an error occurs while managing
 * a portal instance.
 */
public class UtilityException extends Exception {

    private File installDir;

    public UtilityException() {
    }

    public UtilityException(String message) {
        super(message);
    }

    public UtilityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilityException(Throwable cause) {
        super(cause);
    }

    public UtilityException(String message, Throwable cause, File installDir) {
        super(message, cause);
        this.installDir = installDir;
    }

    public File getInstallDir() {
        return installDir;
    }

}
