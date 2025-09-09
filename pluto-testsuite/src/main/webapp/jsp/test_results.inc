<%--
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
--%>

<%@ page isELIgnored="false" %>
<%@ page import="com.liferay.pluto.testsuite.TestResult" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>

<%
    pageContext.setAttribute("PASSED_CODE", new Integer(TestResult.PASSED));
    pageContext.setAttribute("FAILED_CODE", new Integer(TestResult.FAILED));
    pageContext.setAttribute("WARNING_CODE", new Integer(TestResult.WARNING));
%>

<portlet:defineObjects/>

<table>
  <c:choose>
    <c:when test="${results == null}">
      <tr>
        <th colspan="2" style="background-color:red; color:white;">
          TEST RESULT NOT FOUND
        </th>
      </tr>
      <tr>
        <td valign="top">
          <img src="<%= response.encodeURL(request.getContextPath() + "/images/question.gif") %>"
               border="0" />
        </td>
        <td>
          <p>
            The test results could not be found in the request scope. If this
            is an ActionTest (test logic resides in the processAction() method),
            one possible reason may be that the original portlet session where
            the test results reside was invalidated by another portlet.
          </p>
          <p>
            Please return to the introduction page and retry.
          </p>
        </td>
      </tr>
    </c:when>
    <c:when test="${results.failed}">
      <tr>
        <th colspan="2" style="background-color:red; color:white;">
          TEST FAILED
        </th>
      </tr>
    </c:when>
    <c:when test="${results.inQuestion}">
      <tr>
        <th colspan="2" style="background-color:yellow;">
          WARNING
        </th>
      </tr>
    </c:when>
    <c:otherwise>
      <tr>
        <th colspan="2" style="background-color:green; color:white;">
          TEST PASSED
        </th>
      </tr>
    </c:otherwise>
  </c:choose>
  
  <tr>
    <th />
    <th><c:out value="${results.name}"/></th>
  </tr>
  
  <c:forEach var="result" varStatus="status" items="${results.collection}">
    <tr>
      
      <!-- Result Icon ===================================================== -->
      <td valign="top">
        <c:choose>
          <c:when test="${result.returnCode == PASSED_CODE}">
            <img src="<%= response.encodeURL(request.getContextPath() + "/images/yes.gif") %>"
                 border="0" />
          </c:when>
          <c:when test="${result.returnCode == FAILED_CODE}">
            <img src="<%= response.encodeURL(request.getContextPath() + "/images/no.gif") %>"
                 border="0" />
          </c:when>
          <c:otherwise>
            <img src="<%= response.encodeURL(request.getContextPath() + "/images/question.gif") %>"
                 border="0" />
          </c:otherwise>
        </c:choose>
      </td>
      
      <td style="white-space:nowrap;">
        PLT <c:out value="${result.specPLT}"/> - <c:out value="${result.name}"/>
      </td>
    </tr>
    
    <c:choose>
      <c:when test="${result.returnCode == PASSED_CODE}">
        <tr>
          <td />
          <td style="font-size:smaller;">
            <c:out value="${result.description}"/>
          </td>
        </tr>
      </c:when>
      <c:otherwise>
        <tr>
          <td />
          <td style="font-size:smaller;">
            <c:out value="${result.description}"/>
            <br/>
            <span style="color:#FF0000;">
              <c:out value="${result.resultMessage}"/>
            </span>
          </td>
        </tr>
      </c:otherwise>
    </c:choose>
  </c:forEach>
  
</table>

