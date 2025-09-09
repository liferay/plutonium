/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.reconcile.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import com.liferay.plutonium.container.bean.processor.AnnotatedMethodStore;
import com.liferay.plutonium.container.bean.processor.ConfigSummary;
import com.liferay.plutonium.container.bean.processor.tests.FileHelper;
import com.liferay.plutonium.container.om.portlet.EventDefinitionReference;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.om.portlet.PortletDefinition;
import com.liferay.plutonium.container.om.portlet.impl.ConfigurationHolder;
import com.liferay.plutonium.container.om.portlet.impl.EventDefinitionReferenceImpl;
import com.liferay.plutonium.container.reconcile.fixtures.TestPortlet1;
import com.liferay.plutonium.container.reconcile.fixtures.TestPortlet2;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Adds a bean portlet that is defined implicitly through the portlet method annotations.
 * 
 * @author Scott Nicklous
 */
public class ImpliedPortletTest {

   private static final Class<?> TEST_ANNOTATED_CLASS1 = TestPortlet1.class;
   private static final Class<?> TEST_ANNOTATED_CLASS2 = TestPortlet2.class;
   private static final String XML_FILE = 
         "com/liferay/plutonium/container/om/portlet/portlet362Reconcile.xml";
   
   private static final String pkg = "com.liferay.plutonium.container.reconcile.fixtures";

   private static AnnotatedMethodStore ams = null;
   private static ConfigSummary summary = null;
   private static ConfigurationHolder holder =  new ConfigurationHolder();

   // Classes under test
   private static PortletApplicationDefinition app;

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      Set<File> portletMethodClasses = FileHelper.getClasses(pkg);
      
      InputStream in = ImpliedPortletTest.class
            .getClassLoader().getResourceAsStream(XML_FILE);

      Set<Class<?>> configClasses = new HashSet<Class<?>>();
      configClasses.add(TEST_ANNOTATED_CLASS1);
      configClasses.add(TEST_ANNOTATED_CLASS2);

      try {
         holder.scanMethodAnnotations(portletMethodClasses);
         holder.processConfigAnnotations(configClasses);
         holder.processPortletDD(in);     // process portlet xml after annotations
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
      app = holder.getPad();
      
      assertNotNull(ams);
      assertNotNull(summary);
   }

   // Begin portlet app tests ==================================
   
   @Test
   public void testNumberPortlets() {
      assertEquals(4, app.getPortlets().size());
      assertNotNull(app.getPortlet("Portlet1"));
      assertNotNull(app.getPortlet("Portlet2"));
      assertNotNull(app.getPortlet("Portlet3"));
      assertNotNull(app.getPortlet("Portlet4"));
   }
   
   // tests that a portlet with not enough methods is thrown out.
   @Test
   public void incompletePortletTest() throws Exception {
      assertNull(app.getPortlet("IncompletePortlet"));
   }
  
   // Begin portlet 2 tests ================================== 
   
   @Test
   public void test4GetPortletName() {
      PortletDefinition portlet4 = app.getPortlet("Portlet4");
      assertNotNull(portlet4.getPortletName());
      assertEquals("Portlet4", portlet4.getPortletName());
   }

   @Test
   public void test4GetPortletClass() {
      PortletDefinition portlet4 = app.getPortlet("Portlet4");
      assertNull(portlet4.getPortletClass());
   }
   
   @Test
   public void test4processingEvent() throws Exception {
      PortletDefinition portlet4 = app.getPortlet("Portlet4");
      List<EventDefinitionReference> events = portlet4.getSupportedProcessingEvents();
      assertNotNull(events);
      assertEquals(2, events.size());
      assertTrue(events.contains(new EventDefinitionReferenceImpl(new QName("http://www.apache.org/", "event2"))));
      assertTrue(events.contains(new EventDefinitionReferenceImpl(new QName("https://www.java.net/", "event4"))));
   }
   
   @Test
   public void test4publishingEvent() throws Exception {
      PortletDefinition portlet4 = app.getPortlet("Portlet4");
      List<EventDefinitionReference> events = portlet4.getSupportedPublishingEvents();
      assertNotNull(events);
      assertEquals(3, events.size());
      assertTrue(events.contains(new EventDefinitionReferenceImpl(new QName("http://www.apache.org/", "event1"))));
      assertTrue(events.contains(new EventDefinitionReferenceImpl(new QName("https://www.java.net/", "event3"))));
      assertTrue(events.contains(new EventDefinitionReferenceImpl(new QName("https://www.java.net/", "event4"))));
   }
   

}
