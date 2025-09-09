/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.tags;

import java.lang.reflect.Field;
import java.util.Hashtable;

import jakarta.portlet.BaseURL;
import jakarta.portlet.PortletMode;
import jakarta.portlet.PortletModeException;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.PortletURL;
import jakarta.portlet.WindowState;
import jakarta.portlet.WindowStateException;
import jakarta.servlet.jsp.JspException;

/**
 * Abstract supporting class for the JSR168 actionURL and renderURL tag handlers.
 * 
 * @version 2.0
 */

public abstract class PortletURLTag168 extends BaseURLTag {
   private static final long serialVersionUID = 4167483154265467568L;

   protected String          portletMode      = null;

   protected String          windowState      = null;

   protected PortletURL      portletURL       = null;

   /**
    * Sets the portlet mode and window state on the URL.
    * 
    * @throws JspException
    */
   protected void handlePMandWS() throws JspException {
      if (portletMode != null) {// set portlet mode
         try {
            PortletMode mode = (PortletMode) TEI.portletModes.get(portletMode.toUpperCase());

            if (mode == null) {
               mode = new PortletMode(portletMode);// support for custom portlet modes PLUTO-258
            }

            portletURL.setPortletMode(mode);

         } catch (PortletModeException e) {
            throw new JspException(e);
         }
      }

      if (windowState != null) {// set window state
         try {
            WindowState state = (WindowState) TEI.definedWindowStates.get(windowState.toUpperCase());

            if (state == null) {
               state = new WindowState(windowState);// support for custom window states PLUTO-258
            }

            portletURL.setWindowState(state);

         } catch (WindowStateException e) {
            throw new JspException(e);
         }
      }

   }

   /*
    * (non-Javadoc)
    * 
    * @see com.liferay.pluto.tags.BaseURLTag#doStartTag()
    */
   @Override
   public int doStartTag() throws JspException {

      PortletResponse portletResponse = (PortletResponse) pageContext.getRequest().getAttribute(
            Constants.PORTLET_RESPONSE);

      if (portletResponse != null) {

         PortletURL portletURL = createPortletUrl(portletResponse);
         setUrl(portletURL);
         handlePMandWS();

      }

      return super.doStartTag();
   }

   /**
    * Returns the portletMode property.
    * 
    * @return String
    */
   public String getPortletMode() {
      return portletMode;
   }

   /**
    * Returns the windowState property.
    * 
    * @return String
    */
   public String getWindowState() {
      return windowState;
   }

   /**
    * Sets the portletMode property.
    * 
    * @param portletMode
    *           - the portlet mode to set
    * @return void
    */
   public void setPortletMode(String portletMode) {
      this.portletMode = portletMode;
   }

   /**
    * Sets the windowState property.
    * 
    * @param windowState
    *           - the portlet window state to set
    * @return void
    */
   public void setWindowState(String windowState) {
      this.windowState = windowState;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.liferay.pluto.tags.BaseURLTag#getUrl()
    */
   @Override
   protected BaseURL getUrl() {
      return portletURL;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.liferay.pluto.tags.BaseURLTag#setUrl(jakarta.portlet.BaseURL)
    */
   @Override
   protected void setUrl(BaseURL url) {
      this.portletURL = (PortletURL) url;
   }

   /**
    * Creates an actionURL or a renderURL
    * 
    * @param portletResponse
    *           PortletResponse
    * @return PortletURL
    */
   protected abstract PortletURL createPortletUrl(PortletResponse portletResponse);

   /**
    * TagExtraInfo class for PortletURLTag.
    */
   public static class TEI extends BaseURLTag.TEI {
      public final static Hashtable<String, Object> definedWindowStates = getDefinedWindowStates();

      public final static Hashtable<String, Object> portletModes        = getDefinedPortletModes();

      /**
       * Provides a list of all static PortletMode available in the specifications by using introspection
       * 
       * @return Hashtable
       */
      private static Hashtable<String, Object> getDefinedPortletModes() {
         Hashtable<String, Object> portletModes = new Hashtable<String, Object>();
         Field[] f = PortletMode.class.getDeclaredFields();

         for (int i = 0; i < f.length; i++) {
            if (f[i].getType().isAssignableFrom(jakarta.portlet.PortletMode.class)) {
               try {
                  portletModes.put(f[i].get(f[i]).toString().toUpperCase(), f[i].get(f[i]));
               } catch (IllegalAccessException e) {
               }
            }
         }

         return portletModes;
      }

      /**
       * Provides a list of all static WindowsStates available in the specifications by using introspection
       * 
       * @return Hashtable
       */
      private static Hashtable<String, Object> getDefinedWindowStates() {
         Hashtable<String, Object> definedWindowStates = new Hashtable<String, Object>();
         Field[] f = WindowState.class.getDeclaredFields();

         for (int i = 0; i < f.length; i++) {
            if (f[i].getType().isAssignableFrom(jakarta.portlet.WindowState.class)) {
               try {
                  definedWindowStates.put(f[i].get(f[i]).toString().toUpperCase(), f[i].get(f[i]));
               } catch (IllegalAccessException e) {

               }
            }
         }
         return definedWindowStates;
      }
   }

}
