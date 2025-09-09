/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.maven;

/**
 * @goal install
 * @description the plutonium ApplicationServerHook goal installs the plutonium portal into the specified application server
 * @requiresDependencyResolution runtime
 *
 */
public class InstallMojo extends AbstractManagementMojo {

    protected void doExecute() throws Exception {
        getHandler().install(createInstallationConfig());
    }
}
