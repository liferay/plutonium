/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container;

/**
 *
 * @version 1.0
 */
public interface ResourceURLProvider {

    /**
     * Sets the absolute URL to be returned by the provider. E.g.
     * http://host/wps/portal/hello.gif
     * @param path the new absolute url
     */
    void setAbsoluteURL(String path);

    /**
     * Sets a full path URI including the context root. E.g.
     * /wps/portal/hello.gif
     * @param path the new absolute url
     */
    void setFullPath(String path);

    /**
     * Returns a url to a resource as absolute URL starting with protocol so
     * that it can be accessed by a browser.
     * @return the URL as string
     */
    String toString();

}
