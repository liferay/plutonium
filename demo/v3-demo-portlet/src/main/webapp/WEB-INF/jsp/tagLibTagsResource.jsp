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
<%@ page import="java.util.*" %>
<%@ page import="static com.liferay.plutonium.demo.v3.TagLibPortlet.*" %>

<portlet:defineObjects />

<h4>Resource method:</h4>
<h5>Render Parameters:</h5>

<table style='width:100%;'>
<colgroup>
   <col style="width:50%">
   <col style="width:50%">
</colgroup>   

<c:forEach items="${renderParams.getNames()}" var="key">
<tr>
<td>${key}</td>
<td>${Arrays.toString(renderParams.getValues(key))}</td>
</tr>
</c:forEach>
</table>


<h5>Resource Parameters:</h5>

<table style='width:100%;'>
<colgroup>
   <col style="width:50%">
   <col style="width:50%">
</colgroup>   

<c:forEach items="${resourceParams.getNames()}" var="key">
<tr>
<td>${key}</td>
<td>${Arrays.toString(resourceParams.getValues(key))}</td>
</tr>
</c:forEach>
</table>
