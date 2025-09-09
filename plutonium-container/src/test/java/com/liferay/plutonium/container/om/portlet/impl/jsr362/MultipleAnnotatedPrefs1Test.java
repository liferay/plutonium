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
import java.util.Set;

import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.om.portlet.PortletDefinition;
import com.liferay.plutonium.container.om.portlet.impl.ConfigurationHolder;
import com.liferay.plutonium.container.om.portlet.impl.PortletApplicationDefinitionImpl;
import com.liferay.plutonium.container.om.portlet.impl.fixtures.TestAnnotatedPrefs1;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit test cases for JSR 362 portlet application definition.
 * @author Scott Nicklous
 *
 */
public class MultipleAnnotatedPrefs1Test {
   
   // defines both some portlets and a Listener
   private static final Class<?> TEST_ANNOTATED_CLASS1 = TestAnnotatedPrefs1.class;
   
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
      classes.add(TEST_ANNOTATED_CLASS1);
      cfp = new ConfigurationHolder();
      try {
         cfp.processConfigAnnotations(classes);
         try {
            cfp.validate();         // validate and ignore any validation problems.
         } catch (Exception e) {}   
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
    * Tests that the default portletNames='*' value in the portlet
    * preferences validator annotation applies the validator to all
    * portlets in the portlet app.  
    */
   @Test
   public void testPrefs() {
      List<PortletDefinition> list = cut.getPortlets();
      assertNotNull(list);
      assertEquals(3, list.size());
      
      for (PortletDefinition item : list) {
         assertNotNull(item.getPortletPreferences());
         assertNotNull(item.getPortletPreferences().getPreferencesValidator());
         assertEquals(TEST_ANNOTATED_CLASS1.getCanonicalName(), 
               item.getPortletPreferences().getPreferencesValidator());
      }
   }

}
