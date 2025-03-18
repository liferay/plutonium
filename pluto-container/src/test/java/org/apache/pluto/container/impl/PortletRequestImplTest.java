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
package org.apache.pluto.container.impl;

import jakarta.portlet.PortalContext;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletSession;
import org.apache.pluto.container.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class PortletRequestImplTest {

    private Mockery context;
    private ContainerServices mockContainerServices;
    private PortletRequestContext mockPortletRequestContext;
    private PortletResponseContext mockPortletResponseContext;
    private PortletSession mockPortletSession;
    private PortletContainer mockPortletContainer;
    private HttpServletRequest mockHttpServletRequest;
    private HttpSession mockHttpSession;
    private PortletRequestImpl portletRequest;
    private final String lifecyclePhase = PortletRequest.RENDER_PHASE;

    @Before
    public void setUp() {
        context = new Mockery();

        // Mock dependencies
        mockPortletRequestContext = context.mock(PortletRequestContext.class);
        mockPortletResponseContext = context.mock(PortletResponseContext.class);
        mockPortletContainer = context.mock(PortletContainer.class);
        mockContainerServices = context.mock(ContainerServices.class);
        mockHttpServletRequest = context.mock(HttpServletRequest.class);
        mockHttpSession = context.mock(HttpSession.class);
        mockPortletSession = context.mock(PortletSession.class);

        // Set up expectations
        context.checking(new Expectations() {{
            allowing(mockPortletRequestContext).getContainer();
            will(returnValue(mockPortletContainer));

            allowing(mockPortletContainer).getContainerServices();
            will(returnValue(mockContainerServices));

            allowing(mockContainerServices).getPortalContext();
            will(returnValue(context.mock(PortalContext.class)));

            allowing(mockHttpServletRequest).getSession();
            will(returnValue(mockHttpSession));

            allowing(mockHttpSession).getMaxInactiveInterval();
            will(returnValue(5)); // 5 seconds

            allowing(mockHttpSession).getLastAccessedTime();
            will(returnValue(0L)); // Uninitialized lastAccessedTime

            allowing(mockPortletRequestContext).getPortletSession(true);
            will(returnValue(mockPortletSession));

            oneOf(mockPortletSession).invalidate(); // Fix: Add expectation for PortletSession.invalidate()
        }});

        // Instantiate an anonymous subclass of PortletRequestImpl
        portletRequest = new PortletRequestImpl(
            mockPortletRequestContext,
            mockPortletResponseContext,
            PortletRequest.RENDER_PHASE
        ) {};
    }

    @Test
    public void testInvalidateSessionWithUninitializedLastAccessTime() {
        // Invoke the method under test
        portletRequest.getPortletSession().invalidate();

        // Verify expectations
        context.assertIsSatisfied();
    }
}
