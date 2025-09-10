/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.MojoExecutionException;
import com.liferay.plutonium.util.install.InstallationConfig;
import com.liferay.plutonium.util.install.PortalInstaller;
import com.liferay.plutonium.util.install.PortalInstallerFactory;
import com.liferay.plutonium.util.install.ServerConfig;

/**
 * Abstract Mojo for installation tasks.
 *
 * @since 07/29/2005
 */
public abstract class AbstractManagementMojo extends AbstractPlutoniumMojo {

    /**
     * @parameter expression="${domain}"
     */
    protected String domain = "PlutoniumDomain";

    /**
     * @parameter expression="${server}"
     */
    protected String server = "PlutoniumServer";

    /**
     * @parameter expression="${host}"
     */
    protected String host = "localhost";

    /**
     * @parameter expression="${port}"
     */
    protected int port;

    /**
     * @component
     */
    protected ArtifactFactory artifactFactory;

    /**
     * @component
     */
    protected ArtifactResolver artifactResolver;

    /**
     * @parameter expression="${localRepository}
     */
    protected ArtifactRepository artifactRepository;

    /**
     * @parameter expression="${project.remoteArtifactRepositories}"
     */
    protected List<ArtifactRepository> remoteRepositories;

    /**
     * @parameter expression="${ctx}" default-value="plutonium"
     *
     */
    protected String portalContext;

    /**
     * @parameter expression="${pom.currentVersion} default="1.0-SNAPSHOT"
     */
    protected String version;

    protected AbstractManagementMojo() {
    	// Do nothing.
    }

    protected List<File> getSharedDependencies() throws ArtifactNotFoundException, ArtifactResolutionException {
       return getDependencies(InstallationDependency.getSharedDependencies());
    }

    protected List<File> getEndorsedDependencies() throws ArtifactNotFoundException, ArtifactResolutionException {
       return getDependencies(InstallationDependency.getEndorsedDependencies());
    }

    private List<File> getDependencies(Collection<InstallationDependency> artifacts) throws ArtifactNotFoundException, ArtifactResolutionException {
        List<File> list = new ArrayList<File>();
        Iterator<InstallationDependency> it = artifacts.iterator();
        while(it.hasNext()) {
            InstallationDependency dep = it.next();
            Artifact artifact = artifactFactory.createArtifactWithClassifier(
                    dep.getGroupId(), dep.getArtifactId(), dep.getVersion(), dep.getType(), null
            );

            artifactResolver.resolve(artifact, remoteRepositories, artifactRepository);
            if(artifact.getFile() == null) {
                getLog().warn("Unable to find file for artifact: "+artifact.getArtifactId());
            }

            list.add(artifact.getFile());
        }
        return list;
    }

    protected ServerConfig getServerConfig() {
        ServerConfig config = new ServerConfig();
        config.setDomain(domain);
        config.setHost(host);
        config.setPort(port);
        config.setServer(server);
        return config;
    }

    protected PortalInstaller getHandler() {
        return PortalInstallerFactory.getAppServerHandler(installationDirectory);
    }

    protected InstallationConfig createInstallationConfig() throws ArtifactNotFoundException, ArtifactResolutionException {
        InstallationConfig config = new InstallationConfig();
        config.setInstallationDirectory(installationDirectory);
        config.setPortalContextPath(portalContext);
        config.setPortalApplication(getPortalApplication());
        config.setPortletApplications(getPortletApplications());
        config.setEndorsedDependencies(getEndorsedDependencies());
        config.setSharedDependencies(getSharedDependencies());
        config.setServerConfig(getServerConfig());
        return config;
    }

    private File getPortalApplication() throws ArtifactNotFoundException, ArtifactResolutionException  {
        InstallationDependency dep = InstallationDependency.PORTAL;
        Artifact artifact = artifactFactory.createBuildArtifact(
           dep.getGroupId(), dep.getArtifactId(), dep.getVersion(), dep.getType()
        );
        artifactResolver.resolve(artifact, remoteRepositories, artifactRepository);
        return artifact.getFile();
    }

    private Map<String, File> getPortletApplications() throws ArtifactNotFoundException, ArtifactResolutionException {
        Map<String, File> files = new HashMap<String, File>();
        InstallationDependency dep = InstallationDependency.TESTSUITE;
        Artifact artifact = artifactFactory.createBuildArtifact(
                dep.getGroupId(), dep.getArtifactId(), dep.getVersion(), dep.getType()
        );
        artifactResolver.resolve(artifact, remoteRepositories, artifactRepository);

        files.put("testsuite", artifact.getFile());
        /*
        Iterator apps = portletApps.iterator();
        while(apps.hasNext()) {
            //files.add(artifactFactory.createBuildArtifact(
            //    InstallMojo.GROUP_ID, apps.next().toString(), version, "war"
            //).getFile());
        }
        */
        return files;
    }

    protected void doValidate() throws Exception {
        if(installationDirectory == null || !installationDirectory.exists()) {
            throw new MojoExecutionException("A valid installation directory must be provided in order to install plutonium.");

        }
    }
}
