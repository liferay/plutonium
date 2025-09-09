/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.tags;

import jakarta.portlet.PortletResponse;
import jakarta.portlet.PortletURL;
import jakarta.portlet.RenderResponse;

/**
 * 
 * A tag handler for the <CODE>actionURL</CODE> tag as defined in the JSR 168.
 * Creates a url that points to the current Portlet and triggers an 
 * action request with the supplied parameters.
 * 
 * @version 2.0
 */
public class ActionURLTag168 extends PortletURLTag168 {
	
	private static final long serialVersionUID = 286L;

	/**
     * Creates an action PortletURL 
     * @param portletResponse PortletResponse
     * @return PortletURL
     */
	@Override
	protected PortletURL createPortletUrl(PortletResponse portletResponse){
		if (portletResponse instanceof RenderResponse) {
			return ((RenderResponse)portletResponse).createActionURL();			
		}		
		throw new IllegalArgumentException();
	}
    
    
}

