<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://liferay.com/pluto" prefix="pluto" %>

<html>
<head>
    <title>Pluto Portal</title>
    <style type="text/css" title="currentStyle" media="screen">
        @import "<c:out value="${pageContext.request.contextPath}"/>/css/pluto.css";
        @import "<c:out value="${pageContext.request.contextPath}"/>/css/portlet-spec-1.0.css";
    </style>
    <script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/javascript/pluto.js">
    </script>
</head>

<body>

<div id="portal">

    <!-- Header block: the Apache Pluto banner image and description -->
    <div id="header">
        <h1>Apache Pluto</h1>

        <p>An Apache Portals Project</p>
    </div>

    <!-- Logout link -->
    <c:if test="${pageContext.request.remoteUser != null}">
        <div id="logout">
            <a href="<c:url value='/Logout'/>">Logout</a>
        </div>
    </c:if>

    <!-- Navigation block: links to portal pages -->
    <jsp:include page="/WEB-INF/themes/navigation.jsp"/>

    <!-- Content block: portlets are divided into two columns/groups -->
    <div id="content">
        <pluto:isMaximized var="isMax"/>

        <!-- Left column -->
        <c:choose>
            <c:when test="${isMax}">
                <c:set var="portlet" value="/pluto.AboutPortlet!A" scope="request"/>
                <jsp:include page="/WEB-INF/themes/portlet-skin.jsp"/>
                <c:set var="portlet" value="/pluto.AboutPortlet!B" scope="request"/>
                <jsp:include page="/WEB-INF/themes/portlet-skin.jsp"/>
            </c:when>

            <c:otherwise>
                <div id="portlets-left-column">
                    <c:set var="portlet" value="/pluto.AboutPortlet" scope="request"/>
                    <jsp:include page="/WEB-INF/themes/portlet-skin.jsp"/>
                </div>

                <!-- Right column -->
                <div id="portlets-right-column">
                    <c:set var="portlet" value="/testsuite.TestPortlet1" scope="request"/>
                    <jsp:include page="/WEB-INF/themes/portlet-skin.jsp"/>
                </div>
            </c:otherwise>
        </c:choose>

    </div>

    <!-- Footer block: copyright -->
    <div id="footer">
        &copy; 2003-2005 Apache Software Foundation
    </div>

</div>

</body>

</html>


