<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<!-- Plutonium about portlet fragment (displayed in VIEW mode). -->
<table>
  
  <tr>
    <td colspan="2"><h2>About Plutonium Portal Driver</h2></td>
  </tr>
  
  <tr>
    <td>Portal Name:</td>
    <td><c:out value="${driverConfig.portalName}"/></td>
  </tr>
  <tr>
    <td>Portal Version:</td>
    <td><c:out value="${driverConfig.portalVersion}"/></td>
  </tr>
  <tr>
    <td>Servlet Container:</td>
    <td><%= config.getServletContext().getServerInfo() %></td>
  </tr>
  <tr>
    <td>Java Version:</td>
    <td><%= System.getProperty("java.version") %>  (<%= System.getProperty("java.vm.vendor") %> - <%= System.getProperty("java.vm.name") %> build <%= System.getProperty("java.vm.version") %>)</td>
  </tr>
  <tr>
    <td>Operating System:</td>
    <td><%= System.getProperty("os.name") %>  (<%= System.getProperty("os.arch") %> version <%= System.getProperty("os.version") %>)</td>
  </tr>
  <tr>
    <td>Plutonium Website:</td>
    <td>
      <a href="http://liferay.com/plutonium/" target="_blank">
        http://liferay.com/plutonium/
      </a>
    </td>
  </tr>
  
  <tr>
    <td colspan="2">
      <i>Please use the <a href="http://issues.apache.org/jira/secure/BrowseProject.jspa?id=10560" target="_blank">
      Jira issue tracking site</a> to record any problems you are having with
      the Plutonium portal driver. When you report an issue, please include the data (version, os, etc.) collected in
      this portlet in addition to any relevant stack traces or log records and detailed steps on what you were doing when 
      the problem arose.</i>
    </td>
  </tr>
  
</table>

