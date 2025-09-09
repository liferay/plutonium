/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.pluto.driver.container;

import com.liferay.pluto.container.NamespaceMapper;
import com.liferay.pluto.container.PortletWindowID;
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
