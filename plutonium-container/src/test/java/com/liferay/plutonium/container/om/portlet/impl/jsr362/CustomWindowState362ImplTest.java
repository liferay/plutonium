/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.om.portlet.impl.jsr362;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import com.liferay.plutonium.container.om.portlet.CustomWindowState;
import com.liferay.plutonium.container.om.portlet.Description;
import com.liferay.plutonium.container.om.portlet.PortletApplicationDefinition;
import com.liferay.plutonium.container.om.portlet.impl.ConfigurationHolder;
import com.liferay.plutonium.container.om.portlet.impl.CustomWindowStateImpl;
import com.liferay.plutonium.container.om.portlet.impl.DescriptionImpl;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Scott Nicklous
 *
 */
public class CustomWindowState362ImplTest {

   private static final String XML_FILE = 
         "com/liferay/plutonium/container/om/portlet/portlet362Generated.xml";
   
   private static PortletApplicationDefinition pad;

   /**
    * @throws java.lang.Exception
    */
   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      InputStream in = CustomWindowState362ImplTest.class
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
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.jsr362.CustomWindowStateImpl#getWindowState()}.
    */
   @Test
   public void testGetWindowState() {
      CustomWindowState cws = pad.getCustomWindowState("window-state");
      assertNotNull(cws);
      assertEquals(1, pad.getCustomWindowStates().size());
      cws = pad.getCustomWindowStates().get(0);
      assertEquals("window-state", cws.getWindowState());
   }

   /**
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.jsr362.CustomWindowStateImpl#getDescription(java.util.Locale)}.
    */
   @Test
   public void testGetDescription() {
      assertEquals(1, pad.getCustomWindowStates().size());
      CustomWindowState cws = pad.getCustomWindowStates().get(0);
      Locale loc = new Locale("de");
      Description d = cws.getDescription(loc);
      assertNotNull(d);
      assertEquals("description", d.getText());
   }

   /**
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.jsr362.CustomWindowStateImpl#getDescriptions()}.
    */
   @Test
   public void testGetDescriptions() {
      assertEquals(1, pad.getCustomWindowStates().size());
      CustomWindowState cws = pad.getCustomWindowStates().get(0);
      assertEquals("window-state", cws.getWindowState());
   }

   /**
    * Test method for {@link com.liferay.plutonium.container.om.portlet.impl.jsr362.CustomWindowStateImpl#addDescription(com.liferay.plutonium.container.om.portlet.Description)}.
    */
   @Test
   public void testAddDescription() {
      assertEquals(1, pad.getCustomWindowStates().size());
      CustomWindowState cws = 
            new CustomWindowStateImpl(pad.getCustomWindowStates().get(0));
      assertEquals(1, cws.getDescriptions().size());
      Locale loc = Locale.FRENCH;
      String text = "Some description";
      Description d = new DescriptionImpl(loc, text);
      cws.addDescription(d);
      
      List<Description> list = cws.getDescriptions();
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
