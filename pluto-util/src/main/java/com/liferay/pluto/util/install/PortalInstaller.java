/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.install;

import java.io.File;

import com.liferay.pluto.util.UtilityException;

public interface PortalInstaller {

    void install(InstallationConfig config) throws UtilityException, UtilityException;

    void uninstall(InstallationConfig config) throws UtilityException;

    boolean isValidInstallationDirectory(File installDir);
}
