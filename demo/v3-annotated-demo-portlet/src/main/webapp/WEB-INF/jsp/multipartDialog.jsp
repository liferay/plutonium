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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html><head>
<meta charset="UTF-8">
<title>Multipart Portlet</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/infobox.css">
</head>
<body>

<portlet:defineObjects />

<h3>Multipart Portlet</h3>
<p>
This portlet is used to exercise multipart form support.
It allows a file to be selected and displays its contents.
</p>

<div class='parmbox'>
<FORM ACTION='<portlet:actionURL/>' method='POST' enctype='multipart/form-data'>
   <table style='width:100%;'><tr><td align='left'>

   Select color:
   </td><td>
   <input name='color' type='color' value='${color}'>
   </td><td>
   </td></tr><tr><td>
   
   Select file:
   </td><td>
   <input name='file' type='file'>

   </td></tr><tr><td>
   <INPUT VALUE='submit' TYPE='submit'>

   </td></tr></table>
</FORM>
</div>

<hr>
<div class='infobox' style='background-color: ${color};'>
<h5>Info:</h5>
<c:forEach items="${lines}" var='line'>
   ${line}<br>
</c:forEach>
</div>

<hr>
<div class='orangebox'>
<h5>File contents:</h5>

<c:forEach items="${flist}" var='line'>
   ${line}<br>
</c:forEach>

<c:if test="${img != null}">
<p>
   <img alt="temp image" height="${h}" width="${w}" src='<portlet:resourceURL/>'>
</p>
</c:if>
</div>
