/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.AnnotatedConstructor;
import jakarta.enterprise.inject.spi.AnnotatedField;
import jakarta.enterprise.inject.spi.AnnotatedMethod;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.portlet.annotations.PortletRequestScoped;

/**
 * A wrapper for a RequestScoped annotated type 
 * that replaces the <code>{@literal @}RequestScoped</code> annotation with
 * <code>{@literal @}PortletRequestScoped</code> in order to assure that the bean
 * is scoped to the portlet request rather than to the underlying servlet request.
 * 
 * @author Scott
 *
 */
public class PortletRequestScopedAnnotatedType implements AnnotatedType<RequestScoped> {

   // to obtain annotation instances
   @RequestScoped
   @PortletRequestScoped
   private class Dummy {}  
   
   // the wrapped type.
   private final AnnotatedType<RequestScoped> aType;
   
   // The set of annotations
   private final Set<Annotation> annos = new HashSet<Annotation>();
   
   /**
    * Construct the wrapper. 
    */
   public PortletRequestScopedAnnotatedType(AnnotatedType<RequestScoped> type) {
      aType = type;
      annos.addAll(type.getAnnotations());
      annos.remove(Dummy.class.getAnnotation(RequestScoped.class));
      annos.add(Dummy.class.getAnnotation(PortletRequestScoped.class));
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.Annotated#getAnnotation(java.lang.Class)
    */
   @SuppressWarnings({"unchecked"})
   @Override
   public <U extends Annotation> U getAnnotation(Class<U> type) {
      for (Annotation a : annos) {
         if (type.equals(a.annotationType())) {
            return (U) a;
         }
      }
      return null;
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.Annotated#getAnnotations()
    */
   @Override
   public Set<Annotation> getAnnotations() {
      return new HashSet<Annotation>(annos);
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.Annotated#getBaseType()
    */
   @Override
   public Type getBaseType() {
      return aType.getBaseType();
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.Annotated#getTypeClosure()
    */
   @Override
   public Set<Type> getTypeClosure() {
      return aType.getTypeClosure();
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.Annotated#isAnnotationPresent(java.lang.Class)
    */
   @Override
   public boolean isAnnotationPresent(Class<? extends Annotation> type) {
      for (Annotation a : annos) {
         if (type.equals(a.annotationType())) {
            return true;
         }
      }
      return false;
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.AnnotatedType#getConstructors()
    */
   @Override
   public Set<AnnotatedConstructor<RequestScoped>> getConstructors() {
      return aType.getConstructors();
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.AnnotatedType#getFields()
    */
   @Override
   public Set<AnnotatedField<? super RequestScoped>> getFields() {
      return aType.getFields();
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.AnnotatedType#getJavaClass()
    */
   @Override
   public Class<RequestScoped> getJavaClass() {
      return aType.getJavaClass();
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.inject.spi.AnnotatedType#getMethods()
    */
   @Override
   public Set<AnnotatedMethod<? super RequestScoped>> getMethods() {
      return aType.getMethods();
   }

}
