/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.maven;

import com.liferay.pluto.util.install.InstallationConfig;


/**
 * @goal reinstall
 * @requiresDependencyResolution runtime
 */
public class ReinstallMojo extends AbstractManagementMojo {

    public ReinstallMojo() {

    }

    protected void doExecute() throws Exception {
        InstallationConfig config = createInstallationConfig();
        getHandler().uninstall(config);
        getHandler().install(config);
    }
}
