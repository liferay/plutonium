/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.maven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 *
 */
class InstallationDependency {    
    
    private static final Properties VERSION_PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE = "/versions.properties";
    static
    {
        try 
        {
            VERSION_PROPERTIES.load(InstallationDependency.class.getResourceAsStream(PROPERTIES_FILE));
        } 
        catch (IOException e) 
        {
            throw new RuntimeException("Cannot load " + PROPERTIES_FILE + " from the classpath!", e);
        }
    }

    public static final InstallationDependency ANGUS_ACTIVATION =
        new InstallationDependency("org.eclipse.angus", "angus-activation",
            VERSION_PROPERTIES.getProperty("angus-activation.version"));

    public static final InstallationDependency ISTACK_COMMONS_RUNTIME =
        new InstallationDependency("com.sun.istack", "istack-commons-runtime",
            VERSION_PROPERTIES.getProperty("istack-commons-runtime.version"));

    public static final InstallationDependency JAKARTA_ACTIVATION_API =
        new InstallationDependency("jakarta.activation", "jakarta.activation-api",
            VERSION_PROPERTIES.getProperty("jakarta.activation-api.version"));

    public static final InstallationDependency JAKARTA_ENTERPRISE_CDI_API =
        new InstallationDependency("jakarta.enterprise", "jakarta.enterprise.cdi-api",
            VERSION_PROPERTIES.getProperty("jakarta.enterprise.cdi-api.version"));

    public static final InstallationDependency JAKARTA_INJECT_API =
        new InstallationDependency("jakarta.inject", "jakarta.inject-api",
            VERSION_PROPERTIES.getProperty("jakarta.inject-api.version"));

    public static final InstallationDependency JAKARTA_INTERCEPTOR_API =
        new InstallationDependency("jakarta.interceptor", "jakarta.interceptor-api",
            VERSION_PROPERTIES.getProperty("jakarta.interceptor-api.version"));

    public static final InstallationDependency JAKARTA_SERVLET_JSP_JSTL =
        new InstallationDependency("org.glassfish.web", "jakarta.servlet.jsp.jstl",
            VERSION_PROPERTIES.getProperty("jakarta.servlet.jsp.jstl.version"));

    public static final InstallationDependency JAKARTA_SERVLET_JSP_JSTL_API =
        new InstallationDependency("jakarta.servlet.jsp.jstl", "jakarta.servlet.jsp.jstl-api",
            VERSION_PROPERTIES.getProperty("jakarta.servlet.jsp.jstl-api.version"));

    public static final InstallationDependency JAKARTA_XML_BIND_API =
        new InstallationDependency("jakarta.xml.bind", "jakarta.xml.bind-api",
            VERSION_PROPERTIES.getProperty("jakarta.xml.bind-api.version"));

    public static final InstallationDependency JAXB_CORE =
        new InstallationDependency("org.glassfish.jaxb", "jaxb-core",
            VERSION_PROPERTIES.getProperty("jaxb-impl.version"));

    public static final InstallationDependency JAXB_RUNTIME =
        new InstallationDependency("org.glassfish.jaxb", "jaxb-runtime",
            VERSION_PROPERTIES.getProperty("jaxb-impl.version"));

    public static final InstallationDependency JBOSS_CLASSFILEWRITER =
        new InstallationDependency("org.jboss.classfilewriter", "jboss-classfilewriter",
            VERSION_PROPERTIES.getProperty("jboss-classfilewriter.version"));

    public static final InstallationDependency PORTLET_API =
        new InstallationDependency("jakarta.portlet", "jakarta.portlet-api",
            VERSION_PROPERTIES.getProperty("portals.portlet-api.version"));

    public static final InstallationDependency COMMONS_LANG3 =
        new InstallationDependency("org.apache.commons", "commons-lang3",
            VERSION_PROPERTIES.getProperty("commons-lang.version"));

    public static final InstallationDependency CLASSMATE =
        new InstallationDependency("com.fasterxml", "classmate",
            VERSION_PROPERTIES.getProperty("classmate.version"));

    public static final InstallationDependency HIBERNATE_VALIDATOR =
        new InstallationDependency("org.hibernate.validator", "hibernate-validator",
            VERSION_PROPERTIES.getProperty("hibernate-validator.version"));

    public static final InstallationDependency HIBERNATE_VALIDATOR_CDI =
        new InstallationDependency("org.hibernate.validator", "hibernate-validator-cdi",
            VERSION_PROPERTIES.getProperty("hibernate-validator.version"));

    public static final InstallationDependency JAXRS_API =
        new InstallationDependency("jakarta.ws.rs", "jakarta.ws.rs-api",
            VERSION_PROPERTIES.getProperty("jaxrs-api.version"));

    public static final InstallationDependency JBOSS_LOGGING =
        new InstallationDependency("org.jboss.logging", "jboss-logging",
            VERSION_PROPERTIES.getProperty("jboss-logging.version"));

    public static final InstallationDependency MVC_API =
        new InstallationDependency("jakarta.mvc", "jakarta.mvc-api",
            VERSION_PROPERTIES.getProperty("mvc-api.version"));

    public static final InstallationDependency VALIDATION_API =
        new InstallationDependency("jakarta.validation", "jakarta.validation-api",
            VERSION_PROPERTIES.getProperty("validation-api.version"));

    public static final InstallationDependency WELD_API =
        new InstallationDependency("org.jboss.weld", "weld-api",
            VERSION_PROPERTIES.getProperty("weld.api.version"));

    public static final InstallationDependency WELD_SPI =
        new InstallationDependency("org.jboss.weld", "weld-spi",
            VERSION_PROPERTIES.getProperty("weld.api.version"));

    public static final InstallationDependency WELD_CORE_IMPL =
        new InstallationDependency("org.jboss.weld", "weld-core-impl",
            VERSION_PROPERTIES.getProperty("weld.impl.version"));

    public static final InstallationDependency WELD_ENVIRONMENT_COMMON =
        new InstallationDependency("org.jboss.weld.environment", "weld-environment-common",
            VERSION_PROPERTIES.getProperty("weld.impl.version"));

    public static final InstallationDependency WELD_LITE_EXTENSION_TRANSLATOR =
        new InstallationDependency("org.jboss.weld", "weld-lite-extension-translator",
            VERSION_PROPERTIES.getProperty("weld.impl.version"));

    public static final InstallationDependency WELD_WEB =
        new InstallationDependency("org.jboss.weld.module", "weld-web",
            VERSION_PROPERTIES.getProperty("weld.impl.version"));

    public static final InstallationDependency WELD_SERVLET_CORE =
        new InstallationDependency("org.jboss.weld.servlet", "weld-servlet-core",
                VERSION_PROPERTIES.getProperty("weld.impl.version"));

    public static final InstallationDependency ANNOTATION_DETECTOR =
        new InstallationDependency("eu.infomas", "annotation-detector",
                VERSION_PROPERTIES.getProperty("annotation-detector.version"));

    public static final InstallationDependency SLF4J_API =
        new InstallationDependency("org.slf4j", "slf4j-api", 
                VERSION_PROPERTIES.getProperty("slf4j.version"));

    public static final InstallationDependency SLF4J_IMPL =
        new InstallationDependency("org.slf4j", "slf4j-jdk14", 
                VERSION_PROPERTIES.getProperty("slf4j.version"));

    public static final InstallationDependency CONTAINER_API =
        new InstallationDependency("com.liferay.pluto", "pluto-container-api",
                VERSION_PROPERTIES.getProperty("pluto.version"));

    public static final InstallationDependency CONTAINER =
            new InstallationDependency("com.liferay.pluto", "pluto-container", 
                    VERSION_PROPERTIES.getProperty("pluto.version"));

    public static final InstallationDependency  TAGLIB =
            new InstallationDependency("com.liferay.pluto", "pluto-taglib", 
                    VERSION_PROPERTIES.getProperty("pluto.version"));

    public static final InstallationDependency CONTAINER_DRIVER_API =
        new InstallationDependency("com.liferay.pluto", "pluto-container-driver-api",
                VERSION_PROPERTIES.getProperty("pluto.version"));

    public static final InstallationDependency PORTAL =
            new InstallationDependency("com.liferay.pluto", "pluto-portal",
                    VERSION_PROPERTIES.getProperty("pluto.version"), "war");

    public static final InstallationDependency TESTSUITE =
            new InstallationDependency("com.liferay.pluto", "pluto-testsuite", 
                    VERSION_PROPERTIES.getProperty("pluto.version"), "war");

    public static final InstallationDependency CCPP_API =
		new InstallationDependency("javax.ccpp","ccpp",
				VERSION_PROPERTIES.getProperty("ccpp-api.version"));


    private static final List<InstallationDependency> ENDORSED = new ArrayList<InstallationDependency>();
    private static final List<InstallationDependency> SHARED = new ArrayList<InstallationDependency>();

    static {
        SHARED.add(ANGUS_ACTIVATION);
        SHARED.add(ISTACK_COMMONS_RUNTIME);
        SHARED.add(JAKARTA_ACTIVATION_API);
        SHARED.add(JAKARTA_ENTERPRISE_CDI_API);
        SHARED.add(JAKARTA_INJECT_API);
        SHARED.add(JAKARTA_INTERCEPTOR_API);
        SHARED.add(JAKARTA_SERVLET_JSP_JSTL);
        SHARED.add(JAKARTA_SERVLET_JSP_JSTL_API);
        SHARED.add(JAKARTA_XML_BIND_API);
        SHARED.add(JAXB_CORE);
        SHARED.add(JAXB_RUNTIME);
        SHARED.add(JBOSS_CLASSFILEWRITER);
        SHARED.add(PORTLET_API);
        SHARED.add(COMMONS_LANG3);
        SHARED.add(CLASSMATE);
        SHARED.add(HIBERNATE_VALIDATOR);
        SHARED.add(HIBERNATE_VALIDATOR_CDI);
        SHARED.add(JAXRS_API);
        SHARED.add(JBOSS_LOGGING);
        SHARED.add(MVC_API);
        SHARED.add(VALIDATION_API);
        SHARED.add(CONTAINER);
        SHARED.add(CONTAINER_API);
        SHARED.add(CONTAINER_DRIVER_API);
        SHARED.add(TAGLIB);
        SHARED.add(CCPP_API);
        SHARED.add(WELD_API);
        SHARED.add(WELD_SPI);
        SHARED.add(WELD_CORE_IMPL);
        SHARED.add(WELD_ENVIRONMENT_COMMON);
        SHARED.add(WELD_LITE_EXTENSION_TRANSLATOR);
        SHARED.add(WELD_WEB);
        SHARED.add(WELD_SERVLET_CORE);
        SHARED.add(ANNOTATION_DETECTOR);
        SHARED.add(SLF4J_API);
        SHARED.add(SLF4J_IMPL);
    }


    public static Collection<InstallationDependency> getEndorsedDependencies() {
        return Collections.unmodifiableCollection(ENDORSED);
    }

    public static Collection<InstallationDependency> getSharedDependencies() {
        return Collections.unmodifiableCollection(SHARED);
    }

    private String groupId;
    private String artifactId;
    private String version;
    private String type;

    public InstallationDependency(String groupId, String artifactId, String version) {
        this(groupId, artifactId, version, "jar");
    }

    public InstallationDependency(String groupId, String artifactId, String version, String type) {
        if (version == null || version.trim().equalsIgnoreCase(""))
        {
            throw new RuntimeException("Missing or invalid property for artifact " + 
                    artifactId + " in " + PROPERTIES_FILE + "!");
        }
        
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.type = type;
    }


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
