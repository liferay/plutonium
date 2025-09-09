/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;

import com.liferay.plutonium.container.bean.processor.AnnotatedConfigBean;
import com.liferay.plutonium.container.bean.processor.AnnotatedMethodStore;
import com.liferay.plutonium.container.bean.processor.ConfigSummary;
import com.liferay.plutonium.container.bean.processor.PortletCDIExtension;
import com.liferay.plutonium.container.bean.processor.PortletStateScopedConfig;
import com.liferay.plutonium.container.bean.processor.fixtures.PortletStateScopedBadClass;
import com.liferay.plutonium.container.bean.processor.fixtures.PortletStateScopedClass;
import com.liferay.plutonium.container.bean.processor.fixtures.PortletStateScopedNoParamNameClass;
import io.github.cdiunit.AdditionalClasses;
import io.github.cdiunit.AdditionalPackages;
import io.github.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test class for RenderStateScoped beans
 * 
 * @author Scott Nicklous
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(PortletCDIExtension.class)
@AdditionalPackages(PortletStateScopedClass.class)
public class PortletStateScopedTest {
   
   @Inject
   AnnotatedConfigBean acb;
   
   @Inject
   BeanManager beanmgr;
   
   private AnnotatedMethodStore ams = null;
   private ConfigSummary summary = null;
   private PortletStateScopedConfig psconfig = null;
   
   @Before
   public void setUp() {
      ams = acb.getMethodStore();
      summary = acb.getSummary();
      psconfig = acb.getStateScopedConfig();
      psconfig.activate(beanmgr);
   }

   @Test
   public void injectBeanTest() {
      assertNotNull(acb);
      assertNotNull(ams);
      assertNotNull(summary);
      assertNotNull(psconfig);
   }
   
   @Test
   public void annotatedClassPresent1() {
      Set<Class<?>> classes = psconfig.getBeanClasses();
      assertNotNull(classes);
      assertEquals(2, classes.size());
   }

   @Test
   public void annotatedClassPresent2() {
      Set<Class<?>> classes = psconfig.getBeanClasses();
      assertNotNull(classes);
      assertTrue(classes.contains(PortletStateScopedClass.class));
   }

   @Test
   public void annotatedClassPresent3() {
      Set<Class<?>> classes = psconfig.getBeanClasses();
      assertNotNull(classes);
      assertTrue(classes.contains(PortletStateScopedNoParamNameClass.class));
   }

   @Test
   public void annotatedClassNotPresent() {
      Set<Class<?>> classes = psconfig.getBeanClasses();
      assertNotNull(classes);
      assertFalse(classes.contains(PortletStateScopedBadClass.class));
   }

   @Test
   public void paramName1() {
      String name = psconfig.getParamName(PortletStateScopedClass.class);
      assertNotNull(name);
      assertEquals("param1", name);
   }

   @Test
   public void paramName2() {
      String name = psconfig.getParamName(PortletStateScopedNoParamNameClass.class);
      assertNotNull(name);
      assertTrue(name.endsWith("1"));
   }
   
   @Test
   public void errorList() {
      List<Class<?>> list = summary.getStateBeansWithErrors();
      assertNotNull(list);
      assertEquals(1, list.size());
      assertTrue(list.contains(PortletStateScopedBadClass.class));
   }
   
   @Test
   public void errorString() {
      String msg = summary.getStateBeanErrorString(PortletStateScopedBadClass.class);
      assertNotNull(msg);
   }
}
