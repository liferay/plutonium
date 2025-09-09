<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@page import="jakarta.portlet.RenderParameters"%>
<%@ page session="false" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="static com.liferay.pluto.demo.v3.TagLibPortlet.*" %>
<%@ page import="static jakarta.portlet.PortletRequest.*" %>

<portlet:defineObjects />

<div class='infobox'>
   <h4>Objects for phase: <%=portletRequest.getAttribute(LIFECYCLE_PHASE)%></h4>
   
   <table style='width:100%;'>
   <colgroup>
      <col style="width:10%">
      <col style="width:45%">
      <col style="width:45%">
   </colgroup>   
   <tr>
   
   <%int ii=1; %>
   <th align='left'>
   #
   </th><th align='left'>
   Page Object
   </th><th align='left'>
   Is present
   </th>
   </tr><tr>

   <!-- the objects -->
   
   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   renderRequest
   </td><td align='left'>
   <%out.print(renderRequest != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   resourceRequest
   </td><td align='left'>
   <%out.print(resourceRequest != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   actionRequest
   </td><td align='left'>
   <%out.print(actionRequest != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   eventRequest
   </td><td align='left'>
   <%out.print(eventRequest != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   renderResponse
   </td><td align='left'>
   <%out.print(renderResponse != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   resourceResponse
   </td><td align='left'>
   <%out.print(resourceResponse != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   actionResponse
   </td><td align='left'>
   <%out.print(actionResponse != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   eventResponse
   </td><td align='left'>
   <%out.print(eventResponse != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletConfig
   </td><td align='left'>
   <%out.print(portletConfig != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletSession
   </td><td align='left'>
   <%out.print(portletSession != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletSessionScope
   </td><td align='left'>
   <%out.print(portletSessionScope != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletPreferences
   </td><td align='left'>
   <%out.print(portletPreferences != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletPreferencesValues
   </td><td align='left'>
   <%out.print(portletPreferencesValues != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletRequest
   </td><td align='left'>
   <%out.print(portletRequest != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletResponse
   </td><td align='left'>
   <%out.print(portletResponse != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   headerRequest
   </td><td align='left'>
   <%out.print(headerRequest != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   headerResponse
   </td><td align='left'>
   <%out.print(headerResponse != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   clientDataRequest
   </td><td align='left'>
   <%out.print(clientDataRequest != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   mimeResponse
   </td><td align='left'>
   <%out.print(mimeResponse != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   stateAwareResponse
   </td><td align='left'>
   <%out.print(stateAwareResponse != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   renderParams
   </td><td align='left'>
   <%out.print(renderParams != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   mutableRenderParams
   </td><td align='left'>
   <%out.print(mutableRenderParams != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   resourceParams
   </td><td align='left'>
   <%out.print(resourceParams != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   actionParams
   </td><td align='left'>
   <%out.print(actionParams != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletContext
   </td><td align='left'>
   <%out.print(portletContext != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletMode
   </td><td align='left'>
   <%out.print(portletMode != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   windowState
   </td><td align='left'>
   <%out.print(windowState != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   cookies
   </td><td align='left'>
   <%out.print(cookies != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   locale
   </td><td align='left'>
   <%out.print(locale != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   locales
   </td><td align='left'>
   <%out.print(locales != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   namespace
   </td><td align='left'>
   <%out.print(namespace != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   contextPath
   </td><td align='left'>
   <%out.print(contextPath != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   windowId
   </td><td align='left'>
   <%out.print(windowId != null); %>
   </td>
   </tr><tr>

   <td align='left'>
   <%=ii++ %>
   </td><td align='left'>
   portletName
   </td><td align='left'>
   <%out.print(portletName != null); %>
   </td>
   </tr><tr>

   <!-- the objects -->
   
   </tr></table>
</div>

