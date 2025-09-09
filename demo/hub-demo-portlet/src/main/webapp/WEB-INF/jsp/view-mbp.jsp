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

<%
   StringBuffer style = new StringBuffer(256);
   style.append("border-style:solid; border-width:3px;");
   style.append(" padding:4px; overflow:auto;");
   style.append(" border-color:#000088; min-height:70px;");
   style.append(" background:#E0E0E0;");
%>

<div style='clear:both;'>
<div style='float:left;'><h3>Message Box Portlet</h3></div>
<div style='float:right;'>
<form   onsubmit='return false;'><input id='<portlet:namespace/>-clear' type='submit' name='action' value='clear' /></form>
</div>
</div><div style='clear:both;'><hr/>
<p>Messages that arrive via events from other portlets are displayed in this box.</p>
<div id='<portlet:namespace/>-responseDiv' style="<%=style.toString()%>"></div>
</div>
   
<script>
(function () {
   'use strict';
   var pid = '<portlet:namespace/>',
       resdiv = '<portlet:namespace/>-responseDiv',
       clrButton = '<portlet:namespace/>-clear',
   
       state,
       resparms,
       cacheability,
       hub,
   
   // Handler for onStateChange event
   update = function (type, s) {
      console.log("MBP: state updated. Event type = " + type);
      state = s;
      
      hub.createResourceUrl(resparms, cacheability).then(function (url) {
         var xhr = new XMLHttpRequest();
         console.log("MBP: got url: " + url);
         xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
               document.getElementById(resdiv).innerHTML=xhr.responseText;
            }
         };
         xhr.open("GET",url,true);
         xhr.send();
      });
   };
   
   // Handler for "clear" button. execute an action which clears the stored messages
   document.getElementById(clrButton).onclick = function () {
      console.log("MBP: clear button clicked. ");
      hub.action();
   };
   
   // Register portlet with Portlet Hub; add onStateChange listener 
   portlet.register(pid).then(function (pi) {
      console.log("MBP: Message Box portlet registered: " + pid);
      hub = pi;
      resparms = hub.newParameters();
      cacheability = hub.constants.PAGE;
      hub.addEventListener("portlet.onStateChange", update);
   });
   
}());
</script>
