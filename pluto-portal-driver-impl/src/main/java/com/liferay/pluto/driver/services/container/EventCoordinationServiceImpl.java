/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.driver.services.container;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jakarta.portlet.Event;
import jakarta.portlet.PortletException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.liferay.pluto.container.EventCoordinationService;
import com.liferay.pluto.container.PortletContainer;
import com.liferay.pluto.container.PortletContainerException;
import com.liferay.pluto.container.PortletWindow;
import com.liferay.pluto.container.driver.PortletContextService;
import com.liferay.pluto.container.driver.PortletRegistryService;
import com.liferay.pluto.container.om.portlet.EventDefinition;
import com.liferay.pluto.container.om.portlet.EventDefinitionReference;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;
import com.liferay.pluto.container.om.portlet.PortletDefinition;
import com.liferay.pluto.driver.AttributeKeys;
import com.liferay.pluto.driver.config.DriverConfiguration;
import com.liferay.pluto.driver.core.PortalRequestContext;
import com.liferay.pluto.driver.core.PortletWindowImpl;
import com.liferay.pluto.driver.services.portal.PageConfig;
import com.liferay.pluto.driver.services.portal.PortletWindowConfig;
import com.liferay.pluto.driver.url.PortalURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventCoordinationServiceImpl implements EventCoordinationService {
   /** Logger. */
   private static final Logger          LOG           = LoggerFactory
                                                            .getLogger(EventCoordinationServiceImpl.class);

   /** PortletRegistryService used to obtain PortletApplicationConfig objects */
   private final PortletRegistryService portletRegistry;

   /** PortletContextService used to obtain PortletContext objects */
   private final PortletContextService  portletContextService;

   public EventCoordinationServiceImpl(PortletRegistryService portletRegistry,
         PortletContextService portletContextService) {
      this.portletRegistry = portletRegistry;
      this.portletContextService = portletContextService;
   }

   public void processEvents(PortletContainer container,
         PortletWindow portletWindow, HttpServletRequest request,
         HttpServletResponse response, List<Event> events) {
      
      ServletContext containerServletContext = PortalRequestContext.getContext(
            request).getServletContext();
      DriverConfiguration driverConfig = (DriverConfiguration) containerServletContext
            .getAttribute(AttributeKeys.DRIVER_CONFIG);

      // PortalURL portalURL = PortalURLParserImpl.getParser().parse(request);
      PortalURL portalURL = PortalRequestContext.getContext(request)
            .createPortalURL();

      // Map<String, PortletWindowThread> portletWindowThreads = new
      // HashMap<String, PortletWindowThread>();

      // ThreadGroup threadGroup = new ThreadGroup("FireEventThreads");

      for (Event event : events) {
         List<String> portletNames = getAllPortletsRegisteredForEvent(event,
               driverConfig, containerServletContext, portalURL);

         // Deliver events to all portlets in the portal
         // Collection<PortletWindowConfig> portlets =
         // getAllPortlets(driverConfig);

         // Limit event delivery to portlets that are on the current page
         Collection<PortletWindowConfig> portlets = new ArrayList<PortletWindowConfig>();
         for (String pid : portalURL.getPortletIds()) {
            portlets.add(PortletWindowConfig.fromId(pid));
         }

         // iterate all portlets in the portal
         for (PortletWindowConfig config : portlets) {
            PortletWindow window = new PortletWindowImpl(container, config,
                  portalURL);
            if (portletNames != null) {
               for (String portlet : portletNames) {
                  if (portlet.equals(config.getId())) {
                     /*
                      * PLUTO-569: multi-threaded (event) request processing
                      * isn't thread save with the Pluto Portal Driver handling
                      * of request attributes as they all are stored/managed
                      * within the single underlying HttpServletRequest.
                      * Providing proper thread save parallel request processing
                      * would require extensive enhancements to the Pluto Portal
                      * Driver and as such is out-of-scope for the purpose of
                      * the Portal Driver itself.
                      * 
                      * // the thread now is a new one, with possible //
                      * waiting, // for the old to exit
                      * 
                      * 
                      * PortletWindowThread portletWindowThread =
                      * getPortletWindowThread(portletWindowThreads,
                      * threadGroup, container, config, window, request,
                      * response, containerServletContext);
                      * 
                      * // is this event portletWindowThread.addEvent(event);
                      * 
                      * portletWindowThread.start();
                      * 
                      * } } } } waitForEventExecution(threadGroup); try {
                      * Thread.sleep(WAITING_CYCLE); } catch
                      * (InterruptedException e) { LOG.warn(e.getMessage(),e); }
                      * } waitForEventExecution(threadGroup);
                      */
                     doEvent(container, window, event, request, response);
                  }
               }
            }
         }
      }
   }

   @SuppressWarnings("rawtypes")
   protected void doEvent(PortletContainer container,
         PortletWindow portletWindow, Event event, HttpServletRequest request,
         HttpServletResponse response) {
      try {
         Object value = event.getValue();

         XMLStreamReader xml = null;
         try {
            if (value instanceof String) {
               String in = (String) value;
               xml = XMLInputFactory.newInstance().createXMLStreamReader(
                     new StringReader(in));
            }
         } catch (XMLStreamException e1) {
            throw new IllegalStateException(e1);
         } catch (FactoryConfigurationError e1) {
            throw new IllegalStateException(e1);
         }

         if (xml != null) {
            // XMLStreamReader xml = (XMLStreamReader) event.getValue();

            // provider.getEventDefinition(event.getQName());
            try {
               // now test if object is jaxb
               EventDefinition eventDefinitionDD = getEventDefintion(
                     portletWindow, event.getQName());

               ClassLoader loader = portletContextService
                     .getClassLoader(portletWindow.getPortletDefinition()
                           .getApplication().getName());
               Class<? extends Serializable> clazz = loader.loadClass(
                     eventDefinitionDD.getValueType()).asSubclass(
                     Serializable.class);

               JAXBContext jc = JAXBContext.newInstance(clazz);
               Unmarshaller unmarshaller = jc.createUnmarshaller();

               // unmarshaller.setEventHandler(new
               // jakarta.xml.bind.helpers.DefaultValidationEventHandler());

               JAXBElement result = unmarshaller.unmarshal(xml, clazz);

               event = new EventImpl(event.getQName(),
                     (Serializable) result.getValue());
            } catch (JAXBException e) {
               throw new IllegalStateException(e);
            } catch (ClassCastException e) {
               throw new IllegalStateException(e);
            } catch (ClassNotFoundException e) {
               throw new IllegalStateException(e);
            } catch (PortletContainerException e) {
               throw new IllegalStateException(e);
            }
         }
         container.doEvent(portletWindow, request, response, event);
      } catch (PortletException e) {
         LOG.warn(e.getMessage(), e);
      } catch (IOException e) {
         LOG.warn(e.getMessage(), e);
      } catch (PortletContainerException e) {
         LOG.warn(e.getMessage(), e);
      }
   }

   private EventDefinition getEventDefintion(PortletWindow portletWindow, QName name) {
      PortletApplicationDefinition appDD = portletWindow.getPortletDefinition().getApplication();
      
      for (EventDefinition def : appDD.getEventDefinitions()) {
         if (def.getQName().equals(name)) {
            return def;
         }
      }
      
      throw new IllegalStateException();
   }

   private List<String> getAllPortletsRegisteredForEvent(Event event,
         DriverConfiguration driverConfig,
         ServletContext containerServletContext, PortalURL portalURL) {
      
      Set<String> resultSet = new HashSet<String>();
      List<String> resultList = new ArrayList<String>();
      QName eventName = event.getQName();
      // Collection<PortletWindowConfig> portlets = getAllPortlets(driverConfig);
      
      // limit event processing to those portlets on the page
      Collection<PortletWindowConfig> portlets = new ArrayList<PortletWindowConfig>();
      for (String pid : portalURL.getPortletIds()) {
         portlets.add(PortletWindowConfig.fromId(pid));
      }

      for (PortletWindowConfig portlet : portlets) {
         String contextPath = portlet.getContextPath();
         String applicationName = contextPath;
         PortletApplicationDefinition portletAppDD = null;
         try {
            portletAppDD = portletRegistry
                  .getPortletApplication(applicationName);
            List<? extends PortletDefinition> portletDDs = portletAppDD
                  .getPortlets();
            List<QName> aliases = getAllAliases(eventName, portletAppDD);
            for (PortletDefinition portletDD : portletDDs) {
               List<? extends EventDefinitionReference> processingEvents = portletDD
                     .getSupportedProcessingEvents();
               if (isEventSupported(processingEvents, eventName,
                     portletAppDD.getDefaultNamespace())) {
                  if (portletDD.getPortletName().equals(
                        portlet.getPortletName())) {
                     resultSet.add(portlet.getId());
                  }
               } else {

                  if (processingEvents != null) {
                     for (EventDefinitionReference ref : processingEvents) {
                        QName name = ref.getQualifiedName();
                        if (name == null) {
                           continue;
                        }
                        // add also grouped portlets, that ends with "."
                        if (name.toString().endsWith(".")
                              && eventName.toString().startsWith(
                                    name.toString())
                              && portletDD.getPortletName().equals(
                                    portlet.getPortletName())) {
                           resultSet.add(portlet.getId());
                        }
                        // also look for alias names:
                        if (aliases != null) {
                           for (QName alias : aliases) {
                              if (alias.toString().equals(name.toString())
                                    && portletDD.getPortletName().equals(
                                          portlet.getPortletName())) {
                                 resultSet.add(portlet.getId());
                              }
                           }
                        }
                        // also look for default namespaced events
                        if (name.getNamespaceURI() == null
                              || name.getNamespaceURI().equals("")) {
                           String defaultNamespace = portletAppDD
                                 .getDefaultNamespace();
                           QName qname = new QName(defaultNamespace,
                                 name.getLocalPart());
                           if (eventName.toString().equals(qname.toString())
                                 && portletDD.getPortletName().equals(
                                       portlet.getPortletName())) {
                              resultSet.add(portlet.getId());
                           }
                        }
                     }
                  }
               }
            }
         } catch (PortletContainerException e) {
            LOG.warn(e.getMessage(), e);
         }
      }

      // make list
      for (String name : resultSet) {
         resultList.add(name);
      }
      return resultList;
   }

   private boolean isEventSupported(
         List<? extends EventDefinitionReference> supportedEvents,
         QName eventName, String defaultNamespace) {
      if (supportedEvents != null) {
         for (EventDefinitionReference ref : supportedEvents) {
            QName refQName = ref.getQualifiedName();
            if (refQName != null && refQName.equals(eventName)) {
               return true;
            }
         }
      }
      return false;
   }

   private List<QName> getAllAliases(QName eventName,
         PortletApplicationDefinition portletAppDD) {
      if (portletAppDD.getEventDefinitions() != null) {

         for (EventDefinition def : portletAppDD.getEventDefinitions()) {
            QName defQName = def.getQName();
            if (defQName != null && defQName.equals(eventName)) {
               return def.getAliases();
            }
         }
      }
      return null;
   }


   /**
	 * 
	 */
   @SuppressWarnings("unused")
   private Collection<PortletWindowConfig> getAllPortlets(
         DriverConfiguration driverConfig) {
      Collection<PortletWindowConfig> portlets = new ArrayList<PortletWindowConfig>();
      Collection<PageConfig> pages = driverConfig.getPages();
      if (pages != null) {
         Iterator<PageConfig> iPages = pages.iterator();
         while (iPages.hasNext()) {
            PageConfig pageConfig = (PageConfig) iPages.next();
            Collection<String> portletIDs = pageConfig.getPortletIds();
            if (portletIDs != null) {
               Iterator<String> iPortletIDs = portletIDs.iterator();
               while (iPortletIDs.hasNext()) {
                  portlets.add(PortletWindowConfig.fromId(iPortletIDs.next()
                        .toString()));
               }
            }
         }
      }
      return portlets;
   }
}
