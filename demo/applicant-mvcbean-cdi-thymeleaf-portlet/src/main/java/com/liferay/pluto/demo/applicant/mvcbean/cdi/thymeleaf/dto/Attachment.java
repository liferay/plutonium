/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.thymeleaf.dto;

import java.io.File;
import java.io.Serializable;


/**
 * @author  Neil Griffin
 */
public class Attachment implements Serializable {

	private static final long serialVersionUID = 1572382923498234661L;

	private final File file;

	public Attachment(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public String getName() {
		return file.getName();
	}

	public long getSize() {
		return file.length();
	}
}
