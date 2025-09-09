/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.tags;


import static jakarta.portlet.ResourceURL.FULL;
import static jakarta.portlet.ResourceURL.PAGE;
import static jakarta.portlet.ResourceURL.PORTLET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.portlet.BaseURL;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceURL;
import jakarta.servlet.jsp.JspException;


/**
 * A tag handler for the <CODE>resourceURL</CODE> tag as defined in the JSR 286.
 * Creates a url that points to the current Portlet and triggers a 
 * resource request with the supplied parameters.
 * 
 * @version 2.0
 */

public class ResourceURLTag286 extends BaseURLTag {
	
	private static final long serialVersionUID = 286L;	
	
	private String id = null;
	
	private String cacheability = null;
	private static final Map<String, String> cacheabilityMap = new HashMap<String, String>();
	static {
	   cacheabilityMap.put("FULL", FULL);
	   cacheabilityMap.put("PORTLET", PORTLET);
	   cacheabilityMap.put("PAGE", PAGE);
	}
	
	private ResourceURL resourceURL = null;
	

	public ResourceURLTag286() {
		super();
		setEscapeXml(Boolean.TRUE.toString());
	}
	

	/* (non-Javadoc)
	 * @see com.liferay.pluto.tags.BaseURLTag#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {                
        
       PortletConfig portletConfig = 
           (PortletConfig) pageContext.getRequest().getAttribute(Constants.PORTLET_CONFIG);
       Map<String,String[]> containerRuntimeOptions = portletConfig.getContainerRuntimeOptions();
       if (containerRuntimeOptions != null){
           String[] result = containerRuntimeOptions.get(Constants.ESCAPE_XML_RUNTIME_OPTION);
           if (result != null){
               if (result.length > 0){
                   setEscapeXml(result[0]);
               }
           }
       }
		       
        PortletResponse portletResponse = (PortletResponse) pageContext.getRequest()
            .getAttribute(Constants.PORTLET_RESPONSE);
        
        if (portletResponse != null) {
        	
        	ResourceURL resourceURL = createResourceURL(portletResponse);
                 
            if(id != null){
            	resourceURL.setResourceID(id);
            }

            if(cacheability != null){
               List<String> caAttribs = new ArrayList<String>(cacheabilityMap.keySet()); 
               if (!caAttribs.contains(cacheability.toUpperCase())) {
                  StringBuilder txt = new StringBuilder(128);
                  txt.append("Invalid cacheability option: ").append(cacheability);
                  txt.append(", valid options: ").append(caAttribs.toString());
                  throw new JspException(txt.toString());
               }
            	try{
            		resourceURL.setCacheability(cacheabilityMap.get(cacheability));
            	}
            	catch(IllegalArgumentException e){
            		throw new JspException(e);
            	}
            	catch(IllegalStateException e){
            		throw new JspException(e);
            	}
            }
            
            setUrl(resourceURL);
        }
        
        return super.doStartTag();
    }
	
	   
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the cacheability
	 */
	public String getCacheability() {
		return cacheability;
	}


	/**
	 * @param cacheability the cacheability to set
	 * @throws JspException 
	 */
	public void setCacheability(String cacheability) throws JspException {
		this.cacheability = cacheability;
	}
	

    /* (non-Javadoc)
     * @see com.liferay.pluto.tags.BaseURLTag#getUrl()
     */
    @Override
    protected ResourceURL getUrl() {
        return resourceURL;
    }
	
   
    /* (non-Javadoc)
     * @see com.liferay.pluto.tags.BaseURLTag#setUrl(jakarta.portlet.BaseURL)
     */
    @Override
    protected void setUrl(BaseURL url) {
        this.resourceURL = (ResourceURL)url;
    }
	
    
	/**
	 * Creates a resourceURL.
	 * 
	 * @param portletResponse
	 * @return a resourceURL
	 * @throws JspException
	 */
	protected ResourceURL createResourceURL(PortletResponse portletResponse) throws JspException{
		ResourceURL result = null;
		if(portletResponse instanceof RenderResponse){
    		result = ((RenderResponse)portletResponse).createResourceURL();	
    	}
    	else if(portletResponse instanceof ResourceResponse){
    		result = ((ResourceResponse)portletResponse).createResourceURL();
    	}	
    	else{
    		throw new JspException();
    	}
		return result;
	}
}
