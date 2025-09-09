<%--
/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
--%>

<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<% pageContext.setAttribute("now", new java.util.Date()); %>

<html>
  
  <head>
    <title>Pluto Portal</title>
    <style type="text/css" title="currentStyle" media="screen">
      @import "<c:out value="${pageContext.request.contextPath}"/>/css/pluto.css";
    </style>
    <script type="text/javascript"
            src="<c:out value="${pageContext.request.contextPath}"/>/javascript/pluto.js">
    </script>
  </head>

  <body>
    <div id="portal" style="width: 600px;">
      <div id="header">
        <h1>Apache Pluto</h1>
        <p>A Apache Portals Project</p>
      </div>
      <div id="content">
        <div id="login">
          <c:if test='${param.error == "1"}'>
            <p style="color:red;text-align:center">
              Invalid credentials. Please try again
            </p>
          </c:if>
          <form method="POST" action="j_security_check">
            <fieldset>
              <legend>Login to Pluto</legend>
              <div>
                <label for="j_username">User Name</label>
                <input type="text" name="j_username" id="j_username"/>
              </div>
              <div>
                <label for="j_password">Password</label>
                <input type="password" name="j_password" id="j_password"/>
              </div>
              <div>
                <label for="j_login"></label>
                <input type="submit" value="Login" name="login" id="j_login"/>
              </div>
            </fieldset>
          </form>
        </div>
      </div>
      
      <div id="footer">
        &copy; 2003-<fmt:formatDate value="${now}" pattern="yyyy"/> Apache Software Foundation
      </div>
      
    </div>
  
  </body>
  
</html>


