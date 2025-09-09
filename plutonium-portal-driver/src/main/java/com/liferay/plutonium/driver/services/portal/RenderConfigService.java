/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.portal;

import java.util.List;

import com.liferay.plutonium.container.PageResourceId;

/**
 * Service interface defining methods necessary for
 * a provider wishing to manage page administration.
 *
 * @since Aug 10, 2005
 */
public interface RenderConfigService {

    /**
     * Retrieve an ordered list of all PageConfig instances.
     * @return list of all PageConfig instances.
     */
    List<PageConfig> getPages();

    /**
     * Retrieve the PageConfig for the default
     * page.
     * @return PageConfig instance of the default page.
     */
    PageConfig getDefaultPage();

    /**
     * Retrieve the PageConfig for the given
     * page id.
     *
     * @param id
     * @return PageConfig instance for the specified id.
     */
    PageConfig getPage(String id);

    //added for page admin portlet
    void addPage(PageConfig pageConfig);
    void removePage(PageConfig pageConfig);

    // For handling the available page resources and default dependencies
    PageResources getPageResources();
    List<PageResourceId> getDefaultPageDependencies();
        
}
