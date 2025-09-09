/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.tags;


import jakarta.portlet.PortletResponse;
import jakarta.portlet.PortletURL;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceResponse;


/**
 * A tag handler for the <CODE>renderURL</CODE> tag as defined in the JSR 286.
 * Creates a url that points to the current Portlet and triggers a render request 
 * with the supplied parameters.
 * 
 * @version 2.0
 */
public class RenderURLTag286 extends PortletURLTag286 {
	
	private static final long serialVersionUID = 286L;

    /**
     * Creates a render PortletURL
     * @param portletResponse PortletResponse
     * @return PortletURL
     */
	@Override
	protected PortletURL createPortletUrl(PortletResponse portletResponse){
		
		if (portletResponse instanceof RenderResponse) {
			return ((RenderResponse)portletResponse).createRenderURL();			
		}
		else if (portletResponse instanceof ResourceResponse) {
			return ((ResourceResponse)portletResponse).createRenderURL();			
		}
		
		throw new IllegalArgumentException();
	}
    
}

