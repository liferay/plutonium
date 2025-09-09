/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.util.install.file.tomcat5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.liferay.pluto.util.install.InstallationConfig;
import com.liferay.pluto.util.install.file.FileSystemInstaller;

public class Tomcat5FileSystemInstaller extends FileSystemInstaller {

//
// PortalInstaller Interface
//

    /**
     * Determine whether or not this is a valid Tomcat 5.x installation.
     * @param installDir the proposed TOMCAT BASE directory
     * @return true if and only if <code>conf/server.xml</code> and
     *         <code>conf/catalina.properties exist.</code>
     */
    public boolean isValidInstallationDirectory(File installDir) {
        File serverConfig = new File(installDir, "conf/server.xml");
        File catalinaProps = new File(installDir, "conf/catalina.properties");
        return serverConfig.exists() && catalinaProps.exists();
    }

//
// FileSystemInstaller Implementation
//

    public void writeConfiguration(InstallationConfig config)
    throws IOException {

        File contextConfigurationDirectory = getConfigurationDir(config);
        if (! contextConfigurationDirectory.exists()) {
            contextConfigurationDirectory.mkdirs();
        }
            
        Iterator it = config.getPortletApplications().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String context = entry.getKey().toString();
            File portletApp = (File)entry.getValue();

            String deployed = "../"+config.getServerConfig().getDomain() +
                    "/" +  portletApp.getName();
            String contents = getPortletApplicationConfig(context, deployed);
            FileWriter out = new FileWriter(
                    new File(contextConfigurationDirectory, context+".xml"));
            out.write(contents);
            out.flush();
            out.close();
        }

        File xmlFile =  new File(
                contextConfigurationDirectory,
                config.getPortalContextPath()+".xml"
        );

        FileWriter out = new FileWriter(xmlFile);
        out.write(getPortalApplicationConfig(config));
        out.flush();
        out.close();
    }

    protected File getEndorsedDir(InstallationConfig config ) {
        File installationDirectory = config.getInstallationDirectory();
        return new File(installationDirectory, "common/endorsed");
    }

    protected File getSharedDir(InstallationConfig config) {
        File installationDirectory = config.getInstallationDirectory();
        // Tomcat 5 provides commons-logging-api.  Should be a nicer way
        // for installers to indicate what dependencies are provided by the
        // servlet container.
        if ( new File(config.getInstallationDirectory(), "bin/commons-logging-api.jar").exists()) {
            for (Iterator iter = config.getSharedDependencies().iterator(); iter.hasNext();) {
                File dep = (File) iter.next();
                if (dep.getPath().contains("commons-logging-api")) {
                    iter.remove();
                }
            }
        }
        return new File(installationDirectory, "shared/lib");
    }

    protected File getWebAppDir(InstallationConfig config) {
        File installationDirectory = config.getInstallationDirectory();
        return new File(
            installationDirectory, config.getServerConfig().getDomain()
        );
    }

//
// Helpers
//

    protected File getConfigurationDir(InstallationConfig config) {
        File installationDirectory = config.getInstallationDirectory();
        String engine = "Catalina";
        String host   = config.getServerConfig().getHost();
        return new File(installationDirectory, "conf/"+engine+"/"+host);
    }

    private String getPortalApplicationConfig(InstallationConfig config) {
        String war = "../"+config.getServerConfig().getDomain() +
                "/" +  config.getPortalApplication().getName();
        String contextPath = config.getPortalContextPath();
        return getConfigContents(war, contextPath);
    }

    private String getPortletApplicationConfig(String contextPath, String file) {
        return getConfigContents(file, contextPath);
    }

    private String getConfigContents(String war, String contextPath) {
        StringBuffer contents = new StringBuffer();
        contents.append("<Context ")
                .append("path=\"/").append(contextPath).append("\" ")
                .append("docBase=\"").append(war).append("\" ")
                .append("crossContext=\"true\">").append("</Context>");
       return contents.toString();
    }
}
