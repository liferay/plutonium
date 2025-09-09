/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.jsr168;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;
import com.liferay.pluto.container.om.portlet.impl.ConfigurationHolder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit test cases for JSR 168 portlet application definition.
 * @author Scott Nicklous
 *
 */
public class PortletApplicationDefinition168ImplTest {

   private static final String XML_FILE = 
         "com/liferay/pluto/container/om/portlet/portlet168Generated.xml";
   
   private static PortletApplicationDefinition pad;

   /**
    * @throws java.lang.Exception
    */
   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      
      InputStream in = PortletApplicationDefinition168ImplTest.class
            .getClassLoader().getResourceAsStream(XML_FILE);
      
      ConfigurationHolder cfp = new ConfigurationHolder();
      try {
         cfp.processPortletDD(in);
         pad = cfp.getPad();
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.PortletApplicationDefinitionImpl#getId()}.
    */
   @Test
   public void testGetSetId() {
      String val = pad.getId();
      assertNotNull(val);
      assertEquals("id1", val);
      pad.setId("42");
      val = pad.getId();
      assertNotNull(val);
      assertEquals("42", val);
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.PortletApplicationDefinitionImpl#getName()}.
    */
   @Test
   public void testGetSetName() {
      pad.setName("Bob");
      String val = pad.getName();
      assertNotNull(val);
      assertEquals("Bob", val);
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.PortletApplicationDefinitionImpl#getContextPath()}.
    */
   @Test
   public void testSetGetContextPath() {
      pad.setContextPath("Bob");
      String val = pad.getContextPath();
      assertNotNull(val);
      assertEquals("Bob", val);
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.PortletApplicationDefinitionImpl#getVersion()}.
    */
   @Test
   public void testGetSetVersion() {
      String val = pad.getVersion();
      assertNotNull(val);
      assertEquals("1.0", val);
      pad.setVersion("42");
      val = pad.getVersion();
      assertNotNull(val);
      assertEquals("42", val);
   }

}
