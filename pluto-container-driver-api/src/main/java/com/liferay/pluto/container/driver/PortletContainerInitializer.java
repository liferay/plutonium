/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.driver;

import java.io.InputStream;
import java.util.Set;

import jakarta.portlet.annotations.PortletApplication;
import jakarta.portlet.annotations.PortletConfiguration;
import jakarta.portlet.annotations.PortletConfigurations;
import jakarta.portlet.annotations.PortletLifecycleFilter;
import jakarta.portlet.annotations.PortletListener;
import jakarta.portlet.annotations.PortletPreferencesValidator;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.HandlesTypes;

import com.liferay.pluto.container.PortletInvokerService;
import com.liferay.pluto.container.om.portlet.PortletDefinition;
import com.liferay.pluto.container.om.portlet.impl.ConfigurationHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet container initializer that reads the configuration and adds the
 * portlet servlets for any portlets contained in the application.
 * 
 * @author Scott Nicklous
 * 
 */
@HandlesTypes({PortletApplication.class, PortletConfiguration.class,
               PortletLifecycleFilter.class, PortletConfigurations.class,
               PortletListener.class, PortletPreferencesValidator.class})
public class PortletContainerInitializer implements ServletContainerInitializer {

   private static final String WEB_XML     = "/WEB-INF/web.xml";
   private static final String PORTLET_XML = "/WEB-INF/portlet.xml";

   /** Logger. */
   private static final Logger LOG         = LoggerFactory
                                                 .getLogger(PortletContainerInitializer.class);
   private static boolean      isDebug     = LOG.isDebugEnabled();

   /*
    * (non-Javadoc)
    * 
    * @see jakarta.servlet.ServletContainerInitializer#onStartup(java.util.Set,
    * jakarta.servlet.ServletContext)
    */
   @Override
   public void onStartup(Set<Class<?>> classes, ServletContext ctx)
         throws ServletException {

      try {

         
         // scan for method annotations
         
         ConfigurationHolder holder = new ConfigurationHolder();
         holder.scanMethodAnnotations(ctx);

         // Read the annotated configuration

         if (classes != null) {
            holder.processConfigAnnotations(classes);
         }

         // set up for reading the XML files

         InputStream win = ctx.getResourceAsStream(WEB_XML);
         InputStream pin = ctx.getResourceAsStream(PORTLET_XML);

         if (isDebug) {
            StringBuilder txt = new StringBuilder(128);
            txt.append("$$$ ServletContainerInitializer. ctx path: ").append(
                  ctx.getContextPath());
            txt.append(", servlet ctx name: ").append(ctx.getServletContextName());
            txt.append(", # portlet annotations: ").append(
                  (classes != null) ? classes.size() : "null");
            txt.append(", found web.xml: ").append(win != null);
            txt.append(", found portlet.xml: ").append(pin != null);
            LOG.debug(txt.toString());
         }

         // Now read the XML configuration and validate the resulting explicit config

         if (pin != null) {
            // parse the portlet deployment descriptor
            holder.processPortletDD(pin);
         }

         if (win != null) {
            // parse the web app deployment descriptor
            holder.processWebDD(win);
         }

         holder.validate();
         
         // Reconcile the bean config with the explicitly declared portlet configuration.
         
         holder.reconcileBeanConfig();
         
         // If portlets have been found in this servlet context, launch the portlet servlets

         if (holder.getPad().getPortlets().size() > 0) {

            ctx.setAttribute(ConfigurationHolder.ATTRIB_NAME, holder);

            // dynamically deploy the portlet servlets
            for (PortletDefinition pd : holder.getPad().getPortlets()) {
               String pn = pd.getPortletName();
               String mapping = PortletInvokerService.URIPREFIX + pn;
               String servletName = pn + "_PS3";

               if (isDebug) {
                  StringBuilder txt = new StringBuilder();
                  txt.append("Adding PortletServlet3. Portlet name: ");
                  txt.append(pn);
                  txt.append(", servlet name: ").append(servletName);
                  txt.append(", mapping: ").append(mapping);
                  LOG.debug(txt.toString());
               }

               ServletRegistration.Dynamic sr = ctx.addServlet(servletName, PortletServlet3.class);
               sr.addMapping(mapping);
               sr.setInitParameter(PortletServlet3.PORTLET_NAME, pn);
               sr.setAsyncSupported(true);
               if (pd.isMultipartSupported()) {
                  MultipartConfigElement mce = new MultipartConfigElement(pd.getLocation(), 
                        pd.getMaxFileSize(), pd.getMaxRequestSize(), pd.getFileSizeThreshold());
                  sr.setMultipartConfig(mce);
               }
               sr.setLoadOnStartup(100);

            }
            
            // Add the cross-context filter & terminal listener
            
//             FilterRegistration.Dynamic fr = ctx.addFilter("WeldCrossContextFilter", "org.jboss.weld.servlet.WeldCrossContextFilter");
//             EnumSet<DispatcherType> dt = EnumSet.noneOf(DispatcherType.class);
//             dt.add(DispatcherType.FORWARD);
//             dt.add(DispatcherType.INCLUDE);
//             dt.add(DispatcherType.ERROR);
//             fr.addMappingForUrlPatterns(dt, false, "/*");
//             
//             ctx.addListener("org.jboss.weld.servlet.WeldTerminalListener");

            LOG.debug("Completed deployment of servlets & filters for context: " + ctx.getContextPath());

         } else {
            LOG.debug("No portlet definitions for context: " + ctx.getServletContextName());
         }

      } catch (Exception e) {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Exception processing portlet application configuration");
         txt.append(", Servlet ctx name: ").append(
               ctx.getServletContextName());
         txt.append(", Exception: ").append(e.toString());
         LOG.info(txt.toString());
      }

   }

}
