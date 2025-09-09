/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor;

import static com.liferay.pluto.container.bean.processor.MethodDescription.METH_ACT;
import static com.liferay.pluto.container.bean.processor.MethodDescription.METH_EVT;
import static com.liferay.pluto.container.bean.processor.MethodDescription.METH_HDR;
import static com.liferay.pluto.container.bean.processor.MethodDescription.METH_REN;
import static com.liferay.pluto.container.bean.processor.MethodDescription.METH_RES;
import static com.liferay.pluto.container.bean.processor.MethodDescription.METH_DES;
import static com.liferay.pluto.container.bean.processor.MethodDescription.METH_INI;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.mvc.RedirectScoped;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletSession;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.annotations.ActionMethod;
import jakarta.portlet.annotations.DestroyMethod;
import jakarta.portlet.annotations.EventMethod;
import jakarta.portlet.annotations.HeaderMethod;
import jakarta.portlet.annotations.InitMethod;
import jakarta.portlet.annotations.PortletSerializable;
import jakarta.portlet.annotations.PortletSessionScoped;
import jakarta.portlet.annotations.RenderMethod;
import jakarta.portlet.annotations.RenderStateScoped;
import jakarta.portlet.annotations.ServeResourceMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Scott
 *
 */
public class PortletAnnotationRecognizer extends AnnotationRecognizer {
   private static final Logger LOG = LoggerFactory.getLogger(PortletAnnotationRecognizer.class);
   
   
   private final static Set<Class<? extends Annotation>> classAnnotations = 
         new HashSet<Class<? extends Annotation>>();
   
   static {
      classAnnotations.add(RedirectScoped.class);
      classAnnotations.add(RenderStateScoped.class);
      classAnnotations.add(PortletSessionScoped.class);
      classAnnotations.add(SessionScoped.class);
      classAnnotations.add(RequestScoped.class);
   }
   
   // Maps the annotation class to a list of method descriptions. A given annotation
   // may support multiple method signatures, each of which has its own method description.
   private final static Map<Class<? extends Annotation>, List<MethodDescription>> descriptions = 
         new HashMap<Class<? extends Annotation>, List<MethodDescription>>();
   
   static {
      
      ArrayList<MethodDescription> list;
      MethodDescription md;

      // Add method definitions for init methods
      
      list = new ArrayList<>();
      list.add(METH_INI);
      descriptions.put(InitMethod.class, list);
      
      // Add method definitions for destroy methods
      
      list = new ArrayList<>();
      list.add(METH_DES);
      descriptions.put(DestroyMethod.class, list);
      
      // Add method definitions for action methods
      
      list = new ArrayList<>();
      list.add(METH_ACT);

      md = new MethodDescription(String.class,
          new Class<?>[] {ActionRequest.class, ActionResponse.class},
          new Class<?>[] {PortletException.class, java.io.IOException.class},
          MethodType.ACTION);
      md.setMvc(true);
      md.setVariant(SignatureVariant.STRING_ACTIONREQ_ACTIONRESP);
      list.add(md);

      descriptions.put(ActionMethod.class, list);
      
      // Add method definitions for event methods
      
      list = new ArrayList<>();
      list.add(METH_EVT);
      descriptions.put(EventMethod.class, list);
      
      // Add method definitions for serve resource
      
      list = new ArrayList<>();
      
      list.add(METH_RES);

      md = new MethodDescription(String.class,
          new Class<?>[] {ResourceRequest.class, ResourceResponse.class},
          new Class<?>[] {PortletException.class, java.io.IOException.class},
          MethodType.RESOURCE);
      md.setAllowMultiple(true);
      md.setMvc(true);
      md.setVariant(SignatureVariant.STRING_RESOURCEREQ_RESOURCERESP);
      list.add(md);

      md = new MethodDescription(String.class, 
            new Class<?>[] {},  
            new Class<?>[] {PortletException.class, java.io.IOException.class}, 
            MethodType.RESOURCE);
      md.setAllowMultiple(true);
      md.setVariant(SignatureVariant.STRING_VOID);
      list.add(md);
      
      md = new MethodDescription(void.class, 
            new Class<?>[] {},  
            new Class<?>[] {PortletException.class, java.io.IOException.class}, 
            MethodType.RESOURCE);
      md.setAllowMultiple(true);
      md.setVariant(SignatureVariant.VOID_VOID);
      list.add(md);
      
      descriptions.put(ServeResourceMethod.class, list);
      
      // Add method definitions for render
      
      list = new ArrayList<>();
      
      list.add(METH_REN);

      md = new MethodDescription(String.class,
          new Class<?>[] {},
          new Class<?>[] {PortletException.class, java.io.IOException.class},
          MethodType.RENDER);
      md.setAllowMultiple(true);
      md.setVariant(SignatureVariant.STRING_VOID);
      list.add(md);

      md = new MethodDescription(String.class, 
            new Class<?>[] {RenderRequest.class, RenderResponse.class},
            new Class<?>[] {PortletException.class, java.io.IOException.class}, 
            MethodType.RENDER);
      md.setAllowMultiple(true);
      md.setMvc(true);
      md.setVariant(SignatureVariant.STRING_RENDERREQ_RENDERRESP);
      list.add(md);
      
      md = new MethodDescription(void.class, 
            new Class<?>[] {},  
            new Class<?>[] {PortletException.class, java.io.IOException.class}, 
            MethodType.RENDER);
      md.setAllowMultiple(true);
      md.setVariant(SignatureVariant.VOID_VOID);
      list.add(md);

      descriptions.put(RenderMethod.class, list);
      
      // Add method definitions for header methods
      
      list = new ArrayList<>();
      list.add(METH_HDR);
      
      md = new MethodDescription(String.class, 
            new Class<?>[] {},  
            new Class<?>[] {PortletException.class, java.io.IOException.class}, 
            MethodType.HEADER);
      md.setAllowMultiple(true);
      md.setVariant(SignatureVariant.STRING_VOID);
      list.add(md);
      
      md = new MethodDescription(void.class, 
            new Class<?>[] {},  
            new Class<?>[] {PortletException.class, java.io.IOException.class}, 
            MethodType.HEADER);
      md.setAllowMultiple(true);
      md.setVariant(SignatureVariant.VOID_VOID);
      list.add(md);

      descriptions.put(HeaderMethod.class, list);
      
   }

   private final AnnotatedMethodStore ams;
   private final boolean mvc;
   private final RedirectScopedConfig redirectScopedConfig = new RedirectScopedConfig();
   private final PortletStateScopedConfig stateScopedConfig = new PortletStateScopedConfig();
   private final PortletSessionScopedConfig sessionScopedConfig = new PortletSessionScopedConfig();

   /**
    * @param pms
    * @param summary
    */
   public PortletAnnotationRecognizer(AnnotatedMethodStore pms, ConfigSummary summary) {
      this(pms, summary, true);
   }

   /**
    * @param pms
    * @param summary
    */
   public PortletAnnotationRecognizer(AnnotatedMethodStore pms, ConfigSummary summary, boolean mvc) {
      super(classAnnotations, filterDescriptions(descriptions, mvc), summary);
      this.ams = pms;
      this.mvc = mvc;
      LOG.trace("Created the PortletAnnotationRecognizer.");
   }

   private static Map<Class<? extends Annotation>,List<MethodDescription>> filterDescriptions(Map<Class<? extends Annotation>, List<MethodDescription>> descriptions, boolean mvc) {
      if (mvc) {
         return descriptions;
      }

      Map<Class<? extends Annotation>, List<MethodDescription>> filteredDescriptions = new HashMap<>();

      for (Map.Entry<Class<? extends Annotation>, List<MethodDescription>> entry : descriptions.entrySet()) {
      	 List<MethodDescription> filteredMethodDescriptions = new ArrayList<>();
         for (MethodDescription methodDescription: entry.getValue()) {
            if (!methodDescription.isMvc()) {
               filteredMethodDescriptions.add(methodDescription);
            }
         }
         filteredDescriptions.put(entry.getKey(), filteredMethodDescriptions);
      }

      return filteredDescriptions;
   }

   /**
    * Extracts data from the class annotation and stores it for later use.
    * <p>
    * When reading the PortletConfiguration annotation, data from a later 
    * annotation will override data from a preceding annotation for a given portlet name.
    * <p>
    * 
    */
   @SuppressWarnings("unchecked")
   @Override
   protected AnnotatedType<?> handleClassAnnotation(Annotation anno, AnnotatedType<?> aType) throws InvalidAnnotationException {
      String typeName = aType.getJavaClass().getCanonicalName();
      Class<?> theClass = aType.getJavaClass();

      if (anno instanceof RedirectScoped) {

         // Verify the redirect scoped class, store the annotation &
         // bean type with the corresponding bean holder.

         if (!Serializable.class.isAssignableFrom(theClass)) {
            StringBuilder txt = new StringBuilder(128);
            txt.append("Annotation problem: An @RedirectScoped bean must implement java.io.Serializable.");
            txt.append("Annotation: ").append(anno.annotationType().getSimpleName());
            txt.append(", Class: ").append(typeName);
            LOG.debug(txt.toString());
            summary.addStateBeanErrorString(theClass, txt.toString());
         } else {
            redirectScopedConfig.addAnnotation(theClass, (RedirectScoped) anno);
         }
	  }
      else if (anno instanceof RenderStateScoped) {
         
         // Verify the render state scoped class, store the annotation &
         // bean type with the corresponding bean holder.
         
         if (!PortletSerializable.class.isAssignableFrom(theClass)) {
            StringBuilder txt = new StringBuilder(128);
            txt.append("Annotation problem: An @RenderStateScoped bean must implement PortletSerializable. ");
            txt.append("Annotation: ").append(anno.annotationType().getSimpleName());
            txt.append(", Class: ").append(typeName);
            LOG.debug(txt.toString());
            summary.addStateBeanErrorString(theClass, txt.toString());
         } else {
            stateScopedConfig.addAnnotation(theClass, (RenderStateScoped) anno);
         }
         
      }  else if (anno instanceof PortletSessionScoped) {
         
         // Verify the portlet session scoped class, store the annotation &
         // bean type with the corresponding bean holder.
         
         PortletSessionScoped pss = (PortletSessionScoped) anno;
         if ((pss.value() != PortletSession.APPLICATION_SCOPE) && (pss.value() != PortletSession.PORTLET_SCOPE)) {
            StringBuilder txt = new StringBuilder(128);
            txt.append("Annotation problem: A @PortletSessionScoped bean scope must be APPLICATION_SCOPE or PORTLET_SCOPE. ");
            txt.append("Annotation: ").append(anno.annotationType().getSimpleName());
            txt.append(", Scope: ").append(pss.value());
            txt.append(", Class: ").append(typeName);
            LOG.debug(txt.toString());
            summary.addSessionBeanErrorString(theClass, txt.toString());
         } else {
            sessionScopedConfig.addAnnotation(theClass, pss);
         }

      }  else if (anno instanceof RequestScoped) {

         // Wrap the request scoped bean to make it a portlet request scoped bean, add it to 
         // the summary, and return it so it gets processed by CDI

         PortletRequestScopedAnnotatedType at = new PortletRequestScopedAnnotatedType((AnnotatedType<RequestScoped>)aType);
         summary.addReqScopedType(at);
         return at;
         
      }  else if (anno instanceof SessionScoped) {

         // Wrap the session scoped bean to make it a portlet session scoped bean with application
         // scope, add it to the summary, and return it so it gets processed by CDI

         PortletSessionScopedAnnotatedType at = new PortletSessionScopedAnnotatedType((AnnotatedType<SessionScoped>)aType);
         PortletSessionScoped pss = at.getAnnotation(PortletSessionScoped.class);
         sessionScopedConfig.addAnnotation(theClass, pss);
         summary.addAppScopedType(at);
         return at;

      }  else {
         
         // Any other annotation that lands here indicates a programming error
         
         StringBuilder txt = new StringBuilder(128);
         txt.append("Unrecognized class annotation: ")
            .append(anno.annotationType().getSimpleName());
         txt.append(", Class: ").append(typeName);
         LOG.warn(txt.toString());
      }
      return null;
   }

   /**
    * Extracts and stores relevant information from the specified annotation, bean
    * class, and Method.
    *  
    * @param anno
    * @param beanClass
    * @param meth
    */
   @Override
   protected void handleMethod(Annotation anno, Class<?> beanClass, Method meth, MethodDescription desc) {
      ArrayList<String> portletNames = new ArrayList<String>();
      String dispatchId = "";
      
      MethodType type = desc.getType();
      if (anno instanceof RenderMethod) {
         RenderMethod tcc = (RenderMethod) anno;
         portletNames.addAll(Arrays.asList(tcc.portletNames()));
         dispatchId = tcc.portletMode().toUpperCase();
      } else if (anno instanceof HeaderMethod) {
         HeaderMethod tcc = (HeaderMethod) anno;
         portletNames.addAll(Arrays.asList(tcc.portletNames()));
         dispatchId = tcc.portletMode().toUpperCase();
      } else if (anno instanceof ActionMethod) {
         ActionMethod tcc = (ActionMethod) anno;
         portletNames.add(tcc.portletName());
         dispatchId = tcc.actionName();
      } else if (anno instanceof EventMethod) {
         EventMethod tcc = (EventMethod) anno;
         portletNames.add(tcc.portletName());
      } else if (anno instanceof ServeResourceMethod) {
         ServeResourceMethod tcc = (ServeResourceMethod) anno;
         portletNames.addAll(Arrays.asList(tcc.portletNames()));
         dispatchId = tcc.resourceID();
      } else if (anno instanceof DestroyMethod) {
         DestroyMethod tcc = (DestroyMethod) anno;
         portletNames.add(tcc.value());
      } else if (anno instanceof InitMethod) {
         InitMethod tcc = (InitMethod) anno;
         portletNames.add(tcc.value());
      } else {
         StringBuilder txt = new StringBuilder(128);
         txt.append("Unrecognized method annotation: ")
            .append(anno.annotationType().getSimpleName());
         txt.append(", Method: ").append(meth.getName());
         txt.append(", Class: ").append(beanClass.getCanonicalName());
         LOG.debug(txt.toString());
         
         // Store the error for each portlet name in array 
         for (String n : getDisplayNames(anno)) {
            summary.addErrorString(n, txt.toString());
         }
         return;
      }
      
      
      // some classAnnotations allow arrays of portlet names. To simplify method retrieval, 
      // add to the method store with each of the specified portlet names.
      // The proxied method is present once, but it is referred to by multiple 
      // method identifier aliases.
      
      AnnotatedMethod pm = new AnnotatedMethod(anno, beanClass, meth, desc);
      for (String portletName : portletNames) {

         // The portlet name may not be empty. Note that the annotation itself
         // will prevent the field from being null.

         if (portletName.length() == 0) {
            StringBuilder txt = new StringBuilder(128);
            txt.append("Bad portlet name. Name may not be of length 0.");
            txt.append(" Annotation: ").append(anno.annotationType().getSimpleName());
            txt.append(", Method: ").append(meth.getName());
            txt.append(", Class: ").append(beanClass.getCanonicalName());
            LOG.debug(txt.toString());
            summary.addErrorString(portletName, txt.toString());
            return;
         }

         // Asterisk is ignored if not first and only array element.

         if (portletName.equals("*") && portletNames.size() > 1) {
            StringBuilder txt = new StringBuilder(128);
            txt.append("Wildcard character '*' must be first and only array element. Will be ignored.");
            txt.append(" Annotation: ").append(anno.annotationType().getSimpleName());
            txt.append(", Method: ").append(meth.getName());
            txt.append(", Class: ").append(beanClass.getCanonicalName());
            LOG.debug(txt.toString());
            summary.addErrorString(portletName, txt.toString());
            continue;
         }

         // we've collected the required info, so store the method.

         MethodIdentifier mi = new MethodIdentifier(portletName, dispatchId, type);
         ams.addMethod(mi, pm);
      }
   }

   
   /**
    * To be called by the CDI extension afterDeploymentValidation method to
    * verify that the stored methods are consistent and to create the bean references.
    *  
    * @param bm      BeanManager needed to activate the beans.
    * @throws InvalidAnnotationException  If the deployment is inconsistent or if the
    *                                     beans cannot be instantiated.
    */
   @Override
   protected void activateCustomScopes(BeanManager bm) {
      
      // Activate the custom scoped beans
      redirectScopedConfig.activate(bm);
      stateScopedConfig.activate(bm);
      sessionScopedConfig.activate(bm);
   }

   /**
    * @return the stateScopedConfig
    */
   public RedirectScopedConfig getRedirectScopedConfig() {
      return redirectScopedConfig;
   }

   /**
    * @return the stateScopedConfig
    */
   public PortletStateScopedConfig getStateScopedConfig() {
      return stateScopedConfig;
   }

   /**
    * @return the stateScopedConfig
    */
   public PortletSessionScopedConfig getSessionScopedConfig() {
      return sessionScopedConfig;
   }

}
