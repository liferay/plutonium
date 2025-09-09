/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3annotated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

/**
 * Interceptor to provide logging for our project
 * 
 * @author Scott Nicklous
 */
@Interceptor
@Log
public class LoggingInterceptor implements Serializable {

   private static final long serialVersionUID = -73481963162413796L;

   private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class.getCanonicalName());

   /**
    * Method called for use as an interceptor. Called before the intercepted method is 
    * called. 
    * 
    * @param ic
    * @return
    * @throws Exception
    */
   @AroundInvoke
   public Object log(InvocationContext ic) throws Exception {

      String cls = ic.getMethod().getDeclaringClass().getCanonicalName();
      String meth = ic.getMethod().getName();
      
      // Log method entry
      Logger mlogger = LoggerFactory.getLogger(cls);
      mlogger.debug("ENTERING: " + cls, meth);
      
      // Continue through chain until actual bean method is executed
      Object obj = ic.proceed(); 
   
      // this logging statement is only for debugging.
      logger.debug("Method " + meth + " has been called.");
      
      // Now log the exit
      mlogger.debug("EXITING: " + cls, meth);

      return obj;
   }

}
