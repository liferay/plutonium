/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;


/**
 * A tag handler for the <CODE>param</CODE> tag. Defines a parameter that
 * can be added to a <CODE>actionURL</CODE>, a <CODE>resourceURL</CODE> or 
 * a <CODE>renderURL</CODE>
 * <BR>The following attributes are mandatory:
 *   <UL>
 *       <LI><CODE>name</CODE>
 *       <LI><CODE>value</CODE>
 *   </UL>
 *  
 * The parent tag handler must be a subclass of <code>BaseURLTag</code>.      
 *       
 *  @version 2.0
 */
public class ParamTag extends TagSupport {
	
	private static final long serialVersionUID = 286L;

    private String name = null;
    private String value = null;
    private String type = null;

    /* (non-Javadoc)
     * @see jakarta.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public int doStartTag() throws JspException {
        BaseURLTag urlTag = (BaseURLTag)
                findAncestorWithClass(this, BaseURLTag.class);

        if (urlTag == null) {
            throw new JspException(
                "the 'param' Tag must have a actionURL, renderURL " +
                "or resourceURL tag as a parent");
        }
        
        // if it's an action URL, handle the type
        
        if (urlTag instanceof ActionURLTag362) {
           boolean action = true;
           if (type != null) {
              if (type.equalsIgnoreCase("render")) {
                 action = false;
              } else if (!type.equalsIgnoreCase("action")) {
                 throw new JspException("The 'param' tag on an action URL must have a value of 'action' or 'render'.");
              }
           }
           
           if (action) {
              urlTag.addParameter(getName(), getValue());
           } else {
              ((ActionURLTag362)urlTag).addRenderParameter(getName(), getValue());
           }
        } else {
           urlTag.addParameter(getName(), getValue());
        }

        return SKIP_BODY;
    }

    /**
     * Returns the name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the value.
     * @return String
     */
    public String getValue() throws JspException {
        if (value == null) {
            value = "";
        }
        return value;
    }

    /**
     * Sets the name.
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the value.
     * @param value The value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

   /**
    * @return the type
    */
   public String getType() {
      return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(String type) {
      this.type = type;
   }

}
