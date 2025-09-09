<%--
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
--%>

<%@ page isELIgnored="false" %>
<%@ page import="java.util.Map"%>
<%@ page import="com.liferay.pluto.testsuite.TestConfig"%>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>

<portlet:defineObjects/>

<% if (request instanceof jakarta.portlet.RenderRequest) {%>
<center>
  <table>
    <tr>
      <td align="center" nowrap="true">
        
        <%-- Run Previous Test ============================================ --%>
        <portlet:actionURL secure='<%= request.isSecure() ? "True" : "False" %>' var="url">
          <portlet:param name="nextTestId" value='<%= request.getParameter("testId") %>'/>
          <c:forEach var="param" items="${prevTest.actionParameters}">
            <%
                TestConfig.Parameter parameter = (TestConfig.Parameter) pageContext.findAttribute("param");
                String paramName = parameter.getName();
                String paramValue = parameter.getValue();
            %>
            <portlet:param name="<%= paramName %>" value="<%= paramValue %>"/>
          </c:forEach>
        </portlet:actionURL>
        <a href="<%= url %>">
          <img src='<%= response.encodeURL(request.getContextPath() + "/images/previous.png") %>' border="0">
        </a>
        
        <%-- Return to Introduction ======================================= --%>
        <portlet:actionURL secure='<%= request.isSecure() ? "True" : "False" %>' var="url">
          <portlet:param name="testId" value="<%= null %>"/>
        </portlet:actionURL>
        <a href="<%= url %>">
          <img src='<%= response.encodeURL(request.getContextPath() + "/images/return_index.png") %>' border="0">
        </a>
        
        <%-- Run Next Test ================================================ --%>
        <portlet:actionURL secure='<%= request.isSecure() ? "True" : "False" %>' var="url">
          <portlet:param name="previousTestId" value='<%= request.getParameter("testId") %>'/>
          <c:forEach var="param" items="${nextTest.actionParameters}">
            <%
                TestConfig.Parameter parameter = (TestConfig.Parameter) pageContext.findAttribute("param");
                String paramName = parameter.getName();
                String paramValue = parameter.getValue();
            %>
            <portlet:param name="<%= paramName %>" value="<%= paramValue %>"/>
          </c:forEach>
        </portlet:actionURL>
        <a href="<%= url %>">
          <img src='<%= response.encodeURL(request.getContextPath() + "/images/next.png") %>' border="0">
        </a>
        
      </td>
    </tr>
  </table>
</center>

<% } %>

