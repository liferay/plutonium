/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.tags;

import java.io.IOException;

import jakarta.portlet.WindowState;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.BodyTagSupport;
import jakarta.servlet.jsp.tagext.TagSupport;

import com.liferay.plutonium.driver.tags.standard.lang.jstl.ExpressionEvaluatorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.plutonium.driver.AttributeKeys;
import com.liferay.plutonium.driver.config.DriverConfiguration;
import com.liferay.plutonium.driver.core.PortalRequestContext;
import com.liferay.plutonium.driver.url.PortalURL;

/**
 * The tag is used to render a portlet mode anchor specified by the portlet ID and mode.
 * This is designed to live inside of a <plutonium:portlet/> tag.
 *
 * <plutonium:windowStateAnchor portletId="" windowState="maximized"/>
 *
 */
public class PortletWindowStateAnchorTag extends BodyTagSupport {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(PortletWindowStateAnchorTag.class);


    // Private Member Variables ------------------------------------------------
    /** Window state name */
    private String state;

    /** Context-relative URL to icon representing window state */
	private String icon;
    
    /** The portlet ID attribute obtained from parent tag. */
    private String portletId;

    /** The evaluated value of the portlet ID attribute. */
    private String evaluatedPortletId;

    // BodyTagSupport Impl -----------------------------------------------------

    /**
     * Method invoked when the start tag is encountered.
     * @throws JspException  if an error occurs.
     */
    public int doStartTag() throws JspException {

        // Ensure that the modeAnchor tag resides within a portlet tag.
        PortletTag parentTag = (PortletTag) TagSupport.findAncestorWithClass(
                this, PortletTag.class);
        if (parentTag == null) {
            throw new JspException("Portlet window controls may only reside "
                    + "within a plutonium:portlet tag.");
        }

        portletId = parentTag.getPortletId();
        // Evaluate portlet ID attribute.
        evaluatePortletId();

        // Retrieve the portlet window config for the evaluated portlet ID.
        ServletContext servletContext = pageContext.getServletContext();
        DriverConfiguration driverConfig = (DriverConfiguration)
                servletContext.getAttribute(AttributeKeys.DRIVER_CONFIG);

        if (isWindowStateAllowed(driverConfig, state)) {
            // Retrieve the portal environment.
            PortalRequestContext portalEnv = PortalRequestContext.getContext(
                    (HttpServletRequest) pageContext.getRequest());

            PortalURL portalUrl =  portalEnv.createPortalURL();
            portalUrl.setWindowState(evaluatedPortletId, new WindowState(state));

            // Build a string buffer containing the anchor tag
            StringBuffer tag = new StringBuffer();
//            tag.append("<a class=\"" + ToolTips.CSS_CLASS_NAME + "\" href=\"" + portalUrl.toString() + "\">");
//            tag.append("<span class=\"" + state + "\"></span>");
//            tag.append("<span class=\"" + ToolTips.CSS_CLASS_NAME + "\">");
//            tag.append(ToolTips.forWindowState(new WindowState(state)));
//            tag.append("</span></a>");
            tag.append("<a title=\"");
            tag.append(ToolTips.forWindowState(new WindowState(state)));
            tag.append("\" ");
            tag.append("href=\"" + portalUrl.toString() + "\">");
            tag.append("<img border=\"0\" src=\"" + icon + "\" />");
            tag.append("</a>");

            // Print the mode anchor tag.
            try {
                JspWriter out = pageContext.getOut();
                out.print(tag.toString());
            } catch (IOException ex) {
                throw new JspException(ex);
            }
        }


        // Continue to evaluate the tag body.
        return EVAL_BODY_INCLUDE;
    }


    // Package Methods ---------------------------------------------------------

    /**
     * Returns the evaluated portlet ID.
     * @return the evaluated portlet ID.
     */
    String getEvaluatedPortletId() {
        return evaluatedPortletId;
    }



    // Private Methods ---------------------------------------------------------

    /**
     * Evaluates the portlet ID attribute passed into this tag. This method
     * evaluates the member variable <code>portletId</code> and saves the
     * evaluated result to <code>evaluatedPortletId</code>
     * @throws JspException  if an error occurs.
     */
    private void evaluatePortletId() throws JspException {
        Object obj = ExpressionEvaluatorManager.evaluate(
                "portletId", portletId, String.class, this, pageContext);
        if (LOG.isTraceEnabled()) {
            LOG.debug("Evaluated portletId to: " + obj);
        }
        evaluatedPortletId = (String) obj;
    }

    /**
     * @return the portletMode
     */
    public String getWindowState() {
        return state;
    }

    /**
     * @param portletMode the portletMode to set
     */
    public void setWindowState(String state) {
        this.state = state;
    }

    public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}
    
    private boolean isWindowStateAllowed(DriverConfiguration config, String state) {
        LOG.trace("Testing if PortletWindowConfig [" + getEvaluatedPortletId() + "] supports window state [" + state + "]");
        return config.isWindowStateSupported(getEvaluatedPortletId(), state);
    }


}
