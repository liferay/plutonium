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

<div class="portlet-section-body">
<p>
  This portlet is a companion portlet to the 286 Test Portlet.
  It is designed to test interportlet coordination features of version 2.0 of the
  Java Portlet API (JSR-286) which include public render parameters and eventing.  
</p>

<c:if test="${not empty param['public-render-param1']}">
	<p>
		<h4>Public Render Parameter Test Results:</h4>
		<p>
		checkPublicRenderParameter test: <b><c:out value="${param['public-render-param1']}"/></b>	
		</p>
	</p>
</c:if>

<c:if test="${not empty eventInfo}">
	<p>
		<h4>Information from last event:</h4>
		<p><c:out value="${eventInfo}" /></p>
	</p>
</c:if>
</div>