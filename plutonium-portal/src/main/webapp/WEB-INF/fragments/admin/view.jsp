<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div>
  <h2>Portlet Applications</h2>
  <p>
    <ul>
      <c:forEach items="${driverConfig.portletApplications}" var="app">
        <li>
          <c:out value="${app.contextPath}"/>
          <ul>
            <c:forEach items="${app.portlets}" var="portlet">
              <c:out value="${portlet.portletName}"/>
            </c:forEach>
          </ul>
        </li>
      </c:forEach>
    </ul>
  </p>
</div>

<div>
  <h2>Portal Pages</h2>
  <p>
    <ul>
      <c:forEach items="${driverConfig.pages}" var="page">
        <li>
          <c:out value="${page.name}"/><br/>
          &nbsp;&nbsp;<small><c:out value="${page.uri}"/></small>
        </li>
      </c:forEach>
    </ul>
  </p>
</div>


