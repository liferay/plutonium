<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page session="false" %>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<portlet:defineObjects />

<h5><%=request.getAttribute("title") %> for portlet: 
<c:catch var ="exp">
   <!-- try to get portlet name using named bean from portlet artifact producer. -->
   <!-- Works in the case of PortletRequestDispatcher include / forward. -->
   ${portletConfig.getPortletName()} (using named bean)
</c:catch>
<c:if test = "${exp != null}">
   <!-- Contextual context not available during async dispatch -->
   <%=portletConfig.getPortletName() %> (using JSP expression)
</c:if>
</h5>
<p>Dispatch type: <%=resourceRequest.getDispatcherType() %>
<c:catch var ="exp">
   <span style='margin-left: 2em;'>Request #: ${reqnum.getRandomNumber()}</span>
</c:catch>
</p>
<p>Query string parameter qp1: <%=resourceRequest.getResourceParameters().getValue("qp1") %>
<hr>
