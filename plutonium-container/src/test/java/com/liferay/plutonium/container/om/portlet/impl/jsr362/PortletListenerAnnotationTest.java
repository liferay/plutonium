/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl.jsr362;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.liferay.plutonium.container.om.portlet.Listener;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.om.portlet.impl.ConfigurationHolder;
import com.liferay.plutonium.container.om.portlet.impl.ListenerImpl;
import com.liferay.plutonium.container.om.portlet.impl.PortletApplicationDefinitionImpl;
import com.liferay.plutonium.container.om.portlet.impl.fixtures.TestAnnotatedListener;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit test cases for JSR 362 portlet application definition.
 * @author Scott Nicklous
 *
 */
public class PortletListenerAnnotationTest {
   
   private static final Class<?> TEST_ANNOTATED_CLASS = TestAnnotatedListener.class;
   
   private static PortletApplicationDefinition pad;
   private static ConfigurationHolder cfp;
   
   // class under test; cloned new for each test
   private  PortletApplicationDefinition cut;

   /**
    * @throws java.lang.Exception
    */
   @BeforeClass
   public static void setUpBeforeClass() throws Exception {

      Set<Class<?>> classes = new HashSet<Class<?>>();
      classes.add(TEST_ANNOTATED_CLASS);
      cfp = new ConfigurationHolder();
      try {
         cfp.processConfigAnnotations(classes);
         pad = cfp.getPad();
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }
   
   @Before
   public void setUpBefore() throws Exception {
      cut = new PortletApplicationDefinitionImpl(pad);
   }

   /**
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.PortletApplicationDefinitionImpl#getListener(java.lang.String)}.
    */
   @Test
   public void testGetListener() {
      String newItem = "aListener";
      Listener item = cut.getListener(newItem);
      assertNotNull(item);
      Listener listener = cut.getListeners().get(0);
      assertEquals("com.liferay.plutonium.container.om.portlet.impl.fixtures.TestAnnotatedListener",
            listener.getListenerClass());
   }
   
   @Test
   public void testListenerDescription() {
      String newItem = "aListener";
      Listener listener = cut.getListener(newItem);
      assertNotNull(listener);
      assertEquals(2, listener.getDescriptions().size());
      assertEquals("Ein ordentlicher Listener", listener.getDescription(new Locale("de")).getText());
      assertEquals("Quite the listener", listener.getDescription(Locale.ENGLISH).getText());
   }

   @Test
   public void testListenerDisplayName() {
      String newItem = "aListener";
      Listener listener = cut.getListener(newItem);
      assertNotNull(listener);
      assertEquals(2, listener.getDisplayNames().size());
      assertEquals("Ein Listener", listener.getDisplayName(Locale.GERMAN).getText());
      assertEquals("A Listener", listener.getDisplayName(new Locale("en")).getText());
   }

   @Test
   public void testListenerOrdinal() {
      String newItem = "aListener";
      Listener listener = cut.getListener(newItem);
      assertNotNull(listener);
      assertEquals(100, listener.getOrdinal());
   }

   /**
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.PortletApplicationDefinitionImpl#getListeners()}.
    */
   @Test
   public void testGetListeners() {
      String newItem = "aListener";
      List<Listener> list = cut.getListeners();
      assertNotNull(list);
      assertEquals(1, list.size());
      assertEquals(newItem, list.get(0).getListenerName());
      assertEquals(100, list.get(0).getOrdinal());
   }

   /**
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.PortletApplicationDefinitionImpl#addListener(com.liferay.plutonium.container.om.portlet.Listener)}.
    */
   @Test
   public void testAddListener() {
      String newItem = "newListener";
      Listener l = new ListenerImpl("SomeClass");
      l.setListenerName(newItem);
      l.setOrdinal(200);
      cut.addListener(l);
      
      List<Listener> list = cut.getListeners();
      assertNotNull(list);
      assertEquals(2, list.size());
      assertEquals(newItem, list.get(1).getListenerName());
   }

   /**
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.PortletApplicationDefinitionImpl#addListener(com.liferay.plutonium.container.om.portlet.Listener)}.
    */
   @Test
   public void checkListenerOrder1() {
      String newItem = "newListener";
      String oldItem = "aListener";
      Listener l = new ListenerImpl("SomeClass");
      l.setListenerName(newItem);
      l.setOrdinal(10);
      cut.addListener(l);
      
      List<Listener> list = cut.getListeners();
      assertNotNull(list);
      assertEquals(2, list.size());
      assertEquals(newItem, list.get(0).getListenerName());
      assertEquals(oldItem, list.get(1).getListenerName());
   }

   /**
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.PortletApplicationDefinitionImpl#addListener(com.liferay.plutonium.container.om.portlet.Listener)}.
    */
   @Test
   public void checkListenerOrder2() {
      String newItem = "newListener";
      String oldItem = "aListener";
      Listener l = new ListenerImpl("SomeClass");
      l.setListenerName(newItem);
      l.setOrdinal(1000);
      cut.addListener(l);
      
      List<Listener> list = cut.getListeners();
      assertNotNull(list);
      assertEquals(2, list.size());
      assertEquals(newItem, list.get(1).getListenerName());
      assertEquals(oldItem, list.get(0).getListenerName());
   }

   /**
    * Test method to delete listener when class is null
    */
   @Test
   public void deleteListener1() {
      String oldItem = "aListener";
      Listener l = new ListenerImpl((String)null);
      l.setListenerName(oldItem);
      l.setOrdinal(1000);
      cut.addListener(l);
      
      List<Listener> list = cut.getListeners();
      assertNotNull(list);
      assertEquals(0, list.size());
   }

   /**
    * Test method to delete listener when class is empty
    */
   @Test
   public void deleteListener2() {
      String oldItem = "aListener";
      Listener l = new ListenerImpl("");
      l.setListenerName(oldItem);
      l.setOrdinal(1000);
      cut.addListener(l);
      
      List<Listener> list = cut.getListeners();
      assertNotNull(list);
      assertEquals(0, list.size());
   }

   /**
    * Test method for replacing a listener definition
    */
   @Test
   public void testAddDupListener() {
      String newItem = "aListener";
      String clsName = "SomeClass";
      Listener fil = new ListenerImpl(clsName);
      fil.setListenerName(newItem);
      cut.addListener(fil);
      
      List<Listener> list = cut.getListeners();
      assertNotNull(list);
      assertEquals(1, list.size());
      assertEquals(newItem, list.get(0).getListenerName());
      assertEquals(clsName, list.get(0).getListenerClass());
   }

}
