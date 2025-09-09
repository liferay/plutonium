/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.v3annotated;

import jakarta.enterprise.context.RequestScoped;

/**
 * Provides a random number generated once per request.
 * @author Scott Nicklous
 *
 */
@RequestScoped
public class RequestRandomNumberBean {
   
   private int randomNumber;
   
   public RequestRandomNumberBean() {
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
