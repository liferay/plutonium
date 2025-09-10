<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@page import="com.liferay.plutonium.driver.core.PortalRequestContext"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="http://liferay.com/plutonium" prefix="plutonium" %>
<%@ page import="java.util.*,jakarta.portlet.*,com.liferay.plutonium.driver.url.*" %>
<%@ page import="com.liferay.plutonium.driver.config.*,com.liferay.plutonium.driver.*" %>
<%@ page import="com.liferay.plutonium.driver.util.*" %>
<%@ page import="com.liferay.plutonium.container.*,jakarta.servlet.jsp.*" %>
<% pageContext.setAttribute("now", new java.util.Date()); %>

<!--
Portal page template for default theme used by the Plutonium Portal Driver.
This template divides all portlets into two groups (div blocks): the first
group (the left column) displays portlets with odd IDs, while the second group
(the right column) displays portlets with even IDs.
-->

<html>

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Plutonium Portal</title>
    <script type="text/javascript">
       /**
        * Initialization code for portlet hub
        */
       <%
          PageState ps = new PageState(request);
       %>
       var portlet = portlet || {};
       portlet.impl = portlet.impl || {};
       portlet.impl.getInitData = function () {
          var str = '<%=ps.toEncodedJSONString()%>';
          str = str.replace(/%3C/g, "<");
          return JSON.parse(str);
       }
	   portlet.impl.getUrlBase = function () {
		   return '<%=response.encodeURL(ps.getUrlBase())%>';
	   }
	   portlet.impl.getCsrfParameterName = function () {
		   return '<%=ps.getCsrfParameterName()%>';
	   }
       portlet.impl.getCsrfParameterValue = function () {
          return '<%=ps.getCsrfParameterValue()%>';
       }
    </script>

    <c:if test="${empty dynamicResources}">
      <!-- dynamic resources not available, adding static references -->
      <script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/plutonium.js"></script>
      <script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/portletHubImpl.js"></script>
      <script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/portlet.js"></script>
      <style type="text/css" title="currentStyle" media="screen">
          @import "<c:out value="${pageContext.request.contextPath}"/>/css/plutonium.css";
          @import "<c:out value="${pageContext.request.contextPath}"/>/css/portlet-spec-1.0.css";
          @import "<c:out value="${pageContext.request.contextPath}"/>/css/portlet-spec-2.0.css";
      </style>
    </c:if>
    <c:if test="${not empty dynamicResources}">
      <!-- Adding dynamic resources -->
      ${dynamicResources}
    </c:if>
  
    <!-- Now include the head section markup provided by the portlet header processing methods -->
    ${headMarkup}
</head>

<body>

<div id="portal">

    <!-- Header block: the Liferay Plutonium banner image and description -->
    <div id="header">
        <h1>Liferay Plutonium</h1>

        <p>An Apache Portals Project</p>
    </div>

    <!-- Logout link -->
    <div id="logout">
        <a href="<c:url value='/Logout'/>">Logout</a>
    </div>

    <!-- Navigation block: links to portal pages -->
    <jsp:include page="navigation.jsp"/>

    <!-- Content block: portlets are divided into two columns/groups -->
    <div id="content">
        <plutonium:isMaximized var="isMax"/>

        <!-- Left column -->
        <c:choose>
            <c:when test="${isMax}">
                    <c:forEach var="portlet" varStatus="status"
                               items="${currentPage.portletIds}">
                        <c:set var="portlet" value="${portlet}" scope="request"/>
                        <jsp:include page="portlet-skin.jsp"/>
                    </c:forEach>
             </c:when>

            <c:otherwise>
                <div id="portlets-left-column">
                    <c:forEach var="portlet" varStatus="status"
                               items="${currentPage.portletIds}" step="2">
                        <c:set var="portlet" value="${portlet}" scope="request"/>
                        <jsp:include page="portlet-skin.jsp"/>
                    </c:forEach>
                </div>

                <!-- Right column -->
                <div id="portlets-right-column">
                    <c:forEach var="portlet" varStatus="status"
                               items="${currentPage.portletIds}" begin="1" step="2">
                        <c:set var="portlet" value="${portlet}" scope="request"/>
                        <jsp:include page="portlet-skin.jsp"/>
                    </c:forEach>
                </div>

            </c:otherwise>
        </c:choose>

    </div>

    <!-- Footer block: copyright -->
    <div id="footer">
       &copy; 2003-<fmt:formatDate value="${now}" pattern="yyyy"/> Apache Software Foundation | © 2025 Liferay, Inc.
    </div>

</div>

</body>

</html>


