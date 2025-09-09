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

import jakarta.portlet.PortletConfig;

import com.liferay.plutonium.container.bean.processor.AnnotatedMethodStore;
import com.liferay.plutonium.container.bean.processor.ConfigSummary;
import com.liferay.plutonium.container.bean.processor.PortletInvoker;
import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;
import com.liferay.plutonium.container.bean.processor.fixtures.init.Init1;
import com.liferay.plutonium.container.bean.processor.fixtures.init.Init2;
import com.liferay.plutonium.container.bean.processor.fixtures.mocks.MockPortletConfig;
import com.liferay.plutonium.container.om.portlet.impl.ConfigurationHolder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for init method invocation.
 * 
 * @author Scott Nicklous
 *
 */
public class InvokeInitTest {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   private static PortletConfig config = new MockPortletConfig();
   
   private static final String         pkg     = "com.liferay.plutonium.container.bean.processor.fixtures.init";
   
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
      i.init(config);
      List<String> names = meths.getMethods();
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(names.contains(Init1.class.getSimpleName() + "#init1"));
   }
   
   @Test
   public void invoke2() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet2");
      i.init(config);
      List<String> names = meths.getMethods();
      // there are two valid possibilities
      List<String> meths = Arrays.asList(new String[] {
            Init1.class.getSimpleName() + "#init2", Init2.class.getSimpleName() + "#init2",
            });
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(meths.contains(names.get(0)));
   }
   
   @Test
   public void invoke3() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet3");
      i.init(config);
      List<String> names = meths.getMethods();
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(names.contains(Init2.class.getSimpleName() + "#init1"));
   }
   
}
