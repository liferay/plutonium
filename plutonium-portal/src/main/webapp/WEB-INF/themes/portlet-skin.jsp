<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page isELIgnored="false" %>
<%@ taglib uri="http://liferay.com/plutonium" prefix="plutonium" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- Use plutonium portlet tag to render the portlet -->
<plutonium:portlet portletId="${portlet}">

  <!-- Assemble the rendering result -->
  <div class="portlet">
    <table class="header" width="100%">
    	<tr>
    	<td class="header" align="left">
	      <!-- Portlet Title -->
	      <h2 class="title"><plutonium:title/></h2>
	</td>
        <td class="header" align="right">
	      <!-- Portlet Mode Controls -->
	      <plutonium:modeDropDown />
	
	      <!-- Window State Controls -->
	      <plutonium:windowStateAnchor windowState="minimized" icon='<%= request.getContextPath() + "/images/controls/min.png"%>' />
	      <plutonium:windowStateAnchor windowState="maximized" icon='<%= request.getContextPath() + "/images/controls/max.png"%>' />
	      <plutonium:windowStateAnchor windowState="normal" icon='<%= request.getContextPath() + "/images/controls/norm.png"%>' />
    	</td>
    	</tr>
    </table>
    <div class="body">
      <plutonium:render/>
    </div>
  </div>

</plutonium:portlet>

