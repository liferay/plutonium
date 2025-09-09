/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.util.install.file;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import com.liferay.plutonium.util.UtilityException;
import com.liferay.plutonium.util.install.InstallationConfig;
import com.liferay.plutonium.util.install.PortalInstaller;

/**
 * File System based installer.  Copies files to the appropriate
 * locations.
 *
 */
public abstract class FileSystemInstaller implements PortalInstaller {

    protected void copyFilesToDirectory(Collection dependencies, File destination)
    throws IOException {
        Iterator it = dependencies.iterator();
        while(it.hasNext()) {
            File from = (File)it.next();
            FileUtils.copyFileToDirectory(from, destination);
        }
    }

    protected void removeFilesFromDirectory(Collection dependencies, File destination)
            throws IOException {
        Iterator it = dependencies.iterator();
        while(it.hasNext()) {
            File from = (File)it.next();
            File delete = new File(destination, from.getName());
            delete.delete();
        }
    }

    protected void copyFileToDirectory(File file, File destination)
    throws IOException {
        FileUtils.copyFileToDirectory(file, destination);
    }

    /**
     * NOTE: Order is important.  If the server is running, we want to
     *       make sure that the correct order is preserved
     *
     * 1) Install endorsed dependencies
     * 2) Install shared dependencies
     * 4) Prep Time
     *    -- Create a domain directory for the portal
     *    -- Init the configs holder
     * 5) Install the Portlet Applications
     * 6) Install the Portal Application
     * 7) Finally, install the configs
     * @param config
     * @throws com.liferay.plutonium.util.UtilityException
     */
    public void install(InstallationConfig config) throws UtilityException {
        File endorsedDir = getEndorsedDir(config);
        File sharedDir = getSharedDir(config);
        File domainDir = getWebAppDir(config);

        endorsedDir.mkdirs();
        sharedDir.mkdirs();
        domainDir.mkdirs();

        try {
            copyFilesToDirectory(config.getEndorsedDependencies(), endorsedDir);
            copyFilesToDirectory(config.getSharedDependencies(), sharedDir);

            copyFilesToDirectory(config.getPortletApplications().values(),  domainDir);
            copyFileToDirectory(config.getPortalApplication(), domainDir);

            writeConfiguration(config);
        }
        catch(IOException io) {
            throw new UtilityException(
                "Unable to install portal to Tomcat",
                io,
                config.getInstallationDirectory()
            );
        }
    }

    public abstract void writeConfiguration(InstallationConfig config)
    throws IOException;

    protected abstract File getEndorsedDir(InstallationConfig config );

    protected abstract File getSharedDir(InstallationConfig config);

    protected abstract File getWebAppDir(InstallationConfig config);

    public void uninstall(InstallationConfig config)
    throws UtilityException {
        File endorsedDir = getEndorsedDir(config);
        File sharedDir = getSharedDir(config);
        File domainDir = getWebAppDir(config);

        endorsedDir.mkdirs();
        sharedDir.mkdirs();
        domainDir.mkdirs();

        try {
            removeFilesFromDirectory(config.getEndorsedDependencies(), endorsedDir);
            removeFilesFromDirectory(config.getSharedDependencies(), sharedDir);
            removeFilesFromDirectory(config.getPortletApplications().values(),  domainDir);

            File delete = new File(domainDir, config.getPortalApplication().getName());
            delete.delete();
        }
        catch(IOException io) {
            throw new UtilityException("Unable to remove files. ", io, config.getInstallationDirectory());
        }

   }

    public abstract boolean isValidInstallationDirectory(File installDir);
}
