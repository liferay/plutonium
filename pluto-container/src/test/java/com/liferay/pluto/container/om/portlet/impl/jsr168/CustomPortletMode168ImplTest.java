/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.jsr168;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import com.liferay.pluto.container.om.portlet.CustomPortletMode;
import com.liferay.pluto.container.om.portlet.Description;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;
import com.liferay.pluto.container.om.portlet.impl.ConfigurationHolder;
import com.liferay.pluto.container.om.portlet.impl.CustomPortletModeImpl;
import com.liferay.pluto.container.om.portlet.impl.DescriptionImpl;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Scott Nicklous
 *
 */
public class CustomPortletMode168ImplTest {

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
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.CustomPortletModeImpl#getPortletMode()}.
    */
   @Test
   public void testGetPortletMode() {
      CustomPortletMode cpm = pad.getCustomPortletMode("portlet-mode");
      assertNotNull(cpm);
      assertEquals(1, pad.getCustomPortletModes().size());
      cpm = pad.getCustomPortletModes().get(0);
      assertEquals("portlet-mode", cpm.getPortletMode());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.CustomPortletModeImpl#getDescription(java.util.Locale)}.
    */
   @Test
   public void testGetDescription() {
      assertEquals(1, pad.getCustomPortletModes().size());
      CustomPortletMode cpm = pad.getCustomPortletModes().get(0);
      Locale loc = new Locale("de");
      Description d = cpm.getDescription(loc);
      assertNotNull(d);
      assertEquals("description", d.getText());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.CustomPortletModeImpl#getDescriptions()}.
    */
   @Test
   public void testGetDescriptions() {
      assertEquals(1, pad.getCustomPortletModes().size());
      CustomPortletMode cpm = pad.getCustomPortletModes().get(0);
      assertEquals("portlet-mode", cpm.getPortletMode());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr168.CustomPortletModeImpl#addDescription(com.liferay.pluto.container.om.portlet.Description)}.
    */
   @Test
   public void testAddDescription() {
      assertEquals(1, pad.getCustomPortletModes().size());
      CustomPortletMode cpm = 
            new CustomPortletModeImpl(pad.getCustomPortletModes().get(0));
      assertEquals(1, cpm.getDescriptions().size());
      Locale loc = Locale.FRENCH;
      String text = "Some description";
      Description d = new DescriptionImpl(loc, text);
      cpm.addDescription(d);
      
      List<Description> list = cpm.getDescriptions();
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
