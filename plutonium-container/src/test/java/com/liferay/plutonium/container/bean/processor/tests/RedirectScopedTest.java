/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.tests;

import com.liferay.plutonium.container.bean.processor.AnnotatedConfigBean;
import com.liferay.plutonium.container.bean.processor.AnnotatedMethodStore;
import com.liferay.plutonium.container.bean.processor.ConfigSummary;
import com.liferay.plutonium.container.bean.processor.PortletCDIExtension;
import com.liferay.plutonium.container.bean.processor.RedirectScopedConfig;
import com.liferay.plutonium.container.bean.processor.fixtures.RedirectScopedClass;
import io.github.cdiunit.AdditionalClasses;
import io.github.cdiunit.AdditionalPackages;
import io.github.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for RedirectScoped beans
 * 
 * @author Scott Nicklous
 * @author Neil Griffin
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(PortletCDIExtension.class)
@AdditionalPackages(RedirectScopedClass.class)
public class RedirectScopedTest {
   
   @Inject
   AnnotatedConfigBean acb;
   
   @Inject
   BeanManager beanmgr;
   
   private AnnotatedMethodStore annotatedMethodStore;
   private ConfigSummary summary;
   private RedirectScopedConfig redirectScopedConfig;
   
   @Before
   public void setUp() {
      annotatedMethodStore = acb.getMethodStore();
      summary = acb.getSummary();
      redirectScopedConfig = acb.getRedirectScopedConfig();
      redirectScopedConfig.activate(beanmgr);
   }

   @Test
   public void injectBeanTest() {
      assertNotNull(acb);
      assertNotNull(annotatedMethodStore);
      assertNotNull(summary);
      assertNotNull(redirectScopedConfig);
   }
   
   @Test
   public void annotatedClassPresent1() {
      Set<Class<?>> classes = redirectScopedConfig.getBeanClasses();
      assertNotNull(classes);
      assertEquals(1, classes.size());
   }

   @Test
   public void annotatedClassPresent2() {
      Set<Class<?>> classes = redirectScopedConfig.getBeanClasses();
      assertNotNull(classes);
      assertTrue(classes.contains(RedirectScopedClass.class));
   }
}
