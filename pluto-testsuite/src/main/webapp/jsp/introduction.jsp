<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page isELIgnored="false" %>
<%@ page import="java.util.Map" %>
<%@ page import="jakarta.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ page import="com.liferay.pluto.testsuite.TestConfig" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>

<portlet:defineObjects/>

<p>
  This portlet is a portlet specification compatibility test portlet.
  It provides several tests of verying complexities which will assist in
  evaluating compliance with the portlet specification. It was originally
  developed for testing Apache Pluto, however, it does not utilize any
  proprietary APIs and should work with all compliant portlet containers.
</p>

<p>
  Please select one of the following tests:
  <table>
    <c:forEach var="testConfig" items="${testConfigs}" varStatus="status">
      <tr>
        <td>
          # <c:out value="${status.index}"/>.
        </td>
        <td>
          <c:out value="${testConfig.name}"/>
        </td>
        
        <%-- Generate portlet action URL: Start =========================== --%>
        <portlet:actionURL secure='<%= renderRequest.isSecure() ? "True" : "False" %>' escapeXml="true"
                           var="url">
          <portlet:param name="testId"
                         value='<%= ((LoopTagStatus) pageContext.getAttribute("status")).getIndex() + "" %>'/>
          <c:forEach var="param" items="${testConfig.actionParameters}">
            <%
                TestConfig.Parameter parameter = (TestConfig.Parameter)
                        pageContext.findAttribute("param");
                String paramName = parameter.getName();
                String paramValue = parameter.getValue();
            %>
            <portlet:param name="<%= paramName %>"
                           value="<%= paramValue %>"/>
          </c:forEach>
        </portlet:actionURL>
        <%-- Generate portlet action URL: End ============================= --%>
        
        <td>
          <a href="<c:out value="${url}" escapeXml="false"/>">Test</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</p>



