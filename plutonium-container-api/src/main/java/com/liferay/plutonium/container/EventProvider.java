/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container;

import java.io.Serializable;

import jakarta.portlet.Event;
import javax.xml.namespace.QName;

/**
 *
 * @author <a href="mailto:ferdy@informatik.uni-jena.de">Fred Thiele</a>
 * @author <a href="mailto:chrisra@cs.uni-jena.de">Christian Raschka</a>
 *
 */
public interface EventProvider {

    Event createEvent(QName name, Serializable value)
    throws IllegalArgumentException;
}
