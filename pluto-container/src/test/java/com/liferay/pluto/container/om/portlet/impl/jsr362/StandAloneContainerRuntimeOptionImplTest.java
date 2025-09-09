/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.container.om.portlet.impl.jsr362;

import static org.junit.Assert.*;

import java.util.Arrays;

import com.liferay.pluto.container.om.portlet.ContainerRuntimeOption;
import com.liferay.pluto.container.om.portlet.impl.ContainerRuntimeOptionImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Scott Nicklous
 *
 */
public class StandAloneContainerRuntimeOptionImplTest {
   
   private static final String NAME = "name";
   private static final String VAL3 = "v3";
   private static final String[] VALS = {"v1", "v2"}; 
   private static final String[] VALS3 = {"v1", "v2", "v3"}; 
   
   private ContainerRuntimeOption cro;

   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {
      cro = new ContainerRuntimeOptionImpl(NAME, Arrays.asList(VALS));
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr362.ContainerRuntimeOptionImpl#ContainerRuntimeOptionImpl(java.lang.String, java.util.List)}.
    */
   @Test
   public void testContainerRuntimeOptionImplStringListOfString() {
      assertEquals(NAME, cro.getName());
      assertArrayEquals(VALS, cro.getValues().toArray());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr362.ContainerRuntimeOptionImpl#ContainerRuntimeOptionImpl(com.liferay.pluto.container.om.portlet.ContainerRuntimeOption)}.
    */
   @Test
   public void testContainerRuntimeOptionImplContainerRuntimeOption() {
      ContainerRuntimeOption cro2 = new ContainerRuntimeOptionImpl(cro);
      assertEquals(NAME, cro2.getName());
      assertArrayEquals(VALS, cro2.getValues().toArray());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr362.ContainerRuntimeOptionImpl#getName()}.
    */
   @Test
   public void testGetName() {
      assertEquals(NAME, cro.getName());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr362.ContainerRuntimeOptionImpl#getValues()}.
    */
   @Test
   public void testGetValues() {
      assertArrayEquals(VALS, cro.getValues().toArray());
   }

   /**
    * Test method for {@link com.liferay.pluto.container.om.portlet.impl.jsr362.ContainerRuntimeOptionImpl#addValue(java.lang.String)}.
    */
   @Test
   public void testAddValue() {
      ContainerRuntimeOption cro2 = new ContainerRuntimeOptionImpl(cro);
      cro.addValue(VAL3);
      assertArrayEquals(VALS3, cro.getValues().toArray());
      assertArrayEquals(VALS, cro2.getValues().toArray());
   }

}
