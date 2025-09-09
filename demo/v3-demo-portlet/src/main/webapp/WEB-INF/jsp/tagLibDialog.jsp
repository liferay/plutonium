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
<%@ page import="static com.liferay.plutonium.demo.v3.TagLibPortlet.*" %>
<%@ page import="static com.liferay.plutonium.demo.v3.ParamUtil.*" %>

<portlet:defineObjects />

<h3>Tag Library Portlet</h3>
<p>
This portlet exercises new v3 JSP tag library features.</p>
<div class='parmbox'>
<FORM  ACTION='<portlet:actionURL/>' id='<portlet:namespace/>-setParams' method='POST' enctype='application/x-www-form-urlencoded'>
   <table style='width:100%;'><tr><td align='left'>

   Phase:
   </td><td>
   <input type='radio' name='<%=PHASE%>' value='<%=PHASE_REN%>' ${phase == "render" ? "checked" : "" } > <%=PHASE_REN%>
   </td><td>
   <input type='radio' name='<%=PHASE%>' value='<%=PHASE_HDR%>' ${phase == "header" ? "checked" : "" } > <%=PHASE_HDR%>
   </td><td>
   <input type='radio' name='<%=PHASE%>' value='<%=PHASE_RES%>' ${phase == "resource" ? "checked" : "" } > <%=PHASE_RES%>
   </td><td>
   <input type='radio' name='<%=PHASE%>' value='<%=PHASE_ACT%>' ${phase == "action" ? "checked" : "" } > <%=PHASE_ACT%>
   </td><td>
   <input type='radio' name='<%=PHASE%>' value='<%=PHASE_EVT%>' ${phase == "event" ? "checked" : "" } > <%=PHASE_EVT%>
   
   </tr><tr><td align='left'>
   Test type:
   </td><td colspan="2">
   <input type='radio' name='<%=TEST%>' value='<%=TEST_OBJ%>' ${test == "obj" ? "checked" : "" } > Test page objects
   </td><td colspan="2">
   <input type='radio' name='<%=TEST%>' value='<%=TEST_BEAN%>' ${test == "bean" ? "checked" : "" } > Test named beans
   
   </td></tr></table>
</FORM>
<button form='<portlet:namespace/>-setParams' VALUE='submit' TYPE='submit'>Execute</button><br><br>
</div>

