/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.testsuite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A PortletTest implementation can be marked to have all of its test methods
 * run in a certain portlet lifecycle phase by default.  Individual methods
 * annotated with TestPhase will override this setting.  The argument value 
 * should be of the _PHASE constants on jakarta.portlet.PortletRequest, i.e.,
 * RENDER_PHASE, ACTION_PHASE, RESOURCE_PHASE, EVENT_PHASE
 * 
 * @author Benjamin C. Gould
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultTestPhase {

    public String value();
    
}
