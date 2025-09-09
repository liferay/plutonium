/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.plutonium.demo.v3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.liferay.plutonium.demo.v3.TagLibPortlet.ATTRIB_TXT;
import static com.liferay.plutonium.demo.v3.TagLibPortlet.PROXY;
import static com.liferay.plutonium.demo.v3.TagLibPortlet.TEST;

/**
 * Servlet used by the {@link TagLibPortlet}.
 */
@WebServlet(urlPatterns = PROXY)
public class ActEvtProxyServlet extends HttpServlet {
	private static final long serialVersionUID = -1798128019502989930L;
	private static final Logger logger = LoggerFactory.getLogger(ActEvtProxyServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
		ServletException, IOException {
		ProxyRespWrapper wrapped = new ProxyRespWrapper(resp);
		String ttype = req.getParameter(TEST);
		RequestDispatcher rd = req.getRequestDispatcher(ParamUtil.getJsp(ttype));
		rd.include(req, wrapped);
		String output = wrapped.getOutput();
		req.getSession().setAttribute(ATTRIB_TXT, output);
		logger.debug("Proxy executed. Output length: " + ((output == null) ? "null" : output.length()));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private static class ProxyRespWrapper extends HttpServletResponseWrapper {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		public ProxyRespWrapper(HttpServletResponse response) {
			super(response);
		}

		public String getOutput() {
			pw.flush();
			return sw.toString();
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			return pw;
		}

	}
}
