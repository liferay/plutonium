/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.tests;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Methods to help handle class files.
 * 
 * @author Scott Nicklous
 *
 */
public class FileHelper {

   /**
    * Returns a set of all class files for given package.
    * 
    * @param pkg        the package
    * @return           set of classes
    * @throws URISyntaxException
    * @throws IOException
    */
   public static Set<File> getClasses(String pkg) throws URISyntaxException, IOException {
      Set<File> classes = new HashSet<File>();

      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      
      List<URL> urls = Collections.list(cl.getResources(pkg.replaceAll("\\.", "/")));
      for (URL url : urls) {
         File dir = new File(url.toURI());
         if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
               if (file.isFile() && file.getAbsolutePath().endsWith(".class")) {
                  classes.add(file);
               }
            }
         }
      }

      return classes;
   }

}
