/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor;

import jakarta.enterprise.context.ContextNotActiveException;
import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.mvc.RedirectScoped;
import java.lang.annotation.Annotation;

/**
 * This is the Context implementation for the RedirectScoped custom CDI scope.
 * 
 * @author nick
 * @author Neil Griffin
 *
 */
public class RedirectScopedContext implements Context {

   /* (non-Javadoc)
    * @see jakarta.enterprise.context.spi.Context#get(jakarta.enterprise.context.spi.Contextual)
    */
   @Override
   public <T> T get(Contextual<T> bean) {
      RedirectScopedBeanHolder holder = RedirectScopedBeanHolder.getBeanHolder();
      if (holder == null) {
         throw new ContextNotActiveException("The redirect context is not active.");
      }
      return holder.getBean(bean);
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.context.spi.Context#get(jakarta.enterprise.context.spi.Contextual, jakarta.enterprise.context.spi.CreationalContext)
    */
   @Override
   public <T> T get(Contextual<T> bean, CreationalContext<T> crco) {
      RedirectScopedBeanHolder holder = RedirectScopedBeanHolder.getBeanHolder();
      
      if (holder == null) {
         throw new ContextNotActiveException("The redirect context is not active.");
      }
      
      T inst = holder.getBean(bean);
      if (inst == null) {
         inst = bean.create(crco);
         holder.putBeanInstance(bean, crco, inst);
      }

      return inst;
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.context.spi.Context#getScope()
    */
   @Override
   public Class<? extends Annotation> getScope() {
      return RedirectScoped.class;
   }

   /* (non-Javadoc)
    * @see jakarta.enterprise.context.spi.Context#isActive()
    */
   @Override
   public boolean isActive() {
      RedirectScopedBeanHolder holder = RedirectScopedBeanHolder.getBeanHolder();
      return (holder != null);
   }

}
