<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page isELIgnored="false" %>
<%@ taglib uri="http://liferay.com/pluto" prefix="pluto" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- Use pluto portlet tag to render the portlet -->
<pluto:portlet portletId="${portlet}">

  <!-- Assemble the rendering result -->
  <div class="portlet">
    <table class="header" width="100%">
    	<tr>
    	<td class="header" align="left">
	      <!-- Portlet Title -->
	      <h2 class="title"><pluto:title/></h2>
	</td>
        <td class="header" align="right">
	      <!-- Portlet Mode Controls -->
	      <pluto:modeDropDown />
	
	      <!-- Window State Controls -->
	      <pluto:windowStateAnchor windowState="minimized" icon='<%= request.getContextPath() + "/images/controls/min.png"%>' />
	      <pluto:windowStateAnchor windowState="maximized" icon='<%= request.getContextPath() + "/images/controls/max.png"%>' />
	      <pluto:windowStateAnchor windowState="normal" icon='<%= request.getContextPath() + "/images/controls/norm.png"%>' />
    	</td>
    	</tr>
    </table>
    <div class="body">
      <pluto:render/>
    </div>
  </div>

</pluto:portlet>

