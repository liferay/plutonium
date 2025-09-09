/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import static org.junit.Assert.*;

import java.io.InputStream;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Unmarshaller;

import com.liferay.plutonium.container.om.portlet10.impl.PortletAppType;
import com.liferay.plutonium.container.om.portlet10.impl.PortletType;
import org.junit.Before;
import org.junit.Test;

/**
 * Low-level test to read a deployment descriptor
 */
public class JaxbReadTest168Gen {

   private static final String XML_FILE     = "com/liferay/plutonium/container/om/portlet/portlet168Generated.xml";
   private static final String JAXB_CONTEXT = "com.liferay.plutonium.container.om.portlet10.impl:"
                                                  + "com.liferay.plutonium.container.om.portlet20.impl:"
                                                  + "com.liferay.plutonium.container.om.portlet30.impl";

   PortletAppType    portletApp;

   @Before
   public void setUp() throws Exception {

      try {
         JAXBContext cntxt = JAXBContext.newInstance(JAXB_CONTEXT);
         InputStream in = this.getClass().getClassLoader().getResourceAsStream(XML_FILE);
         Unmarshaller um = cntxt.createUnmarshaller();
         JAXBElement<?> jel = (JAXBElement<?>) um.unmarshal(in);
         assertNotNull(jel.getValue());
         assertTrue(jel.getValue() instanceof PortletAppType);
         portletApp = (PortletAppType) jel.getValue();
      } catch (Exception e) {
         System.out.println("\nException during setup: " + e.getMessage()
               + "\n");
         throw e;
      }
   }

   @Test
   public void portlet168Generated() throws Exception {
      assertEquals(portletApp.getPortlet().size(), 1);
      PortletType portlet = portletApp.getPortlet().get(0);
      assertNotNull(portlet);
      
      assertEquals("portlet168", portlet.getPortletName().getValue());
      assertEquals(1, portlet.getDisplayName().size());
      assertEquals("display-name", portlet.getDisplayName().get(0).getValue());
      assertEquals(0, portlet.getDisplayName().get(0).getLang().compareTo("de"));
      
      // Check out the init parameter
      assertEquals(1, portlet.getInitParam().size());
      assertEquals(1, portlet.getInitParam().get(0).getDescription().size());
      assertEquals(0, portlet.getInitParam().get(0).getDescription().get(0).getLang().compareTo("de"));
      assertEquals(0, portlet.getInitParam().get(0).getDescription().get(0).getValue().compareTo("description"));
      assertEquals("name", portlet.getInitParam().get(0).getName().getValue());
      assertEquals("value", portlet.getInitParam().get(0).getValue().getValue());
      
      // test expiration cache value
      assertNotNull(portlet.getExpirationCache());
      assertTrue(portlet.getExpirationCache().getValue() == 50);

      // check for some stuff from the portlet application
      assertEquals("portlet-mode", portletApp.getCustomPortletMode().get(0).getPortletMode().getValue());
      assertEquals("window-state", portletApp.getCustomWindowState().get(0).getWindowState().getValue());
      assertEquals("name", portletApp.getUserAttribute().get(0).getName().getValue());
      assertEquals("portlet.name", portletApp.getSecurityConstraint().get(0)
            .getPortletCollection().getPortletName().get(0).getValue());
   }
   
}
