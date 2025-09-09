/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.liferay.plutonium.container.bean.processor.AnnotatedMethodStore;
import com.liferay.plutonium.container.bean.processor.ConfigSummary;
import com.liferay.plutonium.container.bean.processor.PortletInvoker;
import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;
import com.liferay.plutonium.container.bean.processor.fixtures.destroy.Destroy1;
import com.liferay.plutonium.container.bean.processor.fixtures.destroy.Destroy2;
import com.liferay.plutonium.container.om.portlet.impl.ConfigurationHolder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for destroy method invocation.
 * 
 * @author Scott Nicklous
 *
 */
public class InvokeDestroyTest {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   private static final String         pkg     = "com.liferay.plutonium.container.bean.processor.fixtures.destroy";
   
   private static AnnotatedMethodStore ams     = null;
   private static ConfigSummary        summary = null;
   private static ConfigurationHolder  holder  = new ConfigurationHolder();
   
   @BeforeClass
   public static void setUpClass() throws URISyntaxException, IOException {
      Set<File> classes = FileHelper.getClasses(pkg);
      holder.scanMethodAnnotations(classes);
      holder.reconcileBeanConfig();
      holder.instantiatePortlets(null);
      ams = holder.getMethodStore();
      summary = holder.getConfigSummary();

      assertNotNull(ams);
      assertNotNull(summary);
   }
   
   @Test
   public void invoke1() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet1");
      i.destroy();
      List<String> names = meths.getMethods();
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(names.contains(Destroy1.class.getSimpleName() + "#destroy1"));
   }
   
   @Test
   public void invoke2() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet2");
      i.destroy();
      List<String> names = meths.getMethods();
      // there are two valid possibilities
      List<String> meths = Arrays.asList(new String[] {
            Destroy1.class.getSimpleName() + "#destroy2", Destroy2.class.getSimpleName() + "#destroy2",
            });
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(meths.contains(names.get(0)));
   }
   
   @Test
   public void invoke3() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet3");
      i.destroy();
      List<String> names = meths.getMethods();
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(names.contains(Destroy2.class.getSimpleName() + "#destroy1"));
   }
   
}
