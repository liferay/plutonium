<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page import="static com.liferay.pluto.demo.v3.Constants.*" %>
<%@ page session="false" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<portlet:defineObjects />

<%
   StringBuffer style = new StringBuffer(256);
   style.append("border-style:solid; border-width:3px;");
   style.append(" padding:4px; overflow:auto;");
   style.append(" border-color:#008800; min-height:30px;");
   style.append(" background:#E0E0E0;");
%>

<h3><%=portletConfig.getPortletName()%></h3>
<hr/>
<p>This portlet displays information from the new V3.0 PortletConfig APIs
getPortletModes(String), getWindowStates(String), and getPublicRenderParameterDefinitions()</p>
<h5>Current Portlet Mode: <%=renderRequest.getPortletMode().toString() %></h5>
<h5>Portlet Modes:</h5>
<ul>
<c:forEach items="<%=renderRequest.getAttribute(ATTRIB_PMS) %>" var="pm">
   <li><c:out value="${pm}"></c:out></li>
</c:forEach>
</ul>
<h5>Window States:</h5>
<ul>
<c:forEach items="<%=renderRequest.getAttribute(ATTRIB_WS) %>" var="ws">
   <li><c:out value="${ws}"></c:out></li>
</c:forEach>
</ul>
<h5>Public Render Parameters:</h5>
<ul>
<c:forEach items="<%=renderRequest.getAttribute(ATTRIB_PRPS) %>" var="prp">
   <li><c:out value="${prp}"></c:out></li>
</c:forEach>
</ul>
<h5>Portlet Context Info:</h5>
<ul>
<c:forEach items="<%=renderRequest.getAttribute(ATTRIB_CTX) %>" var="ctx">
   <li><c:out value="${ctx}"></c:out></li>
</c:forEach>
</ul>
