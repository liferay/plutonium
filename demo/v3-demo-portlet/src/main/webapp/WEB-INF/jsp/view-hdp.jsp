<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page session="false" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="static com.liferay.pluto.demo.v3.Constants.*" %>

<portlet:defineObjects />

<div class='headerPortlet'>
<h3>V3 Header Portlet</h3><hr/>
<p>
Portlet to demo the V3 header request functionality. The portlet uses a CSS class that sets a border and
background color. The corresponding style sheet is set through the PortletResponse#addProperty(String, Element)
method. 
</p>
<p>
Character encoding: <%=renderResponse.getCharacterEncoding() %>, 
Content type: <%=renderResponse.getContentType() %>
</p>
<p>
The following section shows cookie information inserted by JavaScript code that was added to the 
output stream during the header phase processing.
</p>
<div  class='markupSection' id='<portlet:namespace/>cookieDiv'></div>
<h5>Portlet Response Properties (read from HeaderResponse):</h5>
<ul>
<c:forEach items="<%=portletSession.getAttribute(ATTRIB_PROPS) %>" var="prop">
   <li><c:out value="${prop}"></c:out></li>
</c:forEach>
</ul>
</div>
