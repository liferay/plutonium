/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.jsr286;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.pluto.container.om.portlet.Description;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;
import com.liferay.pluto.container.om.portlet.UserAttribute;
import com.liferay.pluto.container.om.portlet.impl.ConfigurationHolder;
import com.liferay.pluto.container.om.portlet.impl.DescriptionImpl;
import com.liferay.pluto.container.om.portlet.impl.UserAttributeImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Scott Nicklous
 *
 */
public class UserAttribute286ImplTest {

   private static final String XML_FILE = 
         "com/liferay/pluto/container/om/portlet/portlet286Generated.xml";
   
   private static PortletApplicationDefinition pad;
   private        List<UserAttribute> attrs;
   private        UserAttribute ua;

   /**
    * @throws java.lang.Exception
    */
   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      
      InputStream in = UserAttribute286ImplTest.class
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
   
   @Before
   public void setUpBefore() throws Exception {
      attrs = new ArrayList<UserAttribute>();
      for (UserAttribute ua : pad.getUserAttributes()) {
         attrs.add(new UserAttributeImpl(ua));
      }
      assertEquals(1, attrs.size());
      ua = attrs.get(0);
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.UserAttributeImpl#getName()}.
    */
   @Test
   public void testGetName() {
      assertEquals("name", ua.getName());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.UserAttributeImpl#getDescription(java.util.Locale)}.
    */
   @Test
   public void testGetDescription() {
      Locale loc = new Locale("DE");
      Description desc = ua.getDescription(loc);
      assertNotNull(desc);
      assertEquals("description", desc.getText());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.UserAttributeImpl#getDescriptions()}.
    */
   @Test
   public void testGetDescriptions() {
      List<Description> list = ua.getDescriptions();
      assertNotNull(list);
      assertEquals(1, list.size());
      assertEquals("description", list.get(0).getText());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.UserAttributeImpl#addDescription(com.liferay.pluto.container.om.portlet.Description)}.
    */
   @Test
   public void testAddDescription() {
      Locale loc = Locale.FRENCH;
      String text = "Some description";
      Description d = new DescriptionImpl(loc, text);
      ua.addDescription(d);

      List<Description> list = ua.getDescriptions();
      assertNotNull(list);
      assertEquals(2, list.size());
      for (Description desc : list) {
         if (desc.getLocale().equals(loc)) {
            assertEquals(text, desc.getText());
         } else {
            assertEquals("description", desc.getText());
         }
      }
   }

}
