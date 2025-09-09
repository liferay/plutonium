/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test.jsr286;

import java.io.IOException;

import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.GenericPortlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.ProcessEvent;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import com.liferay.pluto.testsuite.test.jsr286.event.ComplexPayloadEventTest;
import com.liferay.pluto.testsuite.test.jsr286.event.ComposedObject;
import com.liferay.pluto.testsuite.test.jsr286.event.SimplePayloadEventTest;

public class TestCompanionPortlet extends GenericPortlet {

    public static final String EVENT_INFO_PARAM = "eventInfoParameter";
    
    @Override
    protected void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        response.setContentType("text/html");
        
        String eventInfo = request.getParameter(EVENT_INFO_PARAM); 
        request.setAttribute("eventInfo", eventInfo);
        
        getPortletContext()
            .getRequestDispatcher("/jsp/TestCompanionPortlet.jsp")
                .include(request, response);
    }

    @ProcessEvent(name = SimplePayloadEventTest.SIMPLE_PAYLOAD_EVENT)
    public void processSimplePayload(EventRequest request, 
            EventResponse response) throws PortletException, IOException {
        
        StringBuilder feedback = new StringBuilder();
        feedback.append("Simple Payload Event received.");

        if (!SimplePayloadEventTest.SIMPLE_VALUE_PAYLOAD.equals(
                request.getEvent().getValue())) {
            feedback.append("  Payload DID NOT match expected value.");
        } else {
            feedback.append("  Payload matched expected value.");
        }
        
        response.setRenderParameter(EVENT_INFO_PARAM, feedback.toString());
    }
    
    @ProcessEvent(name = ComplexPayloadEventTest.COMPLEX_PAYLOAD_EVENT)
    public void processComplexPayload(EventRequest request, 
            EventResponse response) throws PortletException, IOException {
        
        StringBuilder feedback = new StringBuilder();
        feedback.append("Complex Payload Event received.");

        boolean failed = false;
        ComposedObject value = (ComposedObject) request.getEvent().getValue();
        if (!ComplexPayloadEventTest.TEST_COLOR.equals(value.getColor())) {
            feedback.append("  Color DID NOT match expected value.");
            failed = true;
        }
        if (!ComplexPayloadEventTest.TEST_POINT.equals(value.getPoint())) {
            feedback.append("  Point DID NOT match expected value.");
            failed = true;
        }
        if (!failed) {
            feedback.append("  Payload matched expected values.");
        }
        
        response.setRenderParameter(EVENT_INFO_PARAM, feedback.toString());
    }
        
}