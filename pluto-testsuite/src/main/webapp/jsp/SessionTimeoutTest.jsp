<%@ page import="jakarta.portlet.WindowState" %>
<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>

<portlet:defineObjects/>

<c:choose>
  <c:when test="${results.inQuestion}">
    
    <%-- Generate portlet render URL: Start =============================== --%>
    <portlet:renderURL windowState="<%=WindowState.MAXIMIZED.toString()%>" secure='<%= renderRequest.isSecure() ? "True" : "False" %>'
                       var="url">
      <portlet:param name="maxInactiveIntervalSet" value="<%= Boolean.TRUE.toString() %>"/>
      <portlet:param name="testId" value='<%= renderRequest.getParameter("testId") %>'/>
    </portlet:renderURL>
    <%-- Generate portlet action URL: End ================================= --%>
  
  
  
    <table>
      <tr>
        <th colspan="2" style="background-color:blue;color:white;">MANUAL TEST</th>
      </tr>
      <tr>
        <th colspan="2">Session Timeout Test</th>
      </tr>
      <tr>
        <td>
          <p>
            This test is to validate that portlet session will expire and be
            invalidated by the portlet container after its max inactive interval
            is passed.
          </p>
          <p>
            This test requires manual intervention. Please wait for at least
            5 seconds and click <a href="<c:out value="${url}"/>">here</a>.
          </p>
            <p>
                NOTE: Clicking the url above will maximize this portlet.  This is required
                to ensure that no other portlets on the current page recreate the session we
                are trying to invalidate.
            </p>
        </td>
      </tr>
    </table>
  </c:when>
  <c:otherwise>
    <%@ include file="test_results.inc" %>
  </c:otherwise>
</c:choose>

<%@ include file="navigation.inc" %>


