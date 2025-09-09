/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.fixtures;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author Scott
 *
 */
@ApplicationScoped
public class InvocationResults {

   private List<String> methods = new ArrayList<String>();
   boolean configExists = false;
   
   private static InvocationResults results;

   /**
    * singleton
    */
   private InvocationResults() {
   }
   
   public static InvocationResults getInvocationResults() {
      if (results == null) {
         results = new InvocationResults();
      }
      return results;
   }

   /**
    * @return the methods
    */
   public List<String> getMethods() {
      return methods;
   }
   
   /**
    * clears all methods
    */
   public void reset() {
      methods.clear();
      configExists = false;
   }
   
   /**
    * adds method to results
    * 
    * @param meth
    */
   public void addMethod(String meth) {
      methods.add(meth);
   }

   /**
    * @return the configExists
    */
   public boolean isConfigExists() {
      return configExists;
   }

   /**
    * @param configExists the configExists to set
    */
   public void setConfigExists(boolean configExists) {
      this.configExists = configExists;
   }
}
