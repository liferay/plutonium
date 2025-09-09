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

<portlet:defineObjects />

<!-- This jsp generates markup for the document head section -->

<%
headerResponse.addProperty("JspHeader", "Set in included JSP.");
 %>
   
<meta name="description" content="Portlet Hub Demo Portlet">
<style>
<!--
.markupSection {                 /* special chars & < */
   padding: 5px;
   background-color: #DDFFDD;
   border: thin solid #22EE88;
}
-->
</style>
   
<script type="text/javascript">
//<![CDATA[
(function () {
   'use strict';
   var cdiv = '<portlet:namespace/>cookieDiv',

bootstrap = function () {
   var cookies = document.cookie.split(';'), ii, markup;
   markup = '<p>Current Cookies:</p>';
   for (ii = 0; ii < cookies.length; ii++) {
      /* make sure parser eats & character */
      markup += cookies[ii] + '<br/>';
   }
   document.getElementById(cdiv).innerHTML=markup;
};
   
// execute when document has been loaded 
window.addEventListener('load', bootstrap, false);
}());
//]]>
</script>

<p>Hi</p>
