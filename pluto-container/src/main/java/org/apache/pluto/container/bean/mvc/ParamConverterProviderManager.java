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
package org.apache.pluto.container.bean.mvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class ParamConverterProviderManager {

	private static List<ParamConverterProvider> paramConverterProviders;

	@Inject
	private BeanManager _beanManager;

	public <T> ParamConverter getParamConverter(Class<?> rawType, Type baseType, Annotation[] annotations) {

		for (ParamConverterProvider paramConverterProvider : paramConverterProviders) {
			ParamConverter<T> paramConverter = paramConverterProvider.getConverter((Class<T>) rawType, baseType,
					annotations);

			if (paramConverter != null) {
				return paramConverter;
			}
		}

		return null;
	}

	@PostConstruct
	public void postConstruct() {

		paramConverterProviders = BeanUtil.getBeanInstances(_beanManager, ParamConverterProvider.class);

		Collections.sort(paramConverterProviders, new DescendingPriorityComparator());
	}
}
