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
<%@ page import="com.liferay.pluto.demo.v3annotated.*" %>
<%@ page import="java.util.*" %>

<portlet:defineObjects />

<div class='bluebox'>
<p id="path_info_title">${jsptitle}</p>
<%

RenderLink rl = (RenderLink) request.getAttribute("renderLink");
if (rl != null) {
   out.append(rl.toString());
}

ArrayList<String> pathInfo = (ArrayList<String>) request.getAttribute("pathInfo");
if (pathInfo != null) {
   for (String item : pathInfo) {
      out.append(item);
   }
}

PathDisplay pd = new PathDisplay(request, "JSP (ServletRequest)");
out.append(pd.toMarkup());

pd = null;
if (resourceRequest != null) {
   pd = new PathDisplay(resourceRequest, "JSP (ResourceRequest)");
} else if (renderRequest != null) {
   pd = new PathDisplay(renderRequest, "JSP (RenderRequest)");
}
if (pd != null){
   out.append(pd.toMarkup());
}
 %>
<hr>
</div>