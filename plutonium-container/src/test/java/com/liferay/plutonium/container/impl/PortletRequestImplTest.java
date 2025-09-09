/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.impl;

import jakarta.portlet.PortalContext;
import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletSession;
import com.liferay.plutonium.container.*;
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
