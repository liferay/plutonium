/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.jsr362;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.portlet.PortletRequest;

import com.liferay.pluto.container.om.portlet.Filter;
import com.liferay.pluto.container.om.portlet.FilterMapping;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;
import com.liferay.pluto.container.om.portlet.impl.ConfigurationHolder;
import com.liferay.pluto.container.om.portlet.impl.PortletApplicationDefinitionImpl;
import com.liferay.pluto.container.om.portlet.impl.fixtures.TestAnnotatedFilterMultiple;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit test cases for JSR 362 portlet application definition.
 * @author Scott Nicklous
 *
 */
public class FilterMultiplePortletsTest {
   
   // defines both some portlets and a filter
   private static final Class<?> TEST_ANNOTATED_CLASS = TestAnnotatedFilterMultiple.class;
   
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
         try {
            cfp.validate();         // validate to expand the filter mapping portlet names
         } catch (Exception e) {}   // ignore any validation problems.
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
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.PortletApplicationDefinitionImpl#getFilter(java.lang.String)}.
    */
   @Test
   public void testGetFilter() {
      String newItem = "aFilter";
      Filter item = cut.getFilter(newItem);
      assertNotNull(item);
      Filter filter = cut.getFilters().get(0);
      assertEquals(TEST_ANNOTATED_CLASS.getCanonicalName(), 
            filter.getFilterClass());
      assertEquals("true", filter.getInitParam("execute").getParamValue());
   }
   
   @Test
   public void testFilterLifecycle() {
      String newItem = "aFilter";
      Filter filter = cut.getFilter(newItem);
      assertNotNull(filter);
      List<String> lifecycles =  Arrays.asList(new String[] {PortletRequest.RENDER_PHASE, PortletRequest.RESOURCE_PHASE, PortletRequest.HEADER_PHASE});
      for (String lc : filter.getLifecycles()) {
         assertTrue(lifecycles.contains(lc));
      }
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.PortletApplicationDefinitionImpl#getFilterMapping(java.lang.String)}.
    */
   @Test
   public void testGetFilterMapping() {
      String newItem = "aFilter";
      FilterMapping item = cut.getFilterMapping(newItem);
      assertNotNull(item);
      List<String> names = item.getPortletNames();
      assertEquals(3, names.size());
      assertTrue(names.contains("portlet1"));
      assertTrue(names.contains("portlet2"));
      assertTrue(names.contains("portlet3"));
   }

}
