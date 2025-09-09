/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3annotated;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;

/**
 * Provides a random number generated once per request.
 * @author Scott Nicklous
 *
 */
@SessionScoped
public class SessionRandomNumberBean implements Serializable {
   private static final long serialVersionUID = -5412855426733207637L;

   private int randomNumber;
   
   public SessionRandomNumberBean() {
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
