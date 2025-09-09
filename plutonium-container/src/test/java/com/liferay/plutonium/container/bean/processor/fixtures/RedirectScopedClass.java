/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.bean.processor.fixtures;

import jakarta.mvc.RedirectScoped;
import java.io.Serializable;

/**
 * Verifies that an {@literal @}RedirectScoped bean is recognized.
 *
 * @author Scott Nicklous
 * @author Neil Griffin
 */
@RedirectScoped
public class RedirectScopedClass implements Serializable {

	private static final long serialVersionUID = 3676449534190350039L;
}
