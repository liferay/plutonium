/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.services.container;

import java.io.IOException;
import java.util.List;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventPortlet;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.HeaderPortlet;
import jakarta.portlet.HeaderRequest;
import jakarta.portlet.HeaderResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletContext;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;

import com.liferay.plutonium.container.FilterManager;
import com.liferay.plutonium.container.PortletWindow;
import com.liferay.plutonium.container.om.portlet.Filter;
import com.liferay.plutonium.container.om.portlet.FilterMapping;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.om.portlet.PortletDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Manage the initialization and doFilter {@link FilterChainImpl} for the filter which are
 * declared in the deployment descriptor.
 * @since 05/29/2007
 * @version 2.0
 */
public class FilterManagerImpl implements FilterManager{
   @SuppressWarnings("unused")
   private static final Logger LOG = LoggerFactory.getLogger(FilterManagerImpl.class);
   
    private FilterChainImpl filterchain;
    private PortletApplicationDefinition portletApp;
    private String portletName;
    private String lifeCycle;

    public FilterManagerImpl(PortletWindow portletWindow, String lifeCycle) {
        final PortletDefinition pd = portletWindow.getPortletDefinition();
        this.portletApp = pd.getApplication();
        this.portletName =  pd.getPortletName();
        this.lifeCycle = lifeCycle;
        filterchain = new FilterChainImpl(lifeCycle);
        initFilterChain();
    }
    
    /**
     * passing thru bean manager to enable contextual support in filters.
     */
    @Override
    public void setBeanManager(BeanManager bm) {
       filterchain.setBeanManager(bm);
    }

    private void initFilterChain(){
        List<? extends FilterMapping> filterMappingList = portletApp.getFilterMappings();
        if (filterMappingList!= null){
            for (FilterMapping filterMapping : filterMappingList) {
                if (isFilter(filterMapping, portletName)){
                    //the filter is specified for the portlet, check the filter for the lifecycle
                    List<? extends Filter> filterList = portletApp.getFilters();
                    for (Filter filter : filterList) {
                        //search for the filter in the filter
                        if (filter.getFilterName().equals(filterMapping.getFilterName())){
                            //check the lifecycle
                            if (isLifeCycle(filter, lifeCycle)){
                                //the filter match to the portlet and has the specified lifecycle -> add to chain
                                filterchain.addFilter(filter);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @see com.liferay.plutonium.container.FilterManager#processFilter(jakarta.portlet.EventRequest, jakarta.portlet.EventResponse, jakarta.portlet.EventPortlet, jakarta.portlet.PortletContext)
     */
    @Override
    public void processFilter(EventRequest req, EventResponse res, EventPortlet eventPortlet,PortletContext portletContext)throws PortletException, IOException{
        filterchain.processFilter(req, res, eventPortlet, portletContext);
    }

    /**
     * @see com.liferay.plutonium.container.FilterManager#processFilter(jakarta.portlet.ResourceRequest, jakarta.portlet.ResourceResponse, jakarta.portlet.ResourceServingPortlet, jakarta.portlet.PortletContext)
     */
    @Override
    public void processFilter(ResourceRequest req, ResourceResponse res, ResourceServingPortlet resourceServingPortlet,PortletContext portletContext)throws PortletException, IOException{
        filterchain.reset();     // for async processing
        filterchain.processFilter(req, res, resourceServingPortlet, portletContext);
    }

    /**
     * @see com.liferay.plutonium.container.FilterManager#processFilter(jakarta.portlet.RenderRequest, jakarta.portlet.RenderResponse, jakarta.portlet.Portlet, jakarta.portlet.PortletContext)
     */
    @Override
    public void processFilter(RenderRequest req, RenderResponse res, Portlet portlet,PortletContext portletContext) throws PortletException, IOException{
        filterchain.processFilter(req, res, portlet, portletContext);
    }

    /**
     * @see com.liferay.plutonium.container.FilterManager#processFilter(jakarta.portlet.RenderRequest, jakarta.portlet.HeaderResponse, jakarta.portlet.HeaderPortlet, jakarta.portlet.PortletContext)
     */
    @Override
    public void processFilter(HeaderRequest req, HeaderResponse res, HeaderPortlet portlet,PortletContext portletContext) throws PortletException, IOException{
        filterchain.processFilter(req, res, portlet, portletContext);
    }

    /**
     * @see com.liferay.plutonium.container.FilterManager#processFilter(jakarta.portlet.ActionRequest, jakarta.portlet.ActionResponse, jakarta.portlet.Portlet, jakarta.portlet.PortletContext)
     */
    @Override
    public void processFilter(ActionRequest req, ActionResponse res, Portlet portlet,PortletContext portletContext) throws PortletException, IOException{
        filterchain.processFilter(req, res, portlet, portletContext);
    }

    private boolean isLifeCycle(Filter filter, String lifeCycle){
        List <String> lifeCyclesList = filter.getLifecycles();
        for (String string : lifeCyclesList) {
            if (string.equals(lifeCycle))
                return true;
        }
        return false;
    }

    private boolean isFilter(FilterMapping filterMapping,String portletName){
        List <String> portletNamesList = filterMapping.getPortletNames();
        for (String portletNameFromFilterList : portletNamesList) {
            if (portletNameFromFilterList.endsWith("*")){
                if (portletNameFromFilterList.length()==1){
                    //if name contains only *
                    return true;
                }
                portletNameFromFilterList = portletNameFromFilterList.substring(0, portletNameFromFilterList.length()-1);
                if (portletName.length()>= portletNameFromFilterList.length()){
                    if (portletName.substring(0, portletNameFromFilterList.length()).equals(portletNameFromFilterList)){
                        return true;
                    }
                }
            }
            else if (portletNameFromFilterList.equals(portletName))
                return true;
        }
        return false;
    }


}
