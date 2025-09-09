/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.pluto.driver.services.container;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import jakarta.portlet.Event;
import jakarta.portlet.PortletException;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.pluto.container.PortletContainer;
import com.liferay.pluto.container.PortletContainerException;
import com.liferay.pluto.container.PortletWindow;
import com.liferay.pluto.container.driver.PortletContextService;
import com.liferay.pluto.container.om.portlet.EventDefinition;
import com.liferay.pluto.container.om.portlet.PortletApplicationDefinition;

public class PortletWindowThread extends Thread {
	
	/** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(PortletWindowThread.class);
    
    private PortletContainer container;
    
	private PortletWindow portletWindow;
	
	private HttpServletRequest request;
    private HttpServletResponse response;
	
	/** PortletRegistryService used to obtain PortletApplicationConfig objects */
	private PortletContextService portletContextService;
	
	private List<Event> events = new ArrayList<Event>();

	public PortletWindowThread(ThreadGroup group, String name,
	                           PortletContainer container, PortletWindow window, 
	                           HttpServletRequest request, HttpServletResponse response, 
	                           PortletContextService portletContextService)
	{
		super(group, name);
        this.request = request;
        this.response = response;
		this.portletWindow = window;
		this.container = container;
		this.portletContextService = portletContextService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		super.run();
		while (events.size() > 0) {
			try {
//				synchronized (this) {
					Event event = events.remove(0);
			        Object value = event.getValue();
			        
			        XMLStreamReader xml = null;
					try {
						if (value instanceof String) {
							String in = (String) value; 
							xml = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(in));
						}			
					}  
					catch (XMLStreamException e1) {
						throw new IllegalStateException(e1);
					} catch (FactoryConfigurationError e1) {
						throw new IllegalStateException(e1);
					}
			        
			        if (xml != null) {
			        	//XMLStreamReader xml = (XMLStreamReader) event.getValue();
			        	
			        		//provider.getEventDefinition(event.getQName());
			        	try {
			        		// now test if object is jaxb
			        		EventDefinition eventDefinitionDD = getEventDefintion(event.getQName()); 
			        		
			        		ClassLoader loader = portletContextService.getClassLoader(portletWindow.getPortletDefinition().getApplication().getName());
			        		Class<? extends Serializable> clazz = loader.loadClass(eventDefinitionDD.getValueType()).asSubclass(Serializable.class);

			        		JAXBContext jc = JAXBContext.newInstance(clazz);
			        		Unmarshaller unmarshaller  = jc.createUnmarshaller();

//			        		unmarshaller.setEventHandler(new jakarta.xml.bind.helpers.DefaultValidationEventHandler());

			        		JAXBElement<?> result = unmarshaller.unmarshal(xml,clazz);

			        		event =  new EventImpl(event.getQName(),(Serializable) result.getValue());
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
//				}
			} catch (PortletException e) {
				LOG.warn(e.getMessage(),e);
			} catch (IOException e) {
				LOG.warn(e.getMessage(),e);
			} catch (PortletContainerException e) {
				LOG.warn(e.getMessage(),e);
			}	
		}
	}

	public void addEvent(Event event) {
		this.events.add(event);	
	}

	private EventDefinition getEventDefintion(QName name) {
		PortletApplicationDefinition appDD = portletWindow.getPortletDefinition().getApplication();
		for (EventDefinition def : appDD.getEventDefinitions()){
		   if (def.getQName().equals(name)) {
		      return def;
		   }
		}
		throw new IllegalStateException();
	}
}
