/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.tags;

import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.TagData;
import jakarta.servlet.jsp.tagext.TagExtraInfo;
import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.tagext.VariableInfo;

/**
 * A tag handler for the <CODE>defineObjects</CODE> tag as defined in the JSR 168.
 * Creates the following variables to be used in the JSP: 
 * <UL> 
 * <LI><CODE>{@link RenderRequest} renderRequest</CODE>
 * <LI><CODE>{@link RenderResponse} renderResponse</CODE> 
 * <LI><CODE>{@link PortletConfig} portletConfig</CODE>
 * </UL>
 * 
 * @version 2.0
 */
public class DefineObjectsTag168 extends TagSupport {
	
	private static final long serialVersionUID = 286L;

	/**
	 * Helper method.
	 * <p>
     * Sets an pageContext attribute with <CODE>PAGE_SCOPE</CODE>.
     * 
     * @param attribute - the attribute object to set
     * @param attributeName - the name of the attribute object
     * 
     * @return void
     */
	private void setAttribute(Object attribute, String attributeName){
		if (pageContext.getAttribute(attributeName) == null){   //Set attributes only once
 
            pageContext.setAttribute(attributeName,
            						 attribute,
                                     PageContext.PAGE_SCOPE);
        }
	}
	  
     
	/**
     * Processes the <CODE>defineObjects</CODE> tag.
     * @return <CODE>SKIP_BODY</CODE>
     */
    public int doStartTag() throws JspException {
    	
    	ServletRequest servletRequest = pageContext.getRequest();
    	
    	PortletRequest portletRequest = 
    		(PortletRequest) servletRequest.getAttribute(Constants.PORTLET_REQUEST);
    	
    	PortletResponse portletResponse = 
    		(PortletResponse) servletRequest.getAttribute(Constants.PORTLET_RESPONSE);
    	
    	PortletConfig portletConfig = 
    		(PortletConfig) servletRequest.getAttribute(Constants.PORTLET_CONFIG);
    	
    	// set attribute renderRequest
    	setAttribute(portletRequest, "renderRequest");
    	
    	// set attribute renderResponse
    	setAttribute(portletResponse, "renderResponse");
    	
    	// set attribute portletConfig
    	setAttribute(portletConfig, "portletConfig");   	
    	
        return SKIP_BODY;
    }

    
    
    /**
     * TagExtraInfo class for DefineObjectsTag.
     *
     */
    public static class TEI extends TagExtraInfo {

        public VariableInfo[] getVariableInfo(TagData tagData) {
            VariableInfo[] info = new VariableInfo[]{
            	new VariableInfo("renderRequest",
            					 "jakarta.portlet.RenderRequest",
            					 true,
            					 VariableInfo.AT_BEGIN),
                new VariableInfo("renderResponse",
                   				 "jakarta.portlet.RenderResponse",
                   				 true,
                  				 VariableInfo.AT_BEGIN),                            
                new VariableInfo("portletConfig",
                                 "jakarta.portlet.PortletConfig",
                                 true,
                                 VariableInfo.AT_BEGIN)                                              
            };
            return info;
        }
    }
}

