/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.portlet.Event;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.pluto.container.EventProvider;
import com.liferay.pluto.container.PortletContainerException;
import com.liferay.pluto.container.PortletWindow;
import com.liferay.pluto.container.driver.PortletRegistryService;
import com.liferay.pluto.container.om.portlet.EventDefinition;
import com.liferay.pluto.container.om.portlet.EventDefinitionReference;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;
import com.liferay.pluto.driver.services.portal.PortletWindowConfig;

/**
 * @version $Id$
 */
public class EventProviderImpl implements EventProvider {
   /** Logger. */
   private static final Logger    LOG = LoggerFactory.getLogger(EventProviderImpl.class);
   private PortletWindow          portletWindow;
   private PortletRegistryService portletRegistry;

   public EventProviderImpl(PortletWindow portletWindow, PortletRegistryService portletRegistry) {
      this.portletWindow = portletWindow;
      this.portletRegistry = portletRegistry;
      
      if (LOG.isTraceEnabled()) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Portlet window: ").append(portletWindow.getId().getStringId());
         LOG.debug(txt.toString());
      }

   }

   @SuppressWarnings("unchecked")
   public Event createEvent(QName qname, Serializable value) throws IllegalArgumentException {
      
      if (LOG.isDebugEnabled()) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("QName: ").append(qname.toString());
         txt.append(", value class: ").append((value == null) ? "null": value.getClass().getCanonicalName());
         LOG.debug(txt.toString());
      }
      
      if (isDeclaredAsPublishingEvent(qname)) {
         if (value != null && !isValueInstanceOfDefinedClass(qname, value)) {
            throw new IllegalArgumentException("Payload class (" + value.getClass().getCanonicalName()
                  + ") does not have the right class, check your defined event types in portlet.xml.");
         }
         try {
            if (value == null) {
               return new EventImpl(qname, value);
            } else {
               
               boolean debug = false;
               if (LOG.isDebugEnabled() && (value instanceof HashMap)) {
                  debug = true;
                  StringBuilder txt = new StringBuilder(128);
                  txt.append("Event payload params:");
                  Map<String, String[]> pmap = (HashMap<String, String[]>) value; 
                  for (String name : pmap.keySet()) {
                     txt.append("\nname: ").append(name);
                     txt.append(", vals: ").append(Arrays.toString(pmap.get(name)));
                  }
                  LOG.debug(txt.toString());
               }
               
               ClassLoader cl = Thread.currentThread().getContextClassLoader();
               Writer out = new StringWriter();
               @SuppressWarnings("rawtypes")
               Class clazz = value.getClass();
               try {
                  Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                  JAXBContext jc = JAXBContext.newInstance(clazz);
                  Marshaller marshaller = jc.createMarshaller();
                  JAXBElement<Serializable> element = new JAXBElement<Serializable>(qname, clazz, value);
                  marshaller.marshal(element, out);
               } finally {
                  Thread.currentThread().setContextClassLoader(cl);
               }
               
               if (debug) {
                  LOG.debug("Resulting marshalled HashMap: \n" + out.toString());
               }
               
               return new EventImpl(qname, out.toString());
            }
         } catch (JAXBException e) {
            // maybe there is no valid jaxb binding
            LOG.error("Event handling failed", e);
         } catch (FactoryConfigurationError e) {
            LOG.warn(e.getMessage(), e);
         }
      }
      return null;
   }

   private boolean isDeclaredAsPublishingEvent(QName qname) {
      String applicationId = PortletWindowConfig.parseContextPath(portletWindow.getId().getStringId());
      String applicationName = applicationId;
      String portletName = PortletWindowConfig.parsePortletName(portletWindow.getId().getStringId());
      List<? extends EventDefinitionReference> events = null;
      try {
         events = portletRegistry.getPortlet(applicationName, portletName).getSupportedPublishingEvents();
      } catch (PortletContainerException e1) {
         e1.printStackTrace();
      }
      if (events != null) {
         for (EventDefinitionReference ref : events) {
            QName name = ref.getQualifiedName();
            if (name == null) {
               continue;
            }
            if (qname.equals(name)) {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isValueInstanceOfDefinedClass(QName qname, Serializable value) {
      PortletApplicationDefinition app = portletWindow.getPortletDefinition().getApplication();

      List<? extends EventDefinition> events = app.getEventDefinitions();
      if (events != null) {
         for (EventDefinition def : events) {
            if (def.getQName().equals(qname)) {
               Class<?> declaredPayload = null;
               try {
                  ClassLoader cl = Thread.currentThread().getContextClassLoader();
                  if (cl == null) {
                     cl = this.getClass().getClassLoader();
                  }
                  declaredPayload = cl.loadClass(def.getValueType());
                  if (!declaredPayload.isAssignableFrom(value.getClass())) {
                     StringBuilder txt = new StringBuilder(128);
                     txt.append("Error processing payload. ");
                     txt.append(" Specified class ").append(value.getClass().getCanonicalName());
                     txt.append(" is not a ").append(declaredPayload.getCanonicalName());
                     LOG.warn(txt.toString());
                     return false;
                  }
               } catch (Exception e) {
                  StringBuilder txt = new StringBuilder(128);
                  txt.append("Error processing payload. ");
                  txt.append(" Exception: ").append(e.toString());
                  StringWriter sw = new StringWriter();
                  PrintWriter pw = new PrintWriter(sw);
                  e.printStackTrace(pw);
                  pw.flush();
                  txt.append("\n").append(sw.toString());
                  LOG.warn(txt.toString());
                  return false;
               }
               return true;
            }
         }
      }
      // event not found
      return false;
   }
}
