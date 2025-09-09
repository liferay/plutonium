/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.test.jsr286.filter;

import jakarta.portlet.ActionResponse;
import jakarta.portlet.PortletRequest;

import com.liferay.pluto.testsuite.TestResult;
import com.liferay.pluto.testsuite.annotations.DefaultTestPhase;
import com.liferay.pluto.testsuite.annotations.TestPhase;

@DefaultTestPhase(PortletRequest.EVENT_PHASE)
public class EventFilterTest extends BaseFilterTest {

    public static String EVENT_PHASE_TRIGGER = "trigger-event-phase";
    
    @TestPhase(PortletRequest.ACTION_PHASE)
    public TestResult checkTriggerEventFromActionPhase(ActionResponse response) {
        TestResult result = new TestResult();
        result.setDescription(
                "Trigger the event phase from the action phase" +
		"so that the event filter can be tested.");
        result.setReturnCode(TestResult.UNDEFINED);
        response.setEvent(EVENT_PHASE_TRIGGER, getClass().getName());
        return result;
    }
    
}
