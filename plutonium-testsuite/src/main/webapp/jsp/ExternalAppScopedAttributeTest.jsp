<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.portlet" prefix="portlet" %>

<portlet:defineObjects/>

<script type="text/javascript">
function openCompanionWindow() {
	w = 500;
	h = 400;
	x = (screen.width - w) / 2;
	y = (screen.height - h) / 2;
	window.open('about:blank', 'companionWindow', 'resizable=yes,scrollbars=yes,status=yes,width=' + w + ',height=' + h + ',screenX=' + x + ',screenY=' + y + ',left=' + x + ',top=' + y);
}
</script>

<c:choose>
  <c:when test="${results.inQuestion}">
    <table>
      <tr>
        <th colspan="2" style="background-color:blue;color:white;">MANUAL TEST</th>
      </tr>
      <tr>
        <th colspan="2">Application Scoped Session Attributes Test</th>
      </tr>
      <tr>
        <td>
          <p>
            This test is to validate that application scoped attributes can be
            viewed by resources outside of the portlet. Additionally, it tests
            to make sure that session attributes set by other resources may be
            viewed by the portlet as an application scoped session attribute.
          </p>
          <p>
            This test requires manual intervention. Click
            <a href="<%= renderResponse.encodeURL(renderRequest.getContextPath() + "/test/ExternalAppScopedAttributeTest_Servlet?sessionId=" + request.getSession().getId()) %>"
               target="companionWindow" onclick="openCompanionWindow()">
              here
            </a>
          </p>
        </td>
      </tr>
    </table>
  </c:when>
  <c:otherwise>
    <%@ include file="test_results.inc" %>
  </c:otherwise>
</c:choose>

<%@ include file="navigation.inc" %>