/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.portlet.MimeResponse;
import jakarta.portlet.MimeResponse.Copy;
import jakarta.portlet.BaseURL;
import jakarta.portlet.MutableRenderParameters;
import jakarta.portlet.RenderURL;
import jakarta.servlet.jsp.JspException;

/**
 * A tag handler for the <CODE>renderURL</CODE> tag as defined in the JSR 362. Creates a url that points to the current
 * Portlet and triggers a render request with the supplied parameters.
 * 
 * @author Scott Nicklous
 * 
 * @version 3.0
 */
public class RenderURLTag362 extends RenderURLTag286 {
   private static final long serialVersionUID = -6916378914316203961L;

   MutableRenderParameters   params;

   public RenderURLTag362() {
      super();
   }

   @Override
   public int doStartTag() throws JspException {

      Object obj = pageContext.getRequest().getAttribute(Constants.PORTLET_RESPONSE);
      if ((obj == null) || !(obj instanceof MimeResponse)) {
         throw new JspException("Could not obtain MimeResponse to create the URL.");
      }

      MimeResponse resp = (MimeResponse) obj;
      RenderURL rurl = null;
      if (isCopyCurrentRenderParameters() == true) {
         rurl = resp.createRenderURL(Copy.ALL);
      } else {
         rurl = resp.createRenderURL(Copy.NONE);
      }
      params = rurl.getRenderParameters();
      setUrl(rurl);

      handlePMandWS();
      handleDefaultEscapeXML();
      handleSecureFlag();

      return EVAL_BODY_INCLUDE;
   }

   @Override
   protected void addParameter(String key, String value) {
      if ((key == null) || (key.length() == 0)) {
         throw new IllegalArgumentException("the argument key must not be null or empty!");
      }

      if (value == null || value.length() == 0) {
         params.removeParameter(key);
      } else {
         String[] arrayVals = params.getValues(key);
         if (arrayVals != null) {
            List<String> vals = new ArrayList<String>(Arrays.asList(arrayVals));
            vals.add(value);
            params.setValues(key, vals.toArray(new String[0]));
         } else {
            params.setValue(key, value);
         }
      }
   }
   
   /**
    * prevent additional copying
    */
   @Override
   protected void doCopyCurrentRenderParameters() {
   }
   
   /**
    * prevent additional copying
    */
   @Override
   protected void setUrlParameters(BaseURL url) {
   }
}
