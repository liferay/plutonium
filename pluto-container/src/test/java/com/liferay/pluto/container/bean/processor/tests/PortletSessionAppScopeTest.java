/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.bean.processor.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.portlet.annotations.PortletSessionScoped;

import com.liferay.pluto.container.bean.processor.AnnotatedConfigBean;
import com.liferay.pluto.container.bean.processor.AnnotatedMethodStore;
import com.liferay.pluto.container.bean.processor.ConfigSummary;
import com.liferay.pluto.container.bean.processor.PortletCDIExtension;
import com.liferay.pluto.container.bean.processor.PortletSessionScopedAnnotatedType;
import com.liferay.pluto.container.bean.processor.PortletSessionScopedConfig;
import com.liferay.pluto.container.bean.processor.PortletStateScopedConfig;
import com.liferay.pluto.container.bean.processor.fixtures.PortletStateScopedClass;
import com.liferay.pluto.container.bean.processor.fixtures.SessionScopedApp2;
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
public class PortletSessionAppScopeTest {
   
   @Inject
   AnnotatedConfigBean acb;
   
   private AnnotatedMethodStore ams = null;
   private ConfigSummary summary = null;
   private PortletStateScopedConfig psconfig = null;
   private PortletSessionScopedConfig sessConfig = null;
   
   private PortletSessionScopedAnnotatedType appScoped = null;
   
//    @Inject
//    private SessionScopedApp1 abean;
   
   @Before
   public void setUp() {
      ams = acb.getMethodStore();
      summary = acb.getSummary();
      psconfig = acb.getStateScopedConfig();
      sessConfig = acb.getSessionScopedConfig();
      
      assertNotNull(acb);
      assertNotNull(ams);
      assertNotNull(summary);
      assertNotNull(psconfig);
      assertNotNull(sessConfig);
      
      appScoped = summary.getAppScopedTypes().get(0);
   }
   
   @Test
   public void annotatedClassPresent1() {
      List<PortletSessionScopedAnnotatedType> types = summary.getAppScopedTypes();
      assertNotNull(types);
      assertEquals(1, types.size());
   }
   
   @Test
   public void checkAppScoped() throws Exception {
      assertNotNull(appScoped);
   }
   
   @Test
   public void checkNoSessionScoped() throws Exception {
      Set<Annotation> annos = appScoped.getAnnotations();
      assertNotEquals(0, annos.size());
      boolean ok = true;
      for (Annotation a : annos) {
         if (a.annotationType().equals(SessionScoped.class)) {
            ok = false;
            break;
         }
      }
      assertTrue(ok);
   }
   
   @Test
   public void checkPortletSessionScoped() throws Exception {
      Set<Annotation> annos = appScoped.getAnnotations();
      assertEquals(1, annos.size());
      boolean ok = false;
      for (Annotation a : annos) {
         if (a.annotationType().equals(PortletSessionScoped.class)) {
            ok = true;
            break;
         }
      }
      assertTrue(ok);
   }
   
   @Test
   public void doesNotContainSessionScoped() throws Exception {
      assertFalse(appScoped.isAnnotationPresent(SessionScoped.class));
   }
   
   @Test
   public void containsPortletSessionScoped() throws Exception {
      assertTrue(appScoped.isAnnotationPresent(PortletSessionScoped.class));
   }
   
   @Test
   public void getAnnotation() throws Exception {
      assertNotNull(appScoped.getAnnotation(PortletSessionScoped.class));
   }
   
   @Test
   public void getAnnotationBad() throws Exception {
      assertNull(appScoped.getAnnotation(SessionScoped.class));
   }
   
   @Test
   public void checkJavaClass() throws Exception {
      Class<?> cls = appScoped.getJavaClass();
      assertEquals(SessionScopedApp2.class, cls);
   }
   
   @Test
   public void checkBaseType() throws Exception {
      Type type = appScoped.getBaseType();
      assertEquals(SessionScopedApp2.class, type);
   }
   
//    @Test
//    @InSessionScope
//    public void checkInjection() throws Exception {
//       assertNotNull(abean);
//       assertNotNull(abean.sayHi());
//       assertEquals("Hi!", abean.sayHi());
//    }
   
}
