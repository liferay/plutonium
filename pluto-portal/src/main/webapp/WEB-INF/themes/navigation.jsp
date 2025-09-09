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

<div id="nav">
    <ul id="navigation" >
        <c:forEach var="page" items="${driverConfig.pages}">
            <c:choose>
                <c:when test="${page == currentPage}">
                    <li class="selected">
                        <a href='<c:out value="${pageContext.request.contextPath}"/>/portal/<c:out value="${page.name}"/>'><c:out value="${page.name}"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href='<c:out value="${pageContext.request.contextPath}"/>/portal/<c:out value="${page.name}"/>'><c:out value="${page.name}"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>
