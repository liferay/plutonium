/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor;


/**
 * @author Scott
 *
 */
public class InvalidAnnotationException extends Exception {
   private static final long serialVersionUID = 4387915782102207038L;
   
   private String portletName = "";

   /**
    * @param message
    */
   public InvalidAnnotationException(String message) {
      super(message);
   }

   /**
    * @param cause
    */
   public InvalidAnnotationException(Throwable cause) {
      super(cause);
   }

   /**
    * @param message
    * @param cause
    */
   public InvalidAnnotationException(String message, Throwable cause) {
      super(message, cause);
   }

   /**
    * @param message
    * @param cause
    * @param enableSuppression
    * @param writableStackTrace
    */
   public InvalidAnnotationException(String message, Throwable cause,
         boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public String getPortletName() {
      return portletName;
   }

   public void setPortletName(String portletName) {
      this.portletName = portletName;
   }

}
