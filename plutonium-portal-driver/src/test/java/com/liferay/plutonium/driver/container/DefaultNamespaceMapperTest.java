/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.driver.container;

import com.liferay.plutonium.container.NamespaceMapper;
import com.liferay.plutonium.container.PortletWindowID;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultNamespaceMapperTest {

    private NamespaceMapper mapper;
    private PortletWindowID id1;
    private PortletWindowID id2;

    @Before
    public void setUp() {
        mapper = new DefaultNamespaceMapper();
        id1 = new InternalPortletWindowID();
        id2 = new InternalPortletWindowID();
    }

    @Test
    public void testEncodeUniquenessWithSameName() {
        String mappedA = mapper.encode(id1, "testNumber1");
        String mappedB = mapper.encode(id2, "testNumber1");
        assertNotEquals("Encoded namespaces should be unique for different PortletWindowIDs with the same name.", mappedA, mappedB);
    }

    @Test
    public void testEncodeUniquenessWithSameObjectID() {
        String mappedA = mapper.encode(id1, "testNumber1");
        String mappedB = mapper.encode(id1, "testNumber2");
        assertNotEquals("Encoded namespaces should be unique for the same PortletWindowID with different names.", mappedA, mappedB);
    }

    @Test
    public void testDecode() {
        String original = "original";
        String mappedA = mapper.encode(id1, original);
        assertEquals("Decoded namespace should match the original name.", original, mapper.decode(id1, mappedA));
    }

    @Test
    public void testDecodeInvalidId() {
        String encoded = mapper.encode(id2, "test");
        assertNull("Decoding with a different PortletWindowID should return null.", mapper.decode(id1, encoded));
    }

    private static int objectIDCounter = 1;

    private class InternalPortletWindowID implements PortletWindowID {
        private final int id;

        public InternalPortletWindowID() {
            id = objectIDCounter++;
        }

        @Override
        public String getStringId() {
            return "uniqueId" + id;
        }
    }
}
