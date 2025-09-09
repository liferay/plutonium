/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import java.util.ArrayList;
import java.util.List;

import jakarta.portlet.PortletURLGenerationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.pluto.container.PortletURLListenerService;
import com.liferay.pluto.container.om.portlet.Listener;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;

public class PortletURLListenerServiceImpl implements PortletURLListenerService
{
	/** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(PortletURLListenerServiceImpl.class);

    public List<PortletURLGenerationListener> getPortletURLGenerationListeners(PortletApplicationDefinition app)
    {
        List<PortletURLGenerationListener> listeners = new ArrayList<PortletURLGenerationListener>();
        //this list is needed for the classnames
        List<? extends Listener> portletURLFilterList = app.getListeners();
        //Iterate over the classnames and for each entry in the list the filter..URL is called.
        if (portletURLFilterList != null){
            for (Listener listener : portletURLFilterList) {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                Class<? extends Object> clazz;
                try {
                    clazz = loader.loadClass(listener.getListenerClass() );
                    if (clazz != null){
                        listeners.add((PortletURLGenerationListener)clazz.newInstance());
                    }
                } catch (ClassNotFoundException e) {
                    String message = "The class isn't found.";
                    LOG.error(message);
                } catch (InstantiationException e) {
                    String message = "The instantiation fail.";
                    LOG.error(message);
                } catch (IllegalAccessException e) {
                    String message = "IllegalAccessException";
                    LOG.error(message);
                }
            }
        }
        return listeners;
    }
}
