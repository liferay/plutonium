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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.liferay.plutonium.container.bean.processor.AnnotatedMethodStore;
import com.liferay.plutonium.container.bean.processor.ConfigSummary;
import com.liferay.plutonium.container.bean.processor.PortletInvoker;
import com.liferay.plutonium.container.bean.processor.fixtures.InvocationResults;
import com.liferay.plutonium.container.bean.processor.fixtures.action.Action1;
import com.liferay.plutonium.container.bean.processor.fixtures.action.Action2;
import com.liferay.plutonium.container.bean.processor.fixtures.mocks.MockActionRequest;
import com.liferay.plutonium.container.bean.processor.fixtures.mocks.MockActionResponse;
import com.liferay.plutonium.container.om.portlet.impl.ConfigurationHolder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for invoking the annotated action methods.
 * 
 * @author Scott Nicklous
 *
 */
public class InvokeActionTest {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   
   private static MockActionRequest req = new MockActionRequest();
   private static MockActionResponse resp = new MockActionResponse();
   
   private static final Set<Class<?>> annotatedClasses =
         new HashSet<Class<?>>(Arrays.asList(Action1.class)); 
   private static final String pkg = "com.liferay.plutonium.container.bean.processor.fixtures.action";
   
   private static AnnotatedMethodStore ams = null;
   private static ConfigSummary summary = null;
   private static ConfigurationHolder holder =  new ConfigurationHolder();
   
   @BeforeClass
   public static void setUpClass() throws URISyntaxException, IOException {
      Set<File> classes = FileHelper.getClasses(pkg);
      holder.scanMethodAnnotations(classes);
      holder.processConfigAnnotations(annotatedClasses);
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
      req.setActionName(null);
      i.processAction(req, resp);
      List<String> names = meths.getMethods();
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(names.contains(Action1.class.getSimpleName() + "#action1"));
   }
   
   @Test
   public void invoke2() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet2");
      req.setActionName(null);
      i.processAction(req, resp);
      List<String> names = meths.getMethods();
      // there are two valid possibilities
      List<String> meths = Arrays.asList(new String[] {
            Action1.class.getSimpleName() + "#action2", 
            Action2.class.getSimpleName() + "#action2",
            });
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(meths.contains(names.get(0)));
   }
   
   @Test
   public void invoke3a() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet3");
      req.setActionName("");
      i.processAction(req, resp);
      List<String> names = meths.getMethods();
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(names.contains(Action2.class.getSimpleName() + "#action1a"));
   }
   
   @Test
   public void invoke3b() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet3");
      req.setActionName("Fred");
      i.processAction(req, resp);
      List<String> names = meths.getMethods();
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(names.contains(Action2.class.getSimpleName() + "#action1b"));
   }
   
   @Test
   public void invoke3c() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet3");
      req.setActionName("Barney");
      i.processAction(req, resp);
      List<String> names = meths.getMethods();
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(names.contains(Action2.class.getSimpleName() + "#action1c"));
   }
   
   @Test
   public void invoke6() throws Exception {
      meths.reset();
      PortletInvoker i = new PortletInvoker(ams, "portlet6");
      req.setActionName("Wilma");
      i.processAction(req, resp);
      List<String> names = meths.getMethods();
      // there are two valid possibilities
      List<String> meths = Arrays.asList(new String[] {
            Action2.class.getSimpleName() + "#action6", 
            Action2.class.getSimpleName() + "#action7",
            });
      assertNotNull(names);
      assertEquals(1, names.size());
      assertTrue(meths.contains(names.get(0)));
   }
   
}
