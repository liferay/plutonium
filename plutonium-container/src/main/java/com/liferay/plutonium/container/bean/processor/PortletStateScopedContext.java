/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor;

import java.lang.annotation.Annotation;

import jakarta.enterprise.context.ContextNotActiveException;
import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.portlet.annotations.RenderStateScoped;

/**
 * This is the Context implementation for the RenderStateScoped custom CDI scope.
 * 
 * @author nick
 *
 */
public class PortletStateScopedContext implements Context {

   public PortletStateScopedContext() {
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.context.spi.Context#get(jakarta.enterprise.context.spi.Contextual)
    */
   @Override
   public <T> T get(Contextual<T> bean) {
      PortletStateScopedBeanHolder holder = PortletStateScopedBeanHolder.getBeanHolder();
      if (holder == null) {
         throw new ContextNotActiveException("The render state context is not active.");
      }
      return holder.getBean(bean);
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.context.spi.Context#get(jakarta.enterprise.context.spi.Contextual, jakarta.enterprise.context.spi.CreationalContext)
    */
   @Override
   public <T> T get(Contextual<T> bean, CreationalContext<T> crco) {
      PortletStateScopedBeanHolder holder = PortletStateScopedBeanHolder.getBeanHolder();
      
      if (holder == null) {
         throw new ContextNotActiveException("The render state context is not active.");
      }
      
      // The bean hoder will return an existing bean instance or create a new one
      // if no existing instance is available.
      T inst = holder.getBean(bean, crco);      
      return inst;
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.context.spi.Context#getScope()
    */
   @Override
   public Class<? extends Annotation> getScope() {
      return RenderStateScoped.class;
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.context.spi.Context#isActive()
    */
   @Override
   public boolean isActive() {
      PortletStateScopedBeanHolder holder = PortletStateScopedBeanHolder.getBeanHolder();
      return (holder != null);
   }

}
