/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.thymeleaf.mvc.portlet;

import java.io.Writer;

import jakarta.mvc.engine.ViewEngineContext;
import jakarta.mvc.engine.ViewEngineException;


/**
 * This class provides a way to obtain a {@link Writer} that is associated with an MVC {@link ViewEngineContext}.
 *
 * @author  Neil Griffin
 */
@FunctionalInterface
public interface WriterSupplier {

	public Writer get(ViewEngineContext viewEngineContext) throws ViewEngineException;
}
