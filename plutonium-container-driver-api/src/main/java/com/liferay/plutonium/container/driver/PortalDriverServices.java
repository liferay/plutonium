/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.container.driver;

import com.liferay.plutonium.container.ContainerServices;

/**
 * Combines the ContainerServices and the additional services needed for the Plutonium Portal Driver to integrate with the Plutonium Container
 *
 * @since 2.0
 * @version $Id$
 */
public interface PortalDriverServices extends ContainerServices, PortalDriverContainerServices
{
}
