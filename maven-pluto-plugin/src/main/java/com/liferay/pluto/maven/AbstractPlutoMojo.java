/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.maven;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * @todo Document
 * @since Jul 30, 2005
 */
public abstract class AbstractPlutoMojo extends AbstractMojo {

    /**
     * @parameter expression="${installDir}"
     */
    protected File installationDirectory;

    /**
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;

    public void execute() throws MojoExecutionException {
        // Validation of the installDir property is done by maven.
        try {
            doValidate();
            doExecute();
        } catch (MojoExecutionException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MojoExecutionException("Error Installing Pluto", ex);
        }
    }

    /**
     * Process the actual execution.
     * @throws Exception
     */
    protected abstract void doExecute() throws Exception;

    protected abstract void doValidate() throws Exception;
}
