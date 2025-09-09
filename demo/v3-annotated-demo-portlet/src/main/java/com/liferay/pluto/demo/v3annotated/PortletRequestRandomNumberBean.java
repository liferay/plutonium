/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.v3annotated;

import jakarta.inject.Named;
import jakarta.portlet.annotations.PortletRequestScoped;

/**
 * Provides a random number generated once per request.
 * @author Scott Nicklous
 *
 */
@PortletRequestScoped @Named("reqnum")
public class PortletRequestRandomNumberBean {
   
   private int randomNumber;
   
   public PortletRequestRandomNumberBean() {
      randomNumber = ((int)(Math.random() * 1000));
   }

   /**
    * @return the randomNumber
    */
   public int getRandomNumber() {
      return randomNumber;
   }

   /**
    * @param randomNumber the randomNumber to set
    */
   public void setRandomNumber(int randomNumber) {
      this.randomNumber = randomNumber;
   }

}
