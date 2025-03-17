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
package org.apache.pluto.thymeleaf.portlet;

import jakarta.portlet.PortletContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.web.IWebExchange;

import java.util.Locale;
import java.util.Set;

/**
 * @author Neil Griffin
 */
public class PortletIWebContext implements IWebContext {

	private PortletContext portletContext;
	public PortletIWebContext(PortletContext portletContext) {
		this.portletContext = portletContext;
	}

	@Override
	public IWebExchange getExchange() {
		return null;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public boolean containsVariable(String name) {
		return false;
	}

	@Override
	public Set<String> getVariableNames() {
		return Set.of();
	}

	@Override
	public Object getVariable(String name) {
		return null;
	}
}
