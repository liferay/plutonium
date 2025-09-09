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
<%@ page import="java.io.*,java.util.*,jakarta.portlet.*" %>
<%@ page import="jakarta.portlet.MimeResponse.Copy" %>
<%@ page import="static jakarta.portlet.MimeResponse.Copy.*" %>
<%@ page import="static com.liferay.plutonium.demo.v3.Constants.*" %>

<portlet:defineObjects />


<h3>Status Code Test Portlet</h3>
<p>V3 portlet that displays a resource URL link that causes a status code to be set.</p>
<p>Use the form below to enter the HTTP status code.</p>
<%
   ActionURL aurl = renderResponse.createActionURL();
   String colStyle = "align='left' style='min-width: 25px; padding:0 10px 0 10px;'";
%>
<FORM METHOD='POST' ACTION='<%=aurl.toString() %>' enctype='application/x-www-form-urlencoded' accept-charset='UTF-8'>
   <table><tr><td <%=colStyle %>>
      Status code:
   </td><td <%=colStyle %>>
   <input name='<%=PARAM_STATUSCODE%>' type='text' value='' size='10' maxlength='10'>
   </td><td <%=colStyle %>>
   <INPUT ID='<portlet:namespace />-send' VALUE='send' TYPE='submit'>
   </td></tr></table>
</FORM>
<p><hr/></p>
<%
   String scStr = renderRequest.getRenderParameters().getValue(PARAM_STATUSCODE);
   int sc = -1;
   if (scStr != null && scStr.matches("\\d+")) {
      sc = Integer.parseInt(scStr);
      ResourceURL resurl = renderResponse.createResourceURL();
      out.print("<p><a href='");
      out.print(resurl.toString());
      out.println("'>Resource URL, status code = " + sc + "</a></p>");
   }
%>
<p><hr/></p>
