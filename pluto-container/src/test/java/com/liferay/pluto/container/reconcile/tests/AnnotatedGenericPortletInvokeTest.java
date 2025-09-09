/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.reconcile.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import jakarta.portlet.PortletMode;
import javax.xml.namespace.QName;

import com.liferay.pluto.container.bean.processor.AnnotatedMethodStore;
import com.liferay.pluto.container.bean.processor.ConfigSummary;
import com.liferay.pluto.container.bean.processor.fixtures.InvocationResults;
import com.liferay.pluto.container.om.portlet.impl.ConfigurationHolder;
import com.liferay.pluto.container.reconcile.fixtures.TestPortlet5;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests that if a generic portlet event method is annotated to provide the processing
 * event reference QNames, the methods are part of the same portlet instance.
 * 
 * @author Scott Nicklous
 */
public class AnnotatedGenericPortletInvokeTest {
   
   private InvocationResults meths = InvocationResults.getInvocationResults();
   private static InvokeHelper helper;
   private static final Class<?> TEST_ANNOTATED_CLASS1 = TestPortlet5.class;
   private static AnnotatedMethodStore ams = null;
   private static ConfigSummary summary = null;
   private static ConfigurationHolder holder =  new ConfigurationHolder();

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {

      Set<Class<?>> classes = new HashSet<Class<?>>();
      classes.add(TEST_ANNOTATED_CLASS1);

      try {
         holder.processConfigAnnotations(classes);
         holder.reconcileBeanConfig();
         holder.instantiatePortlets(null);
         try {
            holder.validate();         // validate and ignore any validation problems.
         } catch (Exception e) {}   
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
      ams = holder.getMethodStore();
      summary = holder.getConfigSummary();
      
      assertNotNull(ams);
      assertNotNull(summary);
      
      helper = new InvokeHelper(ams);
   }

   @Before
   public void setUpBefore() throws Exception {
      helper.init("Portlet5", null);
   }
  
   // Begin portlet 1 tests ================================== 

   @Test
   public void test1init() throws Exception {
      String expectedMeth = TestPortlet5.class.getSimpleName() + "#init";
      helper.init("Portlet5", expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1destroy() throws Exception {
      String expectedMeth = TestPortlet5.class.getSimpleName() + "#destroy";
      helper.destroy("Portlet5", expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1action() throws Exception {
      String expectedMeth = TestPortlet5.class.getSimpleName() + "#processAction";
      helper.action("Portlet5", null, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1event1() throws Exception {
      String expectedMeth = TestPortlet5.class.getSimpleName() + "#processEvent";
      QName qn = new QName("http://www.apache.org/", "event1");
      helper.event("Portlet5", qn, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1event2() throws Exception {
      String expectedMeth = TestPortlet5.class.getSimpleName() + "#processEvent";
      QName qn = new QName("https://www.java.net/", "event4");
      helper.event("Portlet5", qn, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1header() throws Exception {
      String expectedMeth = TestPortlet5.class.getSimpleName() + "#renderHeaders";
      PortletMode pm = PortletMode.VIEW;
      helper.header("Portlet5", pm, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1render() throws Exception {
      String expectedMeth = TestPortlet5.class.getSimpleName() + "#render";
      PortletMode pm = PortletMode.VIEW;
      helper.render("Portlet5", pm, expectedMeth);
      assertTrue(meths.isConfigExists());
   }

   @Test
   public void test1res() throws Exception {
      String expectedMeth = TestPortlet5.class.getSimpleName() + "#serveResource";
      String resid = null;
      helper.resource("Portlet5", resid, expectedMeth);
      assertTrue(meths.isConfigExists());
   }
   
}
